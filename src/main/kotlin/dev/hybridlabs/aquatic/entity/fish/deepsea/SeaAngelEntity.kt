package dev.hybridlabs.aquatic.entity.fish.deepsea

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class SeaAngelEntity(entityType: EntityType<out SeaAngelEntity>, world: World) :
    HybridAquaticDeepSeaFishEntity(entityType, world, emptyMap(), HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }
}