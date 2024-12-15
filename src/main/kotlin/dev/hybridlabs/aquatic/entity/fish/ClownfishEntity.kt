package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

class ClownfishEntity(entityType: EntityType<out ClownfishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, variants = hashMapOf(
        "ocellaris" to FishVariant.biomeVariant("ocellaris", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "percula" to FishVariant.biomeVariant("percula", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "whiteband" to FishVariant.biomeVariant("whiteband", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "tomato" to FishVariant.biomeVariant("tomato", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "cinnamon" to FishVariant.biomeVariant("cinnamon", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "clarkii" to FishVariant.biomeVariant("clarkii", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "pink_skunk" to FishVariant.biomeVariant("pink_skunk", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        "orange_skunk" to FishVariant.biomeVariant("orange_skunk", listOf(HybridAquaticBiomeTags.REEF),
            ignore = listOf(FishVariant.Ignore.ANIMATION)),
        ),
        listOf(
            HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK)) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    public override fun getLootTableId(): Identifier {
        return Identifier("hybrid-aquatic", "entities/clownfish")
    }

    private var targetAnemonePos: BlockPos? = null

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }

    override fun tick() {
        super.tick()

        if (targetAnemonePos == null || world.getBlockState(targetAnemonePos).isOf(HybridAquaticBlocks.ANEMONE)) {
            targetAnemonePos = findNearbyAnemone()
        }

        if (targetAnemonePos != null) {
            val distanceToAnemone = distanceTo(targetAnemonePos!!)

            if (distanceToAnemone > 5.0) {
                navigateToAnemone(targetAnemonePos!!)
            }
        }
    }

    private fun navigateToAnemone(ventBlockPos: BlockPos) {
        this.navigation.startMovingTo(ventBlockPos.x.toDouble(), ventBlockPos.y.toDouble(), ventBlockPos.z.toDouble(), this.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED))
    }

    private fun findNearbyAnemone(): BlockPos? {
        for (i in -5..5) {
            for (j in -5..5) {
                for (k in -5..5) {
                    val blockPos = BlockPos((x + i).toInt(), (y + j).toInt(), (z + k).toInt())
                    val blockState = world.getBlockState(blockPos)

                    if (blockState.isOf(HybridAquaticBlocks.ANEMONE)) {
                        return blockPos
                    }
                }
            }
        }
        return null
    }

    private fun distanceTo(pos: BlockPos): Double {
        return this.blockPos.getSquaredDistance(pos.x.toDouble(), pos.y.toDouble(), pos.z.toDouble())
    }
}