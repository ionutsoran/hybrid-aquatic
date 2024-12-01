package dev.hybridlabs.aquatic.entity.fish.ray

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class StingrayEntity(entityType: EntityType<out StingrayEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "spotted_eagle" to FishVariant.biomeVariant("spotted_eagle", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF)),
        "blue_spotted" to FishVariant.biomeVariant("blue_spotted", listOf(HybridAquaticBiomeTags.REEF)),),
        listOf(HybridAquaticEntityTags.NONE), listOf(HybridAquaticEntityTags.NONE)) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "blue_spotted" -> Identifier("hybrid-aquatic", "gameplay/blue_spotted_stingray")
            "spotted_eagle" -> Identifier("hybrid-aquatic", "gameplay/spotted_eagle_ray")
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