package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects;
import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.fluid.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Unique
    private int brineEffectTick = 100;
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

    @Unique
    public FluidState brine$applyToxicShockInBrine(FluidState state) {
        if ((Object) this instanceof LivingEntity entity) {

            if (state.getFluid() == HybridAquaticFluids.INSTANCE.getBRINE() ||
                    state.getFluid() == HybridAquaticFluids.INSTANCE.getFLOWING_BRINE()) {

                if (brineEffectTick >= 100) {

                    if (isWearingDivingSet(entity)) {
                        entity.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                                HybridAquaticStatusEffects.INSTANCE.getCORROSION(), 100, 0));
                    } else {
                        entity.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                                HybridAquaticStatusEffects.INSTANCE.getTOXIC_SHOCK(), 100, 0));
                        entity.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                                HybridAquaticStatusEffects.INSTANCE.getCORROSION(), 100, 0));
                    }

                    brineEffectTick = 0;
                }

                brineEffectTick++;
            }
        }
        return state;
    }


    @Unique
    private boolean isWearingDivingSet(LivingEntity entity) {
        var helmet = entity.getEquippedStack(EquipmentSlot.HEAD);
        var chestplate = entity.getEquippedStack(EquipmentSlot.CHEST);
        var leggings = entity.getEquippedStack(EquipmentSlot.LEGS);
        var boots = entity.getEquippedStack(EquipmentSlot.FEET);

        return helmet.isOf(HybridAquaticItems.INSTANCE.getDIVING_HELMET()) &&
                chestplate.isOf(HybridAquaticItems.INSTANCE.getDIVING_SUIT()) &&
                leggings.isOf(HybridAquaticItems.INSTANCE.getDIVING_LEGGINGS()) &&
                boots.isOf(HybridAquaticItems.INSTANCE.getDIVING_BOOTS());
    }
}