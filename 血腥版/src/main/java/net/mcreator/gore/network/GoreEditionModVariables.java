package net.mcreator.gore.network;

import java.util.ArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload.Type;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedData.Factory;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.Clone;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerChangedDimensionEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadContext;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys;

@EventBusSubscriber(
   bus = Bus.MOD
)
public class GoreEditionModVariables {
   public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister.create(Keys.ATTACHMENT_TYPES, "gore_edition");
   public static final DeferredHolder<AttachmentType<?>, AttachmentType<GoreEditionModVariables.PlayerVariables>> PLAYER_VARIABLES = ATTACHMENT_TYPES.register(
      "player_variables", () -> AttachmentType.serializable(GoreEditionModVariables.PlayerVariables::new).build()
   );

   @SubscribeEvent
   public static void registerMessages(RegisterPayloadHandlersEvent event) {
      PayloadRegistrar registrar = event.registrar("1");
      registrar.playToClient(
         GoreEditionModVariables.SavedDataSyncMessage.TYPE,
         GoreEditionModVariables.SavedDataSyncMessage.STREAM_CODEC,
         GoreEditionModVariables.SavedDataSyncMessage::handler
      );
      registrar.playToClient(
         GoreEditionModVariables.PlayerVariablesSyncMessage.TYPE,
         GoreEditionModVariables.PlayerVariablesSyncMessage.STREAM_CODEC,
         GoreEditionModVariables.PlayerVariablesSyncMessage::handler
      );
   }

   public static GoreEditionModVariables.PlayerVariables getPlayerVariables(Entity entity) {
      return (GoreEditionModVariables.PlayerVariables)entity.getData(PLAYER_VARIABLES);
   }

   @EventBusSubscriber
   public static class EventBusVariableHandlers {
      @SubscribeEvent
      public static void onPlayerLoggedInSyncPlayerVariables(PlayerLoggedInEvent event) {
         if (!event.getEntity().level().isClientSide()) {
            for (Entity entityiterator : new ArrayList<Player>(event.getEntity().level().players())) {
               GoreEditionModVariables.getPlayerVariables(entityiterator).syncPlayerVariables(entityiterator);
            }
         }
      }

      @SubscribeEvent
      public static void onPlayerRespawnedSyncPlayerVariables(PlayerRespawnEvent event) {
         if (!event.getEntity().level().isClientSide()) {
            for (Entity entityiterator : new ArrayList<Player>(event.getEntity().level().players())) {
               GoreEditionModVariables.getPlayerVariables(entityiterator).syncPlayerVariables(entityiterator);
            }
         }
      }

      @SubscribeEvent
      public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerChangedDimensionEvent event) {
         if (!event.getEntity().level().isClientSide()) {
            for (Entity entityiterator : new ArrayList<Player>(event.getEntity().level().players())) {
               GoreEditionModVariables.getPlayerVariables(entityiterator).syncPlayerVariables(entityiterator);
            }
         }
      }

