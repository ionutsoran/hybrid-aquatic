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
                    CountPlacementModifier.of(2),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.BROWN_SEAWEED_PATCH,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.BROWN_SEAWEED_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.RED_SEAWEED_PATCH,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.RED_SEAWEED_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.GREEN_SEAWEED_PATCH,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.GREEN_SEAWEED_PATCH),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                    CountPlacementModifier.of(1),
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
                )
            )
        )

        // thermal vents
        entries.add(
            HybridAquaticPlacedFeatures.THERMAL_VENT_PATCH,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.THERMAL_VENT_PATCH),
                listOf(
                    NoiseBasedCountPlacementModifier.of(5, 100.0, 0.0),
                    SquarePlacementModifier.of(),
                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Int.MIN_VALUE, -12),
                    CountPlacementModifier.of(1),
                )
            )
        )

        // brine lake
        entries.add(
            HybridAquaticPlacedFeatures.BRINE_LAKE,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.BRINE_LAKE),
                listOf(
                    SquarePlacementModifier.of(),
                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Int.MIN_VALUE, -8),
                    CountPlacementModifier.of(1),
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DEEP_CORAL_MUSHROOM,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.DEEP_CORAL_MUSHROOM),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DEEP_CORAL_TREE,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.DEEP_CORAL_TREE),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DEEP_CORAL_CLAW,
            PlacedFeature(entries.ref(HybridAquaticConfiguredFeatures.DEEP_CORAL_CLAW),
                listOf(
                    SquarePlacementModifier.of(),
                    PlacedFeatures.OCEAN_FLOOR_WG_HEIGHTMAP,
                )
            )
        )

        entries.add(
            HybridAquaticPlacedFeatures.DEEP_OCEAN_VEGETATION,
            PlacedFeature(
                entries.ref(HybridAquaticConfiguredFeatures.DEEP_OCEAN_VEGETATION),
                listOf(
                    NoiseBasedCountPlacementModifier.of(40, 200.0, 0.0),
                    SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Int.MIN_VALUE, -12),
                    SquarePlacementModifier.of(),
                )
            )
        )
    }

    override fun getName(): String {
        return "Placed Features"
    }
}
