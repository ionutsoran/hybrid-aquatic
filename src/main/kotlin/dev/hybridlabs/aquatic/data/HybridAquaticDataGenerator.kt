package dev.hybridlabs.aquatic.data

import dev.hybridlabs.aquatic.data.client.LanguageProvider
import dev.hybridlabs.aquatic.data.client.ModelProvider
import dev.hybridlabs.aquatic.data.server.BlockLootTableProvider
import dev.hybridlabs.aquatic.data.server.BlockTagProvider
import dev.hybridlabs.aquatic.data.server.SeaMessageProvider
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator
import net.minecraft.registry.RegistryBuilder

object HybridAquaticDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()
        pack.addProvider(::LanguageProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::BlockTagProvider)
        pack.addProvider(::SeaMessageProvider)
    }

    override fun buildRegistry(registryBuilder: RegistryBuilder) {
        registryBuilder.addRegistry(HybridAquaticRegistryKeys.SEA_MESSAGE) {}
    }
}
