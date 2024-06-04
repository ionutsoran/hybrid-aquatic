package dev.hybridlabs.aquatic.world.biome

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.world.biome.surface.HybridAquaticMaterialRules
import net.minecraft.util.Identifier
import terrablender.api.Regions
import terrablender.api.SurfaceRuleManager
import terrablender.api.TerraBlenderApi

class HybridAquaticTerrablenderAPI : TerraBlenderApi {
    override fun onTerraBlenderInitialized() {
        Regions.register(HybridAquaticOverworldCommonRegion(Identifier(HybridAquatic.MOD_ID, "common"), 3))
        Regions.register(HybridAquaticOverworldUncommonRegion(Identifier(HybridAquatic.MOD_ID, "uncommon"), 2))

        SurfaceRuleManager.addSurfaceRules(
            SurfaceRuleManager.RuleCategory.OVERWORLD,
            HybridAquatic.MOD_ID,
            HybridAquaticMaterialRules.makeRules())
    }
}