      @SubscribeEvent
      public static void clonePlayer(Clone event) {
         if (!event.getOriginal().level().isClientSide()) {
            GoreEditionModVariables.PlayerVariables original = (GoreEditionModVariables.PlayerVariables)event.getOriginal()
               .getData(GoreEditionModVariables.PLAYER_VARIABLES);
            GoreEditionModVariables.PlayerVariables clone = (GoreEditionModVariables.PlayerVariables)event.getEntity()
               .getData(GoreEditionModVariables.PLAYER_VARIABLES);
            clone.center_y_player = original.center_y_player;
            clone.nsc_cattack = original.nsc_cattack;
            clone.nsc_order_for_c = original.nsc_order_for_c;
            clone.player_blood_particles = original.player_blood_particles;
            clone.remove_body_parts = original.remove_body_parts;
            clone.stop_x = original.stop_x;
            clone.stop_y = original.stop_y;
            clone.stop_z = original.stop_z;
            clone.warning_screen_frames = original.warning_screen_frames;
            clone.warning_screen_toggle = original.warning_screen_toggle;
            clone.third_hand_itemstack_store = original.third_hand_itemstack_store;
            clone.prev_xash_x = original.prev_xash_x;
            clone.prev_xash_y = original.prev_xash_y;
            clone.prev_xash_z = original.prev_xash_z;
            clone.ready_inf = original.ready_inf;
            if (!event.isWasDeath()) {
               clone.blood_in_screen_i = original.blood_in_screen_i;
               clone.blood_in_screen_i_1 = original.blood_in_screen_i_1;
               clone.blood_in_screen_i_amount = original.blood_in_screen_i_amount;
               clone.blood_screen_cooldown_i = original.blood_screen_cooldown_i;
               clone.click_ = original.click_;
               clone.cooldown_i = original.cooldown_i;
               clone.cooldown_i_l = original.cooldown_i_l;
               clone.deals_fangs_thieves_damage = original.deals_fangs_thieves_damage;
               clone.exarrack_monster_screen = original.exarrack_monster_screen;
               clone.exarrack_monster_screen_frames = original.exarrack_monster_screen_frames;
               clone.has_fangs_in_inventory = original.has_fangs_in_inventory;
               clone.hell_ashes_teleport_timer_for_player = original.hell_ashes_teleport_timer_for_player;
               clone.hell_cursed = original.hell_cursed;
               clone.hell_cursed_downgrade_timer_for_player = original.hell_cursed_downgrade_timer_for_player;
               clone.hell_cursed_for_player = original.hell_cursed_for_player;
               clone.night_vision_remaining = original.night_vision_remaining;
               clone.player_hengeyon_attacking = original.player_hengeyon_attacking;
               clone.player_hengeyon_targeting = original.player_hengeyon_targeting;
               clone.player_voodo_cursed = original.player_voodo_cursed;
               clone.stop_hell_cursed_for_player = original.stop_hell_cursed_for_player;
               clone.vitality_stealings = original.vitality_stealings;
               clone.third_hand_boolean_cooldown = original.third_hand_boolean_cooldown;
               clone.third_hand_number_cooldown = original.third_hand_number_cooldown;
            }

            for (Entity entityiterator : new ArrayList<Player>(event.getEntity().level().players())) {
               GoreEditionModVariables.getPlayerVariables(entityiterator).syncPlayerVariables(entityiterator);
            }
         }
      }

