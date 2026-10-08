package net.mcreator.gore.procedures;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationFactory;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentSerialization;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@EventBusSubscriber(
   modid = "gore_edition",
   bus = Bus.MOD,
   value = {Dist.CLIENT}
)
public class SetupAnimationsProcedure {
   @SubscribeEvent
   public static void onClientSetup(FMLClientSetupEvent event) {
      PlayerAnimationFactory.ANIMATION_DATA_FACTORY
         .registerFactory(ResourceLocation.fromNamespaceAndPath("gore_edition", "player_animation"), 1000, SetupAnimationsProcedure::registerPlayerAnimations);
   }

   private static IAnimation registerPlayerAnimations(AbstractClientPlayer player) {
      return new ModifierLayer();
   }

   public static void execute() {
      execute(null);
   }

   private static void execute(@Nullable Event event) {
   }

   @EventBusSubscriber(
      bus = Bus.MOD
   )
   public static class GoreEditionModAnimationMessage implements CustomPacketPayload {
      public static final Type<SetupAnimationsProcedure.GoreEditionModAnimationMessage> TYPE = new Type(
         ResourceLocation.fromNamespaceAndPath("gore_edition", "animation")
      );
      public static final StreamCodec<RegistryFriendlyByteBuf, SetupAnimationsProcedure.GoreEditionModAnimationMessage> STREAM_CODEC = StreamCodec.of(
         (buf, msg) -> {
            ComponentSerialization.STREAM_CODEC.encode(buf, msg.animation);
            buf.writeInt(msg.target);
            buf.writeBoolean(msg.override);
         },
         buf -> new SetupAnimationsProcedure.GoreEditionModAnimationMessage(
               (Component)ComponentSerialization.STREAM_CODEC.decode(buf), buf.readInt(), buf.readBoolean()
            )
      );
      Component animation;
      int target;
      boolean override;

      public GoreEditionModAnimationMessage(Component animation, int target, boolean override) {
         this.animation = animation;
         this.target = target;
         this.override = override;
      }

      public Type<? extends CustomPacketPayload> type() {
         return TYPE;
      }

      public static void handler(SetupAnimationsProcedure.GoreEditionModAnimationMessage message, IPayloadContext context) {
         context.enqueueWork(
            () -> {
               Level level = Minecraft.getInstance().player.level();
               if (level.getEntity(message.target) != null) {
                  Player player = (Player)level.getEntity(message.target);
                  if (player instanceof AbstractClientPlayer player_) {
                     ModifierLayer<IAnimation> animation = (ModifierLayer<IAnimation>)PlayerAnimationAccess.getPlayerAssociatedData(player_)
                        .get(ResourceLocation.fromNamespaceAndPath("gore_edition", "player_animation"));
                     if (animation != null && (message.override || !animation.isActive())) {
                        animation.setAnimation(
                           new KeyframeAnimationPlayer(
                              (KeyframeAnimation)PlayerAnimationRegistry.getAnimation(
                                 ResourceLocation.fromNamespaceAndPath("gore_edition", message.animation.getString())
                              )
                           )
                        );
                     }
                  }
               }
            }
         );
      }

      @SubscribeEvent
      public static void registerMessage(RegisterPayloadHandlersEvent event) {
         PayloadRegistrar registrar = event.registrar("1");
         registrar.playToClient(TYPE, STREAM_CODEC, SetupAnimationsProcedure.GoreEditionModAnimationMessage::handler);
      }
   }
}
