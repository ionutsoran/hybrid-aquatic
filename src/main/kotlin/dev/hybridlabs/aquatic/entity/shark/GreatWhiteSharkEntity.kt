package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.goal.SharkJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class GreatWhiteSharkEntity(entityType: EntityType<out GreatWhiteSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.LARGE_PREY), false, true) {

    //#region Air & Jumping
    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, RevengeGoal(this))
        goalSelector.add(5, SharkJumpGoal(this, 10))
    }

    init {
        this.air = 800
    }

    override fun getMaxAir(): Int {
        return 2400
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
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 54.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 26.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return 0
    }
}
