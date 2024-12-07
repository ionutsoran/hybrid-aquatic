package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks.BRINE
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider
import net.minecraft.advancement.Advancement
import net.minecraft.advancement.AdvancementFrame
import net.minecraft.advancement.criterion.EnterBlockCriterion
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.advancement.criterion.OnKilledCriterion
import net.minecraft.block.Blocks.WATER
import net.minecraft.predicate.entity.EntityPredicate
import net.minecraft.text.Text
import net.minecraft.util.Identifier
import java.util.function.Consumer

class AdvancementProvider(output: FabricDataOutput) : FabricAdvancementProvider(output) {
    override fun generateAdvancement(consumer: Consumer<Advancement>?) {
        val rootAdvancement = Advancement.Builder.create()
            .display(
                HybridAquaticItems.YELLOWFIN_TUNA,
                Text.translatable("advancements.hybrid-aquatic.enter_water.title"),
                Text.translatable("advancements.hybrid-aquatic.enter_water.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                true
            )
            .criterion(
                "enter_water",
                EnterBlockCriterion.Conditions.block(WATER)
            )
            .build(Identifier("hybrid-aquatic", "root"))
        consumer?.accept(rootAdvancement)

        val glowstickAdvancement = Advancement.Builder.create()
            .parent(rootAdvancement)
            .display(
                HybridAquaticItems.GLOWSTICK,
                Text.translatable("advancements.hybrid-aquatic.glowstick.title"),
                Text.translatable("advancements.hybrid-aquatic.glowstick.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                true
            )
            .criterion(
                "obtain_glowstick",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BUOY)
            )
            .build(Identifier("hybrid-aquatic", "glowstick"))
        consumer?.accept(glowstickAdvancement)

        val buoyAdvancement = Advancement.Builder.create()
            .parent(glowstickAdvancement)
            .display(
                HybridAquaticItems.BUOY,
                Text.translatable("advancements.hybrid-aquatic.buoy.title"),
                Text.translatable("advancements.hybrid-aquatic.buoy.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                true
            )
            .criterion(
                "obtain_buoy",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BUOY)
            )
            .build(Identifier("hybrid-aquatic", "buoy"))
        consumer?.accept(buoyAdvancement)

        val divingSuitAdvancement = Advancement.Builder.create()
            .parent(buoyAdvancement)
            .display(
                HybridAquaticItems.DIVING_HELMET,
                Text.translatable("advancements.hybrid-aquatic.diving_suit.title"),
                Text.translatable("advancements.hybrid-aquatic.diving_suit.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.GOAL,
                true,
                true,
                false
            )
            .criterion(
                "obtain_diving_suit",
                InventoryChangedCriterion.Conditions.items(
                    HybridAquaticItems.DIVING_HELMET,
                    HybridAquaticItems.DIVING_SUIT,
                    HybridAquaticItems.DIVING_LEGGINGS,
                    HybridAquaticItems.DIVING_BOOTS
                )
            )
            .build(Identifier("hybrid-aquatic", "diving_suit"))
        consumer?.accept(divingSuitAdvancement)

        val brineAdvancement = Advancement.Builder.create()
            .display(
                HybridAquaticItems.BRINE_BUCKET,
                Text.translatable("advancements.hybrid-aquatic.brine.title"),
                Text.translatable("advancements.hybrid-aquatic.brine.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                true
            )
            .criterion(
                "enter_brine",
                EnterBlockCriterion.Conditions.block(BRINE)
            )
            .build(Identifier("hybrid-aquatic", "brine"))
        consumer?.accept(divingSuitAdvancement)

        val obtainPearlAdvancement = Advancement.Builder.create()
            .parent(rootAdvancement)
            .display(
                HybridAquaticItems.PEARL,
                Text.translatable("advancements.hybrid-aquatic.pearl.title"),
                Text.translatable("advancements.hybrid-aquatic.pearl.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                false
            )
            .criterion(
                "obtain_pearl",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.PEARL)
            )
            .build(Identifier("hybrid-aquatic", "pearl"))
        consumer?.accept(obtainPearlAdvancement)

        val obtainBlackPearlAdvancement = Advancement.Builder.create()
            .parent(obtainPearlAdvancement)
            .display(
                HybridAquaticItems.BLACK_PEARL,
                Text.translatable("advancements.hybrid-aquatic.black_pearl.title"),
                Text.translatable("advancements.hybrid-aquatic.black_pearl.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.TASK,
                true,
                true,
                true
            )
            .criterion(
                "obtain_black_pearl",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.BLACK_PEARL)
            )
            .build(Identifier("hybrid-aquatic", "black_pearl"))
        consumer?.accept(obtainBlackPearlAdvancement)

        val ominousHookAdvancement = Advancement.Builder.create()
            .parent(rootAdvancement)
            .display(
                HybridAquaticItems.OMINOUS_HOOK,
                Text.translatable("advancements.hybrid-aquatic.ominous_hook.title"),
                Text.translatable("advancements.hybrid-aquatic.ominous_hook.description"),
                Identifier("textures/gui/advancements/backgrounds/adventure.png"),
                AdvancementFrame.GOAL,
                true,
                true,
                false
            )
            .criterion(
                "obtain_ominous_hook",
                InventoryChangedCriterion.Conditions.items(HybridAquaticItems.OMINOUS_HOOK)
            )
            .build(Identifier("hybrid-aquatic", "ominous_hook"))
        consumer?.accept(ominousHookAdvancement)

        val killKarkinosAdvancement = Advancement.Builder.create()
            .parent(ominousHookAdvancement)
            .display(
                HybridAquaticItems.KARKINOS_CLAW,
                Text.translatable("advancements.hybrid-aquatic.kill_karkinos.title"),
                Text.translatable("advancements.hybrid-aquatic.kill_karkinos.description"),
                Identifier("textures/gui/advancements/backgrounds/water.png"),
                AdvancementFrame.CHALLENGE,
                true,
                true,
                true
            )
            .criterion(
                "kill_karkinos",
                OnKilledCriterion.Conditions.createPlayerKilledEntity(
                    EntityPredicate.Builder.create().type(HybridAquaticEntityTypes.KARKINOS).build()
                )
            )
            .build(Identifier("hybrid-aquatic", "kill_karkinos"))
        consumer?.accept(killKarkinosAdvancement)

    }
}