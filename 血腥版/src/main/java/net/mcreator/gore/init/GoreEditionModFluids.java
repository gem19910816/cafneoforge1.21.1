package net.mcreator.gore.init;

import net.mcreator.gore.fluid.AcidFluid;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GoreEditionModFluids {
   public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(BuiltInRegistries.FLUID, "gore_edition");
   public static final DeferredHolder<Fluid, FlowingFluid> ACID = REGISTRY.register("acid", () -> new AcidFluid.Source());
   public static final DeferredHolder<Fluid, FlowingFluid> FLOWING_ACID = REGISTRY.register("flowing_acid", () -> new AcidFluid.Flowing());

   @EventBusSubscriber(
      bus = Bus.MOD,
      value = {Dist.CLIENT}
   )
   public static class FluidsClientSideHandler {
      @SubscribeEvent
      public static void clientSetup(FMLClientSetupEvent event) {
         ItemBlockRenderTypes.setRenderLayer((Fluid)GoreEditionModFluids.ACID.get(), RenderType.translucent());
         ItemBlockRenderTypes.setRenderLayer((Fluid)GoreEditionModFluids.FLOWING_ACID.get(), RenderType.translucent());
      }
   }
}
