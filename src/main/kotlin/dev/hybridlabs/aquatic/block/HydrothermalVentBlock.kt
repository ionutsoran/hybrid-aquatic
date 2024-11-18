package dev.hybridlabs.aquatic.block

import net.minecraft.block.*
import net.minecraft.block.enums.Thickness
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.fluid.Fluids
import net.minecraft.item.ItemPlacementContext
import net.minecraft.particle.ParticleTypes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.state.StateManager
import net.minecraft.state.property.EnumProperty
import net.minecraft.state.property.Property
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.util.shape.VoxelShape
import net.minecraft.util.shape.VoxelShapes
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

@Suppress("DEPRECATION", "SameParameterValue")
class HydrothermalVentBlock(settings: Settings?) :
    Block(settings),
    Waterloggable {
    companion object {
        val THICKNESS: EnumProperty<Thickness> = EnumProperty.of("thickness", Thickness::class.java, Thickness.TIP, Thickness.MIDDLE, Thickness.BASE)

        private val TIP_COLLISION_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)
        private val MIDDLE_COLLISION_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_COLLISION_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)

        private val TIP_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 4.0, 13.0)
        private val MIDDLE_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
        private val BASE_SHAPE = createCuboidShape(3.0, 0.0, 3.0, 13.0, 16.0, 13.0)
    }

    init {
        this.defaultState = this.stateManager.defaultState.with(THICKNESS, Thickness.TIP)
    }

    override fun appendProperties(builder: StateManager.Builder<Block?, BlockState?>) {
        builder.add(
            *arrayOf<Property<*>>(
                THICKNESS,
            )
        )
    }

    @Deprecated("Deprecated in Java")
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

    @Deprecated("Deprecated in Java", ReplaceWith("VoxelShapes.empty()", "net.minecraft.util.shape.VoxelShapes"))
    override fun getCullingShape(state: BlockState?, world: BlockView?, pos: BlockPos?): VoxelShape {
        return VoxelShapes.empty()
    }

    @Deprecated("Deprecated in Java", ReplaceWith("false"))
    override fun isShapeFullCube(state: BlockState?, world: BlockView?, pos: BlockPos?): Boolean {
        return false
    }

    @Deprecated("Deprecated in Java")
    override fun getOutlineShape(
        state: BlockState,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape {
        val voxelShape = when (val thickness = state.get(THICKNESS) as Thickness) {
            Thickness.TIP -> TIP_SHAPE
            Thickness.MIDDLE -> MIDDLE_SHAPE
            Thickness.BASE -> BASE_SHAPE
            else -> throw IllegalStateException("Unexpected thickness: $thickness")
        }
        val vec3d = state.getModelOffset(world, pos)
        return voxelShape.offset(vec3d.x, 0.0, vec3d.z)
    }

    private fun getThickness(world: WorldView, pos: BlockPos, direction: Direction): Thickness {
        val blockAbove = world.getBlockState(pos.offset(direction))
        val blockBelow = world.getBlockState(pos.offset(direction.opposite))

        return when {
            (blockBelow.isSolid || blockBelow.block is HydrothermalVentBlock) && blockAbove.block !is HydrothermalVentBlock -> Thickness.TIP
            blockBelow.isSolid && blockAbove.block is HydrothermalVentBlock -> Thickness.BASE
            blockBelow.block is HydrothermalVentBlock && blockAbove.block is HydrothermalVentBlock -> Thickness.MIDDLE
            else -> Thickness.MIDDLE
        }
    }

    override fun canFillWithFluid(world: BlockView, pos: BlockPos, state: BlockState, fluid: Fluid): Boolean {
        return false
    }

    override fun tryFillWithFluid(
        world: WorldAccess,
        pos: BlockPos,
        state: BlockState,
        fluidState: FluidState
    ): Boolean {
        return false
    }

    override fun randomDisplayTick(state: BlockState?, world: World?, pos: BlockPos?, random: Random?) {
        super.randomDisplayTick(state, world, pos, random)
        if (world != null && pos != null) {
            spawnSmokeParticle(world, pos)
        }
    }

    private fun spawnSmokeParticle(world: World, pos: BlockPos) {
        val random = world.getRandom()
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

    override fun getPlacementState(ctx: ItemPlacementContext): BlockState? {
        val worldAccess = ctx.world
        val blockPos = ctx.blockPos
        val fluidState = worldAccess.getFluidState(blockPos)

        if (fluidState.isIn(FluidTags.WATER) && fluidState.level == 8) {
            val thickness = getThickness(worldAccess, blockPos, Direction.UP)
            return this.defaultState.with(THICKNESS, thickness)
        }

        return null
    }

    @Deprecated("Deprecated in Java", ReplaceWith("Fluids.WATER.getStill(false)", "net.minecraft.fluid.Fluids"))
    override fun getFluidState(state: BlockState): FluidState {
        return Fluids.WATER.getStill(false)
    }
}
