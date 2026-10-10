package net.mcreator.survivalinstinct.init;

import net.minecraft.world.level.block.ComposterBlock;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class SurvivalInstinctModCompostableItems {
   @SubscribeEvent
   public static void addComposterItems(FMLCommonSetupEvent event) {
      ComposterBlock.COMPOSTABLES.put(SurvivalInstinctModItems.ROTTEN_APPLE.get(), 0.7F);
      ComposterBlock.COMPOSTABLES.put(SurvivalInstinctModItems.ROTTEN_ORANGE.get(), 0.7F);
   }
}
