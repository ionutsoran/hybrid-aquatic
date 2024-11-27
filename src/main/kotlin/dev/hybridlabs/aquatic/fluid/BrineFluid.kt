package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.item.Item
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView

open class BrineFluid : FlowableFluid() {
    override fun isInfinite(world: World): Boolean {
        return false
    }

    override fun beforeBreakingBlock(world: WorldAccess, pos: BlockPos, state: BlockState) {
        val blockEntity = if (state.hasBlockEntity()) world.getBlockEntity(pos) else null
        Block.dropStacks(state, world, pos, blockEntity)
    }

    override fun getFlowSpeed(world: WorldView): Int {
        return 3
    }

    override fun getLevelDecreasePerBlock(world: WorldView): Int {
        return 2
    }

    override fun getLevel(state: FluidState): Int {
        return 8
    }

    override fun getTickRate(world: WorldView): Int {
        return 5
    }

    override fun getBlastResistance(): Float {
        return 100F
    }

    override fun canBeReplacedWith(
        state: FluidState,
        world: BlockView,
        pos: BlockPos,
        fluid: Fluid,
        direction: Direction
    ): Boolean {
        return false
    }

    override fun getStill(): Fluid {
        return HybridAquaticFluids.BRINE
    }

    override fun getFlowing(): Fluid {
        return HybridAquaticFluids.FLOWING_BRINE
    }

    override fun getBucketItem(): Item {
        return HybridAquaticItems.BRINE_BUCKET
    }

    public override fun toBlockState(state: FluidState): BlockState {
        return HybridAquaticBlocks.BRINE.defaultState.with(FluidBlock.LEVEL, getBlockStateLevel(state))
    }

    override fun isStill(state: FluidState): Boolean {
        return false
    }

    class Flowing : BrineFluid() {
        override fun appendProperties(builder: StateManager.Builder<Fluid?, FluidState?>) {
            super.appendProperties(builder)
            builder.add(LEVEL)
        }

        override fun getLevel(state: FluidState): Int {
            return state.get(LEVEL)
        }

        override fun isStill(state: FluidState): Boolean {
            return false
        }
    }

    class Still : BrineFluid() {
        override fun getLevel(state: FluidState): Int {
            return 8
        }

        override fun isStill(state: FluidState): Boolean {
            return true
        }
    }
}
