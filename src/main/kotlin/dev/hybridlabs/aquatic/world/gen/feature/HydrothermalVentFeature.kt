package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.block.Blocks
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random
import net.minecraft.world.Heightmap
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import kotlin.math.max
import kotlin.math.sqrt

class HydrothermalVentFeature(codec: Codec<HydrothermalVentFeatureConfig>?) :
    Feature<HydrothermalVentFeatureConfig>(codec) {

    override fun generate(context: FeatureContext<HydrothermalVentFeatureConfig>): Boolean {
        val world = context.world
        val origin = context.origin
        val random = context.random

        val clusterRadius = 4
        val ventCount = 2 + random.nextInt(3)
        val spawnChance = 0.5f

        var generatedAny = false

        for (i in 0 until ventCount) {
            if (random.nextFloat() < spawnChance) {
                val offsetX = random.nextInt(clusterRadius * 2 + 1) - clusterRadius
                val offsetZ = random.nextInt(clusterRadius * 2 + 1) - clusterRadius
                val candidatePos = origin.add(offsetX, 0, offsetZ)

                val topY = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, candidatePos.x, candidatePos.z)
                val candidateTopPos = BlockPos(candidatePos.x, topY, candidatePos.z)

                val distanceFromCenter = sqrt((offsetX * offsetX + offsetZ * offsetZ).toDouble())
                val heightMultiplier = 1.0 - (distanceFromCenter / clusterRadius).coerceIn(0.0, 1.0)

                if (generateSingleVent(world, candidateTopPos, random, heightMultiplier)) {
                    generateFireCoralPatches(world, candidateTopPos, random, clusterRadius)
                    generatedAny = true
                }
            }
        }

        return generatedAny
    }

    private fun generateSingleVent(
        world: WorldAccess,
        startPos: BlockPos,
        random: Random,
        heightMultiplier: Double
    ): Boolean {
        var currentPos = startPos

        if (!isValidBasePosition(world, currentPos)) return false

        val baseThickness = 1 + random.nextInt(3)
        for (i in 0 until baseThickness) {
            world.setBlockState(currentPos, Blocks.DRIPSTONE_BLOCK.defaultState, 2)
            currentPos = currentPos.up()
        }

        val maxVentHeight = 5
        val minVentHeight = 2
        val ventHeight = max(
            minVentHeight,
            (minVentHeight + (maxVentHeight - minVentHeight) * heightMultiplier).toInt()
        )

        for (i in 0 until ventHeight) {
            if (world.getBlockState(currentPos).isOf(Blocks.WATER)) {
                world.setBlockState(currentPos, HybridAquaticBlocks.HYDROTHERMAL_VENT.defaultState, 2)
                currentPos = currentPos.up()
            } else {
                break
            }
        }

        if (world.getBlockState(currentPos).isOf(Blocks.WATER)) {
            world.setBlockState(currentPos, HybridAquaticBlocks.HYDROTHERMAL_VENT.defaultState, 2)
        }

        return true
    }

    private fun generateFireCoralPatches(
        world: WorldAccess,
        ventPos: BlockPos,
        random: Random,
        clusterRadius: Int
    ) {
        val patchCount = 3 + random.nextInt(4)

        for (i in 0 until patchCount) {
            val offsetX = random.nextInt(clusterRadius * 2 + 1) - clusterRadius
            val offsetZ = random.nextInt(clusterRadius * 2 + 1) - clusterRadius
            val candidatePos = ventPos.add(offsetX, 0, offsetZ)

            if (world.getBlockState(candidatePos).isOf(Blocks.WATER)) {
                val belowBlock = world.getBlockState(candidatePos.down())
                if (belowBlock.isOpaqueFullCube(world, candidatePos.down())) {
                    val fireCoralPatchSize = 1 + random.nextInt(3)
                    for (j in 0 until fireCoralPatchSize) {
                        val coralPos = candidatePos.add(
                            random.nextInt(3) - 1,
                            0,
                            random.nextInt(3) - 1
                        )

                        if (world.getBlockState(coralPos).isOf(Blocks.WATER)) {
                            world.setBlockState(coralPos, Blocks.FIRE_CORAL.defaultState, 2)
                        }
                    }
                }
            }
        }
    }

    private fun isValidBasePosition(world: WorldAccess, pos: BlockPos): Boolean {
        val belowBlock = world.getBlockState(pos.down())
        val currentBlock = world.getBlockState(pos)
        return belowBlock.isOpaqueFullCube(world, pos.down()) && currentBlock.isOf(Blocks.WATER)
    }
}