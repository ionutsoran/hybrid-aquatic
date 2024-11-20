package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import net.minecraft.block.*
import net.minecraft.block.enums.Thickness
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.pathing.NavigationType
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.state.property.BooleanProperty
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Box
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

@Suppress("DEPRECATION", "SameParameterValue", "OVERRIDE_DEPRECATION")
class HydrothermalVentBlock(
    private val emitsParticles: Boolean,
    private val fireDamage: Int,
    settings: Settings?
) : Block(settings), Waterloggable {

    init {
        defaultState = stateManager.defaultState
            .with(THICKNESS, Thickness.TIP)
            .with(WATERLOGGED, true)
    }

    override fun canPathfindThrough(state: BlockState, world: BlockView, pos: BlockPos, type: NavigationType): Boolean {
        return false
    }

    override fun canPlaceAt(state: BlockState, world: WorldView, pos: BlockPos): Boolean {
        val supportingPos = pos.down()
        val supportingState = world.getBlockState(supportingPos)
        return supportingState.isOf(this) || supportingState.isSideSolidFullSquare(world, supportingPos, Direction.UP)
    }

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val world = ctx.world
        val pos = ctx.blockPos
        return defaultState
            .with(THICKNESS, getThickness(world, pos))
            .with(WATERLOGGED, world.getFluidState(pos).fluid == Fluids.WATER)
    }

    override fun getStateForNeighborUpdate(
        state: BlockState,
        direction: Direction,
        neighborState: BlockState,
        world: WorldAccess,
        pos: BlockPos,
        neighborPos: BlockPos
    ): BlockState {
        if (state.get(Properties.WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world))
        }

        if (!canPlaceAt(state, world, pos)) {
            return Blocks.AIR.defaultState
        }

        return if (direction != Direction.DOWN && direction != Direction.UP) {
            state
        } else {
            state.with(THICKNESS, getThickness(world, pos))
        }
    }

    override fun randomDisplayTick(state: BlockState, world: World, pos: BlockPos, random: Random) {
        if (state.get(THICKNESS) == Thickness.TIP) {
            spawnSmokeParticle(world, pos, random)
        }
        if (random.nextInt(10) == 0) {
            world.playSound(
                pos.x.toDouble() + 0.5,
                pos.y.toDouble() + 0.5,
                pos.z.toDouble() + 0.5,
                SoundEvents.BLOCK_LAVA_POP,
                SoundCategory.BLOCKS,
                0.5f + random.nextFloat(),
                random.nextFloat() * 0.7f + 0.6f,
                false
            )
        }

        if (state.get(THICKNESS) == Thickness.TIP && this.emitsParticles && random.nextInt(5) == 0) {
            for (i in 0 until random.nextInt(1) + 1) {
                world.addParticle(
                    ParticleTypes.LAVA, pos.x.toDouble() + 0.5, pos.y.toDouble() + 0.5, pos.z.toDouble() + 0.5,
                    (random.nextFloat() / 2.0f).toDouble(), 5.0E-5,
                    (random.nextFloat() / 2.0f).toDouble()
                )
            }
        }
    }

    private fun getThickness(world: WorldView, currentPos: BlockPos): Thickness {
        val blockAbove = world.getBlockState(currentPos.offset(Direction.UP))

        return if (blockAbove.isOf(this)) {
            val blockBelow = world.getBlockState(currentPos.offset(Direction.DOWN))
            if (blockBelow.isOf(this)) {
                Thickness.MIDDLE
            } else {
                Thickness.BASE
            }
        } else {
            Thickness.TIP
        }
    }

    private fun spawnSmokeParticle(world: World, pos: BlockPos, random: Random) {
        world.addParticle(
            ParticleTypes.CAMPFIRE_SIGNAL_SMOKE,
            pos.x.toDouble() + 0.5 + random.nextDouble() / 4.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            pos.y.toDouble() + 0.4,
            pos.z.toDouble() + 0.5 + random.nextDouble() / 4.0 * (if (random.nextBoolean()) 1 else -1).toDouble(),
            0.0,
            0.01,
            0.0
        )
    }

    override fun onSteppedOn(world: World, pos: BlockPos?, state: BlockState?, entity: Entity) {
        if (world.isClient || pos == null || state == null) return

        if (state.get(THICKNESS) == Thickness.TIP) {
            val boundingBox = Box(pos.x - 0.5, pos.y.toDouble(), pos.z - 0.5, pos.x + 1.5, pos.y + 3.0, pos.z + 1.5)
            val entities = world.getEntitiesByClass(LivingEntity::class.java, boundingBox) { true }

            for (nearbyEntity in entities) {
                if (!nearbyEntity.bypassesSteppingEffects() && !EnchantmentHelper.hasFrostWalker(nearbyEntity)) {
                    nearbyEntity.damage(world.damageSources.hotFloor(), fireDamage.toFloat())
                    nearbyEntity.addStatusEffect(StatusEffectInstance(HybridAquaticStatusEffects.CORROSION, 200, 0)) // 10 seconds
                }
            }
        }

        super.onSteppedOn(world, pos, state, entity)
    }

    override fun getCollisionShape(
        state: BlockState,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        val voxelShape = when (val thickness = state.get(THICKNESS) as Thickness) {
            Thickness.TIP -> TIP_COLLISION_SHAPE
            Thickness.MIDDLE -> MIDDLE_COLLISION_SHAPE
            Thickness.BASE -> BASE_COLLISION_SHAPE
            else -> throw IllegalStateException("Unexpected thickness: $thickness")
        }
        val vec3d = state.getModelOffset(world, pos)
        return voxelShape.offset(vec3d.x, 0.0, vec3d.z)
    }

    override fun getCullingShape(state: BlockState, world: BlockView, pos: BlockPos): VoxelShape {
        return VoxelShapes.empty()
    }

    override fun getOutlineShape(
        state: BlockState,
        world: BlockView,
        pos: BlockPos,
        context: ShapeContext
    ): VoxelShape {
        val voxelShape = when (val thickness = state.get(THICKNESS) as Thickness) {
            Thickness.TIP -> TIP_SHAPE
            Thickness.MIDDLE -> MIDDLE_SHAPE
            Thickness.BASE -> BASE_SHAPE
            else -> throw IllegalStateException("Unexpected thickness: $thickness")
        }

        val modelOffset = state.getModelOffset(world, pos)
        return voxelShape.offset(modelOffset.x, 0.0, modelOffset.z)
    }

    override fun getFluidState(state: BlockState): FluidState {
        return if (state.get(WATERLOGGED)) {
            Fluids.WATER.getStill(false)
        } else {
            super.getFluidState(state)
        }
    }

    override fun appendProperties(builder: StateManager.Builder<Block?, BlockState?>) {
        builder.add(THICKNESS, WATERLOGGED)
    }

    companion object {
        val THICKNESS: EnumProperty<Thickness> = EnumProperty.of("thickness", Thickness::class.java, Thickness.TIP, Thickness.MIDDLE, Thickness.BASE)
        val WATERLOGGED: BooleanProperty = Properties.WATERLOGGED

        private val TIP_COLLISION_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)
        private val MIDDLE_COLLISION_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_COLLISION_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)

        private val TIP_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)
        private val MIDDLE_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
    }
}
