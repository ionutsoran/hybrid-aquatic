package dev.hybridlabs.aquatic.effect

import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ArmorItem

class CorrosionStatusEffect : StatusEffect(StatusEffectCategory.HARMFUL, 0x9d9136) {

    override fun applyUpdateEffect(entity: LivingEntity, amplifier: Int) {
        if (entity.world.isClient) return
        if (entity is PlayerEntity) {
            corrodeTool(entity)
            corrodeArmor(entity)
        }
    }

    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean {
        return duration % 20 == 0
    }

    private fun corrodeTool(player: PlayerEntity) {
        val mainHandStack = player.mainHandStack
        if (mainHandStack.isDamageable) {
            mainHandStack.damage(1, player) { it.sendToolBreakStatus(player.activeHand) }
        }

        val offHandStack = player.offHandStack
        if (offHandStack.isDamageable) {
            offHandStack.damage(1, player) { it.sendToolBreakStatus(player.activeHand) }
        }
    }

    private fun corrodeArmor(player: PlayerEntity) {
        for (slot in EquipmentSlot.entries) {
            val armorStack = player.getEquippedStack(slot)
            if (armorStack.isDamageable) {
                armorStack.damage(1, player) { it.sendEquipmentBreakStatus(slot) }
            }
        }
    }
}