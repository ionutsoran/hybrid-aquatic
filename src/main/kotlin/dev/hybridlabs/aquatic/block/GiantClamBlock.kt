package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.enum.GiantClamState
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.ShapeContext
import net.minecraft.block.Waterloggable
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.item.ItemStack
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.hit.BlockHitResult
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView
import net.minecraft.world.World

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class GiantClamBlock(
    private val emitsParticles: Boolean,
    private val biteDamage: Int,
    settings: Settings
) : Block(settings), Waterloggable {

    private var pearlTimer: Int = 12000

    init {
        defaultState = stateManager.defaultState
            .with(STATE, GiantClamState.CLOSED)
            .with(WATERLOGGED, true)
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
    }

    override fun scheduledTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random?) {
        val waterlogged = state.get(WATERLOGGED)
        val currentState = state.get(STATE)

        if (currentState == GiantClamState.DEAD) return

        if (pearlTimer > 0) {
            pearlTimer--

            val newState = when {
                !waterlogged -> GiantClamState.DEAD
                pearlTimer > 0 -> GiantClamState.CLOSED
                else -> GiantClamState.OPEN
            }

            if (newState != currentState) {
                world.setBlockState(pos, state.with(STATE, newState), 2)
            }

            if (pearlTimer > 0) {
                world.scheduleBlockTick(pos, this, 20)
            }
        }
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape = COLLISION_SHAPE

    override fun getOutlineShape(state: BlockState, world: BlockView, pos: BlockPos, context: ShapeContext): VoxelShape = SHAPE

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val waterlogged = ctx.world.getFluidState(ctx.blockPos).fluid == Fluids.WATER
        return defaultState.with(WATERLOGGED, waterlogged)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) Fluids.WATER.getStill(false) else super.getFluidState(state)
    }

    override fun appendProperties(builder: StateManager.Builder<Block, BlockState>) {
        builder.add(STATE, WATERLOGGED)
    }

    override fun onUse(
        state: BlockState,
        world: World,
        pos: BlockPos,
        player: PlayerEntity,
        hand: Hand,
        hit: BlockHitResult
    ): ActionResult {
        if (!world.isClient) {
            val currentState = state.get(STATE)
            when (currentState) {
                GiantClamState.OPEN -> {
                    pearlTimer = 12000
                    world.setBlockState(pos, state.with(STATE, GiantClamState.CLOSED), 3)

                    val itemToDrop = if (world.random.nextFloat() < 0.25) {
                        ItemStack(HybridAquaticItems.BLACK_PEARL)
                    } else {
                        ItemStack(HybridAquaticItems.PEARL)
                    }
                    dropStack(world, pos, itemToDrop)
                }
                GiantClamState.CLOSED -> {
                    pearlTimer = 0
                    world.setBlockState(pos, state.with(STATE, GiantClamState.OPEN), 3)
                }
                else -> return ActionResult.PASS
            }
        }
        return ActionResult.SUCCESS
    }

    override fun onSteppedOn(world: World, pos: BlockPos?, state: BlockState?, entity: Entity) {
        if (world.isClient || pos == null || state == null) return

        val currentState = state.get(STATE)
        if (currentState == GiantClamState.OPEN) {
            world.setBlockState(pos, state.with(STATE, GiantClamState.CLOSED), 3)
            pearlTimer = 12000

            val boundingBox = Box(pos.x - 0.5, pos.y.toDouble(), pos.z - 0.5, pos.x + 1.5, pos.y + 3.0, pos.z + 1.5)
            val entities = world.getEntitiesByClass(LivingEntity::class.java, boundingBox) { true }

            for (nearbyEntity in entities) {
                if (!nearbyEntity.bypassesSteppingEffects()) {
                    nearbyEntity.damage(world.damageSources.genericKill(), biteDamage.toFloat())
                }
            }
        }

        super.onSteppedOn(world, pos, state, entity)
    }


    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: Random) {
        if (state.get(STATE) == GiantClamState.OPEN && emitsParticles && random.nextInt(5) == 0) {
            for (i in 0..random.nextInt(1)) {
                world.addParticle(
                    ParticleTypes.BUBBLE_COLUMN_UP,
                    pos.x + 0.5, pos.y + 0.5, pos.z + 0.5,
                    random.nextFloat() / 2.0, 0.0, random.nextFloat() / 2.0
                )
            }
        }
    }

    companion object {
        val STATE: EnumProperty<GiantClamState> = EnumProperty.of(
            "state",
            GiantClamState::class.java,
            GiantClamState.OPEN,
            GiantClamState.CLOSED,
            GiantClamState.DEAD
        )
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED
        private val SHAPE: VoxelShape = createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
        private val COLLISION_SHAPE: VoxelShape = createCuboidShape(2.0, 0.0, 2.0, 14.0, 8.0, 14.0)
    }
}