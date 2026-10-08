package com.carrion.doomsdaycontainers;

import com.mojang.brigadier.context.CommandContext;
import java.util.Map;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

/**
 * /doomsdaycontainers stats     —— 看看容器化生效了多少个方块
 * /doomsdaycontainers selftest  —— 服务端自检：逐个构造界面，检查行数/格子数/库存绑定是否正确
 *
 * 自检是为专门服务器准备的：不需要客户端就能确认"界面能不能开、开的是几行、绑没绑上库存"。
 */
public final class DoomsdayContainersCommand {
    private DoomsdayContainersCommand() {}

    public static void register(RegisterCommandsEvent event) {
        event.getDispatcher().register(Commands.literal("doomsdaycontainers")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("stats").executes(DoomsdayContainersCommand::stats))
                .then(Commands.literal("sounds").executes(DoomsdayContainersCommand::sounds))
                .then(Commands.literal("selftest").executes(DoomsdayContainersCommand::selftest))
                .executes(DoomsdayContainersCommand::stats));
    }

    private static int stats(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        int configured = ContainerTargets.configuredTargets().size();
        int existing = ContainerTargets.existingContainers().size();
        int registered = ContainerTargets.registeredTypes().size();
        source.sendSuccess(() -> Component.literal(
                "[末日容器] 清单目标 " + configured + " 个；本附属注册方块实体 " + registered
                        + " 个；原模组自带方块实体 " + existing + " 个"), false);
        return configured;
    }

    /** /doomsdaycontainers sounds —— 列出每个方块用的是哪一对开关音效。 */
    private static int sounds(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        java.util.Set<String> distinct = new java.util.TreeSet<>();
        for (ResourceLocation id : ContainerTargets.configuredTargets().keySet()) {
            Block block = BuiltInRegistries.BLOCK.get(id);
            String description = ContainerSounds.describe(block);
            distinct.add(description);
            DoomsdayContainers.LOGGER.info("DDC_SOUND {} 类别={} 音效={}",
                    id, ContainerSounds.categoryOf(block), description);
            source.sendSuccess(() -> Component.literal("  " + id + " → " + description), false);
        }
        int count = distinct.size();
        source.sendSuccess(() -> Component.literal(
                "[末日容器] 共 " + ContainerTargets.configuredTargets().size() + " 个容器，用了 " + count + " 种不同的开关音效"), false);
        return count;
    }

    private static int selftest(CommandContext<CommandSourceStack> context) {
        CommandSourceStack source = context.getSource();
        ServerLevel level = source.getLevel();
        Player probe = FakePlayerFactory.getMinecraft(level);
        Inventory inventory = probe.getInventory();

        int ok = 0;
        int fail = 0;
        for (Map.Entry<ResourceLocation, Integer> entry : ContainerTargets.configuredTargets().entrySet()) {
            ResourceLocation id = entry.getKey();
            int slots = entry.getValue();
            int expectedRows = Mth.clamp((slots + 8) / 9, 1, 6);
            String tag = "DDC_SELFTEST " + id;
            try {
                Block block = BuiltInRegistries.BLOCK.get(id);
                BlockState state = block.defaultBlockState();
                BlockEntity be = block instanceof EntityBlock entityBlock
                        ? entityBlock.newBlockEntity(BlockPos.ZERO, state)
                        : null;
                if (be == null) {
                    fail++;
                    DoomsdayContainers.LOGGER.error("{} FAIL 没有方块实体", tag);
                    continue;
                }
                if (!(be instanceof MenuProvider provider)) {
                    fail++;
                    DoomsdayContainers.LOGGER.error("{} FAIL 方块实体不是 MenuProvider", tag);
                    continue;
                }
                AbstractContainerMenu menu = provider.createMenu(1, inventory, probe);
                if (menu == null) {
                    fail++;
                    DoomsdayContainers.LOGGER.error("{} FAIL 界面为 null", tag);
                    continue;
                }
                int containerSlots = menu.slots.size() - 36;
                int rows = menu instanceof ChestMenu chestMenu ? chestMenu.getRowCount() : -1;
                boolean bound = !(menu instanceof ChestMenu chestMenu) || chestMenu.getContainer() == be;
                MenuType<?> expected = switch (expectedRows) {
                    case 1 -> MenuType.GENERIC_9x1;
                    case 2 -> MenuType.GENERIC_9x2;
                    case 3 -> MenuType.GENERIC_9x3;
                    case 4 -> MenuType.GENERIC_9x4;
                    case 5 -> MenuType.GENERIC_9x5;
                    default -> MenuType.GENERIC_9x6;
                };
                boolean good = rows == expectedRows && containerSlots == slots && menu.getType() == expected && bound;

                // 开关音效：在世界上真的摆一个方块，走一遍真实的 startOpen / stopOpen（应当各派发一次声音）
                long soundBefore = ContainerSounds.PLAYED;
                BlockPos scratch = new BlockPos(8, 200, 8);
                boolean placed = false;
                try {
                    level.setBlock(scratch, state, 3);
                    placed = true;
                    if (level.getBlockEntity(scratch) instanceof Container container) {
                        container.startOpen(probe);
                        container.stopOpen(probe);
                    }
                } catch (Throwable ignored) {
                    // 音效检查失败不掩盖界面检查的结果
                } finally {
                    if (placed) level.removeBlock(scratch, false);
                }
                long sounds = ContainerSounds.PLAYED - soundBefore;
                boolean soundOk = sounds >= 2;
                good = good && soundOk;

                if (good) {
                    ok++;
                    DoomsdayContainers.LOGGER.info("{} OK slots={} menu={} rows={} 绑定库存={} 音效类别={} 开={} 关={} 派发={}",
                            tag, slots, menu.getType(), rows, bound, ContainerSounds.categoryOf(block),
                            ContainerSounds.idOf(ContainerSounds.LAST_OPEN), ContainerSounds.idOf(ContainerSounds.LAST_CLOSE), sounds);
                } else {
                    fail++;
                    DoomsdayContainers.LOGGER.error("{} FAIL 期望 slots={} rows={} menu={} 音效>=2；实际 slots={} rows={} menu={} 绑定={} 音效={}",
                            tag, slots, expectedRows, expected, containerSlots, rows, menu.getType(), bound, sounds);
                }
            } catch (Throwable t) {
                fail++;
                DoomsdayContainers.LOGGER.error("{} FAIL 异常 {}", tag, t.toString());
            }
        }
        int total = ok + fail;
        int finalOk = ok;
        int finalFail = fail;
        source.sendSuccess(() -> Component.literal(
                "[末日容器] 自检完成：" + finalOk + "/" + total + " 通过" + (finalFail > 0 ? "，失败 " + finalFail : "")), false);
        DoomsdayContainers.LOGGER.info("DDC_SELFTEST SUMMARY ok={} fail={} total={}", ok, fail, total);
        return ok;
    }
}
