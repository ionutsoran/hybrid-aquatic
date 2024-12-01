package dev.hybridlabs.aquatic.entity.fish.ray

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.BreatheAirGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class MantaRayEntity(entityType: EntityType<out MantaRayEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, emptyMap(),
        listOf(HybridAquaticEntityTags.NONE), listOf(HybridAquaticEntityTags.NONE)) {

    //#region Air & Jumping
    override fun initGoals() {
        super.initGoals()
        goalSelector.add(2, BreatheAirGoal(this))
        goalSelector.add(5, FishJumpGoal(this, 10))
    }

    init {
        this.air = 600
    }

    override fun getMaxAir(): Int {
        return 1200
    }

    override fun getAir(): Int {
        return super.getAir().coerceAtLeast(0)
    }

    override fun tick() {
        super.tick()

        if (this.isSubmergedInWater) {
            this.air = (this.air - 1).coerceAtLeast(0)

        } else {
            this.air = this.maxAir
        }
    }

    //#endregion
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.7)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}