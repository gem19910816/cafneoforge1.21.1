package net.mcreator.dyairdrop.network;

import java.util.function.Consumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.attachment.IAttachmentHolder;
import net.neoforged.neoforge.attachment.IAttachmentSerializer;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

/**
 * Player / map / world variables for dyairdrop.
 *
 * NeoForge 1.21.1 has no {@code CapabilityManager} player capabilities and no
 * {@code LazyOptional}, so the old Forge capability is expressed as a data
 * attachment.  Call sites keep the shape they had in the MCreator source:
 * {@code DyairdropModVariables.get(entity).field} and
 * {@code DyairdropModVariables.with(entity, vars -> ...)}.
 */
public class DyairdropModVariables {
   public static final DeferredRegister<AttachmentType<?>> REGISTRY =
      DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, "dyairdrop");

   public static final DeferredHolder<AttachmentType<?>, AttachmentType<PlayerVariables>> PLAYER_VARIABLES =
      REGISTRY.register("player_variables", () -> AttachmentType.builder(PlayerVariables::new)
         .serialize(new IAttachmentSerializer<CompoundTag, PlayerVariables>() {
            public PlayerVariables read(IAttachmentHolder holder, CompoundTag tag, HolderLookup.Provider provider) {
               PlayerVariables variables = new PlayerVariables();
               variables.readNBT(tag);
               return variables;
            }

            public CompoundTag write(PlayerVariables attachment, HolderLookup.Provider provider) {
               return attachment.writeNBT();
            }
         })
         .copyOnDeath()
         .build());

   public DyairdropModVariables() {
   }

   public static PlayerVariables get(Entity entity) {
      if (entity == null) {
         return new PlayerVariables();
      }
      return entity.getData(PLAYER_VARIABLES.get());
   }

   public static void with(Entity entity, Consumer<PlayerVariables> action) {
      if (entity == null) {
         return;
      }
      action.accept(entity.getData(PLAYER_VARIABLES.get()));
   }

   public static boolean has(Entity entity) {
      return entity != null && entity.hasData(PLAYER_VARIABLES.get());
   }

   @EventBusSubscriber(
      modid = "dyairdrop"
   )
   public static class EventBusVariableHandlers {
      public EventBusVariableHandlers() {
      }

      @SubscribeEvent
      public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
         if (event.getEntity() != null && !event.getEntity().level().isClientSide()) {
            get(event.getEntity()).syncPlayerVariables(event.getEntity());
         }
      }

      @SubscribeEvent
      public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
         if (event.getEntity() != null && !event.getEntity().level().isClientSide()) {
            get(event.getEntity()).syncPlayerVariables(event.getEntity());
         }
      }

      @SubscribeEvent
      public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
         if (event.getEntity() != null && !event.getEntity().level().isClientSide()) {
            get(event.getEntity()).syncPlayerVariables(event.getEntity());
         }
      }

      @SubscribeEvent
      public static void clonePlayer(PlayerEvent.Clone event) {
         if (event.getOriginal() == null || event.getEntity() == null) {
            return;
         }
         PlayerVariables original = get(event.getOriginal());
         PlayerVariables clone = get(event.getEntity());
         clone.airdroploot = original.airdroploot;
         clone.airdropblock = original.airdropblock;
         clone.keyre = original.keyre;
         if (!event.isWasDeath()) {
            clone.password = original.password;
            clone.showlight = original.showlight;
            clone.pw = original.pw;
            clone.keyticking = original.keyticking;
            clone.passwordre = original.passwordre;
         }
      }

      @SubscribeEvent
      public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
         if (event.getEntity() == null || event.getEntity().level().isClientSide()) {
            return;
         }
         MapVariables mapdata = MapVariables.get(event.getEntity().level());
         WorldVariables worlddata = WorldVariables.get(event.getEntity().level());
         if (mapdata != null) {
            PacketDistributor.sendToPlayer((ServerPlayer)event.getEntity(), new SavedDataSyncMessage(0, mapdata));
         }
         if (worlddata != null) {
            PacketDistributor.sendToPlayer((ServerPlayer)event.getEntity(), new SavedDataSyncMessage(1, worlddata));
         }
      }

      @SubscribeEvent
      public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
         if (event.getEntity() == null || event.getEntity().level().isClientSide()) {
            return;
         }
         WorldVariables worlddata = WorldVariables.get(event.getEntity().level());
         if (worlddata != null) {
            PacketDistributor.sendToPlayer((ServerPlayer)event.getEntity(), new SavedDataSyncMessage(1, worlddata));
         }
      }
   }

   public static class MapVariables extends SavedData {
      public static final String DATA_NAME = "dyairdrop_mapvars";
      public static MapVariables clientSide = new MapVariables();

      public MapVariables() {
      }

      public static MapVariables load(CompoundTag tag, HolderLookup.Provider provider) {
         MapVariables data = new MapVariables();
         data.read(tag);
         return data;
      }

      public void read(CompoundTag nbt) {
      }

      @Override
      public CompoundTag save(CompoundTag nbt, HolderLookup.Provider provider) {
         return nbt;
      }

      public void syncData(LevelAccessor world) {
         this.setDirty();
         if (world instanceof Level level && !level.isClientSide()) {
            PacketDistributor.sendToAllPlayers(new SavedDataSyncMessage(0, this));
         }
      }

      public static MapVariables get(LevelAccessor world) {
         if (world instanceof ServerLevelAccessor serverLevelAcc
            && serverLevelAcc.getLevel().getServer() != null
            && serverLevelAcc.getLevel().getServer().getLevel(Level.OVERWORLD) != null) {
            return serverLevelAcc.getLevel()
               .getServer()
               .getLevel(Level.OVERWORLD)
               .getDataStorage()
               .computeIfAbsent(new SavedData.Factory<>(MapVariables::new, MapVariables::load), DATA_NAME);
         }
         return clientSide;
      }
   }

   public static class PlayerVariables {
      public String airdroploot = "";
      public String airdropblock = "";
      public String password = "";
      public double showlight = 0.0;
      public String pw = "";
      public double keyticking = 0.0;
      public String passwordre = "";
      public String keyre = "";

      public PlayerVariables() {
      }

      public void syncPlayerVariables(Entity entity) {
         if (entity instanceof ServerPlayer serverPlayer) {
            PacketDistributor.sendToPlayer(serverPlayer, new PlayerVariablesSyncMessage(this));
         }
      }

      public CompoundTag writeNBT() {
         CompoundTag nbt = new CompoundTag();
         nbt.putString("airdroploot", this.airdroploot);
         nbt.putString("airdropblock", this.airdropblock);
         nbt.putString("password", this.password);
         nbt.putDouble("showlight", this.showlight);
         nbt.putString("pw", this.pw);
         nbt.putDouble("keyticking", this.keyticking);
         nbt.putString("passwordre", this.passwordre);
         nbt.putString("keyre", this.keyre);
         return nbt;
      }

      public void readNBT(Tag tag) {
         if (!(tag instanceof CompoundTag nbt)) {
            return;
         }
         this.airdroploot = nbt.getString("airdroploot");
         this.airdropblock = nbt.getString("airdropblock");
         this.password = nbt.getString("password");
         this.showlight = nbt.getDouble("showlight");
         this.pw = nbt.getString("pw");
         this.keyticking = nbt.getDouble("keyticking");
         this.passwordre = nbt.getString("passwordre");
         this.keyre = nbt.getString("keyre");
      }
   }

   public static class PlayerVariablesSyncMessage implements CustomPacketPayload {
      public static final CustomPacketPayload.Type<PlayerVariablesSyncMessage> TYPE =
         new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("dyairdrop", "player_variables_sync"));
      public static final StreamCodec<RegistryFriendlyByteBuf, PlayerVariablesSyncMessage> STREAM_CODEC = StreamCodec.of(
         (buf, message) -> buf.writeNbt(message.data.writeNBT()),
         buf -> {
            CompoundTag tag = buf.readNbt();
            PlayerVariables variables = new PlayerVariables();
            if (tag != null) {
               variables.readNBT(tag);
            }
            return new PlayerVariablesSyncMessage(variables);
         });

      private final PlayerVariables data;

      public PlayerVariablesSyncMessage(PlayerVariables data) {
         this.data = data;
      }

      public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
         return TYPE;
      }

      public static void handle(PlayerVariablesSyncMessage message, net.neoforged.neoforge.network.handling.IPayloadContext context) {
         context.enqueueWork(() -> {
            // context.player() is the local player on a client-bound payload; using
            // Minecraft.getInstance() here would pull client classes into the
            // dedicated-server class load.
            Entity player = context.player();
            if (player == null) {
               return;
            }
            PlayerVariables variables = get(player);
            variables.airdroploot = message.data.airdroploot;
            variables.airdropblock = message.data.airdropblock;
            variables.password = message.data.password;
            variables.showlight = message.data.showlight;
            variables.pw = message.data.pw;
            variables.keyticking = message.data.keyticking;
            variables.passwordre = message.data.passwordre;
            variables.keyre = message.data.keyre;
         });
      }
   }

   public static class SavedDataSyncMessage implements CustomPacketPayload {
      public static final CustomPacketPayload.Type<SavedDataSyncMessage> TYPE =
         new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath("dyairdrop", "saved_data_sync"));
      public static final StreamCodec<RegistryFriendlyByteBuf, SavedDataSyncMessage> STREAM_CODEC = StreamCodec.of(
         (buf, message) -> {
            buf.writeInt(message.type);
            buf.writeBoolean(message.data != null);
            if (message.data != null) {
               buf.writeNbt(message.data.save(new CompoundTag(), null));
            }
         },
         buf -> {
            int type = buf.readInt();
            SavedData data = null;
            if (buf.readBoolean()) {
               CompoundTag nbt = buf.readNbt();
               if (nbt != null) {
                  data = type == 0 ? MapVariables.load(nbt, null) : WorldVariables.load(nbt, null);
               }
            }
            return new SavedDataSyncMessage(type, data);
         });

      private final int type;
      private SavedData data;

      public SavedDataSyncMessage(int type, SavedData data) {
         this.type = type;
         this.data = data;
      }

      public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
         return TYPE;
      }

      public static void handle(SavedDataSyncMessage message, net.neoforged.neoforge.network.handling.IPayloadContext context) {
         context.enqueueWork(() -> {
            if (message.data == null) {
               return;
            }
            if (message.type == 0) {
               MapVariables.clientSide = (MapVariables)message.data;
            } else {
               WorldVariables.clientSide = (WorldVariables)message.data;
            }
         });
      }
   }

   public static class WorldVariables extends SavedData {
      public static final String DATA_NAME = "dyairdrop_worldvars";
      public double airdropcool = 0.0;
      public static WorldVariables clientSide = new WorldVariables();

      public WorldVariables() {
      }

      public static WorldVariables load(CompoundTag tag, HolderLookup.Provider provider) {
         WorldVariables data = new WorldVariables();
         data.read(tag);
         return data;
      }

      public void read(CompoundTag nbt) {
         this.airdropcool = nbt.getDouble("airdropcool");
      }

      @Override
      public CompoundTag save(CompoundTag nbt, HolderLookup.Provider provider) {
         nbt.putDouble("airdropcool", this.airdropcool);
         return nbt;
      }

      public void syncData(LevelAccessor world) {
         this.setDirty();
         if (world instanceof ServerLevel level && !level.isClientSide()) {
            PacketDistributor.sendToPlayersInDimension(level, new SavedDataSyncMessage(1, this));
         }
      }

      public static WorldVariables get(LevelAccessor world) {
         if (world instanceof ServerLevel level) {
            return level.getDataStorage()
               .computeIfAbsent(new SavedData.Factory<>(WorldVariables::new, WorldVariables::load), DATA_NAME);
         }
         return clientSide;
      }
   }
}
