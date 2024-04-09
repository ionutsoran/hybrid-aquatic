package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.entity.EntityType
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // prey source
        getOrCreateTagBuilder(HybridAquaticEntityTags.SMALL_PREY)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.COWFISH,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.UNICORN_FISH,
                HybridAquaticEntityTypes.ZEBRA_DANIO,
                HybridAquaticEntityTypes.SEAHORSE,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.BLUE_TANG,
                HybridAquaticEntityTypes.NAUTILUS,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.DUNGENESS_CRAB,
                HybridAquaticEntityTypes.FIDDLER_CRAB,
                HybridAquaticEntityTypes.HERMIT_CRAB,
                HybridAquaticEntityTypes.GHOST_CRAB,
                HybridAquaticEntityTypes.VAMPIRE_CRAB,
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
                HybridAquaticEntityTypes.SPIDER_CRAB,
                HybridAquaticEntityTypes.YETI_CRAB,
                HybridAquaticEntityTypes.HORSESHOE_CRAB,
                HybridAquaticEntityTypes.FLOWER_CRAB,
                HybridAquaticEntityTypes.GIANT_ISOPOD,
                HybridAquaticEntityTypes.SHRIMP,
                HybridAquaticEntityTypes.CRAYFISH,
                HybridAquaticEntityTypes.LOBSTER,
                HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY,
                HybridAquaticEntityTypes.VAMPIRE_SQUID,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.DRAGONFISH,
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
                HybridAquaticEntityTypes.STONEFISH,
                HybridAquaticEntityTypes.TOADFISH,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.SEA_ANGEL,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.MEDIUM_PREY)
            .add(
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.YELLOWFIN_TUNA,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.MAHIMAHI,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.NEEDLEFISH,
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_PREY)
            .add(
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.OARFISH,
                EntityType.TURTLE,
                EntityType.COW,
                EntityType.PIG,
                EntityType.SHEEP,
            )

        // prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.NONE)

        getOrCreateTagBuilder(HybridAquaticEntityTags.BULL_SHARK_PREY)
            .addTag(HybridAquaticEntityTags.MEDIUM_PREY)
            .addTag(HybridAquaticEntityTags.LARGE_PREY)

        getOrCreateTagBuilder(HybridAquaticEntityTags.FRILLED_SHARK_PREY)
            .addTag(HybridAquaticEntityTags.SMALL_PREY)

        getOrCreateTagBuilder(HybridAquaticEntityTags.GREAT_WHITE_SHARK_PREY)
            .addTag(HybridAquaticEntityTags.MEDIUM_PREY)
            .addTag(HybridAquaticEntityTags.LARGE_PREY)

        getOrCreateTagBuilder(HybridAquaticEntityTags.HAMMERHEAD_SHARK_PREY)
            .addTag(HybridAquaticEntityTags.SMALL_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)

        getOrCreateTagBuilder(HybridAquaticEntityTags.THRESHER_SHARK_PREY)
            .addTag(HybridAquaticEntityTags.SMALL_PREY)

        getOrCreateTagBuilder(HybridAquaticEntityTags.TIGER_SHARK_PREY)
            .addTag(HybridAquaticEntityTags.MEDIUM_PREY)
            .addTag(HybridAquaticEntityTags.LARGE_PREY)

        getOrCreateTagBuilder(HybridAquaticEntityTags.STINGRAY_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)

        getOrCreateTagBuilder(HybridAquaticEntityTags.LIONFISH_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.BLUE_TANG,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.OPAH_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.OARFISH_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.CUTTLEFISH_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)

        getOrCreateTagBuilder(HybridAquaticEntityTags.FIREFLY_SQUID_PREY)
            .addTag(HybridAquaticEntityTags.SHRIMP)

        getOrCreateTagBuilder(HybridAquaticEntityTags.TRIGGERFISH_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.SEA_URCHIN,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.SUNFISH_PREY)
            .add(
                HybridAquaticEntityTypes.BARREL_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
                HybridAquaticEntityTypes.COMPASS_JELLYFISH,
                HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
                HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
                HybridAquaticEntityTypes.SEA_NETTLE,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.MORAY_EEL_PREY)
            .add(
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.BLUE_TANG,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.TUNA_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.ANGLERFISH_PREY)
            .add(
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.DRAGONFISH_PREY)
            .add(
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.OCTOPUS_PREY)
            .addTag(HybridAquaticEntityTags.CRAB)
            .addTag(HybridAquaticEntityTags.SHRIMP)

        getOrCreateTagBuilder(HybridAquaticEntityTags.MAHI_PREY)
            .add(
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.NEEDLEFISH_PREY)
            .add(
                HybridAquaticEntityTypes.SHRIMP,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.PIRANHA_PREY)
            .add(
                EntityType.FROG,
                EntityType.CHICKEN,
                EntityType.RABBIT,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.ZEBRA_DANIO,
                HybridAquaticEntityTypes.TIGER_BARB,
            )

        // fishes
        getOrCreateTagBuilder(HybridAquaticEntityTags.FISHES)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.YELLOWFIN_TUNA,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.SEA_ANGEL,
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.VAMPIRE_SQUID,
                HybridAquaticEntityTypes.MAHIMAHI,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.NAUTILUS,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.OSCAR,
                HybridAquaticEntityTypes.UNICORN_FISH,
                HybridAquaticEntityTypes.ZEBRA_DANIO,
                HybridAquaticEntityTypes.TOADFISH,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.STONEFISH,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.SEAHORSE,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.COWFISH,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.DRAGONFISH,
                HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY,
                HybridAquaticEntityTypes.BLUE_TANG
            )

        // sharks
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHARKS)
            .add(
                HybridAquaticEntityTypes.BULL_SHARK,
                HybridAquaticEntityTypes.BASKING_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                HybridAquaticEntityTypes.FRILLED_SHARK,
                HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.WHALE_SHARK,
            )

        // critters
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRAB)
            .add(
                HybridAquaticEntityTypes.DUNGENESS_CRAB,
                HybridAquaticEntityTypes.FIDDLER_CRAB,
                HybridAquaticEntityTypes.HERMIT_CRAB,
                HybridAquaticEntityTypes.GHOST_CRAB,
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
                HybridAquaticEntityTypes.FLOWER_CRAB,
                HybridAquaticEntityTypes.SPIDER_CRAB,
                HybridAquaticEntityTypes.HORSESHOE_CRAB,
                HybridAquaticEntityTypes.GIANT_ISOPOD,
                HybridAquaticEntityTypes.YETI_CRAB,
            )
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHRIMP)
            .add(
                HybridAquaticEntityTypes.SHRIMP,
                HybridAquaticEntityTypes.CRAYFISH,
                HybridAquaticEntityTypes.LOBSTER,
        )

        getOrCreateTagBuilder(HybridAquaticEntityTags.CRITTER)
            .add(
                HybridAquaticEntityTypes.NUDIBRANCH,
                HybridAquaticEntityTypes.SEA_URCHIN,
                HybridAquaticEntityTypes.SEA_CUCUMBER,
                HybridAquaticEntityTypes.STARFISH,
            )

        // jellyfish
        getOrCreateTagBuilder(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
                HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.MAUVE_STINGER,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
                HybridAquaticEntityTypes.STARFISH,
                HybridAquaticEntityTypes.COMPASS_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
            )
    }
}
