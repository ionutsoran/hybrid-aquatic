package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World

class SnailfishEntity(entityType: EntityType<out SnailfishEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world, emptyMap(),
        listOf(
            HybridAquaticEntityTags.NONE
        ),
        listOf(
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun getLimitPerChunk(): Int {
        return 3
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 1.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }
}