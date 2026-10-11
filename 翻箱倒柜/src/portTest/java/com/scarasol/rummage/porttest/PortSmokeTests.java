package com.scarasol.rummage.porttest;

import com.mojang.authlib.GameProfile;
import com.scarasol.itemrarity.data.RarityGrade;
import com.scarasol.itemrarity.data.RarityManager;
import com.scarasol.itemrarity.network.RarityGradePacket;
import com.scarasol.itemrarity.util.RarityGradeUtil;
import com.scarasol.rummage.RummageMod;
import com.scarasol.rummage.api.mixin.IRummageMenu;
import com.scarasol.rummage.api.mixin.IRummageable;
import com.scarasol.rummage.init.RummageAttributes;
import com.scarasol.rummage.network.HoverSlotPacket;
import com.scarasol.rummage.network.RummageActionPacket;
import com.scarasol.rummage.network.SyncRummageStatePacket;
import com.scarasol.rummage.util.CommonContainerUtil;
import io.netty.buffer.Unpooled;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.ChestBoat;
import net.minecraft.world.entity.vehicle.MinecartChest;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.storage.loot.LootTable;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayer;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.items.SlotItemHandler;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.network.handling.IPayloadContext;

import java.lang.reflect.Proxy;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.BitSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@EventBusSubscriber(modid = RummageMod.MODID)
public final class PortSmokeTests {
    private static int checks;

    @SubscribeEvent
    public static void run(ServerStartedEvent event) {
        if (!Boolean.getBoolean("rummage.portTests")) return;
        String result;
        try {
            test(event.getServer().overworld());
            result = "PASS: " + checks + " port smoke checks";
            RummageMod.LOGGER.info(result);
        } catch (Throwable error) {
            result = "FAIL after " + checks + " checks: " + error;
            RummageMod.LOGGER.error(result, error);
        }
        try {
            Files.writeString(Path.of("port-test-results.txt"), result + System.lineSeparator());
        } catch (Exception error) {
            RummageMod.LOGGER.error("Cannot write test results", error);
        }
        event.getServer().execute(() -> event.getServer().halt(false));
    }

