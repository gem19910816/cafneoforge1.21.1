package com.chaosz.tarkovstamina;

import com.chaosz.tarkovstamina.backpack.BackpackRegistration;
import com.chaosz.tarkovstamina.client.HudConfig;
import com.chaosz.tarkovstamina.condition.DepressionSystem;
import com.chaosz.tarkovstamina.condition.DiseaseSystem;
import com.chaosz.tarkovstamina.condition.PanicSystem;
import com.chaosz.tarkovstamina.item.BodyMonitorHandler;
import com.chaosz.tarkovstamina.item.StaminaEntities;
import com.chaosz.tarkovstamina.item.StaminaItems;
import com.chaosz.tarkovstamina.network.StaminaNetwork;
import com.chaosz.tarkovstamina.profession.FishingSystem;
import com.chaosz.tarkovstamina.profession.ProfessionSystem;
import com.chaosz.tarkovstamina.profession.SkillTerminalHandler;
import com.chaosz.tarkovstamina.stamina.ExerciseSystem;
import com.chaosz.tarkovstamina.stamina.InjectionSystem;
import com.chaosz.tarkovstamina.stamina.WeightSystem;
import com.chaosz.tarkovstamina.survival.PoopSystem;
import com.chaosz.tarkovstamina.survival.ShitballThrowHandler;
import com.chaosz.tarkovstamina.ui.StatusCommand;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static com.chaosz.tarkovstamina.backpack.BackpackRegistration.HIKING_BACKPACK;
import static com.chaosz.tarkovstamina.backpack.BackpackRegistration.MILITARY_BACKPACK;
import static com.chaosz.tarkovstamina.backpack.BackpackRegistration.SATCHEL;
import static com.chaosz.tarkovstamina.backpack.BackpackRegistration.SCHOOL_BAG;

/**
 * CAF 生存核心 主类。
 *
 * <p>1.21.1 NeoForge 去掉了 {@code FMLJavaModLoadingContext}：事件总线与模组容器
 * 直接作为构造参数注入，配置也改成从 {@link ModContainer} 注册。</p>
 */
@Mod(TarkovStamina.MOD_ID)
public final class TarkovStamina {
    public static final String MOD_ID = "tarkov_stamina";

    // 创造标签页
    private static final DeferredRegister<CreativeModeTab> TAB_REG =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> CAF_TAB = TAB_REG
            .register("tab", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + MOD_ID))
                    .icon(() -> new ItemStack(StaminaItems.SPECIAL_STRENGTH_INJECTION.get()))
                    .displayItems((params, output) -> {
                        // 体力系统物品
                        output.accept(StaminaItems.SPECIAL_STRENGTH_INJECTION.get());
                        output.accept(StaminaItems.BODY_MONITOR.get());
                        output.accept(StaminaItems.SHIT.get());
                        output.accept(StaminaItems.LAPTOP.get());
                        // 背包
                        output.accept(SATCHEL.get());
                        output.accept(SCHOOL_BAG.get());
                        output.accept(HIKING_BACKPACK.get());
                        output.accept(MILITARY_BACKPACK.get());
                    })
                    .build());

    public TarkovStamina(IEventBus modBus, ModContainer container) {
        IEventBus forgeBus = NeoForge.EVENT_BUS;

        // ── 网络 ──（1.21.1：在 RegisterPayloadHandlersEvent 里登记类型+编解码器+处理器）
        modBus.addListener(StaminaNetwork::register);

        // ── 物品注册 ──
        StaminaItems.REGISTRY.register(modBus);

        // ── 实体注册 ──
        StaminaEntities.REGISTRY.register(modBus);

        // ── 创造标签页 ──
        TAB_REG.register(modBus);

        // ── 体力核心 ──
        forgeBus.register(StaminaSystem.class);
        forgeBus.register(ExerciseSystem.class);
        forgeBus.register(WeightSystem.class);
        forgeBus.register(InjectionSystem.class);

        // ── 状态系统 ──
        forgeBus.register(DepressionSystem.class);
        forgeBus.register(DiseaseSystem.class);
        forgeBus.register(PanicSystem.class);

        // ── 生存系统 ──
        forgeBus.register(PoopSystem.class);
        forgeBus.register(ShitballThrowHandler.class);

        // ── 职业系统 ──
        forgeBus.register(ProfessionSystem.class);
        forgeBus.register(FishingSystem.class);
        forgeBus.register(SkillTerminalHandler.class);

        // ── 命令 ──
        forgeBus.register(StatusCommand.class);

        // ── 物品交互 ──
        // 【不要】写 forgeBus.register(StaminaItems.class)：NeoForge 的事件总线对
        // 「注册了一个没有任何 @SubscribeEvent 方法的类」是直接抛
        // IllegalArgumentException（Forge 当年是静默放行的）。上游那行在 1.20.1 上
        // 是个无副作用的空操作，搬到 1.21.1 会让模组在构造期就崩掉。
        // 物品本身由 StaminaItems.REGISTRY 注册到 mod 总线，与游戏总线无关。
        forgeBus.register(BodyMonitorHandler.class);

        // ── 配置 ──
        container.registerConfig(ModConfig.Type.COMMON, StaminaConfig.SPEC);
        container.registerConfig(ModConfig.Type.CLIENT, HudConfig.SPEC, "tarkov_stamina_hud-client.toml");

        // ═══════════════════════════════════════════════════════════════
        //  CAF 核心整合模块
        // ═══════════════════════════════════════════════════════════════

        // ── 军用背包（namespace: caf）──
        BackpackRegistration.register(modBus);

        // ── 帐篷交互读条（TentChannelHandler 自带 @EventBusSubscriber 自动注册）──
    }
}
