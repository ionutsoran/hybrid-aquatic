package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.world.Difficulty
import net.minecraft.world.World

class BullSharkEntity(entityType: EntityType<out BullSharkEntity>, world: World) :
    HybridAquaticSharkEntity(entityType, world, HybridAquaticEntityTags.BULL_SHARK_PREY, false, true) {

    override fun getLimitPerChunk(): Int {
        return 1
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, RevengeGoal(this))
    }
        companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 36.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0)
        }
    }

    override fun tryAttack(target: Entity?): Boolean {
        if (super.tryAttack(target)) {

            playSound(SoundEvents.ENTITY_FOX_BITE,5.0F,0.0F)

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

    override fun getMaxSize() : Int {
        return 2
    }

    override fun getMinSize(): Int {
        return -2
    }
}
