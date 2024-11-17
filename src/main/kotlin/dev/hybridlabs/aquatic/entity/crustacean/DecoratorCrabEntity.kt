package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class DecoratorCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
    HybridAquaticCrustaceanEntity(entityType, world, true, false, variants = hashMapOf(
        "fire" to CrustaceanVariant.biomeVariant("fire", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "tube" to CrustaceanVariant.biomeVariant("tube", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "brain" to CrustaceanVariant.biomeVariant("brain", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "bubble" to CrustaceanVariant.biomeVariant("bubble", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "horn" to CrustaceanVariant.biomeVariant("horn", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "thorn" to CrustaceanVariant.biomeVariant("thorn", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),
        "lophelia" to CrustaceanVariant.biomeVariant("lophelia", HybridAquaticBiomeTags.REEF,
            ignore = listOf(CrustaceanVariant.Ignore.MODEL, CrustaceanVariant.Ignore.ANIMATION)),)) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "fire" -> Identifier("hybrid-aquatic", "gameplay/decorator_fire")
            "tube" -> Identifier("hybrid-aquatic", "gameplay/decorator_tube")
            "brain" -> Identifier("hybrid-aquatic", "gameplay/decorator_brain")
            "bubble" -> Identifier("hybrid-aquatic", "gameplay/decorator_bubble")
            "horn" -> Identifier("hybrid-aquatic", "gameplay/decorator_horn")
            "thorn" -> Identifier("hybrid-aquatic", "gameplay/decorator_thorn")
            "lophelia" -> Identifier("hybrid-aquatic", "gameplay/decorator_lophelia")
            else -> super.getLootTableId()
        }
    }

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