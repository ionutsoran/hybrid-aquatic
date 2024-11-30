package dev.hybridlabs.aquatic.client.render.entity.miniboss

import dev.hybridlabs.aquatic.client.model.entity.miniboss.ManglerfishEntityModel
import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context

class ManglerfishEntityRenderer(context: Context) : HybridAquaticMinibossEntityRenderer<HybridAquaticMinibossEntity>(context, ManglerfishEntityModel(), true) {
    override fun getMotionAnimThreshold(animatable: HybridAquaticMinibossEntity): Float {
        return 0.0025f
    }
}