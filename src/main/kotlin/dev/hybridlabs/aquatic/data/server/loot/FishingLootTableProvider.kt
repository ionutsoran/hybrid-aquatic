package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.loot.entry.MessageInABottleItemEntry
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.util.Identifier
import java.util.function.BiConsumer

class FishingLootTableProvider(output: FabricDataOutput) : SimpleFabricLootTableProvider(output, LootContextTypes.FISHING) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        exporter.accept(
            HybridAquaticLootTables.FISHING_DEEP_SEA_FISH_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_DEEP_SEA_FISH_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ANGLERFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BARRELEYE)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRAGONFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLASHLIGHT_FISH)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.RATFISH)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OARFISH)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SUNFISH)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OPAH)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.COELACANTH)
                                .weight(2)
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.FISHING_REEF_FISH_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_REEF_FISH_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SURGEONFISH_LINED)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SURGEONFISH_ORANGESHOULDER)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SURGEONFISH_SOHAL)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.YELLOW_TANG)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.POWDER_BLUE_TANG)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUE_TANG)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.UNICORNFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUE_SPOTTED_STINGRAY)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.CLOWNFISH)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.COWFISH)
                                .weight(5)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.LIONFISH)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.NEEDLEFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MORAY_EEL)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.PARROTFISH)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ROCKFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TRIGGERFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SEA_BASS)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SEAHORSE)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SPOTTED_EAGLE_RAY)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TOADFISH)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.STONEFISH)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLASHLIGHT_FISH)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLYING_FISH)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SQUIRREL_FISH)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.CORAL_GROUPER)
                                .weight(4)
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.FISHING_OPEN_OCEAN_FISH_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_OPEN_OCEAN_FISH_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUEFIN_TUNA)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.YELLOWFIN_TUNA)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MAHI)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MACKEREL)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.NEEDLEFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ROCKFISH)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SEA_BASS)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SPOTTED_EAGLE_RAY)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OARFISH)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SUNFISH)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OPAH)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLASHLIGHT_FISH)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLYING_FISH)
                                .weight(4)
                        )
                )
        )

        exporter.accept(
            HybridAquaticLootTables.FISHING_TROPICAL_FRESHWATER_FISH_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_TROPICAL_FRESHWATER_FISH_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.NEON_TETRA)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OSCAR)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.PIRANHA)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TIGER_BARB)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DANIO)
                                .weight(3)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BETTA)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DISCUS)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GOURAMI)
                                .weight(2)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GOLDFISH)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.CARP)
                                .weight(4)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.KOI)
                                .weight(1)
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GOLDEN_DORADO)
                                .weight(1)
                        )
                )
        )

        // fishing treasure loot table extension
        exporter.accept(
            HybridAquaticLootTables.FISHING_TREASURE_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_TREASURE_ID)
                .pool(
                    LootPool.builder()
                        .with(ItemEntry.builder(HybridAquaticItems.CRAB_POT))
                        .with(ItemEntry.builder(HybridAquaticItems.HYBRID_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.OAK_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.SPRUCE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.BIRCH_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.DARK_OAK_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.ACACIA_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.JUNGLE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.MANGROVE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.CHERRY_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_CRATE))
                        .with(MessageInABottleItemEntry.builder())
                )
        )
    }
}