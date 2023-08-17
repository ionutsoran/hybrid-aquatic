package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.VampireSquidEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class VampireSquidEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, VampireSquidEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}