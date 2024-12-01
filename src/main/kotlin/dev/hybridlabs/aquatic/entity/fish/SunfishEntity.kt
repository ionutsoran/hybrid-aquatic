package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.BreatheAirGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class SunfishEntity(entityType: EntityType<out SunfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "ocean" to FishVariant.biomeVariant("ocean", listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS, HybridAquaticBiomeTags.TROPICAL_OCEANS)),
        "hoodwinker" to FishVariant.biomeVariant("hoodwinker", listOf(HybridAquaticBiomeTags.TROPICAL_OCEANS)),
        "sharptail" to FishVariant.biomeVariant("sharptail", listOf(HybridAquaticBiomeTags.TEMPERATE_OCEANS))),
        listOf(
            HybridAquaticEntityTags.JELLYFISH),
        listOf(
            HybridAquaticEntityTags.SHARK)) {

    public override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/sunfish")
    }

    override fun getLimitPerChunk(): Int {
        return 2
    }

    //#region Air & Jumping
    override fun initGoals() {
        super.initGoals()
        goalSelector.add(2, BreatheAirGoal(this))
        goalSelector.add(5, FishJumpGoal(this, 10))
    }

    init {
        this.air = 1200
    }

    override fun getMaxAir(): Int {
        return 3800
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
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 16.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 10.0)
        }
    }
}