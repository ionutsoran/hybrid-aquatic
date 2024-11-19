package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HydrothermalVentBlock
import dev.hybridlabs.aquatic.block.TubeWormBlock
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.enums.Thickness
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.Heightmap
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import kotlin.math.max
import kotlin.math.sqrt

class VentPatchFeature(codec: Codec<VentPatchFeatureConfig>) : Feature<VentPatchFeatureConfig>(codec) {
    override fun generate(context: FeatureContext<VentPatchFeatureConfig>): Boolean {
        var generated = false

        val world = context.world
        val origin = context.origin
        val random = context.random

        val (baseProvider, ventProvider, wormProvider, countProvider, radiusProvider, wormCountProvider, wormRadiusProvider) = context.config

        val count = countProvider.get(random)
        repeat(count) {
            val radius = radiusProvider.get(random)
            val offsetX = random.nextInt(radius * 2 + 1) - radius
            val offsetZ = random.nextInt(radius * 2 + 1) - radius
            val candidatePos = origin.add(offsetX, 0, offsetZ)

            val topY = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, candidatePos.x, candidatePos.z)
            val candidateTopPos = BlockPos(candidatePos.x, topY, candidatePos.z)

            val distanceFromCenter = sqrt((offsetX * offsetX + offsetZ * offsetZ).toDouble())
            val heightMultiplier = 1.0 - (distanceFromCenter / radius).coerceIn(0.0, 1.0)

            if (generateSingleVent(world, candidateTopPos, random, heightMultiplier, baseProvider, ventProvider)) {
                val wormCount = wormCountProvider.get(random)
                val wormRadius = wormRadiusProvider.get(random)
                generateWormPatch(world, candidateTopPos, random, wormCount, wormRadius, wormProvider)
                generated = true
            }
        }

        return generated
    }

    private fun generateSingleVent(
        world: WorldAccess,
        startPos: BlockPos,
        random: Random,
        heightMultiplier: Double,
        baseProvider: BlockStateProvider,
        ventProvider: BlockStateProvider,
    ): Boolean {
        val mutablePos = startPos.mutableCopy()

        val state = ventProvider.get(random, mutablePos)
        if (!isValidPosition(world, mutablePos, state)) {
            return false
        }

        val baseThickness = 1 + random.nextInt(3)
        repeat(baseThickness) {
            val state = baseProvider.get(random, mutablePos)
            world.setBlockState(mutablePos, state, Block.NOTIFY_LISTENERS)
            mutablePos.move(Direction.UP)
        }

        val ventHeight = calculateVentHeight(heightMultiplier)
        repeat(ventHeight) { cycle ->
            generateHydrothermalVent(world, mutablePos, cycle, ventHeight, state)
            mutablePos.move(Direction.UP)
        }

        return true
    }

    fun generateHydrothermalVent(
        world: WorldAccess,
        pos: BlockPos,
        cycle: Int,
        height: Int,
        state: BlockState
    ) {
        val thickness = getHydrothermalVentThickness(cycle, height)
        world.setBlockState(
            pos,
            state
                .with(Properties.WATERLOGGED, world.isWater(pos))
                .with(HydrothermalVentBlock.THICKNESS, thickness),
            Block.NOTIFY_LISTENERS
        )
    }

    fun getHydrothermalVentThickness(cycle: Int, height: Int): Thickness {
        if (cycle == 0) {
            return Thickness.BASE
        }

        if (cycle == height - 1) {
            return Thickness.TIP
        }

        return Thickness.MIDDLE
    }

    private fun calculateVentHeight(heightMultiplier: Double): Int {
        val maxVentHeight = 5
        val minVentHeight = 2
        return max(minVentHeight, (minVentHeight + (maxVentHeight - minVentHeight) * heightMultiplier).toInt())
    }

    private fun generateWormPatch(
        world: WorldAccess,
        pos: BlockPos,
        random: Random,
        count: Int,
        radius: Int,
        stateProvider: BlockStateProvider,
    ) {
        repeat(count) {
            val offset = BlockPos(
                random.nextInt(radius * 2) - radius,
                random.nextInt(radius * 2) - radius,
                random.nextInt(radius * 2) - radius,
            )

            val wormPos = pos.add(offset)

            if (!world.isWater(wormPos)) {
                return@repeat
            }

            val baseState = stateProvider.get(random, wormPos)

            val stateWithWorms = baseState.with(TubeWormBlock.WORMS, random.nextInt(4) + 1)

            if (isValidPosition(world, wormPos, stateWithWorms)) {
                world.setBlockState(wormPos, stateWithWorms, Block.NOTIFY_LISTENERS)
            }
        }
    }

    private fun isValidPosition(world: WorldAccess, pos: BlockPos, state: BlockState): Boolean {
        val existingState = world.getBlockState(pos)

        if (!existingState.isReplaceable) {
            return false
        }

        return state.canPlaceAt(world, pos)
    }
}
