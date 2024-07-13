package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class CarpEntity(entityType: EntityType<out CarpEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "koi_ai_goromo" to FishVariant.biomeVariant("koi_ai_goromo", HybridAquaticBiomeTags.KOI_SPAWN_BIOMES),
        "koi_hajiro" to FishVariant.biomeVariant("koi_hajiro", HybridAquaticBiomeTags.KOI_SPAWN_BIOMES),
        "koi_platinum" to FishVariant.biomeVariant("koi_platinum", HybridAquaticBiomeTags.KOI_SPAWN_BIOMES),
        "koi_tancho" to FishVariant.biomeVariant("koi_tancho", HybridAquaticBiomeTags.KOI_SPAWN_BIOMES),),
        HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}