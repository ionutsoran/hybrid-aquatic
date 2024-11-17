package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class NudibranchEntity(entityType: EntityType<out NudibranchEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world, variants = hashMapOf(
        "pyjama" to CritterVariant.biomeVariant("pyjama", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "bullock" to CritterVariant.biomeVariant("bullock", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "sagami" to CritterVariant.biomeVariant("sagami", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "festiva" to CritterVariant.biomeVariant("festiva", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "magnificent" to CritterVariant.biomeVariant("magnificent", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "kubaryana" to CritterVariant.biomeVariant("kubaryana", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "yonowae" to CritterVariant.biomeVariant("yonowae", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "kuniei" to CritterVariant.biomeVariant("kuniei", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        "baba" to CritterVariant.biomeVariant("baba", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS, HybridAquaticBiomeTags.REEF),
            ignore = listOf(CritterVariant.Ignore.MODEL, CritterVariant.Ignore.ANIMATION)),
        )) {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.1)
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isSubmergedInWater) {
            event.controller.setAnimation(WALK_ANIMATION)
        } else {
            event.controller.setAnimation(FLOP_ANIMATION)
        }
        return PlayState.CONTINUE
    }

    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}