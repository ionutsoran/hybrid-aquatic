package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class BrineLakeFeatureConfig(
    val barrierProvider: BlockStateProvider,
    val fluidProvider: BlockStateProvider,
) : FeatureConfig {
    companion object {
        val CODEC: Codec<BrineLakeFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("barrier").forGetter(BrineLakeFeatureConfig::barrierProvider),
                BlockStateProvider.TYPE_CODEC.fieldOf("fluid").forGetter(BrineLakeFeatureConfig::fluidProvider),
            ).apply(instance, ::BrineLakeFeatureConfig)
        }
    }
}