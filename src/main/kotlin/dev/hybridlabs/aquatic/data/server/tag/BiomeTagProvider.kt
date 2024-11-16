package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BiomeTags
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome
import net.minecraft.world.biome.BiomeKeys
import java.util.concurrent.CompletableFuture

class BiomeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider<Biome>(output, RegistryKeys.BIOME, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup?) {
        // spawn biomes

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SANDY_BEACHES)
            .add(BiomeKeys.BEACH)
            .addOptional(Identifier("wythers", "tropical_beach"))
            .addOptional(Identifier("biomesoplenty", "dune_beach"))
            .addOptional(Identifier("biomeswevegone", "rainbow_beach"))
            .addOptional(Identifier("terrestria", "volcanic_island_beach"))
            .addOptional(Identifier("mysticsbiomes", "lagoon"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ROCKY_BEACHES)
            .add(BiomeKeys.STONY_SHORE)
            .addOptional(Identifier("biomesoplenty", "gravel_beach"))
            .addOptional(Identifier("biomeswevegone", "dacite_shore"))
            .addOptional(Identifier("biomeswevegone", "basalt_barrera"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.BOTTLE_SPAWN_BIOMES)
            .forceAddTag(BiomeTags.IS_OCEAN)
            .forceAddTag(BiomeTags.IS_DEEP_OCEAN)
            .forceAddTag(BiomeTags.IS_BEACH)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.OCEAN)
            .add(BiomeKeys.OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_OCEAN)
            .add(BiomeKeys.DEEP_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.COLD_OCEANS)
            .add(BiomeKeys.COLD_OCEAN,
                BiomeKeys.FROZEN_OCEAN)

            getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_COLD_OCEANS)
            .add(BiomeKeys.DEEP_COLD_OCEAN,
                BiomeKeys.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ALL_COLD_OCEANS)
            .add(BiomeKeys.COLD_OCEAN,
                BiomeKeys.FROZEN_OCEAN,
                BiomeKeys.DEEP_COLD_OCEAN,
                BiomeKeys.DEEP_FROZEN_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.DEEP_WARM_OCEANS)
            .add(BiomeKeys.DEEP_LUKEWARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.ALL_WARM_OCEANS)
            .add(BiomeKeys.LUKEWARM_OCEAN,
                BiomeKeys.DEEP_LUKEWARM_OCEAN)

        getOrCreateTagBuilder(HybridAquaticBiomeTags.REEF)
            .add(BiomeKeys.WARM_OCEAN)
            .addOptional(Identifier("regions_unexplored", "rocky_reef"))
            .addOptional(Identifier("biomeswevegone", "lush_stacks"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.JUNGLE)
            .forceAddTag(BiomeTags.IS_JUNGLE)
            .addOptional(Identifier("wythers", "flooded_jungle"))
            .addOptional(Identifier("biomesoplenty", "rainforest"))
            .addOptional(Identifier("biomesoplenty", "rocky_rainforest"))
            .addOptional(Identifier("biomeswevegone", "crag_gardens"))
            .addOptional(Identifier("biomeswevegone", "jacaranda_jungle"))
            .addOptional(Identifier("biomeswevegone", "fragment_jungle"))
            .addOptional(Identifier("biomeswevegone", "tropical_rainforest"))
            .addOptional(Identifier("terrestria", "hemlock_rainforest"))
            .addOptional(Identifier("terrestria", "hemlock_clearing"))
            .addOptional(Identifier("terrestria", "rainbow_rainforest"))
            .addOptional(Identifier("terrestria", "rainbow_rainforest_lake"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.SWAMP)
            .add(BiomeKeys.SWAMP)
            .addOptional(Identifier("wythers", "waterlily_swamp"))
            .addOptional(Identifier("terralith", "orchid_swamp"))
            .addOptional(Identifier("biomesoplenty", "bayou"))
            .addOptional(Identifier("biomeswevegone", "cypress_swamplands"))
            .addOptional(Identifier("biomeswevegone", "bayou"))
            .addOptional(Identifier("terrestria", "cypress_swamp"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MANGROVES)
            .add(BiomeKeys.MANGROVE_SWAMP)
            .addOptional(Identifier("biomeswevegone", "white_mangrove_marshes"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.MARSHES)
            .addOptional(Identifier("regions_unexplored", "marsh"))
            .addOptional(Identifier("biomesoplenty", "marsh"))
            .addOptional(Identifier("biomesoplenty", "wetland"))
            .addOptional(Identifier("biomesoplenty", "floodplain"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.RIVERS)
            .add(BiomeKeys.RIVER)
            .addOptional(Identifier("wythers", "jungle_river"))
            .addOptional(Identifier("wythers", "tropical_forest_river"))
            .addOptional(Identifier("terralith", "warm_river"))
            .addOptional(Identifier("regions_unexplored", "tropical_river"))
            .addOptional(Identifier("regions_unexplored", "muddy_river"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.TROPICAL_RIVERS)
            .addOptional(Identifier("wythers", "jungle_river"))
            .addOptional(Identifier("wythers", "tropical_forest_river"))
            .addOptional(Identifier("terralith", "warm_river"))
            .addOptional(Identifier("regions_unexplored", "tropical_river"))

        getOrCreateTagBuilder(HybridAquaticBiomeTags.CHERRY)
            .add(BiomeKeys.CHERRY_GROVE)
            .addOptional(Identifier("regions_unexplored", "mauve_hills"))
            .addOptional(Identifier("regions_unexplored", "magnolia_woodland"))
    }
}
