package net.gem19910816.dyairdrop.network;

import java.util.function.Supplier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.gem19910816.dyairdrop.DyairdropMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.saveddata.SavedData;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.common.util.INBTSerializable;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class DyairdropModVariables {
	public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPES = DeferredRegister
			.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, DyairdropMod.MODID);

	public static final Supplier<AttachmentType<PlayerVariables>> PLAYER_VARIABLES_ATTACHMENT = ATTACHMENT_TYPES.register(
			"player_variables",
			() -> AttachmentType.builder(PlayerVariables::new).serialize(PlayerVariables.CODEC).build());

	public static class EventBusVariableHandlers {
		@SubscribeEvent
		public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				event.getEntity().getData(PLAYER_VARIABLES_ATTACHMENT.get()).syncPlayerVariables(event.getEntity());
			}
		}

		@SubscribeEvent
		public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				event.getEntity().getData(PLAYER_VARIABLES_ATTACHMENT.get()).syncPlayerVariables(event.getEntity());
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				event.getEntity().getData(PLAYER_VARIABLES_ATTACHMENT.get()).syncPlayerVariables(event.getEntity());
			}
		}

		@SubscribeEvent
		public static void clonePlayer(PlayerEvent.Clone event) {
			PlayerVariables original = event.getOriginal().getData(PLAYER_VARIABLES_ATTACHMENT.get());
			PlayerVariables clone = event.getEntity().getData(PLAYER_VARIABLES_ATTACHMENT.get());
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
			if (!event.getEntity().level().isClientSide()) {
				SavedData mapdata = DyairdropModVariables.MapVariables.get(event.getEntity().level());
				SavedData worlddata = DyairdropModVariables.WorldVariables.get(event.getEntity().level());
				if (mapdata != null) {
					PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
							new SavedDataSyncPayload(0, mapdata.save(new CompoundTag(), event.getEntity().level().registryAccess())));
				}

				if (worlddata != null) {
					PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
							new SavedDataSyncPayload(1, worlddata.save(new CompoundTag(), event.getEntity().level().registryAccess())));
				}
			}
		}

		@SubscribeEvent
		public static void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
			if (!event.getEntity().level().isClientSide()) {
				SavedData worlddata = DyairdropModVariables.WorldVariables.get(event.getEntity().level());
				if (worlddata != null) {
					PacketDistributor.sendToPlayer((ServerPlayer) event.getEntity(),
							new SavedDataSyncPayload(1, worlddata.save(new CompoundTag(), event.getEntity().level().registryAccess())));
				}
			}
		}
	}

	public static class MapVariables extends SavedData {
		public static final String DATA_NAME = "dyairdrop_mapvars";
		static DyairdropModVariables.MapVariables clientSide = new DyairdropModVariables.MapVariables();

		public static DyairdropModVariables.MapVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
			DyairdropModVariables.MapVariables data = new DyairdropModVariables.MapVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
		}

		@Override
		public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof Level level && !level.isClientSide()) {
				PacketDistributor.sendToAllPlayers(new SavedDataSyncPayload(0, this.save(new CompoundTag(), level.registryAccess())));
			}
		}

		public static DyairdropModVariables.MapVariables get(LevelAccessor world) {
			return world instanceof ServerLevelAccessor serverLevelAcc
					? serverLevelAcc.getServer()
							.overworld()
							.getDataStorage()
							.computeIfAbsent(new SavedData.Factory<>(DyairdropModVariables.MapVariables::new,
									DyairdropModVariables.MapVariables::load, null), DATA_NAME)
					: clientSide;
		}
	}

	public static class PlayerVariables implements INBTSerializable<CompoundTag> {
		public String airdroploot = "";
		public String airdropblock = "";
		public String password = "";
		public double showlight = 0.0;
		public String pw = "";
		public double keyticking = 0.0;
		public String passwordre = "";
		public String keyre = "";

		public static final Codec<PlayerVariables> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				Codec.STRING.optionalFieldOf("airdroploot", "").forGetter(v -> v.airdroploot),
				Codec.STRING.optionalFieldOf("airdropblock", "").forGetter(v -> v.airdropblock),
				Codec.STRING.optionalFieldOf("password", "").forGetter(v -> v.password),
				Codec.DOUBLE.optionalFieldOf("showlight", 0.0).forGetter(v -> v.showlight),
				Codec.STRING.optionalFieldOf("pw", "").forGetter(v -> v.pw),
				Codec.DOUBLE.optionalFieldOf("keyticking", 0.0).forGetter(v -> v.keyticking),
				Codec.STRING.optionalFieldOf("passwordre", "").forGetter(v -> v.passwordre),
				Codec.STRING.optionalFieldOf("keyre", "").forGetter(v -> v.keyre)).apply(instance, PlayerVariables::new));

		public PlayerVariables() {
		}

		private PlayerVariables(String airdroploot, String airdropblock, String password, double showlight, String pw,
				double keyticking, String passwordre, String keyre) {
			this.airdroploot = airdroploot;
			this.airdropblock = airdropblock;
			this.password = password;
			this.showlight = showlight;
			this.pw = pw;
			this.keyticking = keyticking;
			this.passwordre = passwordre;
			this.keyre = keyre;
		}

		/** Compatibility helper: keeps MCreator-style ifPresent writes working with attachments. */
		public void ifPresentData(java.util.function.Consumer<PlayerVariables> action) {
			action.accept(this);
		}

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayer serverPlayer) {
				PacketDistributor.sendToPlayer(serverPlayer, new PlayerVariablesSyncPayload(this.serializeNBT(serverPlayer.registryAccess())));
			}
		}

		@Override
		public CompoundTag serializeNBT(HolderLookup.Provider provider) {
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

		@Override
		public void deserializeNBT(HolderLookup.Provider provider, CompoundTag nbt) {
			this.airdroploot = nbt.getString("airdroploot");
			this.airdropblock = nbt.getString("airdropblock");
			this.password = nbt.getString("password");
			this.showlight = nbt.getDouble("showlight");
			this.pw = nbt.getString("pw");
			this.keyticking = nbt.getDouble("keyticking");
			this.passwordre = nbt.getString("passwordre");
			this.keyre = nbt.getString("keyre");
		}

		public static PlayerVariables fromNBT(CompoundTag nbt) {
			PlayerVariables variables = new PlayerVariables();
			variables.deserializeNBT(null, nbt);
			return variables;
		}
	}

	public static class WorldVariables extends SavedData {
		public static final String DATA_NAME = "dyairdrop_worldvars";
		public double airdropcool = 0.0;
		static DyairdropModVariables.WorldVariables clientSide = new DyairdropModVariables.WorldVariables();

		public static DyairdropModVariables.WorldVariables load(CompoundTag tag, HolderLookup.Provider lookupProvider) {
			DyairdropModVariables.WorldVariables data = new DyairdropModVariables.WorldVariables();
			data.read(tag);
			return data;
		}

		public void read(CompoundTag nbt) {
			this.airdropcool = nbt.getDouble("airdropcool");
		}

		@Override
		public CompoundTag save(CompoundTag nbt, HolderLookup.Provider lookupProvider) {
			nbt.putDouble("airdropcool", this.airdropcool);
			return nbt;
		}

		public void syncData(LevelAccessor world) {
			this.setDirty();
			if (world instanceof ServerLevel level && !level.isClientSide()) {
				PacketDistributor.sendToPlayersInDimension(level, new SavedDataSyncPayload(1, this.save(new CompoundTag(), level.registryAccess())));
			}
		}

		public static DyairdropModVariables.WorldVariables get(LevelAccessor world) {
			return world instanceof ServerLevel level
					? level.getDataStorage().computeIfAbsent(new SavedData.Factory<>(DyairdropModVariables.WorldVariables::new,
							DyairdropModVariables.WorldVariables::load, null), DATA_NAME)
					: clientSide;
		}
	}
}
