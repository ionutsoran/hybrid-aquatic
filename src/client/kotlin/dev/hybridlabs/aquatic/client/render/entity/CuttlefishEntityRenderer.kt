package dev.hybridlabs.aquatic.client.render.entity

import dev.hybridlabs.aquatic.client.model.entity.CuttlefishEntityModel
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.client.render.entity.EntityRendererFactory.Context
import software.bernie.geckolib.renderer.GeoEntityRenderer

class CuttlefishEntityRenderer(context: Context) : GeoEntityRenderer<HybridAquaticFishEntity>(context, CuttlefishEntityModel())