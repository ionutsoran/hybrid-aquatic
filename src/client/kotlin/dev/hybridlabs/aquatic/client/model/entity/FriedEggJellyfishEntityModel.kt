package dev.hybridlabs.aquatic.client.model.entity

import dev.hybridlabs.aquatic.entity.jellyfish.HybridAquaticJellyfishEntity
import net.minecraft.client.render.RenderLayer
import net.minecraft.util.Identifier

class FriedEggJellyfishEntityModel : HybridAquaticJellyfishEntityModel<HybridAquaticJellyfishEntity>("fried_egg_jellyfish") {
    override fun getRenderType(animatable: HybridAquaticJellyfishEntity?, texture: Identifier?): RenderLayer {
        return RenderLayer.getEntityTranslucent(texture)
    }
}
