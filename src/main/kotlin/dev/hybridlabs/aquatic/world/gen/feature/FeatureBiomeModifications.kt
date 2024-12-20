package dev.hybridlabs.aquatic.world.gen.feature

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.world.gen.GenerationStep

/**
 * Applies biome modifications to features when initialised.
 */
object FeatureBiomeModifications {
    init {
        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.ANEMONE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.SARGASSUM
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.DEEP_TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.FLOATING_SARGASSUM
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.RED_SEAWEED_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.SHALLOW_TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.RED_SEAWEED_MEADOW
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.TROPICAL_OCEANS),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.GREEN_SEAWEED_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.REEF),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_OCEAN),
            GenerationStep.Feature.VEGETAL_DECORATION,
            HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES),
            GenerationStep.Feature.TOP_LAYER_MODIFICATION,
            HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN),
            GenerationStep.Feature.SURFACE_STRUCTURES,
            HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH
        )

        BiomeModifications.addFeature(
            BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN),
            GenerationStep.Feature.SURFACE_STRUCTURES,
            HybridAquaticPlacedFeatures.BRINE_LAKE
        )

//        BiomeModifications.addFeature(
//            BiomeSelectors.tag(BiomeTags.IS_DEEP_OCEAN),
//            GenerationStep.Feature.VEGETAL_DECORATION,
//            HybridAquaticPlacedFeatures.DEEP_OCEAN_VEGETATION
//        )
    }
}
