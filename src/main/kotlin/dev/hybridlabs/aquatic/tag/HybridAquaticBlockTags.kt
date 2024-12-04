package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.block.Block
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier

object HybridAquaticBlockTags {

    val PLUSHIES = create("plushies")

    val ANEMONES_GENERATE_IN = create("anemones_generate_in")

    val GIANT_CLAM_GENERATE_IN = create("giant_clam_generate_in")

    val TUBE_SPONGE_GENERATE_IN = create("tube_sponge_generate_in")

    val MESSAGE_IN_A_BOTTLE_SPAWNS_IN = create("message_in_a_bottle_spawns_in")

    val CRAB_DIGGABLE_BLOCKS = create("crab_diggable_blocks")

    val DEEP_CORAL_PLANTS = create("deep_coral_plants")
    val DEEP_CORAL_BLOCKS = create("deep_coral_blocks")
    val DEEP_CORALS = create("deep_corals")
    val DEEP_WALL_CORALS = create("deep_corals")

    private fun create(id: String): TagKey<Block> {
        return TagKey.of(RegistryKeys.BLOCK, Identifier(HybridAquatic.MOD_ID, id))
    }
}
