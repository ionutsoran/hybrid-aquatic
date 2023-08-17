package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.SeaAngelEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer
import software.bernie.geckolib.renderer.layer.AutoGlowingGeoLayer

class SeaAngelEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, SeaAngelEntityModel()) {
    init {
        addRenderLayer(AutoGlowingGeoLayer(this))
    }
}