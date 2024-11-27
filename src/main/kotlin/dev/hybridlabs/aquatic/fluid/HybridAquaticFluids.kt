package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.fluid.Fluid
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticFluids {
    val FLOWING_BRINE = register("flowing_brine", BrineFluid.Flowing())
    val BRINE = register("brine", BrineFluid.Still())

    private fun <T : Fluid> register(id: String, fluid: T): T {
        return Registry.register(Registries.FLUID, Identifier(HybridAquatic.MOD_ID, id), fluid)
    }
}
