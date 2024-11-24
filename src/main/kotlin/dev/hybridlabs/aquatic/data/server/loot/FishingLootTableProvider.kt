package dev.hybridlabs.aquatic.data.server.loot

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.HybridAquaticLootTables
import dev.hybridlabs.aquatic.loot.entry.MessageInABottleItemEntry
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.condition.LocationCheckLootCondition
import net.minecraft.loot.context.LootContextTypes
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.predicate.entity.LocationPredicate
import net.minecraft.util.Identifier
import net.minecraft.world.biome.BiomeKeys
import java.util.function.BiConsumer

class FishingLootTableProvider(output: FabricDataOutput) : SimpleFabricLootTableProvider(output, LootContextTypes.FISHING) {
    override fun accept(exporter: BiConsumer<Identifier, LootTable.Builder>) {
        val needsJungle = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.JUNGLE)
        )

        val needsSparseJungle = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.SPARSE_JUNGLE)
        )

        val needsBambooJungle = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.BAMBOO_JUNGLE)
        )

        val needsSwamp = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.SWAMP)
        )

        val needsMangroveSwamp = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.MANGROVE_SWAMP)
        )

        val needsBeach = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.BEACH)
        )

        val needsOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.OCEAN)
        )

        val needsDeepOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.DEEP_OCEAN)
        )

        val needsColdOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.COLD_OCEAN)
        )

        val needsDeepColdOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.DEEP_COLD_OCEAN)
        )

        val needsFrozenOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.FROZEN_OCEAN)
        )

        val needsDeepFrozenOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.DEEP_FROZEN_OCEAN)
        )

        val needsLukewarmOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.LUKEWARM_OCEAN)
        )

        val needsDeepLukewarmOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.DEEP_LUKEWARM_OCEAN)
        )

        val needsWarmOcean = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create()
                .biome(BiomeKeys.WARM_OCEAN)
        )

        val needsRiver = LocationCheckLootCondition.builder(
            LocationPredicate.Builder.create().biome(BiomeKeys.RIVER)
        )

        // fishing fish loot table extension
        exporter.accept(
            HybridAquaticLootTables.FISHING_FISH_ID,
            LootTable.builder()
                .randomSequenceId(HybridAquaticLootTables.FISHING_FISH_ID)
                .pool(
                    LootPool.builder()
                        .with(
                            ItemEntry.builder(HybridAquaticItems.LIONFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.GOURAMI)
                                .weight(5)
                                .conditionally(needsJungle.or(needsSparseJungle.or(needsBambooJungle)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.FLASHLIGHT_FISH)
                                .weight(5)
                                .conditionally(needsBeach.or
                                    (needsOcean.or
                                    (needsDeepOcean.or
                                    (needsLukewarmOcean.or
                                    (needsDeepLukewarmOcean.or
                                    (needsColdOcean.or
                                    (needsDeepColdOcean.or
                                    (needsFrozenOcean.or(needsDeepFrozenOcean)
                                ))))))))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DISCUS)
                                .weight(5)
                                .conditionally(needsJungle.or(needsSparseJungle.or(needsBambooJungle)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BETTA)
                                .weight(5)
                                .conditionally(needsSwamp.or(needsMangroveSwamp))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DANIO)
                                .weight(5)
                                .conditionally(needsJungle.or(needsSparseJungle.or(needsBambooJungle)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.NEON_TETRA)
                                .weight(5)
                                .conditionally(needsJungle.or(needsSparseJungle.or(needsBambooJungle)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MAHI)
                                .weight(5)
                                .conditionally(needsLukewarmOcean.or(needsDeepLukewarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.YELLOWFIN_TUNA)
                                .weight(5)
                                .conditionally(needsDeepOcean.or(needsDeepColdOcean.or(needsDeepFrozenOcean.or(needsDeepLukewarmOcean))))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUEFIN_TUNA)
                                .weight(5)
                                .conditionally(needsDeepOcean.or(needsDeepColdOcean.or(needsDeepFrozenOcean.or(needsDeepLukewarmOcean))))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OPAH)
                                .weight(5)
                                .conditionally(needsLukewarmOcean.or(needsDeepLukewarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ROCKFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsOcean.or(needsDeepOcean)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SEA_BASS)
                                .weight(5)
                                .conditionally(needsBeach.or(needsOcean.or(needsDeepOcean)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUE_SPOTTED_STINGRAY)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SPOTTED_EAGLE_RAY)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.MORAY_EEL)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.NEEDLEFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean.or(needsLukewarmOcean)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.PIRANHA)
                                .weight(5)
                                .conditionally(needsJungle.or(needsSparseJungle.or(needsBambooJungle)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.ANGLERFISH)
                                .weight(5)
                                .conditionally(needsDeepOcean.or(needsDeepLukewarmOcean.or(needsDeepColdOcean.or(needsDeepFrozenOcean))))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BARRELEYE)
                                .weight(5)
                                .conditionally(needsDeepOcean.or(needsDeepLukewarmOcean.or(needsDeepColdOcean.or(needsDeepFrozenOcean))))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.DRAGONFISH)
                                .weight(5)
                                .conditionally(needsDeepOcean.or(needsDeepLukewarmOcean.or(needsDeepColdOcean.or(needsDeepFrozenOcean))))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OARFISH)
                                .weight(5)
                                .conditionally(needsDeepOcean.or(needsDeepLukewarmOcean.or(needsDeepColdOcean.or(needsDeepFrozenOcean))))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SUNFISH)
                                .weight(5)
                                .conditionally(needsDeepOcean.or(needsDeepLukewarmOcean.or(needsLukewarmOcean.or(needsOcean.or(needsDeepOcean)))))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.BLUE_TANG)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.YELLOW_TANG)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.POWDER_BLUE_TANG)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SURGEONFISH_SOHAL)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean.or(needsLukewarmOcean)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SURGEONFISH_ORANGESHOULDER)
                                .weight(5)
                                .conditionally(needsWarmOcean.or(needsLukewarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.SURGEONFISH_LINED)
                                .weight(5)
                                .conditionally(needsWarmOcean.or(needsLukewarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.CLOWNFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.UNICORNFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean.or(needsLukewarmOcean)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.COWFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TOADFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.PARROTFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TRIGGERFISH)
                                .weight(5)
                                .conditionally(needsBeach.or(needsWarmOcean.or(needsLukewarmOcean)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.TIGER_BARB)
                                .weight(5)
                                .conditionally(needsJungle.or(needsSparseJungle.or(needsBambooJungle)))
                        )
                        .with(
                            ItemEntry.builder(HybridAquaticItems.OSCAR)
                                .weight(5)
                                .conditionally(needsJungle.or(needsSparseJungle.or(needsBambooJungle)))
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
                        .with(ItemEntry.builder(HybridAquaticItems.CRAB_POT)
                            .weight(5)
                            .conditionally(needsBeach.or
                                (needsOcean.or
                                (needsDeepOcean.or
                                (needsLukewarmOcean.or
                                (needsDeepLukewarmOcean.or
                                (needsWarmOcean.or
                                (needsColdOcean.or
                                (needsDeepColdOcean.or
                                (needsFrozenOcean.or(needsDeepFrozenOcean)
                            ))))))))))
                        .with(ItemEntry.builder(HybridAquaticItems.HYBRID_CRATE)
                            .weight(5)
                            .conditionally(needsBeach.or
                                (needsOcean.or
                                (needsDeepOcean.or
                                (needsLukewarmOcean.or
                                (needsDeepLukewarmOcean.or
                                (needsWarmOcean.or
                                (needsColdOcean.or
                                (needsDeepColdOcean.or
                                (needsFrozenOcean.or(needsDeepFrozenOcean)
                        ))))))))))
                        .with(ItemEntry.builder(HybridAquaticItems.OAK_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.SPRUCE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.BIRCH_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.DARK_OAK_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.ACACIA_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.JUNGLE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.MANGROVE_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.CHERRY_CRATE))
                        .with(ItemEntry.builder(HybridAquaticItems.DRIFTWOOD_CRATE)
                            .weight(5))
                        .with(MessageInABottleItemEntry.builder())
                )
        )
    }
}