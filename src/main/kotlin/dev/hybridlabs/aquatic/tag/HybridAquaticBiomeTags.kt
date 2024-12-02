package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome

object HybridAquaticBiomeTags {

    val BOTTLE_SPAWN_BIOMES = create("bottle_spawn_biomes")

    val ARCTIC_OCEANS = create("arctic_oceans")
    val COLD_OCEANS = create("cold_oceans")
    val TEMPERATE_OCEANS = create("temperate_oceans")
    val TROPICAL_OCEANS = create("tropical_oceans")
    val REEF = create("reef")
    val JUNGLE = create("jungle")
    val CHERRY = create("cherry")
    val CAVES = create("caves")
    val SWAMP = create("swamp")
    val MARSHES = create("marshes")
    val MANGROVES = create("mangroves")
    val RIVERS = create("rivers")
    val TROPICAL_RIVERS = create("tropical_rivers")
    val SANDY_BEACHES = create("sandy_beaches")
    val ROCKY_BEACHES = create("rocky_beaches")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.of(RegistryKeys.BIOME, Identifier(HybridAquatic.MOD_ID, id))
    }
}
