package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class HermitCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: World) :
    HybridAquaticCrustaceanEntity(entityType, world, false, false, variants = hashMapOf(
        "shell" to CrustaceanVariant.biomeVariant(
            "shell", HybridAquaticBiomeTags.SANDY_BEACHES,
            ignore = listOf(CrustaceanVariant.Ignore.ANIMATION)
        ),
        "skull" to CrustaceanVariant.biomeVariant(
            "skull", HybridAquaticBiomeTags.SANDY_BEACHES,
            ignore = listOf(CrustaceanVariant.Ignore.ANIMATION)
        ),
    )) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "skull" -> Identifier("hybrid-aquatic", "gameplay/hermit_crab_skull")
            "shell" -> Identifier("hybrid-aquatic", "gameplay/hermit_crab_shell")
            else -> super.getLootTableId()
        }
    }

    private var isHiding: Boolean = false
    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_ARMOR, 5.0)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 5.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    private fun startHiding() {
        isHiding = true
        hidingTimer = 200
    }

    override fun tick() {
        super.tick()

        if (isHiding) {
            hidingTimer--

            if (hidingTimer <= 0 && (world.time - lastDamageTime) >= 200) {
                isHiding = false
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.3
                attributes.getCustomInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 5.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 5.0
            } else {
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)?.baseValue = 100.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 50.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 50.0
            }
        }
    }

    override fun damage(source: net.minecraft.entity.damage.DamageSource?, amount: Float): Boolean {
        if (!isHiding) {
            startHiding()
        }

        lastDamageTime = world.time

        return super.damage(source, amount)
    }
}