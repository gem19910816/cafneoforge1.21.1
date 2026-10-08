package net.mcreator.gore.init;

import net.mcreator.gore.network.ZKeyMessage;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent.Post;
import net.neoforged.neoforge.network.PacketDistributor;

@EventBusSubscriber(
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class GoreEditionModKeyMappings {
   public static final KeyMapping Z_KEY = new KeyMapping("key.gore_edition.z_key", 90, "key.categories.gameplay") {
      private boolean isDownOld = false;

      public void setDown(boolean isDown) {
         super.setDown(isDown);
         if (this.isDownOld != isDown && isDown) {
            PacketDistributor.sendToServer(new ZKeyMessage(0, 0), new CustomPacketPayload[0]);
            ZKeyMessage.pressAction(Minecraft.getInstance().player, 0, 0);
         }

         this.isDownOld = isDown;
      }
   };

   @SubscribeEvent
   public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
      event.register(Z_KEY);
   }

   @EventBusSubscriber({Dist.CLIENT})
   public static class KeyEventListener {
      @SubscribeEvent
      public static void onClientTick(Post event) {
         if (Minecraft.getInstance().screen == null) {
            GoreEditionModKeyMappings.Z_KEY.consumeClick();
         }
      }
   }
}
