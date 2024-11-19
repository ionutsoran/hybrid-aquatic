package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.util.math.intprovider.IntProvider
import net.minecraft.world.gen.feature.FeatureConfig
import net.minecraft.world.gen.stateprovider.BlockStateProvider

data class VentPatchFeatureConfig(
    val baseProvider: BlockStateProvider,
    val ventProvider: BlockStateProvider,
    val coralProvider: BlockStateProvider,
    val count: IntProvider,
    val spreadRadius: IntProvider,
    val coralCount: IntProvider,
    val coralSpreadRadius: IntProvider,
) : FeatureConfig {
    companion object {
        val CODEC: Codec<VentPatchFeatureConfig> = RecordCodecBuilder.create { instance ->
            instance.group(
                BlockStateProvider.TYPE_CODEC.fieldOf("base_block").forGetter(VentPatchFeatureConfig::baseProvider),
                BlockStateProvider.TYPE_CODEC.fieldOf("vent_block").forGetter(VentPatchFeatureConfig::ventProvider),
                BlockStateProvider.TYPE_CODEC.fieldOf("coral_block").forGetter(VentPatchFeatureConfig::coralProvider),
                IntProvider.POSITIVE_CODEC.fieldOf("count").forGetter(VentPatchFeatureConfig::count),
                IntProvider.POSITIVE_CODEC.fieldOf("spread_radius").forGetter(VentPatchFeatureConfig::spreadRadius),
                IntProvider.POSITIVE_CODEC.fieldOf("coral_count").forGetter(VentPatchFeatureConfig::coralCount),
                IntProvider.POSITIVE_CODEC.fieldOf("coral_spread_radius").forGetter(VentPatchFeatureConfig::coralSpreadRadius),
            ).apply(instance, ::VentPatchFeatureConfig)
        }
    }
}