    private static void test(ServerLevel level) {
        BlockPos pos = new BlockPos(0, 150, 0);
        level.setBlockAndUpdate(pos, Blocks.CHEST.defaultBlockState());
        ChestBlockEntity chest = (ChestBlockEntity) level.getBlockEntity(pos);
        check(chest instanceof IRummageable, "Chest mixin");
        ResourceKey<LootTable> loot = ResourceKey.create(Registries.LOOT_TABLE,
                ResourceLocation.withDefaultNamespace("chests/simple_dungeon"));
        chest.setLootTable(loot, 1L);
        IRummageable rummage = (IRummageable) chest;
        check(rummage.isNeedRummage(), "Loot table locks chest");
        chest.setLootTable(null);
        chest.setItem(0, new ItemStack(Items.DIAMOND));
        UUID id = rummage.getRummageableUUID();
        UUID completed = UUID.randomUUID();
        rummage.addFullyRummagedPlayer(completed);
        CompoundTag saved = chest.saveWithFullMetadata(level.registryAccess());
        BlockEntity restored = BlockEntity.loadStatic(pos, chest.getBlockState(), saved, level.registryAccess());
        check(restored instanceof IRummageable, "Restored chest mixin");
        IRummageable restoredRummage = (IRummageable) restored;
        check(restoredRummage.isNeedRummage(), "Lock persists in NBT");
        check(restoredRummage.getRummageableUUID().equals(id), "UUID persists in NBT");
        check(restoredRummage.getFullyRummagedPlayer().contains(completed), "Completed player persists");

        FakePlayer player = new FakePlayer(level, new GameProfile(UUID.randomUUID(), "PortTester"));
        check(player.getAttribute(RummageAttributes.RUMMAGE_MODIFIER) != null, "Player attributes registered");
        ChestMenu menu = ChestMenu.threeRows(1, player.getInventory(), chest);
        player.containerMenu = menu;
        check(menu instanceof IRummageMenu, "Menu mixin");
        check(!menu.slots.get(0).mayPickup(player), "Masked slot denies pickup");
        check(rummage.isSlotRummaged(player, 1), "Empty slot is already searched");
        InvWrapper wrapper = new InvWrapper(chest);
        check(wrapper.extractItem(0, 1, false).isEmpty(), "Automation cannot extract");
        check(!wrapper.insertItem(1, new ItemStack(Items.GOLD_INGOT), false).isEmpty(), "Automation cannot insert");
        SlotItemHandler slot = new SlotItemHandler(wrapper, 0, 0, 0);
        check(!slot.mayPickup(player), "Item handler slot cannot bypass lock");
        rummage.markSlotRummaged(player, 0);
        check(menu.slots.get(0).mayPickup(player), "Search unlocks pickup");
        check(slot.mayPickup(player), "Searched item handler slot works");
        check(CommonContainerUtil.getTarget(menu.slots.get(0), menu).entity() == rummage, "Slot resolves real container");

        MinecartChest cart = new MinecartChest(EntityType.CHEST_MINECART, level);
        cart.setLootTable(loot, 2L);
        check(((IRummageable) cart).isNeedRummage(), "Minecart loot locks");
        ChestBoat boat = new ChestBoat(EntityType.CHEST_BOAT, level);
        boat.setLootTable(loot);
        check(((IRummageable) boat).isNeedRummage(), "Chest boat loot locks");

        check(roundTrip(level, HoverSlotPacket.STREAM_CODEC, new HoverSlotPacket(-1)).slotIndex() == -1, "Hover packet codec");
        RummageActionPacket action = new RummageActionPacket(4, 1, 80);
        check(roundTrip(level, RummageActionPacket.STREAM_CODEC, action).equals(action), "Action packet codec");
        BitSet mask = new BitSet();
        mask.set(0);
        mask.set(88);
        check(roundTrip(level, SyncRummageStatePacket.STREAM_CODEC, new SyncRummageStatePacket(mask)).maskedMenuSlots().equals(mask), "Mask codec");
        var gradePacket = new RarityGradePacket("port_test", List.of(ResourceLocation.withDefaultNamespace("diamond")));
        check(roundTrip(level, RarityGradePacket.STREAM_CODEC, gradePacket).equals(gradePacket), "Rarity packet codec");

        RarityGrade grade = new RarityGrade("port_test", 1, null, "#FF0000", 1);
        grade.add(ResourceLocation.withDefaultNamespace("diamond"));
        RarityManager.registerRarityData(grade);
        RarityGradeUtil.INIT = true;
        IPayloadContext context = (IPayloadContext) Proxy.newProxyInstance(
                IPayloadContext.class.getClassLoader(), new Class<?>[]{IPayloadContext.class},
                (proxy, method, args) -> switch (method.getName()) {
                    case "player" -> player;
                    case "enqueueWork" -> {
                        ((Runnable) args[0]).run();
                        yield CompletableFuture.completedFuture(null);
                    }
                    default -> throw new UnsupportedOperationException(method.getName());
                });
        check(!player.hasPermissions(2), "Security test player is not operator");
        RarityGradePacket.handle(new RarityGradePacket("port_test", List.of()), context);
        check(grade.getAllId().size() == 1, "Unauthorized packet cannot mutate rarity");
        if (net.neoforged.fml.ModList.get().isLoaded("corpse")) {
            CompatSmokeTests.run(level, player);
            check(true, "Optional integrations pass");
        }
        level.removeBlock(pos, false);
    }

    private static <T> T roundTrip(ServerLevel level, StreamCodec<RegistryFriendlyByteBuf, T> codec, T packet) {
        RegistryFriendlyByteBuf buf = new RegistryFriendlyByteBuf(Unpooled.buffer(), level.registryAccess());
        try {
            codec.encode(buf, packet);
            T decoded = codec.decode(buf);
            check(!buf.isReadable(), "Codec consumes all bytes");
            return decoded;
        } finally {
            buf.release();
        }
    }

    private static void check(boolean condition, String message) {
        if (!condition) throw new AssertionError(message);
        checks++;
    }
}
