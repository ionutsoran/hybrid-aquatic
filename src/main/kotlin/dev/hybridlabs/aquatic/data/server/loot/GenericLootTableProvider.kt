package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

class GenericLootTableProvider(output: FabricDataOutput) : SimpleFabricLootTableProvider(output, LootContextTypes.GENERIC) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        exporter.accept(
            HybridAquaticLootTables.CRAB_POT_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.CRAB_POT_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.COCONUT_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GHOST_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.YETI_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SPIDER_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.VAMPIRE_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DUNGENESS_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.LIGHTFOOT_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FIDDLER_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLOWER_CRAB_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.RAW_SHRIMP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.LOBSTER_CLAW)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.RAW_LOBSTER_TAIL)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.HYBRID_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.EEL_SCARF)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MOON_JELLYFISH_HAT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MANGLERFISH_FIN)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MANGLERFISH_LURE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.THRESHER_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GREAT_WHITE_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TIGER_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BULL_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.WHALE_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BASKING_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.HAMMERHEAD_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FRILLED_SHARK_PLUSHIE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DRIFTWOOD_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.DRIFTWOOD_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_LOG)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(8.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_PLANKS)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(16.0f, 32.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_DOOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 3.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_TRAPDOOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.OAK_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.OAK_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HORSE_ARMOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EXPERIENCE_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MAP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FISHING_NET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.OAK_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.SPRUCE_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HORSE_ARMOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EXPERIENCE_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MAP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FISHING_NET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.SPRUCE_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.BIRCH_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HORSE_ARMOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EXPERIENCE_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MAP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FISHING_NET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.BIRCH_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.ACACIA_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HORSE_ARMOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EXPERIENCE_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MAP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FISHING_NET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.ACACIA_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.DARK_OAK_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HORSE_ARMOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EXPERIENCE_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MAP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FISHING_NET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.DARK_OAK_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.MANGROVE_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HORSE_ARMOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EXPERIENCE_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MAP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FISHING_NET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MANGROVE_PROPAGULE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.CHERRY_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HORSE_ARMOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EXPERIENCE_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MAP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FISHING_NET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.CHERRY_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.JUNGLE_CRATE_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(Items.IRON_INGOT)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 9.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EMERALD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(3.0f, 5.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.IRON_HORSE_ARMOR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.EXPERIENCE_BOTTLE)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.MAP)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 16.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.FISHING_ROD)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FISHING_NET)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f)))
                        )
                        .with(
                            ItemEntry.builder(Items.JUNGLE_SAPLING)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(4.0f, 8.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.TUBE_SPONGE_LOOT_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.TUBE_SPONGE_LOOT_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SPONGE_CHUNK)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f)))
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.VENT_LOOT_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.VENT_LOOT_ID)
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1F))
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SULFUR)
                                .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0f, 2.0f)))
                        )
                )
                .pool(
                    LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1F))
                        .with(ItemEntry.builder(Items.RAW_GOLD).weight(1))
                        .with(ItemEntry.builder(Items.RAW_IRON).weight(3))
                        .with(ItemEntry.builder(Items.RAW_COPPER).weight(5))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BLUE_TANG,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.BLUE_TANG))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BLUE_SPOTTED_STINGRAY,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.BLUE_SPOTTED_STINGRAY))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.UNICORNFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.UNICORNFISH))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SOHAL,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SURGEONFISH_SOHAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.ORANGESHOULDER,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SURGEONFISH_ORANGESHOULDER))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.POWDER_BLUE_TANG,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.POWDER_BLUE_TANG))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.YELLOW_TANG,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.YELLOW_TANG))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SEAHORSE,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SEAHORSE))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SUNFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SUNFISH))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.GOLDFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.GOLDFISH))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.KOI,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.KOI))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CARP,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.CARP))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.PARROTFISH,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.PARROTFISH))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SEABASS_BLACK,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SEA_BASS))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.YELLOWFIN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.YELLOWFIN_TUNA))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.BLUEFIN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.BLUEFIN_TUNA))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.SPOTTED_EAGLE_RAY,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.SPOTTED_EAGLE_RAY))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CLAWED_LOBSTER,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.LOBSTER_CLAW))
                )
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.RAW_LOBSTER_TAIL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CLAWLESS_LOBSTER,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.RAW_LOBSTER_TAIL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HERMIT_CRAB_SKULL,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.SKELETON_SKULL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.HERMIT_CRAB_SHELL,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.NAUTILUS_SHELL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_FIRE,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.FIRE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_BRAIN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.BRAIN_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_TUBE,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.TUBE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_BUBBLE,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.BUBBLE_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_HORN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.HORN_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_THORN,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.THORN_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.DECORATOR_LOPHELIA,
            LootTable.builder()
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.LOPHELIA_CORAL))
                )
        )

        exporter.accept(
            HybridAquaticLootTables.CRAB_DIGGING_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.CRAB_DIGGING_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(Items.AIR).weight(10))
                        .with(ItemEntry.builder(Items.NAUTILUS_SHELL).weight(2))
                        .with(ItemEntry.builder(Items.GOLD_NUGGET).weight(3))
                        .with(ItemEntry.builder(Items.IRON_NUGGET).weight(5))
                )
        )
    }
}
