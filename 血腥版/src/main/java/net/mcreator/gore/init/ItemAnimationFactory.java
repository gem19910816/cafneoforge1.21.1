package net.mcreator.gore.init;

import net.mcreator.gore.item.SquitchgunItem;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.PlayerTickEvent.Pre;
import software.bernie.geckolib.animatable.GeoItem;

@EventBusSubscriber
public class ItemAnimationFactory {
   @SubscribeEvent
   public static void animatedItems(Pre event) {
      String animation = "";
      ItemStack mainhandItem = event.getEntity().getMainHandItem().copy();
      ItemStack offhandItem = event.getEntity().getOffhandItem().copy();
      if (mainhandItem.getItem() instanceof GeoItem || offhandItem.getItem() instanceof GeoItem) {
         if (mainhandItem.getItem() instanceof SquitchgunItem animatable) {
            animation = ItemTagHelper.getString(mainhandItem, "geckoAnim");
            if (!animation.isEmpty()) {
               ItemTagHelper.putString(event.getEntity().getMainHandItem(), "geckoAnim", "");
               if (event.getEntity().level().isClientSide()) {
                  ((SquitchgunItem)event.getEntity().getMainHandItem().getItem()).animationprocedure = animation;
               }
            }
         }

         if (offhandItem.getItem() instanceof SquitchgunItem animatablex) {
            animation = ItemTagHelper.getString(offhandItem, "geckoAnim");
            if (!animation.isEmpty()) {
               ItemTagHelper.putString(event.getEntity().getOffhandItem(), "geckoAnim", "");
               if (event.getEntity().level().isClientSide()) {
                  ((SquitchgunItem)event.getEntity().getOffhandItem().getItem()).animationprocedure = animation;
               }
            }
         }
      }
   }
}
