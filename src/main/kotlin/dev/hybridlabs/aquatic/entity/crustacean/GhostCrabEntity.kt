package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class GhostCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
    HybridAquaticCrustaceanEntity(entityType, world, false, false, true, variants = hashMapOf(
        "purple" to CrustaceanVariant.biomeVariant("purple", HybridAquaticBiomeTags.SANDY_BEACHES,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "yellow" to CrustaceanVariant.biomeVariant("yellow", HybridAquaticBiomeTags.SANDY_BEACHES,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "red" to CrustaceanVariant.biomeVariant("red", HybridAquaticBiomeTags.SANDY_BEACHES,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "white" to CrustaceanVariant.biomeVariant("white", HybridAquaticBiomeTags.SANDY_BEACHES,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)))) {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
    }

    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}