      @SubscribeEvent
      public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
         if (!event.getEntity().level().isClientSide()) {
            SavedData mapdata = GoreEditionModVariables.MapVariables.get(event.getEntity().level());
            SavedData worlddata = GoreEditionModVariables.WorldVariables.get(event.getEntity().level());
            if (mapdata != null) {
               PacketDistributor.sendToPlayer(
                  (ServerPlayer)event.getEntity(),
                  new GoreEditionModVariables.SavedDataSyncMessage(0, mapdata.save(new CompoundTag(), event.getEntity().level().registryAccess())),
                  new CustomPacketPayload[0]
               );
            }

            if (worlddata != null) {
               PacketDistributor.sendToPlayer(
                  (ServerPlayer)event.getEntity(),
                  new GoreEditionModVariables.SavedDataSyncMessage(1, worlddata.save(new CompoundTag(), event.getEntity().level().registryAccess())),
                  new CustomPacketPayload[0]
               );
            }
         }
      }

      @SubscribeEvent
      public static void onPlayerChangedDimension(PlayerChangedDimensionEvent event) {
         if (!event.getEntity().level().isClientSide()) {
            SavedData worlddata = GoreEditionModVariables.WorldVariables.get(event.getEntity().level());
            if (worlddata != null) {
               PacketDistributor.sendToPlayer(
                  (ServerPlayer)event.getEntity(),
                  new GoreEditionModVariables.SavedDataSyncMessage(1, worlddata.save(new CompoundTag(), event.getEntity().level().registryAccess())),
                  new CustomPacketPayload[0]
               );
            }
         }
      }
   }

   public static class MapVariables extends SavedData {
      public static final String DATA_NAME = "gore_edition_mapvars";
      public double hengeyon_died = 0.0;
      public double warning_screen_relaxing_ambiance = 0.0;
      static GoreEditionModVariables.MapVariables clientSide = new GoreEditionModVariables.MapVariables();

      public static GoreEditionModVariables.MapVariables load(CompoundTag tag) {
         GoreEditionModVariables.MapVariables data = new GoreEditionModVariables.MapVariables();
         data.read(tag);
         return data;
      }

      public void read(CompoundTag nbt) {
         this.hengeyon_died = nbt.getDouble("hengeyon_died");
         this.warning_screen_relaxing_ambiance = nbt.getDouble("warning_screen_relaxing_ambiance");
      }

      public CompoundTag save(CompoundTag nbt, Provider registries) {
         nbt.putDouble("hengeyon_died", this.hengeyon_died);
         nbt.putDouble("warning_screen_relaxing_ambiance", this.warning_screen_relaxing_ambiance);
         return nbt;
      }

      public void syncData(LevelAccessor world) {
         this.setDirty();
         if (world instanceof Level level && !world.isClientSide()) {
            PacketDistributor.sendToAllPlayers(
               new GoreEditionModVariables.SavedDataSyncMessage(0, this.save(new CompoundTag(), level.registryAccess())), new CustomPacketPayload[0]
            );
         }
      }

      public static GoreEditionModVariables.MapVariables get(LevelAccessor world) {
         return world instanceof ServerLevelAccessor serverLevelAcc
            ? (GoreEditionModVariables.MapVariables)serverLevelAcc.getLevel()
               .getServer()
               .getLevel(Level.OVERWORLD)
               .getDataStorage()
               .computeIfAbsent(new Factory<GoreEditionModVariables.MapVariables>(GoreEditionModVariables.MapVariables::new, (tag, reg) -> load(tag)), "gore_edition_mapvars")
            : clientSide;
      }
   }

   public static class PlayerVariables implements INBTSerializable<CompoundTag> {
      public boolean blood_in_screen_i = false;
      public boolean blood_in_screen_i_1 = false;
      public double blood_in_screen_i_amount = 0.0;
      public double blood_screen_cooldown_i = 0.0;
      public double center_y_player = 1.0;
      public double click_ = 0.0;
      public double cooldown_i = 0.0;
      public boolean cooldown_i_l = false;
      public double deals_fangs_thieves_damage = 0.0;
      public boolean exarrack_monster_screen = false;
      public double exarrack_monster_screen_frames = 0.0;
      public boolean has_fangs_in_inventory = false;
      public double hell_ashes_teleport_timer_for_player = 0.0;
      public double hell_cursed = 0.0;
      public double hell_cursed_downgrade_timer_for_player = 0.0;
      public double hell_cursed_for_player = 0.0;
      public double night_vision_remaining = 0.0;
      public boolean nsc_cattack = false;
      public double nsc_order_for_c = 0.0;
      public String player_blood_particles = "Generic";
      public double player_hengeyon_attacking = 0.0;
      public boolean player_hengeyon_targeting = false;
      public boolean player_voodo_cursed = false;
      public double remove_body_parts = 0.0;
      public boolean stop_hell_cursed_for_player = false;
      public double stop_x = 0.0;
      public double stop_y = 0.0;
      public double stop_z = 0.0;
      public double vitality_stealings = 0.0;
      public double warning_screen_frames = 0.0;
      public boolean warning_screen_toggle = false;
      public boolean third_hand_boolean_cooldown = false;
      public double third_hand_number_cooldown = 0.0;
      public ItemStack third_hand_itemstack_store = ItemStack.EMPTY;
      public double prev_xash_x = 0.0;
      public double prev_xash_y = 0.0;
      public double prev_xash_z = 0.0;
      public boolean ready_inf = false;

      public void syncPlayerVariables(Entity entity) {
         if (entity instanceof ServerPlayer serverPlayer && entity.level() instanceof ServerLevel serverLevel) {
            PacketDistributor.sendToPlayersInDimension(
               serverLevel,
               new GoreEditionModVariables.PlayerVariablesSyncMessage(this.writeNBT(serverLevel.registryAccess()), entity.getId()),
               new CustomPacketPayload[0]
            );
         }
      }

      public CompoundTag writeNBT(Provider registries) {
         CompoundTag nbt = new CompoundTag();
         nbt.putBoolean("blood_in_screen_i", this.blood_in_screen_i);
         nbt.putBoolean("blood_in_screen_i_1", this.blood_in_screen_i_1);
         nbt.putDouble("blood_in_screen_i_amount", this.blood_in_screen_i_amount);
         nbt.putDouble("blood_screen_cooldown_i", this.blood_screen_cooldown_i);
         nbt.putDouble("center_y_player", this.center_y_player);
         nbt.putDouble("click_", this.click_);
         nbt.putDouble("cooldown_i", this.cooldown_i);
         nbt.putBoolean("cooldown_i_l", this.cooldown_i_l);
         nbt.putDouble("deals_fangs_thieves_damage", this.deals_fangs_thieves_damage);
         nbt.putBoolean("exarrack_monster_screen", this.exarrack_monster_screen);
         nbt.putDouble("exarrack_monster_screen_frames", this.exarrack_monster_screen_frames);
         nbt.putBoolean("has_fangs_in_inventory", this.has_fangs_in_inventory);
         nbt.putDouble("hell_ashes_teleport_timer_for_player", this.hell_ashes_teleport_timer_for_player);
         nbt.putDouble("hell_cursed", this.hell_cursed);
         nbt.putDouble("hell_cursed_downgrade_timer_for_player", this.hell_cursed_downgrade_timer_for_player);
         nbt.putDouble("hell_cursed_for_player", this.hell_cursed_for_player);
         nbt.putDouble("night_vision_remaining", this.night_vision_remaining);
         nbt.putBoolean("nsc_cattack", this.nsc_cattack);
         nbt.putDouble("nsc_order_for_c", this.nsc_order_for_c);
         nbt.putString("player_blood_particles", this.player_blood_particles);
         nbt.putDouble("player_hengeyon_attacking", this.player_hengeyon_attacking);
         nbt.putBoolean("player_hengeyon_targeting", this.player_hengeyon_targeting);
         nbt.putBoolean("player_voodo_cursed", this.player_voodo_cursed);
         nbt.putDouble("remove_body_parts", this.remove_body_parts);
         nbt.putBoolean("stop_hell_cursed_for_player", this.stop_hell_cursed_for_player);
         nbt.putDouble("stop_x", this.stop_x);
         nbt.putDouble("stop_y", this.stop_y);
         nbt.putDouble("stop_z", this.stop_z);
         nbt.putDouble("vitality_stealings", this.vitality_stealings);
         nbt.putDouble("warning_screen_frames", this.warning_screen_frames);
         nbt.putBoolean("warning_screen_toggle", this.warning_screen_toggle);
         nbt.putBoolean("third_hand_boolean_cooldown", this.third_hand_boolean_cooldown);
         nbt.putDouble("third_hand_number_cooldown", this.third_hand_number_cooldown);
         // 1.21: ItemStack#save throws "Cannot encode empty ItemStack" for an empty stack.
         // 1.20.1 tolerated it. The field defaults to ItemStack.EMPTY, so every player's first
         // login used to fail inside PlayerLoggedInEvent -> placeNewPlayer -> connection closed.
         // Absent key <=> empty stack; readNBT already falls back to ItemStack.EMPTY.
         if (!this.third_hand_itemstack_store.isEmpty()) {
            nbt.put("third_hand_itemstack_store", this.third_hand_itemstack_store.save(registries, new CompoundTag()));
         }
         nbt.putDouble("prev_xash_x", this.prev_xash_x);
         nbt.putDouble("prev_xash_y", this.prev_xash_y);
         nbt.putDouble("prev_xash_z", this.prev_xash_z);
         nbt.putBoolean("ready_inf", this.ready_inf);
         return nbt;
      }

      public void readNBT(Provider registries, CompoundTag nbt) {
         this.blood_in_screen_i = nbt.getBoolean("blood_in_screen_i");
         this.blood_in_screen_i_1 = nbt.getBoolean("blood_in_screen_i_1");
         this.blood_in_screen_i_amount = nbt.getDouble("blood_in_screen_i_amount");
         this.blood_screen_cooldown_i = nbt.getDouble("blood_screen_cooldown_i");
         this.center_y_player = nbt.getDouble("center_y_player");
         this.click_ = nbt.getDouble("click_");
         this.cooldown_i = nbt.getDouble("cooldown_i");
         this.cooldown_i_l = nbt.getBoolean("cooldown_i_l");
         this.deals_fangs_thieves_damage = nbt.getDouble("deals_fangs_thieves_damage");
         this.exarrack_monster_screen = nbt.getBoolean("exarrack_monster_screen");
         this.exarrack_monster_screen_frames = nbt.getDouble("exarrack_monster_screen_frames");
         this.has_fangs_in_inventory = nbt.getBoolean("has_fangs_in_inventory");
         this.hell_ashes_teleport_timer_for_player = nbt.getDouble("hell_ashes_teleport_timer_for_player");
         this.hell_cursed = nbt.getDouble("hell_cursed");
         this.hell_cursed_downgrade_timer_for_player = nbt.getDouble("hell_cursed_downgrade_timer_for_player");
         this.hell_cursed_for_player = nbt.getDouble("hell_cursed_for_player");
         this.night_vision_remaining = nbt.getDouble("night_vision_remaining");
         this.nsc_cattack = nbt.getBoolean("nsc_cattack");
         this.nsc_order_for_c = nbt.getDouble("nsc_order_for_c");
         this.player_blood_particles = nbt.getString("player_blood_particles");
         this.player_hengeyon_attacking = nbt.getDouble("player_hengeyon_attacking");
         this.player_hengeyon_targeting = nbt.getBoolean("player_hengeyon_targeting");
         this.player_voodo_cursed = nbt.getBoolean("player_voodo_cursed");
         this.remove_body_parts = nbt.getDouble("remove_body_parts");
         this.stop_hell_cursed_for_player = nbt.getBoolean("stop_hell_cursed_for_player");
         this.stop_x = nbt.getDouble("stop_x");
         this.stop_y = nbt.getDouble("stop_y");
         this.stop_z = nbt.getDouble("stop_z");
         this.vitality_stealings = nbt.getDouble("vitality_stealings");
         this.warning_screen_frames = nbt.getDouble("warning_screen_frames");
         this.warning_screen_toggle = nbt.getBoolean("warning_screen_toggle");
         this.third_hand_boolean_cooldown = nbt.getBoolean("third_hand_boolean_cooldown");
         this.third_hand_number_cooldown = nbt.getDouble("third_hand_number_cooldown");
         // Only parse when the key is present: ItemStack.parse logs "Tried to load invalid
         // item: 'No key id in MapLike[{}]'" for an absent/empty tag, and the write side
         // omits the key entirely when the stored stack is empty.
         this.third_hand_itemstack_store = nbt.contains("third_hand_itemstack_store")
            ? (ItemStack)ItemStack.parse(registries, nbt.getCompound("third_hand_itemstack_store")).orElse(ItemStack.EMPTY)
            : ItemStack.EMPTY;
         this.prev_xash_x = nbt.getDouble("prev_xash_x");
         this.prev_xash_y = nbt.getDouble("prev_xash_y");
         this.prev_xash_z = nbt.getDouble("prev_xash_z");
         this.ready_inf = nbt.getBoolean("ready_inf");
      }

      public CompoundTag serializeNBT(Provider provider) {
         return this.writeNBT(provider);
      }

      public void deserializeNBT(Provider provider, CompoundTag nbt) {
         this.readNBT(provider, nbt);
      }
   }

   public static class PlayerVariablesSyncMessage implements CustomPacketPayload {
      public static final Type<GoreEditionModVariables.PlayerVariablesSyncMessage> TYPE = new Type(
         ResourceLocation.fromNamespaceAndPath("gore_edition", "player_variables_sync")
      );
      public static final StreamCodec<RegistryFriendlyByteBuf, GoreEditionModVariables.PlayerVariablesSyncMessage> STREAM_CODEC = StreamCodec.of(
         (buf, msg) -> {
            ByteBufCodecs.COMPOUND_TAG.encode(buf, msg.data);
            buf.writeInt(msg.target);
         }, buf -> new GoreEditionModVariables.PlayerVariablesSyncMessage((CompoundTag)ByteBufCodecs.COMPOUND_TAG.decode(buf), buf.readInt())
      );
      private final int target;
      private final CompoundTag data;

      public PlayerVariablesSyncMessage(CompoundTag data, int entityid) {
         this.data = data;
         this.target = entityid;
      }

      public Type<? extends CustomPacketPayload> type() {
         return TYPE;
      }

      public static void handler(GoreEditionModVariables.PlayerVariablesSyncMessage message, IPayloadContext context) {
         context.enqueueWork(
            () -> {
               if (Minecraft.getInstance().player != null) {
                  GoreEditionModVariables.PlayerVariables variables = GoreEditionModVariables.getPlayerVariables(
                     Minecraft.getInstance().player.level().getEntity(message.target)
                  );
                  variables.readNBT(Minecraft.getInstance().player.level().registryAccess(), message.data);
               }
            }
         );
      }
   }

   public static class SavedDataSyncMessage implements CustomPacketPayload {
      public static final Type<GoreEditionModVariables.SavedDataSyncMessage> TYPE = new Type(
         ResourceLocation.fromNamespaceAndPath("gore_edition", "saved_data_sync")
      );
      public static final StreamCodec<RegistryFriendlyByteBuf, GoreEditionModVariables.SavedDataSyncMessage> STREAM_CODEC = StreamCodec.of((buf, msg) -> {
         buf.writeInt(msg.type);
         ByteBufCodecs.COMPOUND_TAG.encode(buf, msg.data);
      }, buf -> new GoreEditionModVariables.SavedDataSyncMessage(buf.readInt(), (CompoundTag)ByteBufCodecs.COMPOUND_TAG.decode(buf)));
      private final int type;
      private final CompoundTag data;

      public SavedDataSyncMessage(int type, CompoundTag data) {
         this.type = type;
         this.data = data;
      }

      public Type<? extends CustomPacketPayload> type() {
         return TYPE;
      }

      public static void handler(GoreEditionModVariables.SavedDataSyncMessage message, IPayloadContext context) {
         context.enqueueWork(() -> {
            if (message.type == 0) {
               GoreEditionModVariables.MapVariables.clientSide = new GoreEditionModVariables.MapVariables();
               GoreEditionModVariables.MapVariables.clientSide.read(message.data);
            } else {
               GoreEditionModVariables.WorldVariables.clientSide = new GoreEditionModVariables.WorldVariables();
               GoreEditionModVariables.WorldVariables.clientSide.read(message.data);
            }
         });
      }
   }

   public static class WorldVariables extends SavedData {
      public static final String DATA_NAME = "gore_edition_worldvars";
      public double ashes_wind_tick = 0.0;
      static GoreEditionModVariables.WorldVariables clientSide = new GoreEditionModVariables.WorldVariables();

      public static GoreEditionModVariables.WorldVariables load(CompoundTag tag) {
         GoreEditionModVariables.WorldVariables data = new GoreEditionModVariables.WorldVariables();
         data.read(tag);
         return data;
      }

      public void read(CompoundTag nbt) {
         this.ashes_wind_tick = nbt.getDouble("ashes_wind_tick");
      }

      public CompoundTag save(CompoundTag nbt, Provider registries) {
         nbt.putDouble("ashes_wind_tick", this.ashes_wind_tick);
         return nbt;
      }

      public void syncData(LevelAccessor world) {
         this.setDirty();
         if (world instanceof ServerLevel level) {
            PacketDistributor.sendToPlayersInDimension(
               level, new GoreEditionModVariables.SavedDataSyncMessage(1, this.save(new CompoundTag(), level.registryAccess())), new CustomPacketPayload[0]
            );
         }
      }

      public static GoreEditionModVariables.WorldVariables get(LevelAccessor world) {
         return world instanceof ServerLevel level
            ? (GoreEditionModVariables.WorldVariables)level.getDataStorage()
               .computeIfAbsent(new Factory<GoreEditionModVariables.WorldVariables>(GoreEditionModVariables.WorldVariables::new, (tag, reg) -> load(tag)), "gore_edition_worldvars")
            : clientSide;
      }
   }
}
