package net.mcreator.gore.init;

import net.mcreator.gore.fluid.types.AcidFluidType;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

public class GoreEditionModFluidTypes {
   public static final DeferredRegister<FluidType> REGISTRY = DeferredRegister.create(Keys.FLUID_TYPES, "gore_edition");
   public static final DeferredHolder<FluidType, FluidType> ACID_TYPE = REGISTRY.register("acid", () -> new AcidFluidType());
}
