package dev.hybridlabs.aquatic.entity.fish.ray

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class StingrayEntity(entityType: EntityType<out StingrayEntity>, world: World) :
    HybridAquaticRayEntity(entityType, world, variants = hashMapOf(
        "spotted_eagle" to RayVariant.biomeVariant("spotted_eagle", HybridAquaticBiomeTags.STINGRAY_SPAWN_BIOMES),
        "blue_spotted" to RayVariant.biomeVariant("blue_spotted", HybridAquaticBiomeTags.STINGRAY_SPAWN_BIOMES),),
        HybridAquaticEntityTags.STINGRAY_PREY, HybridAquaticEntityTags.STINGRAY_PREDATOR) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "blue_spotted" -> Identifier("hybrid-aquatic", "gameplay/blue_spotted_stingray")
            else -> super.getLootTableId()
        }
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}