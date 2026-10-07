package com.gem19910816.selfaid.registry;

import net.neoforged.neoforge.common.ModConfigSpec;

/** COMMON 配置管服务端玩法逻辑，CLIENT 配置管 HUD 显示。 */
public final class SelfAidConfig {
    private static final ModConfigSpec.Builder COMMON_BUILDER = new ModConfigSpec.Builder();
    private static final ModConfigSpec.Builder CLIENT_BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec COMMON_SPEC;
    public static final ModConfigSpec CLIENT_SPEC;

    public static final ModConfigSpec.BooleanValue ENABLE_SYSTEM;
    public static final ModConfigSpec.BooleanValue HEAD_DEATH;
    public static final ModConfigSpec.BooleanValue TORSO_DEATH;
    public static final ModConfigSpec.BooleanValue NATURAL_REGEN_DISTRIBUTES;
    public static final ModConfigSpec.BooleanValue LIMB_PENALTIES;
    public static final ModConfigSpec.DoubleValue DAMAGE_SCALE;

    public static final ModConfigSpec.BooleanValue SHOW_HUD;
    public static final ModConfigSpec.BooleanValue HIDE_VANILLA_HEALTHBAR;
    public static final ModConfigSpec.DoubleValue HUD_SCALE;
    public static final ModConfigSpec.IntValue HUD_X;
    public static final ModConfigSpec.IntValue HUD_Y;

    static {
        COMMON_BUILDER.comment("SelfAid body-part health system").push("system");
        ENABLE_SYSTEM = COMMON_BUILDER.comment("Enable splitting player damage across body parts")
                .define("enableSystem", true);
        HEAD_DEATH = COMMON_BUILDER.comment("Dying when the head part reaches zero").define("headDeath", true);
        TORSO_DEATH = COMMON_BUILDER.comment("Dying when the torso part reaches zero").define("torsoDeath", true);
        NATURAL_REGEN_DISTRIBUTES = COMMON_BUILDER
                .comment("Natural regeneration also refills the lowest body part").define("naturalRegenDistributes",
                        true);
        LIMB_PENALTIES = COMMON_BUILDER.comment("Empty limbs apply weakness / slowness penalties")
                .define("limbPenalties", true);
        DAMAGE_SCALE = COMMON_BUILDER.comment("Multiplier for the damage applied to body parts").defineInRange(
                "damageScale", 1.0, 0.0, 10.0);
        COMMON_BUILDER.pop();
        COMMON_SPEC = COMMON_BUILDER.build();

        CLIENT_BUILDER.comment("SelfAid HUD").push("hud");
        SHOW_HUD = CLIENT_BUILDER.comment("Show the body-part health HUD").define("showHud", true);
        HIDE_VANILLA_HEALTHBAR = CLIENT_BUILDER
                .comment("Hide the vanilla hearts row above the hotbar (FirstAid-style)").define("hideVanillaHealthbar",
                        true);
        HUD_SCALE = CLIENT_BUILDER.comment("HUD widget scale").defineInRange("scale", 1.0, 0.5, 4.0);
        HUD_X = CLIENT_BUILDER.comment("HUD widget left offset in pixels").defineInRange("x", 6, 0, 4096);
        HUD_Y = CLIENT_BUILDER.comment("HUD widget top offset in pixels").defineInRange("y", 6, 0, 4096);
        CLIENT_BUILDER.pop();
        CLIENT_SPEC = CLIENT_BUILDER.build();
    }

    private SelfAidConfig() {
    }
}
