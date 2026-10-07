package cn.blockforge.generated.cropexpansion;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

/**
 * Create And Flesh_Crop Expansion（齿轮与腐肉作物扩展）
 * —— 一个像原版小麦一样可种植的作物模组。
 *
 * <p>本工程是 1.20.1 Forge 版（mod制作\1.20.1forge\药草作物）的
 * NeoForge 1.21.1 移植版。
 */
@Mod(CropExpansion.MOD_ID)
public final class CropExpansion {
    public static final String MOD_ID = "crop_expansion";
    /** Java 侧模组名；游戏内模组列表显示的标题见 neoforge.mods.toml 的 displayName。 */
    public static final String MOD_NAME = "Create And Flesh_Crop Expansion";

    /**
     * NeoForge 的模组构造器可以直接注入 mod 事件总线。
     * 1.20.1 Forge 用的 {@code FMLJavaModLoadingContext.get().getModEventBus()} 已被移除，
     * 所以这里改成构造器参数。
     */
    public CropExpansion(IEventBus modEventBus) {
        ModContent.BLOCKS.register(modEventBus);
        ModContent.ITEMS.register(modEventBus);
    }
}
