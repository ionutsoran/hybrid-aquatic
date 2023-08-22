package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.RegistryKeys
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.Identifier
import net.minecraft.world.biome.Biome

object HybridAquaticBiomeTags {

    val CLOWNFISH_SPAWN_BIOMES = create("clownfish_spawn_biomes")
    val YELLOWFIN_SPAWN_BIOMES = create("yellowfin_spawn_biomes")
    val MAHIMAHI_SPAWN_BIOMES = create("mahimahi_spawn_biomes")
    val BLUE_TANG_SPAWN_BIOMES = create("blue_tang_spawn_biomes")
    val COWFISH_SPAWN_BIOMES = create("cowfish_spawn_biomes")
    val SEAHORSE_SPAWN_BIOMES = create("seahorse_spawn_biomes")
    val SUNFISH_SPAWN_BIOMES = create("sunfish_spawn_biomes")
    val BLUE_SPOTTED_STINGRAY_SPAWN_BIOMES = create("blue_spotted_stingray_spawn_biomes")
    val LIONFISH_SPAWN_BIOMES = create("lionfish_spawn_biomes")
    val MOON_JELLY_SPAWN_BIOMES = create("moon_jelly_spawn_biomes")
    val OARFISH_SPAWN_BIOMES = create("oarfish_spawn_biomes")
    val ANGLERFISH_SPAWN_BIOMES = create("anglerfish_spawn_biomes")
    val TOADFISH_SPAWN_BIOMES = create("toadfish_spawn_biomes")
    val STONEFISH_SPAWN_BIOMES = create("stonefish_spawn_biomes")
    val SEA_ANGEL_SPAWN_BIOMES = create("sea_angel_spawn_biomes")
    val DRAGONFISH_SPAWN_BIOMES = create("dragonfish_spawn_biomes")
    val UNICORN_FISH_SPAWN_BIOMES = create("unicorn_fish_spawn_biomes")
    val ROCKFISH_SPAWN_BIOMES = create("rockfish_spawn_biomes")
    val MORAY_EEL_SPAWN_BIOMES = create("moray_eel_spawn_biomes")
    val OPAH_SPAWN_BIOMES = create("opah_spawn_biomes")
    val VAMPIRE_SQUID_SPAWN_BIOMES = create("vampire_squid_spawn_biomes")
    val FLASHLIGHT_FISH_SPAWN_BIOMES = create("flashlight_fish_spawn_biomes")
    val FIREFLY_SQUID_SPAWN_BIOMES = create("firefly_squid_spawn_biomes")
    val CUTTLEFISH_SPAWN_BIOMES = create("cuttlefish_spawn_biomes")
    val GLOWING_SUCKER_OCTOPUS_SPAWN_BIOMES = create("glowing_sucker_octopus_spawn_biomes")
    val TRIGGERFISH_SPAWN_BIOMES = create("triggerfish_spawn_biomes")
    val TIGER_BARB_SPAWN_BIOMES = create("tiger_barb_spawn_biomes")
    val OSCAR_SPAWN_BIOMES = create("oscar_spawn_biomes")
    val ZEBRA_DANIO_SPAWN_BIOMES = create("zebra_danio_spawn_biomes")
    val GOURAMI_SPAWN_BIOMES = create("gourami_spawn_biomes")
    val PIRANHA_SPAWN_BIOMES = create("piranha_spawn_biomes")
    val DISCUS_SPAWN_BIOMES = create("discus_spawn_biomes")
    val TETRA_SPAWN_BIOMES = create("tetra_spawn_biomes")
    val NAUTILUS_SPAWN_BIOMES = create("nautilus_spawn_biomes")
    val BETTA_SPAWN_BIOMES = create("betta_spawn_biomes")
    val NEEDLEFISH_SPAWN_BIOMES = create("needlefish_spawn_biomes")
    val GREAT_WHITE_SHARK_SPAWN_BIOMES = create("great_white_shark_spawn_biomes")
    val TIGER_SHARK_SPAWN_BIOMES = create("tiger_shark_spawn_biomes")
    val HAMMERHEAD_SHARK_SPAWN_BIOMES = create("hammerhead_shark_spawn_biomes")
    val FRILLED_SHARK_SPAWN_BIOMES = create("frilled_shark_spawn_biomes")
    val THRESHER_SHARK_SPAWN_BIOMES = create("thresher_shark_spawn_biomes")
    val BULL_SHARK_SPAWN_BIOMES = create("bull_shark_spawn_biomes")
    val WHALE_SHARK_SPAWN_BIOMES = create("whale_shark_spawn_biomes")
    val BASKING_SHARK_SPAWN_BIOMES = create("basking_shark_spawn_biomes")
    val CRAB_SPAWN_BIOMES = create("crab_spawn_biomes")
    val FIDDLER_CRAB_SPAWN_BIOMES = create("fiddler_crab_spawn_biomes")
    val HERMIT_CRAB_SPAWN_BIOMES = create("hermit_crab_spawn_biomes")
    val STARFISH_SPAWN_BIOMES = create("starfish_spawn_biomes")
    val SEA_CUCUMBER_SPAWN_BIOMES = create("sea_cucumber_spawn_biomes")
    val NUDIBRANCH_SPAWN_BIOMES = create("nudibranch_spawn_biomes")
    val SEA_URCHIN_SPAWN_BIOMES = create("sea_urchin_spawn_biomes")
    val GIANT_CLAM_SPAWN_BIOMES = create("giant_clam_spawn_biomes")

    private fun create(id: String): TagKey<Biome> {
        return TagKey.of(RegistryKeys.BIOME, Identifier(HybridAquatic.MOD_ID, id))
    }
}