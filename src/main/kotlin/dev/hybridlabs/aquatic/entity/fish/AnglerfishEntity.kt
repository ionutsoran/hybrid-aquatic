package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.entity.miniboss.ManglerfishEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.Goal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.PathAwareEntity
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class AnglerfishEntity(entityType: EntityType<out AnglerfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, emptyMap(),
        listOf(
            HybridAquaticEntityTags.SMALL_PREY),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK)) {

    override fun getLimitPerChunk(): Int {
        return 3
    }

    override fun initGoals() {
        goalSelector.add(2, FollowManglerfishGoal(this, 1.5, 4.0F, 8.0F))
        targetSelector.add(1, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasStatusEffect(HybridAquaticStatusEffects.THALASSOPHOBIA) })
        super.initGoals()
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.4)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_SPEED, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
        }
    }

    internal class FollowManglerfishGoal(
        private val mob: PathAwareEntity,
        private val speed: Double,
        private val minDistance: Float,
        private val maxDistance: Float
    ) : Goal() {

        private var target: ManglerfishEntity? = null

        override fun canStart(): Boolean {
            val closestManglerfish = mob.world.getEntitiesByClass(
                ManglerfishEntity::class.java,
                mob.boundingBox.expand(maxDistance.toDouble())
            ) { true }
                .minByOrNull { it.squaredDistanceTo(mob) }

            if (closestManglerfish != null && closestManglerfish.squaredDistanceTo(mob) > (minDistance * minDistance)) {
                target = closestManglerfish
                return true
            }
            return false
        }

        override fun shouldContinue(): Boolean {
            return target != null && target!!.isAlive && mob.squaredDistanceTo(target!!) > (minDistance * minDistance)
        }

        override fun start() {
            mob.navigation.startMovingTo(target, speed)
        }

        override fun stop() {
            target = null
            mob.navigation.stop()
        }

        override fun tick() {
            target?.let {
                mob.navigation.startMovingTo(it, speed)
            }
        }
    }
}