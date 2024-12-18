package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import dev.hybridlabs.aquatic.tag.HybridAquaticFluidTags;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Unique
    private int brineEffectTick = 100;
    
    @Inject(
        method = "updateMovementInFluid",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/fluid/FluidState;isIn(Lnet/minecraft/registry/tag/TagKey;)Z"
        ),
        locals = LocalCapture.CAPTURE_FAILSOFT
    )
    private void applyToxicShockInBrine(TagKey<Fluid> tag, double speed, CallbackInfoReturnable<Boolean> cir, Box box, int i, int j, int k, int l, int m, int n, double d, boolean bl, boolean bl2, Vec3d vec3d, int o, BlockPos.Mutable mutable, int p, int q, int r, FluidState fluidState) {
        if ((Object) this instanceof LivingEntity entity) {
            
            if (fluidState.isIn(HybridAquaticFluidTags.INSTANCE.getBRINE())) {
                if (brineEffectTick >= 100) {
                    if (!isWearingDivingSet(entity)) entity.addStatusEffect(new StatusEffectInstance(HybridAquaticStatusEffects.INSTANCE.getTOXIC_SHOCK(), 100, 0));
                    entity.addStatusEffect(new StatusEffectInstance(HybridAquaticStatusEffects.INSTANCE.getCORROSION(), 100, 0));
                    
                    brineEffectTick = 0;
                }
                
                brineEffectTick++;
            }
        }
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