package com.carrion.doomsdaycontainers;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;

/**
 * 分类开关音效：木箱是木盖声、金属柜是铁门声、冰箱是冰箱门、售货机/ATM 是卷帘门、
 * 行李箱是皮箱声、纸箱是纸板声、塑料箱是塑料声、遗体是黏腻声、垃圾袋是布料声……
 *
 * 每个方块的音效类别来自 {@code container_targets.json} 里的 {@code sound} 字段（由 tools/gen_targets.ps1 生成），
 * 也可以直接写成具体音效 id 覆盖，例如：
 * <pre>
 * "acrate": { "slots": 9, "sound": "wood_crate", "sound_open": "minecraft:barrel_open", "sound_close": "minecraft:barrel_close" }
 * </pre>
 * 想换成原模组的梗音效也行：{@code "sound_open": "doomsday_decoration:huzai"}。
 */
public final class ContainerSounds {
    /** 一对开/关音效。 */
    public record Pair(SoundEvent open, SoundEvent close) {}

    private static Pair pair(SoundEvent open, SoundEvent close) {
        return new Pair(open, close);
    }

    /** 类别 -> 音效对。 */
    private static final Map<String, Pair> CATEGORIES = new LinkedHashMap<>();
    public static final Pair FALLBACK = pair(SoundEvents.BARREL_OPEN, SoundEvents.BARREL_CLOSE);

    static {
        CATEGORIES.put("wood_crate", pair(SoundEvents.BARREL_OPEN, SoundEvents.BARREL_CLOSE));                       // 木箱 / 板条箱：木盖子
        CATEGORIES.put("wood_cabinet", pair(SoundEvents.WOODEN_DOOR_OPEN, SoundEvents.WOODEN_DOOR_CLOSE));          // 木柜：木门吱呀
        CATEGORIES.put("glass_cabinet", pair(SoundEvents.GLASS_PLACE, SoundEvents.WOODEN_TRAPDOOR_CLOSE));          // 玻璃柜：玻璃门
        CATEGORIES.put("wood_drawer", pair(SoundEvents.WOODEN_TRAPDOOR_OPEN, SoundEvents.WOODEN_TRAPDOOR_CLOSE));   // 木抽屉：抽拉
        CATEGORIES.put("wood_shelf", pair(SoundEvents.WOOD_PLACE, SoundEvents.WOOD_HIT));                           // 货架：没有门，木器轻响
        CATEGORIES.put("metal_door", pair(SoundEvents.IRON_DOOR_OPEN, SoundEvents.IRON_DOOR_CLOSE));                // 金属柜 / 储物柜 / 保险箱 / 冰箱
        CATEGORIES.put("metal_lid", pair(SoundEvents.IRON_TRAPDOOR_OPEN, SoundEvents.IRON_TRAPDOOR_CLOSE));         // 工具箱 / 弹药箱 / 油桶 / 垃圾桶
        CATEGORIES.put("metal_flap", pair(SoundEvents.WOODEN_BUTTON_CLICK_ON, SoundEvents.WOODEN_BUTTON_CLICK_OFF)); // 邮箱：翻盖咔哒
        CATEGORIES.put("vault", pair(SoundEvents.VAULT_OPEN_SHUTTER, SoundEvents.VAULT_CLOSE_SHUTTER));             // 售货机 / ATM / 收银台：卷帘
        CATEGORIES.put("appliance", pair(SoundEvents.COPPER_TRAPDOOR_OPEN, SoundEvents.COPPER_TRAPDOOR_CLOSE));     // 饮水机 / 咖啡机
        CATEGORIES.put("cardboard", pair(SoundEvents.ITEM_FRAME_ADD_ITEM, SoundEvents.ITEM_FRAME_REMOVE_ITEM));     // 纸箱：纸板
        CATEGORIES.put("plastic", pair(SoundEvents.SLIME_BLOCK_PLACE, SoundEvents.SLIME_BLOCK_BREAK));              // 塑料箱：塑料
        // 注意：1.21.1 里 ARMOR_EQUIP_LEATHER 是 Holder<SoundEvent>，要取 .value()
        CATEGORIES.put("luggage", pair(SoundEvents.ARMOR_EQUIP_LEATHER.value(), SoundEvents.BUNDLE_INSERT));        // 行李箱 / 垃圾袋：皮革 + 布料
        CATEGORIES.put("corpse", pair(SoundEvents.SLIME_SQUISH, SoundEvents.HONEY_BLOCK_SLIDE));                    // 遗体：黏腻
        CATEGORIES.put("body_bag", pair(SoundEvents.BUNDLE_REMOVE_ONE, SoundEvents.BUNDLE_DROP_CONTENTS));          // 裹尸袋：拉链 + 布料
        CATEGORIES.put("cart", pair(SoundEvents.CHAIN_PLACE, SoundEvents.CHAIN_HIT));                               // 推车 / 购物车：金属碰撞
    }

