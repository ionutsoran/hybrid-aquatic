package dev.hybridlabs.aquatic.client.render.entity.critter

import dev.hybridlabs.aquatic.client.model.entity.SeaUrchinEntityModel
import dev.hybridlabs.aquatic.entity.critter.HybridAquaticCritterEntity
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import net.minecraft.client.util.math.MatrixStack

class SeaUrchinEntityRenderer(context: Context) : HybridAquaticCritterEntityRenderer<HybridAquaticCritterEntity>(context, SeaUrchinEntityModel()) {
    override fun render(
        entity: HybridAquaticCritterEntity?,
        entityYaw: Float,
        partialTick: Float,
        poseStack: MatrixStack?,
        bufferSource: VertexConsumerProvider?,
        packedLight: Int
    ) {
        val size = HybridAquaticCritterEntity.getScaleAdjustment(entity!!, 0.05f)
        poseStack!!.scale(size, size, size)
        super.render(entity, entityYaw, partialTick, poseStack, bufferSource, packedLight)
    }
}