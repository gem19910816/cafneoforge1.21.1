package com.carrion.doomsdaycontainers;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 末日装饰：容器附属 (Doomsday Containers)
 *
 * 原理：末日装饰里的"箱子/柜子/货架/垃圾桶…"原本只是贴图方块，没有方块实体。
 * 本附属用 Mixin 给这些方块类补上 {@code EntityBlock} 接口（不修改原模组任何文件），
 * 再为每个方块注册一个方块实体类型 + 物品处理器能力，于是它们就成了真正的容器：
 * 右键开原版箱子界面、内容持久化、漏斗/传送带/管道可以存取、比较器有红石信号、破坏掉落内容。
 */
@Mod(DoomsdayContainers.MODID)
public class DoomsdayContainers {
    public static final String MODID = "doomsdaycontainers";
    public static final String TARGET_MODID = "doomsday_decoration";
    public static final Logger LOGGER = LogManager.getLogger("DoomsdayContainers");

    public DoomsdayContainers(IEventBus modEventBus) {
        ContainerTargets.register(modEventBus);
        modEventBus.addListener(this::registerCapabilities);
        NeoForge.EVENT_BUS.addListener(DoomsdayContainersCommand::register);
    }

    /** 让漏斗 / 溜槽 / 物流管道 / 其他模组能通过 NeoForge 的 ItemHandler 能力存取这些容器。 */
    private void registerCapabilities(RegisterCapabilitiesEvent event) {
        for (BlockEntityType<GenericContainerBlockEntity> type : ContainerTargets.registeredTypes()) {
            event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, type,
                    (blockEntity, side) -> side == null
                            ? new InvWrapper(blockEntity)                  // 无方向访问（部分管道/自动化）
                            : new SidedInvWrapper(blockEntity, side));     // 指定面访问（漏斗等）
        }
    }
}
