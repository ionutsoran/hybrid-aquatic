package dev.hybridlabs.aquatic.client.model.entity.miniboss

import dev.hybridlabs.aquatic.entity.miniboss.HybridAquaticMinibossEntity
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.entity.model.EntityModelPartNames
import net.minecraft.util.math.MathHelper
import software.bernie.geckolib.core.animation.AnimationState

class ManglerfishEntityModel : HybridAquaticMinibossEntityModel<HybridAquaticMinibossEntity>("manglerfish") {

    override fun setCustomAnimations(
        animatable: HybridAquaticMinibossEntity,
        instanceId: Long,
        animationState: AnimationState<HybridAquaticMinibossEntity>
    ) {
        super.setCustomAnimations(animatable, instanceId, animationState)
        val deltaTime: Float = MinecraftClient.getInstance().tickDelta

        val body = animationProcessor.getBone(EntityModelPartNames.BODY)

        val pitch = MathHelper.clamp(MathHelper.lerp(deltaTime, animatable.prevPitch, animatable.pitch), -45f, 45f)
        body.rotX = pitch * -MathHelper.RADIANS_PER_DEGREE
    }
}
