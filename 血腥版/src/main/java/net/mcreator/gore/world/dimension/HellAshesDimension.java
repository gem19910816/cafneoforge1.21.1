package net.mcreator.gore.world.dimension;

import net.minecraft.client.renderer.DimensionSpecialEffects;
import net.minecraft.client.renderer.DimensionSpecialEffects.SkyType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterDimensionSpecialEffectsEvent;

public class HellAshesDimension {
   @EventBusSubscriber(value = Dist.CLIENT, 
      bus = Bus.MOD
   )
   public static class HellAshesSpecialEffectsHandler {
      @SubscribeEvent
      @OnlyIn(Dist.CLIENT)
      public static void registerDimensionSpecialEffects(RegisterDimensionSpecialEffectsEvent event) {
         DimensionSpecialEffects customEffect = new DimensionSpecialEffects(Float.NaN, true, SkyType.NONE, false, false) {
            public Vec3 getBrightnessDependentFogColor(Vec3 color, float sunHeight) {
               return new Vec3(0.0, 0.0, 0.0);
            }

            public boolean isFoggyAt(int x, int y) {
               return true;
            }

            public float[] getSunriseColor(float p_108872_, float p_108873_) {
               return null;
            }
         };
         event.register(ResourceLocation.parse("gore_edition:the_xash"), customEffect);
      }
   }
}
