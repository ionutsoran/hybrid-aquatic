package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.FluidBlock
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.fluid.FluidState
import net.minecraft.item.Item
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.state.StateManager
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.BlockView
import net.minecraft.world.World
import net.minecraft.world.WorldAccess
import net.minecraft.world.WorldView
import java.util.*

open class BrineFluid : FlowableFluid() {
    override fun isInfinite(world: World): Boolean {
        return false
    }

    override fun beforeBreakingBlock(world: WorldAccess, pos: BlockPos, state: BlockState) {
        val blockEntity = if (state.hasBlockEntity()) world.getBlockEntity(pos) else null
        Block.dropStacks(state, world, pos, blockEntity)
    }

    override fun getFlowSpeed(world: WorldView): Int {
        return 2
    }

    override fun getLevelDecreasePerBlock(world: WorldView): Int {
        return 1
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

    override fun matchesType(fluid: Fluid): Boolean {
        return fluid === HybridAquaticFluids.BRINE || fluid === HybridAquaticFluids.FLOWING_BRINE
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

    public override fun randomDisplayTick(world: World, pos: BlockPos, state: FluidState?, random: Random) {
        val blockPos = pos.up()
        if (world.getBlockState(blockPos).isOf(Blocks.WATER) && !world.getBlockState(blockPos).isOpaqueFullCube(world, blockPos)) {
            if (random.nextInt(50) == 0) {
                val d = pos.x.toDouble() + random.nextDouble()
                val e = pos.y.toDouble() + 1.0
                val f = pos.z.toDouble() + random.nextDouble()

                val upwardVelocity = 0.1 + random.nextDouble() * 0.4
                world.addParticle(ParticleTypes.MYCELIUM, d, e, f, 0.0, upwardVelocity, 0.0)
            }
        }
    }

    override fun getBucketFillSound(): Optional<SoundEvent> {
        return Optional.of(SoundEvents.ITEM_BUCKET_FILL)
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
