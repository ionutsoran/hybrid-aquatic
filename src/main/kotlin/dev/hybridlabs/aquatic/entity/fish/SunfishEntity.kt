package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState

class SunfishEntity(entityType: EntityType<out SunfishEntity>, world: World) : HybridAquaticFishEntity(entityType, world) {
    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishJumpGoal(this, 10))
    }
    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP))
            return PlayState.CONTINUE
        }

        if (!isSubmergedInWater) {
            event.controller.setAnimation(RawAnimation.begin().then("flop", Animation.LoopType.LOOP))
            return PlayState.CONTINUE
        }

        if (isWet && isFallFlying) {
            event.controller.setAnimation(RawAnimation.begin().then("swim", Animation.LoopType.LOOP))
            return PlayState.CONTINUE
        }
        return PlayState.STOP
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0)
        }
    }
}