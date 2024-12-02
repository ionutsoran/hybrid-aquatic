package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.entity.ai.goal.SharkJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.BreatheAirGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.world.Difficulty
import net.minecraft.world.World

class ThresherSharkEntity(entityType: EntityType<out ThresherSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.SMALL_PREY, HybridAquaticEntityTags.MEDIUM_PREY), false, false) {

    //#region Air & Jumping
    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, RevengeGoal(this))
        goalSelector.add(2, BreatheAirGoal(this))
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
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 36.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.8)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 26.0)
        }
    }

    override fun tryAttack(target: Entity?): Boolean {
        if (super.tryAttack(target)) {

            playSound(SoundEvents.ENTITY_FOX_BITE,1.0F,0.0F)

            if (target is LivingEntity) {
                var i = 0
                if (world.difficulty == Difficulty.NORMAL) {
                    i = 7
                } else if (world.difficulty == Difficulty.HARD) {
                    i = 15
                }

                if (i > 0) {
                    target.addStatusEffect(StatusEffectInstance(HybridAquaticStatusEffects.BLEEDING, i * 20, 0), this)
                }
            }

            return true
        } else {
            return false
        }
    }

    override fun getMaxSize(): Int {
        return 2
    }

    override fun getMinSize(): Int {
        return -2
    }
}
