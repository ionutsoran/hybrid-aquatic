@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.world.gen.feature.*
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.HorizontalFacingBlock
import net.minecraft.registry.RegistryWrapper
import net.minecraft.state.property.Properties
import net.minecraft.util.collection.DataPool
import net.minecraft.util.math.Direction
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.NoiseBlockStateProvider
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        // anemone patch
        entries.add(
            HybridAquaticConfiguredFeatures.ANEMONE_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER, RandomPatchFeatureConfig(
                    3, 3, 3,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            WeightedBlockStateProvider(
                                DataPool.builder<BlockState>()
                                    .add(HybridAquaticBlocks.ANEMONE.defaultState.with(Properties.WATERLOGGED, true), 1)
                                    .add(
                                        HybridAquaticBlocks.STRAWBERRY_ANEMONE.defaultState.with(
                                            Properties.WATERLOGGED,
                                            true
                                        ), 3
                                    )
                                    .build()
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.RED_SEAWEED_PATCH,
            ConfiguredFeature(
                Feature.FLOWER, RandomPatchFeatureConfig(
                    10, 5, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(HybridAquaticBlocks.RED_SEAWEED.defaultState)
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.RED_SEAWEED_MEADOW,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                    100, 10, 10,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            NoiseBlockStateProvider(
                                237L,
                                DoublePerlinNoiseSampler.NoiseParameters(-5, -5.0, *DoubleArray(0)),
                                1.0f,
                                listOf<BlockState>(
                                    HybridAquaticBlocks.RED_SEAWEED.defaultState
                                )
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        //#region Sargassum

        entries.add(
            HybridAquaticConfiguredFeatures.SARGASSUM,
            ConfiguredFeature(
                HybridAquaticFeatures.SARGASSUM, SargassumFeatureConfig(
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.SARGASSUM)
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.FLOATING_SARGASSUM,
            ConfiguredFeature(
                Feature.RANDOM_PATCH, RandomPatchFeatureConfig(
                    100, 10, 10,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            NoiseBlockStateProvider(
                                237L,
                                DoublePerlinNoiseSampler.NoiseParameters(-5, 5.0, *DoubleArray(0)),
                                1.0f,
                                listOf<BlockState>(
                                    HybridAquaticBlocks.FLOATING_SARGASSUM.defaultState
                                )
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.GREEN_SEAWEED_PATCH,
            ConfiguredFeature(
                Feature.FLOWER, RandomPatchFeatureConfig(
                    10, 5, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(HybridAquaticBlocks.GREEN_SEAWEED.defaultState)
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        // tube sponge patch
        entries.add(
            HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH,
            ConfiguredFeature(
                Feature.FLOWER,
                RandomPatchFeatureConfig(
                    4, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(
                                HybridAquaticBlocks.TUBE_SPONGE.defaultState.with(
                                    Properties.WATERLOGGED,
                                    true
                                )
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        // giant clam patch
        entries.add(
            HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER, RandomPatchFeatureConfig(
                    2, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            WeightedBlockStateProvider(
                                DataPool.builder<BlockState>()
                                    .add(
                                        HybridAquaticBlocks.GIANT_CLAM.defaultState.with(Properties.WATERLOGGED, true)
                                            .with(HorizontalFacingBlock.FACING, Direction.EAST), 1
                                    )
                                    .add(
                                        HybridAquaticBlocks.GIANT_CLAM.defaultState.with(Properties.WATERLOGGED, true)
                                            .with(HorizontalFacingBlock.FACING, Direction.NORTH), 1
                                    )
                                    .build()
                            )
                        ),
                        BlockPredicate.matchingBlocks(Blocks.WATER)
                    )
                )
            )
        )

        // message in a bottle
        entries.add(
            HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE,
            ConfiguredFeature(
                HybridAquaticFeatures.MESSAGE_IN_A_BOTTLE, MessageInABottleFeatureConfig(
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE)
                )
            )
        )

        // thermal vents
        entries.add(
            HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH,
            ConfiguredFeature(
                HybridAquaticFeatures.VENT_PATCH, VentPatchFeatureConfig(
                    SimpleBlockStateProvider.of(Blocks.TUFF),
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.THERMAL_VENT),
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.TUBE_WORM),
                    UniformIntProvider.create(2, 3),
                    ConstantIntProvider.create(2),
                    UniformIntProvider.create(1, 3),
                    ConstantIntProvider.create(8),
                    UniformIntProvider.create(TubeWormBlock.WORMS.min, TubeWormBlock.WORMS.max),
                )
            )
        )

        // brine lake
        entries.add(
            HybridAquaticConfiguredFeatures.BRINE_LAKE,
            ConfiguredFeature(
                HybridAquaticFeatures.BRINE_LAKE_FEATURE, BrineLakeFeatureConfig(
                    SimpleBlockStateProvider.of(Blocks.TUFF),
                    SimpleBlockStateProvider.of(HybridAquaticBlocks.BRINE),
                )
            )
        )

//        // deep coral
//        entries.add(
//            HybridAquaticConfiguredFeatures.DEEP_CORAL_TREE,
//            ConfiguredFeature(
//                HybridAquaticFeatures.DEEP_CORAL_TREE, DefaultFeatureConfig()
//            )
//        )
//
//        entries.add(
//            HybridAquaticConfiguredFeatures.DEEP_CORAL_CLAW,
//            ConfiguredFeature(
//                HybridAquaticFeatures.DEEP_CORAL_CLAW, DefaultFeatureConfig()
//            )
//        )
//
//        entries.add(
//            HybridAquaticConfiguredFeatures.DEEP_CORAL_MUSHROOM,
//            ConfiguredFeature(
//                HybridAquaticFeatures.DEEP_CORAL_MUSHROOM, DefaultFeatureConfig()
//            )
//        )
//
//        entries.add(
//            HybridAquaticConfiguredFeatures.DEEP_OCEAN_VEGETATION,
//            ConfiguredFeature(
//                Feature.SIMPLE_RANDOM_SELECTOR,
//                SimpleRandomFeatureConfig(
//                    RegistryEntryList.of(
//                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_TREE),
//                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_CLAW),
//                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_MUSHROOM)
//                    )
//                )
//            )
//        )
    }

    override fun getName(): String {
        return "Configured Features"
    }
}
