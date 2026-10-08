package net.mcreator.gore.init;

import net.mcreator.gore.client.model.ModelSkeleton_Without_Arm_Head;
import net.mcreator.gore.client.model.Modelprojectile_wither_skull;
import net.mcreator.gore.client.model.Modelskeleton_without_arm_arm;
import net.mcreator.gore.client.model.Modelwither_skull;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterLayerDefinitions;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class GoreEditionModModels {
   @SubscribeEvent
   public static void registerLayerDefinitions(RegisterLayerDefinitions event) {
      event.registerLayerDefinition(Modelwither_skull.LAYER_LOCATION, Modelwither_skull::createBodyLayer);
      event.registerLayerDefinition(Modelskeleton_without_arm_arm.LAYER_LOCATION, Modelskeleton_without_arm_arm::createBodyLayer);
      event.registerLayerDefinition(ModelSkeleton_Without_Arm_Head.LAYER_LOCATION, ModelSkeleton_Without_Arm_Head::createBodyLayer);
      event.registerLayerDefinition(Modelprojectile_wither_skull.LAYER_LOCATION, Modelprojectile_wither_skull::createBodyLayer);
   }
}
