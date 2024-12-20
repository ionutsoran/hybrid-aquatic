package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import dev.hybridlabs.aquatic.tag.HybridAquaticFluidTags;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.fluid.FluidState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @Unique
    private int brineEffectTick = 0;

    @Inject(
            method = "baseTick",
            at = @At(
                    value = "TAIL"
            )
    )
    private void applyToxicShockInBrine(CallbackInfo ci) {
        LivingEntity livingEntity = (LivingEntity) (Object) this;

        if (!livingEntity.getWorld().isClient && brineEffectTick >= 20) {
            brineEffectTick = 0;
            Box box = livingEntity.getBoundingBox().contract(0.001);
            int entityMinX = MathHelper.floor(box.minX);
            int entityMaxX = MathHelper.ceil(box.maxX);
            int entityMinY = MathHelper.floor(box.minY);
            int entityMaxY = MathHelper.ceil(box.maxY);
            int entityMinZ = MathHelper.floor(box.minZ);
            int entityMaxZ = MathHelper.ceil(box.maxZ);
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for(int entityX = entityMinX; entityX < entityMaxX; ++entityX) {
                for(int entityY = entityMinY; entityY < entityMaxY; ++entityY) {
                    for (int entityZ = entityMinZ; entityZ < entityMaxZ; ++entityZ) {
                        mutable.set(entityX, entityY, entityZ);
                        FluidState fluidState = livingEntity.getWorld().getFluidState(mutable);

//                        if(fluidState.isIn(HybridAquaticFluidTags.INSTANCE.getBRINE())) tickBrine();
                    }
                }
            }

        }

        brineEffectTick++;
    }

//    @Unique
//    private void tickBrine() {
//        LivingEntity livingEntity = (LivingEntity) (Object) this;
//
//        if (!isWearingDivingSet(livingEntity)) livingEntity.addStatusEffect(new StatusEffectInstance(HybridAquaticStatusEffects.INSTANCE.getTOXIC_SHOCK(), 100, 0));
//        livingEntity.addStatusEffect(new StatusEffectInstance(HybridAquaticStatusEffects.INSTANCE.getCORROSION(), 100, 0));
//    }
//
//    @Unique
//    private boolean isWearingDivingSet(LivingEntity entity) {
//        var helmet = entity.getEquippedStack(EquipmentSlot.HEAD);
//        var chestplate = entity.getEquippedStack(EquipmentSlot.CHEST);
//        var leggings = entity.getEquippedStack(EquipmentSlot.LEGS);
//        var boots = entity.getEquippedStack(EquipmentSlot.FEET);
//
//        return helmet.isOf(HybridAquaticItems.INSTANCE.getDIVING_HELMET()) &&
//               chestplate.isOf(HybridAquaticItems.INSTANCE.getDIVING_SUIT()) &&
//               leggings.isOf(HybridAquaticItems.INSTANCE.getDIVING_LEGGINGS()) &&
//               boots.isOf(HybridAquaticItems.INSTANCE.getDIVING_BOOTS());
//    }
}