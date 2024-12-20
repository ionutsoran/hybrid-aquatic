package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.BlockTags
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // misc
        getOrCreateTagBuilder(HybridAquaticBlockTags.ANEMONES_GENERATE_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.GIANT_CLAM_GENERATE_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.TUBE_SPONGE_GENERATE_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.MESSAGE_IN_A_BOTTLE_SPAWNS_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.CRAB_DIGGABLE_BLOCKS)
            .add(Blocks.SAND)
            .add(Blocks.SUSPICIOUS_SAND)
            .add(Blocks.GRAVEL)
            .add(Blocks.SUSPICIOUS_GRAVEL)

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
            .add(HybridAquaticBlocks.DRIFTWOOD_FENCE)

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
            .add(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE)

        getOrCreateTagBuilder(BlockTags.LOGS_THAT_BURN)
            .add(HybridAquaticBlocks.DRIFTWOOD_LOG)
            .add(HybridAquaticBlocks.DRIFTWOOD_WOOD)
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG)
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD)

        getOrCreateTagBuilder(BlockTags.UNDERWATER_BONEMEALS)
            .add(HybridAquaticBlocks.RED_SEAWEED)
            .add(HybridAquaticBlocks.GREEN_SEAWEED)

        getOrCreateTagBuilder(BlockTags.CORAL_BLOCKS)
            .add(HybridAquaticBlocks.BUTTON_CORAL_BLOCK)
            .add(HybridAquaticBlocks.SUN_CORAL_BLOCK)

        getOrCreateTagBuilder(BlockTags.CORALS)
            .add(HybridAquaticBlocks.BUTTON_CORAL)
            .add(HybridAquaticBlocks.SUN_CORAL)

        getOrCreateTagBuilder(BlockTags.CORAL_PLANTS)
            .add(HybridAquaticBlocks.BUTTON_CORAL_FAN)
            .add(HybridAquaticBlocks.SUN_CORAL_FAN)
            .add(HybridAquaticBlocks.RED_SEAWEED)
            .add(HybridAquaticBlocks.GREEN_SEAWEED)

        getOrCreateTagBuilder(BlockTags.WALL_CORALS)
            .add(HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN)
            .add(HybridAquaticBlocks.SUN_CORAL_WALL_FAN)

        getOrCreateTagBuilder(HybridAquaticBlockTags.DEEP_CORALS)
            .forceAddTag(HybridAquaticBlockTags.DEEP_CORAL_PLANTS)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_FAN)
            .add(HybridAquaticBlocks.THORN_CORAL_FAN)

        getOrCreateTagBuilder(HybridAquaticBlockTags.DEEP_CORAL_PLANTS)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL)
            .add(HybridAquaticBlocks.THORN_CORAL)

        getOrCreateTagBuilder(HybridAquaticBlockTags.DEEP_CORAL_BLOCKS)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK)
            .add(HybridAquaticBlocks.THORN_CORAL_BLOCK)

        getOrCreateTagBuilder(HybridAquaticBlockTags.DEEP_WALL_CORALS)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN)
            .add(HybridAquaticBlocks.THORN_CORAL_WALL_FAN)

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
            .add(HybridAquaticBlocks.BUTTON_CORAL_BLOCK)
            .add(HybridAquaticBlocks.DEAD_BUTTON_CORAL_BLOCK)
            .add(HybridAquaticBlocks.LOPHELIA_CORAL_BLOCK)
            .add(HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_BLOCK)
            .add(HybridAquaticBlocks.THORN_CORAL_BLOCK)
            .add(HybridAquaticBlocks.DEAD_THORN_CORAL_BLOCK)
            .add(HybridAquaticBlocks.THERMAL_VENT)
            .add(HybridAquaticBlocks.GIANT_CLAM)

        getOrCreateTagBuilder(BlockTags.LOGS)
            .add(HybridAquaticBlocks.DRIFTWOOD_LOG)
            .add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG)

        // plushies
        Registries.BLOCK
            .filter(filterHybridAquatic(Registries.BLOCK))
            .forEach { block ->
                // plushies
                if (block is PlushieBlock) {
                    getOrCreateTagBuilder(HybridAquaticBlockTags.PLUSHIES).add(block)
                }
            }
    }
}
