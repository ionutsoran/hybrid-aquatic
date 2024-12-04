@file:Suppress("UnstableApiUsage")

package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.registry.RegistryWrapper
import net.minecraft.world.Heightmap
import net.minecraft.world.gen.feature.PlacedFeature
import net.minecraft.world.gen.feature.PlacedFeatures
import net.minecraft.world.gen.placementmodifier.*
import java.util.concurrent.CompletableFuture

class PlacedFeatureProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: RegistryWrapper.WrapperLookup, entries: Entries) {
        // anemone patch
        entries.add(
            HybridAquaticPlacedFeatures.ANEMONE_PATCH,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.ANEMONE_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                    BiomePlacementModifier.of()
                )
            )
        )

        // giant clam patch
        entries.add(
            HybridAquaticPlacedFeatures.GIANT_CLAM_PATCH,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.GIANT_CLAM_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                    BiomePlacementModifier.of()
                )
            )
        )

        // sponge patch
        entries.add(
            HybridAquaticPlacedFeatures.TUBE_SPONGE_PATCH,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.TUBE_SPONGE_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                    BiomePlacementModifier.of()
                )
            )
        )

        // message in a bottle
        entries.add(
            HybridAquaticPlacedFeatures.MESSAGE_IN_A_BOTTLE,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.MESSAGE_IN_A_BOTTLE),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP,
                    RarityFilterPlacementModifier.of(150),
                    BiomePlacementModifier.of()
                )
            )
        )

        // thermal vents
        entries.add(
            HybridAquaticPlacedFeatures.HYDROTHERMAL_VENT,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.HYDROTHERMAL_VENT_PATCH),
                listOf(
                    NoiseBasedCountPlacementModifier.of(10, 200.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.BOTTOM_TO_120_RANGE,
                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Int.MIN_VALUE, -32),
                    CountPlacementModifier.of(4),
                    BiomePlacementModifier.of()
                )
            )
        )

        // brine lake
        entries.add(
            HybridAquaticPlacedFeatures.BRINE_LAKE,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.BRINE_LAKE),
                listOf(
                    RarityFilterPlacementModifier.of(5),
                    SquarePlacementModifier.of(),
                    HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG),
                    BiomePlacementModifier.of(),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DEEP_CORAL_MUSHROOM,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.DEEP_CORAL_MUSHROOM),
                listOf(
                    SquarePlacementModifier.of(),
                    HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DEEP_CORAL_TREE,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.DEEP_CORAL_TREE),
                listOf(
                    SquarePlacementModifier.of(),
                    HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DEEP_CORAL_CLAW,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.DEEP_CORAL_CLAW),
                listOf(
                    SquarePlacementModifier.of(),
                    HeightmapPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DEEP_OCEAN_VEGETATION,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.DEEP_OCEAN_VEGETATION),
                listOf(
                    NoiseBasedCountPlacementModifier.of(20, 400.0, 0.0),
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    BiomePlacementModifier.of()
                )
            )
        )
    }

    override fun getName(): String {
        return "Placed Features"
    }
}
