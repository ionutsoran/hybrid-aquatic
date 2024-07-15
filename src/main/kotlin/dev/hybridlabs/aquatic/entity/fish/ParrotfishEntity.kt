package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.world.World

class ParrotfishEntity(entityType: EntityType<out ParrotfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "humphead" to FishVariant.biomeVariant("humphead", HybridAquaticBiomeTags.PARROTFISH_SPAWN_BIOMES),),
        HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {

    public override fun getLootTableId(): Identifier {
        return when (this.variant?.variantName) {
            "blue_tang" -> Identifier("hybrid-aquatic", "gameplay/blue_tang")
            "sohal" -> Identifier("hybrid-aquatic", "gameplay/sohal")
            "orangeshoulder" -> Identifier("hybrid-aquatic", "gameplay/orangeshoulder")
            "unicornfish" -> Identifier("hybrid-aquatic", "gameplay/unicornfish")
            else -> super.getLootTableId()
        }
    }

    override fun tick() {
        super.tick()

        return when (this.variant?.variantName) {
            "powder_blue_tang" -> attributes.getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 1.5
            "yellow_tang" -> attributes.getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 1.5

            else -> attributes.getCustomInstance(EntityAttributes.GENERIC_MAX_HEALTH)?.baseValue = 4.0
        }
    }

    override fun getLimitPerChunk(): Int {
        return 4
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
}