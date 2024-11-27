package dev.hybridlabs.aquatic.fluid

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.fluid.FlowableFluid
import net.minecraft.fluid.Fluid
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object HybridAquaticFluids {

    val BRINE = register("brine", BrineFluid()) as FlowableFluid

    val FLOWING_BRINE = register("flowing_brine", BrineFluid()) as FlowableFluid

    private fun register(id: String, fluid: Fluid): Fluid {
        return Registry.register(Registries.FLUID, Identifier(HybridAquatic.MOD_ID, id), fluid)
    }
}