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
        // small prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.SMALL_PREY)
            .add(
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.COWFISH,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.MACKEREL,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.SURGEONFISH,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLY,
                HybridAquaticEntityTypes.FLYING_FISH,
                EntityType.SALMON,
                EntityType.COD,
                EntityType.TROPICAL_FISH
            )

        // medium prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.MEDIUM_PREY)
            .add(
                HybridAquaticEntityTypes.RATFISH,
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.SEA_BASS,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.PARROTFISH,
                HybridAquaticEntityTypes.MORAY_EEL,
            )

        // large prey
        getOrCreateTagBuilder(HybridAquaticEntityTags.LARGE_PREY)
            .add(
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MAHI,
                EntityType.PLAYER,
                EntityType.TURTLE,
            )

        // crustaceans
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRUSTACEAN)
            .add(
                HybridAquaticEntityTypes.COCONUT_CRAB,
                HybridAquaticEntityTypes.CRAYFISH,
                HybridAquaticEntityTypes.DECORATOR_CRAB,
                HybridAquaticEntityTypes.DUNGENESS_CRAB,
                HybridAquaticEntityTypes.FIDDLER_CRAB,
                HybridAquaticEntityTypes.FLOWER_CRAB,
                HybridAquaticEntityTypes.GHOST_CRAB,
                HybridAquaticEntityTypes.GIANT_ISOPOD,
                HybridAquaticEntityTypes.HERMIT_CRAB,
                HybridAquaticEntityTypes.HORSESHOE_CRAB,
                HybridAquaticEntityTypes.LIGHTFOOT_CRAB,
                HybridAquaticEntityTypes.LOBSTER,
                HybridAquaticEntityTypes.SHRIMP,
                HybridAquaticEntityTypes.SPIDER_CRAB,
                HybridAquaticEntityTypes.VAMPIRE_CRAB,
                HybridAquaticEntityTypes.YETI_CRAB,
            )

        // cephalopods
        getOrCreateTagBuilder(HybridAquaticEntityTags.CEPHALOPOD)
            .add(
                HybridAquaticEntityTypes.CUTTLEFISH,
                HybridAquaticEntityTypes.FIREFLY_SQUID,
                HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS,
                HybridAquaticEntityTypes.NAUTILUS,
                HybridAquaticEntityTypes.UMBRELLA_OCTOPUS,
                HybridAquaticEntityTypes.VAMPIRE_SQUID,
                EntityType.SQUID,
                EntityType.GLOW_SQUID
            )

        getOrCreateTagBuilder(HybridAquaticEntityTags.NONE)

        // sharks
        getOrCreateTagBuilder(HybridAquaticEntityTags.SHARK)
            .add(
                HybridAquaticEntityTypes.BASKING_SHARK,
                HybridAquaticEntityTypes.BULL_SHARK,
                HybridAquaticEntityTypes.FRILLED_SHARK,
                HybridAquaticEntityTypes.GREAT_WHITE_SHARK,
                HybridAquaticEntityTypes.HAMMERHEAD_SHARK,
                HybridAquaticEntityTypes.THRESHER_SHARK,
                HybridAquaticEntityTypes.TIGER_SHARK,
                HybridAquaticEntityTypes.WHALE_SHARK,
            )

        // critters
        getOrCreateTagBuilder(HybridAquaticEntityTags.CRITTER)
            .add(
                HybridAquaticEntityTypes.NUDIBRANCH,
                HybridAquaticEntityTypes.SEA_CUCUMBER,
                HybridAquaticEntityTypes.SEA_URCHIN,
                HybridAquaticEntityTypes.STARFISH,
                HybridAquaticEntityTypes.SEA_ANGEL,
            )

        // jellyfish
        getOrCreateTagBuilder(HybridAquaticEntityTags.JELLYFISH)
            .add(
                HybridAquaticEntityTypes.ATOLLA_JELLYFISH,
                HybridAquaticEntityTypes.BARREL_JELLYFISH,
                HybridAquaticEntityTypes.BLUE_JELLYFISH,
                HybridAquaticEntityTypes.BIG_RED_JELLYFISH,
                HybridAquaticEntityTypes.CAULIFLOWER_JELLYFISH,
                HybridAquaticEntityTypes.COMPASS_JELLYFISH,
                HybridAquaticEntityTypes.COSMIC_JELLYFISH,
                HybridAquaticEntityTypes.FRIED_EGG_JELLYFISH,
                HybridAquaticEntityTypes.LIONS_MANE_JELLYFISH,
                HybridAquaticEntityTypes.MAUVE_STINGER,
                HybridAquaticEntityTypes.MOON_JELLYFISH,
                HybridAquaticEntityTypes.NOMURA_JELLYFISH,
                HybridAquaticEntityTypes.SEA_NETTLE,
            )

        // fish
        getOrCreateTagBuilder(HybridAquaticEntityTags.FISH)
            .add(
                HybridAquaticEntityTypes.AFRICAN_BUTTERFLY,
                HybridAquaticEntityTypes.BETTA,
                HybridAquaticEntityTypes.CARP,
                HybridAquaticEntityTypes.CLOWNFISH,
                HybridAquaticEntityTypes.COWFISH,
                HybridAquaticEntityTypes.DANIO,
                HybridAquaticEntityTypes.DISCUS,
                HybridAquaticEntityTypes.FLASHLIGHT_FISH,
                HybridAquaticEntityTypes.FLYING_FISH,
                HybridAquaticEntityTypes.GOLDFISH,
                HybridAquaticEntityTypes.GOURAMI,
                HybridAquaticEntityTypes.LIONFISH,
                HybridAquaticEntityTypes.MACKEREL,
                HybridAquaticEntityTypes.MAHI,
                HybridAquaticEntityTypes.MORAY_EEL,
                HybridAquaticEntityTypes.NEEDLEFISH,
                HybridAquaticEntityTypes.OARFISH,
                HybridAquaticEntityTypes.OPAH,
                HybridAquaticEntityTypes.OSCAR,
                HybridAquaticEntityTypes.PARROTFISH,
                HybridAquaticEntityTypes.PIRANHA,
                HybridAquaticEntityTypes.ROCKFISH,
                HybridAquaticEntityTypes.SEA_BASS,
                HybridAquaticEntityTypes.SEAHORSE,
                HybridAquaticEntityTypes.SUNFISH,
                HybridAquaticEntityTypes.SURGEONFISH,
                HybridAquaticEntityTypes.TETRA,
                HybridAquaticEntityTypes.TIGER_BARB,
                HybridAquaticEntityTypes.TRIGGERFISH,
                HybridAquaticEntityTypes.TUNA,
                HybridAquaticEntityTypes.MANTA_RAY,
                HybridAquaticEntityTypes.STINGRAY,
                HybridAquaticEntityTypes.ANGLERFISH,
                HybridAquaticEntityTypes.BARRELEYE,
                HybridAquaticEntityTypes.DRAGONFISH,
                HybridAquaticEntityTypes.RATFISH,
            )

        // entities that you can catch with the fishing net
        getOrCreateTagBuilder(HybridAquaticEntityTags.CAN_USE_FISHING_NET_ON)
            .addTag(HybridAquaticEntityTags.JELLYFISH)
            .addTag(HybridAquaticEntityTags.CRITTER)
            .addTag(HybridAquaticEntityTags.CRUSTACEAN)
            .addTag(HybridAquaticEntityTags.FISH)
    }
}
