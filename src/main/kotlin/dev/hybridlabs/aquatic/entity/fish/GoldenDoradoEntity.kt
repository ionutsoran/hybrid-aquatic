package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class GoldenDoradoEntity(entityType: EntityType<out GoldenDoradoEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, emptyMap(),
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.MEDIUM_PREY),
        listOf(
            HybridAquaticEntityTags.SHARK)) {

    override fun getLimitPerChunk(): Int {
        return 1
    }

    override fun initGoals() {
        super.initGoals()
        targetSelector.add(1, RevengeGoal(this, *arrayOfNulls(0)).setGroupRevenge(*arrayOfNulls(0)))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
        }
    }
    override fun speedModifier(): Double {
        return 0.005
    }
}