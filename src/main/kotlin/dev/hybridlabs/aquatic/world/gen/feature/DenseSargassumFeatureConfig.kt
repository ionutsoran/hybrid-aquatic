package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class DenseSargassumFeatureConfig(val toPlace: BlockStateProvider) : FeatureConfig {
    companion object {
        val CODEC: Codec<DenseSargassumFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("to_place")
                    .forGetter(DenseSargassumFeatureConfig::toPlace)
            ).apply(instance, ::DenseSargassumFeatureConfig)
        }
    }
}