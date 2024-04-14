package dev.hybridlabs.aquatic.entity.crustacean

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class ShrimpEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
    HybridAquaticCrustaceanEntity(entityType, world, 20, false, false, false) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
        }
    }
    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}