    /** 方块 -> {类别, 覆盖用的开音效 id, 关音效 id} */
    private static final Map<Block, String[]> CONFIG = new IdentityHashMap<>();
    /** 方块 -> 解析好的音效对（第一次用到时才解析，避免注册表还没填好） */
    private static final Map<Block, Pair> RESOLVED = new IdentityHashMap<>();

    /** 诊断计数：真正派发出去的开关音效次数。自检命令用它确认音效链路确实通了。 */
    public static long PLAYED = 0L;
    public static SoundEvent LAST_OPEN;
    public static SoundEvent LAST_CLOSE;

    private ContainerSounds() {}

    // ------------------------------------------------------------------ 配置

    public static void register(Block block, String category, String soundOpen, String soundClose) {
        CONFIG.put(block, new String[]{category, soundOpen, soundClose});
    }

    public static Pair forBlock(Block block) {
        return RESOLVED.computeIfAbsent(block, b -> resolve(CONFIG.get(b)));
    }

    public static String categoryOf(Block block) {
        String[] cfg = CONFIG.get(block);
        return cfg == null || cfg[0] == null ? "wood_crate" : cfg[0];
    }

    private static Pair resolve(String[] cfg) {
        Pair base = cfg == null ? FALLBACK : CATEGORIES.getOrDefault(cfg[0], FALLBACK);
        if (cfg == null) return base;
        SoundEvent open = cfg[1] == null ? null : lookup(cfg[1]);
        SoundEvent close = cfg[2] == null ? null : lookup(cfg[2]);
        return new Pair(open == null ? base.open() : open, close == null ? base.close() : close);
    }

    private static SoundEvent lookup(String id) {
        ResourceLocation key = ResourceLocation.tryParse(id);
        if (key == null) {
            DoomsdayContainers.LOGGER.warn("音效 id 写错了：{}", id);
            return null;
        }
        SoundEvent event = BuiltInRegistries.SOUND_EVENT.get(key);
        if (event == null) {
            DoomsdayContainers.LOGGER.warn("找不到音效 {}，将使用类别默认音效", id);
        }
        return event;
    }

    public static String describe(Block block) {
        Pair pair = forBlock(block);
        return idOf(pair.open()) + " / " + idOf(pair.close());
    }

    public static String idOf(SoundEvent event) {
        ResourceLocation key = event == null ? null : BuiltInRegistries.SOUND_EVENT.getKey(event);
        return key == null ? String.valueOf(event) : key.toString();
    }

    public static Map<String, Pair> categories() {
        return new HashMap<>(CATEGORIES);
    }

    // ------------------------------------------------------------------ 播放

    public static void playOpen(BlockEntity blockEntity) {
        play(blockEntity, forBlock(blockEntity.getBlockState().getBlock()).open(), true);
    }

    public static void playClose(BlockEntity blockEntity) {
        play(blockEntity, forBlock(blockEntity.getBlockState().getBlock()).close(), false);
    }

    private static void play(BlockEntity blockEntity, SoundEvent event, boolean opening) {
        if (event == null) return;
        Level level = blockEntity.getLevel();
        if (level == null || level.isClientSide) return;
        BlockPos pos = blockEntity.getBlockPos();
        // playSound(null, ...) 会把声音广播给附近的客户端；音量/音调对齐原版箱子
        level.playSound(null, pos, event, SoundSource.BLOCKS, 0.5F, level.random.nextFloat() * 0.1F + 0.9F);
        PLAYED++;
        if (opening) {
            LAST_OPEN = event;
        } else {
            LAST_CLOSE = event;
        }
    }
}
