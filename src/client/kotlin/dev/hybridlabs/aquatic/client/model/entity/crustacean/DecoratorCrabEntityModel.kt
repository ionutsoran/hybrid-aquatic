package dev.hybridlabs.aquatic.client.model.entity.crustacean

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.entity.crustacean.DecoratorCrabEntity
import net.minecraft.util.Identifier

class DecoratorCrabEntityModel : HybridAquaticCrustaceanEntityModel<DecoratorCrabEntity>("decorator_crab") {
    override fun getTextureResource(animatable: DecoratorCrabEntity?): Identifier {
        val texturePath = when (animatable?.randomValue) {
            0 -> "textures/entity/crustacean/decorator_crab/brain.png"
            1 -> "textures/entity/crustacean/decorator_crab/bubble.png"
            2 -> "textures/entity/crustacean/decorator_crab/button.png"
            3 -> "textures/entity/crustacean/decorator_crab/fire.png"
            4 -> "textures/entity/crustacean/decorator_crab/horn.png"
            5 -> "textures/entity/crustacean/decorator_crab/lophelia.png"
            6 -> "textures/entity/crustacean/decorator_crab/sun.png"
            7 -> "textures/entity/crustacean/decorator_crab/thorn.png"
            8 -> "textures/entity/crustacean/decorator_crab/tube.png"
            9 -> "textures/entity/crustacean/decorator_crab/none.png"
            else -> "textures/entity/crustacean/decorator_crab/none.png"
        }

        return Identifier(HybridAquatic.MOD_ID, texturePath)
    }
}