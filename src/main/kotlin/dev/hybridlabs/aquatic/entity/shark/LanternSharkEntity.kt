package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class LanternSharkEntity(entityType: EntityType<out LanternSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.SMALL_PREY), false, false) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
        }
    }

    override fun getMaxSize() : Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }
}
