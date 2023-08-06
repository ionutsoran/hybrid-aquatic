package dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie

import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import net.minecraft.client.model.ModelData
import net.minecraft.client.model.ModelPart
import net.minecraft.client.model.ModelPartBuilder
import net.minecraft.client.model.ModelTransform
import net.minecraft.client.model.TexturedModelData
import net.minecraft.client.render.entity.model.EntityModelPartNames.HEAD

/**
 * The model for the Bull Shark Blahaj Plushie.
 * @see BlahajPlushieBlock.Variant.BULL_SHARK
 */
class BullSharkBlahajPlushieModel(override val root: ModelPart) : BlahajPlushieModel() {
    override val head: ModelPart = root.getChild(HEAD)

    companion object {
        fun createModelData(): TexturedModelData {
            val modelData = ModelData()
            val rootPart = modelData.root

            rootPart.addChild(
                HEAD,
                ModelPartBuilder.create()
                    .uv(0, 12).cuboid(-1.5f, -3.0f, 1.5f, 3.0f, 3.0f, 2.0f)
                    .uv(16, 16).cuboid(-0.5f, -4.5f, 3.0f, 1.0f, 5.0f, 2.0f)
                    .uv(0, 0).cuboid(-2.5f, -3.0f, -2.5f, 5.0f, 3.0f, 4.0f)
                    .uv(0, 2).cuboid(-2.5f, -2.5f, -4.5f, 1.0f, 1.0f, 1.0f)
                    .uv(11, 9).cuboid(-2.0f, -1.0f, -5.5f, 4.0f, 1.0f, 3.0f)
                    .uv(10, 13).cuboid(-0.5f, -5.0f, -2.5f, 1.0f, 2.0f, 3.0f)
                    .uv(15, 13).cuboid(2.5f, -1.0f, -2.5f, 2.0f, 1.0f, 2.0f)
                    .uv(14, 0).cuboid(-4.5f, -1.0f, -2.5f, 2.0f, 1.0f, 2.0f)
                    .uv(0, 7).cuboid(-2.0f, -3.0f, -5.5f, 4.0f, 2.0f, 3.0f)
                    .uv(0, 0).cuboid(1.5f, -2.5f, -4.5f, 1.0f, 1.0f, 1.0f),
                ModelTransform.NONE
            )

            return TexturedModelData.of(modelData, 32, 32)
        }
    }
}
