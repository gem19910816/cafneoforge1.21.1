package net.mcreator.survivalinstinct.init;

import net.mcreator.survivalinstinct.client.renderer.NailProyectileRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class SurvivalInstinctModEntityRenderers {
   @SubscribeEvent
   public static void registerEntityRenderers(RegisterRenderers event) {
      event.registerEntityRenderer(SurvivalInstinctModEntities.HOMEMADE_BOMB_PROYECTILE.get(), ThrownItemRenderer::new);
      event.registerEntityRenderer(SurvivalInstinctModEntities.MOLOTOV.get(), ThrownItemRenderer::new);
      event.registerEntityRenderer(SurvivalInstinctModEntities.NAIL_PROYECTILE.get(), NailProyectileRenderer::new);
   }
}
