package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.entity.shark.HybridAquaticSharkEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext
import net.minecraft.entity.EntityType
import net.minecraft.entity.SpawnGroup
import net.minecraft.entity.SpawnRestriction
import net.minecraft.registry.tag.TagKey
import net.minecraft.world.Heightmap
import net.minecraft.world.biome.Biome

object HybridAquaticSpawningRegistry {

    fun register() {
        createFishSpawn(HybridAquaticEntityTypes.CLOWNFISH, HybridAquaticBiomeTags.CLOWNFISH_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.YELLOWFIN_TUNA, HybridAquaticBiomeTags.YELLOWFIN_SPAWN_BIOMES, 15, 2, 4)
        createFishSpawn(HybridAquaticEntityTypes.MAHIMAHI, HybridAquaticBiomeTags.MAHIMAHI_SPAWN_BIOMES, 15, 2, 4)
        createFishSpawn(HybridAquaticEntityTypes.BLUE_TANG, HybridAquaticBiomeTags.BLUE_TANG_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.COWFISH, HybridAquaticBiomeTags.COWFISH_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.SEAHORSE, HybridAquaticBiomeTags.SEAHORSE_SPAWN_BIOMES, 15, 1, 3)
        createFishSpawn(HybridAquaticEntityTypes.SUNFISH, HybridAquaticBiomeTags.SUNFISH_SPAWN_BIOMES, 10, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.BLUE_SPOTTED_STINGRAY, HybridAquaticBiomeTags.BLUE_SPOTTED_STINGRAY_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.LIONFISH, HybridAquaticBiomeTags.LIONFISH_SPAWN_BIOMES, 15, 1, 3)
        createFishSpawn(HybridAquaticEntityTypes.ANGLERFISH, HybridAquaticBiomeTags.ANGLERFISH_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.MOON_JELLY, HybridAquaticBiomeTags.MOON_JELLY_SPAWN_BIOMES, 15, 2, 4)
        createFishSpawn(HybridAquaticEntityTypes.TOADFISH, HybridAquaticBiomeTags.TOADFISH_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.OARFISH, HybridAquaticBiomeTags.OARFISH_SPAWN_BIOMES, 10, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.UNICORN_FISH, HybridAquaticBiomeTags.UNICORN_FISH_SPAWN_BIOMES, 15, 1, 3)
        createFishSpawn(HybridAquaticEntityTypes.SEA_ANGEL, HybridAquaticBiomeTags.SEA_ANGEL_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.STONEFISH, HybridAquaticBiomeTags.STONEFISH_SPAWN_BIOMES, 15, 1, 1)
        createFishSpawn(HybridAquaticEntityTypes.ROCKFISH, HybridAquaticBiomeTags.ROCKFISH_SPAWN_BIOMES, 15, 1, 3)
        createFishSpawn(HybridAquaticEntityTypes.MORAY_EEL, HybridAquaticBiomeTags.MORAY_EEL_SPAWN_BIOMES, 15, 1, 1)
        createFishSpawn(HybridAquaticEntityTypes.FLASHLIGHT_FISH, HybridAquaticBiomeTags.FLASHLIGHT_FISH_SPAWN_BIOMES, 15, 2, 4)
        createFishSpawn(HybridAquaticEntityTypes.FIREFLY_SQUID, HybridAquaticBiomeTags.FIREFLY_SQUID_SPAWN_BIOMES, 15, 2, 3)
        createFishSpawn(HybridAquaticEntityTypes.OPAH, HybridAquaticBiomeTags.OPAH_SPAWN_BIOMES, 15, 1, 3)
        createFishSpawn(HybridAquaticEntityTypes.CUTTLEFISH, HybridAquaticBiomeTags.CUTTLEFISH_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.VAMPIRE_SQUID, HybridAquaticBiomeTags.VAMPIRE_SQUID_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.GLOWING_SUCKER_OCTOPUS, HybridAquaticBiomeTags.GLOWING_SUCKER_OCTOPUS_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.TRIGGERFISH, HybridAquaticBiomeTags.TRIGGERFISH_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.TIGER_BARB, HybridAquaticBiomeTags.TIGER_BARB_SPAWN_BIOMES, 15, 2, 3)
        createFishSpawn(HybridAquaticEntityTypes.PIRANHA, HybridAquaticBiomeTags.PIRANHA_SPAWN_BIOMES, 10, 2, 3)
        createFishSpawn(HybridAquaticEntityTypes.OSCAR, HybridAquaticBiomeTags.OSCAR_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.GOURAMI, HybridAquaticBiomeTags.GOURAMI_SPAWN_BIOMES, 15, 1, 2)
        createFishSpawn(HybridAquaticEntityTypes.ZEBRA_DANIO, HybridAquaticBiomeTags.ZEBRA_DANIO_SPAWN_BIOMES, 10, 2, 3)
        createFishSpawn(HybridAquaticEntityTypes.DISCUS, HybridAquaticBiomeTags.DISCUS_SPAWN_BIOMES, 10, 1, 2)
        createSharkSpawn(HybridAquaticEntityTypes.GREAT_WHITE_SHARK, HybridAquaticBiomeTags.GREAT_WHITE_SHARK_SPAWN_BIOMES, 10, 1, 2)
        createSharkSpawn(HybridAquaticEntityTypes.TIGER_SHARK, HybridAquaticBiomeTags.TIGER_SHARK_SPAWN_BIOMES, 10, 1, 2)
        createSharkSpawn(HybridAquaticEntityTypes.HAMMERHEAD_SHARK, HybridAquaticBiomeTags.HAMMERHEAD_SHARK_SPAWN_BIOMES, 10, 1, 2)
        createSharkSpawn(HybridAquaticEntityTypes.FRILLED_SHARK, HybridAquaticBiomeTags.FRILLED_SHARK_SPAWN_BIOMES, 10, 1, 2)
        createSharkSpawn(HybridAquaticEntityTypes.THRESHER_SHARK, HybridAquaticBiomeTags.THRESHER_SHARK_SPAWN_BIOMES, 10, 1, 2)
        createSharkSpawn(HybridAquaticEntityTypes.BULL_SHARK, HybridAquaticBiomeTags.BULL_SHARK_SPAWN_BIOMES, 10, 1, 2)
        createSharkSpawn(HybridAquaticEntityTypes.WHALE_SHARK, HybridAquaticBiomeTags.WHALE_SHARK_SPAWN_BIOMES, 10, 1, 2)
        createSharkSpawn(HybridAquaticEntityTypes.BASKING_SHARK, HybridAquaticBiomeTags.BASKING_SHARK_SPAWN_BIOMES, 10, 1, 2)
    }

    private inline fun <reified T : HybridAquaticFishEntity> createFishSpawn(entityType: EntityType<T>, spawnTag: TagKey<Biome>, weight: Int, minGroup: Int, maxGroup: Int, ) {
        BiomeModifications.addSpawn({
            ctx: BiomeSelectionContext -> ctx.hasTag(spawnTag)
        }, SpawnGroup.WATER_AMBIENT, entityType, weight, minGroup, maxGroup)

        SpawnRestriction.register(
            entityType, SpawnRestriction.Location.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HybridAquaticFishEntity::canSpawnPredicate
        )
    }
    private inline fun <reified T : HybridAquaticSharkEntity> createSharkSpawn(entityType: EntityType<T>, spawnTag: TagKey<Biome>, weight: Int, minGroup: Int, maxGroup: Int, ) {
        BiomeModifications.addSpawn({
                ctx: BiomeSelectionContext -> ctx.hasTag(spawnTag)
        }, SpawnGroup.WATER_CREATURE, entityType, weight, minGroup, maxGroup)

        SpawnRestriction.register(
            entityType, SpawnRestriction.Location.IN_WATER,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, HybridAquaticSharkEntity::canSpawnPredicate
        )
    }
}