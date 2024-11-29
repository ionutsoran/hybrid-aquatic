package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @ModifyVariable(
            method = "updateMovementInFluid",
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lnet/minecraft/entity/Entity;getWorld()Lnet/minecraft/world/World;",
                            ordinal = 0
                    )
            ),
            at = @At(value = "STORE"
            )
    )

    public FluidState brine$applyPoisonEffectInBrine(FluidState state) {
        if ((Object) this instanceof LivingEntity entity) {
            if (state.getFluid() == HybridAquaticFluids.INSTANCE.getBRINE()) {
                int duration = 100;
                int amplifier = 1;
                entity.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(StatusEffects.POISON, duration, amplifier));
            }
        }
        return state;
    }
}