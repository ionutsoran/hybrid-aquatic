@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import dev.hybridlabs.aquatic.world.gen.feature.*
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.entry.RegistryEntryList
import net.minecraft.state.property.Properties
import net.minecraft.util.math.intprovider.ConstantIntProvider
import net.minecraft.util.math.intprovider.UniformIntProvider
import net.minecraft.world.gen.blockpredicate.BlockPredicate
import net.minecraft.world.gen.feature.*
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider
import java.util.concurrent.CompletableFuture

class ConfiguredFeatureProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        // anemone patch
        entries.add(
            HybridAquaticConfiguredFeatures.ANEMONE_PATCH,
            ConfiguredFeature(
                Feature.NO_BONEMEAL_FLOWER, RandomPatchFeatureConfig(
                    2, 2, 2,
                    PlacedFeatures.createEntry(
                        Feature.SIMPLE_BLOCK,
                        SimpleBlockFeatureConfig(
                            BlockStateProvider.of(HybridAquaticBlocks.ANEMONE.defaultState.with(Properties.WATERLOGGED, true))
                        ),
                        BlockPredicate.matchingBlockTag(HybridAquaticBlockTags.ANEMONES_GENERATE_IN)
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
                            BlockStateProvider.of(HybridAquaticBlocks.TUBE_SPONGE.defaultState.with(Properties.WATERLOGGED, true))
                        ),
                        BlockPredicate.matchingBlockTag(HybridAquaticBlockTags.TUBE_SPONGE_GENERATE_IN)
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
                            BlockStateProvider.of(HybridAquaticBlocks.GIANT_CLAM.defaultState.with(Properties.WATERLOGGED, true))
                        ),
                        BlockPredicate.matchingBlockTag(HybridAquaticBlockTags.GIANT_CLAM_GENERATE_IN)
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
                    UniformIntProvider.create(2, 5),
                    ConstantIntProvider.create(5),
                    UniformIntProvider.create(4, 8),
                    ConstantIntProvider.create(8),
                    UniformIntProvider.create(TubeWormBlock.WORMS.min, TubeWormBlock.WORMS.max),
                )
            )
        )

        // deep coral
        entries.add(
            HybridAquaticConfiguredFeatures.DEEP_CORAL_TREE,
            ConfiguredFeature(
                HybridAquaticFeatures.DEEP_CORAL_TREE, DefaultFeatureConfig()
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.DEEP_CORAL_CLAW,
            ConfiguredFeature(
                HybridAquaticFeatures.DEEP_CORAL_CLAW, DefaultFeatureConfig()
            )
        )

        entries.add(
            HybridAquaticConfiguredFeatures.DEEP_CORAL_MUSHROOM,
            ConfiguredFeature(
                HybridAquaticFeatures.DEEP_CORAL_MUSHROOM, DefaultFeatureConfig()
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

        entries.add(
            HybridAquaticConfiguredFeatures.DEEP_OCEAN_VEGETATION,
            ConfiguredFeature(
                Feature.SIMPLE_RANDOM_SELECTOR,
                SimpleRandomFeatureConfig(
                    RegistryEntryList.of(
                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_TREE),
                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_CLAW),
                        entries.ref(HybridAquaticPlacedFeatures.DEEP_CORAL_MUSHROOM)
                    )
                )
            )
        )
    }

    override fun getName(): String {
        return "Configured Features"
    }
}
