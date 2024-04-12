package dev.hybridlabs.aquatic.client.model.entity.fish

import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import net.minecraft.util.Identifier

class BettaEntityModel : HybridAquaticFishEntityModel<HybridAquaticFishEntity>("betta") {
    override fun getTextureResource(animatable: HybridAquaticFishEntity?): Identifier {
        if (animatable != null) return getVariantTexture(dev.hybridlabs.aquatic.client.model.entity.fish.BettaEntityModel.Companion.allVariants[animatable.variant])
        return super.getTextureResource(animatable)
    }

    companion object {
        val allVariants: Array<String> = arrayOf("black", "blue_yellow", "blue", "green", "pink", "red", "red_blue", "white")
    }
}

