package com.carrion.doomsdaycontainers;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.RegisterEvent;

/**
 * 目标方块注册表：读取 {@code /doomsdaycontainers/container_targets.json}，
 * 为每一个"看起来像容器"的原模组方块注册一个方块实体类型。
 *
 * 注意：必须在"方块实体类型"注册事件里做这件事 —— 模组构造阶段所有注册表都还是空的，
 * 那时候去查方块只会查到空气。
 *
 * 找不到的方块（原模组换版本、删方块）会被跳过并记录一条 WARN，不会导致崩溃。
 */
public final class ContainerTargets {
    public static final String RESOURCE = "/doomsdaycontainers/container_targets.json";

    /** 方块 -> 格子数 */
    private static final Map<Block, Integer> SLOTS = new IdentityHashMap<>();
    /** 方块 -> 方块实体类型 */
    private static final Map<Block, BlockEntityType<GenericContainerBlockEntity>> TYPES = new IdentityHashMap<>();
    /** 原版方块 id -> 方块实体类型（有序，方便日志/遍历） */
    private static final Map<ResourceLocation, BlockEntityType<GenericContainerBlockEntity>> TYPES_BY_ID = new LinkedHashMap<>();
    /** 原模组已有的容器（例如板条箱），不重复注册方块实体 */
    private static final Map<ResourceLocation, Integer> SKIPPED_EXISTING = new LinkedHashMap<>();
    /** 清单里配置的全部目标（含原模组自带方块实体的），有序 */
    private static final Map<ResourceLocation, Integer> CONFIGURED = new LinkedHashMap<>();
    /** 用到的音效类别（去重，方便日志里一眼看出配了几种声音） */
    private static final java.util.Set<String> SOUND_CATEGORIES = new java.util.TreeSet<>();

    private ContainerTargets() {}

    public static void register(IEventBus modEventBus) {
        modEventBus.addListener(ContainerTargets::onRegister);
    }

    // ------------------------------------------------------------------ 加载

    private static void onRegister(RegisterEvent event) {
        if (!event.getRegistryKey().equals(BuiltInRegistries.BLOCK_ENTITY_TYPE.key())) return;
        loadFromResources(event);
    }

    private static void loadFromResources(RegisterEvent event) {
        JsonObject root;
        try (InputStream in = DoomsdayContainers.class.getResourceAsStream(RESOURCE)) {
            if (in == null) {
                DoomsdayContainers.LOGGER.error("找不到目标清单 {} —— 附属不会生效", RESOURCE);
                return;
            }
            root = JsonParser.parseReader(new InputStreamReader(in, StandardCharsets.UTF_8)).getAsJsonObject();
        } catch (Exception e) {
            DoomsdayContainers.LOGGER.error("读取 {} 失败", RESOURCE, e);
            return;
        }

        JsonObject targets = root.has("targets") ? root.getAsJsonObject("targets") : root;
        int ok = 0, missing = 0, existing = 0;
        for (Map.Entry<String, JsonElement> entry : targets.entrySet()) {
            String path = entry.getKey();
            int slots = 27;
            boolean nativeContainer = false;
            String soundCategory = null;
            String soundOpen = null;
            String soundClose = null;
            try {
                if (entry.getValue().isJsonObject()) {
                    JsonObject o = entry.getValue().getAsJsonObject();
                    if (o.has("slots")) slots = o.get("slots").getAsInt();
                    if (o.has("native")) nativeContainer = o.get("native").getAsBoolean();
                    if (o.has("sound")) soundCategory = o.get("sound").getAsString();
                    if (o.has("sound_open")) soundOpen = o.get("sound_open").getAsString();
                    if (o.has("sound_close")) soundClose = o.get("sound_close").getAsString();
                } else {
                    slots = entry.getValue().getAsInt();
                }
            } catch (Exception e) {
                DoomsdayContainers.LOGGER.warn("目标 {} 的 slots 字段无法解析，按 27 处理", path);
            }
            slots = normaliseSlots(slots);

            ResourceLocation id = ResourceLocation.fromNamespaceAndPath(DoomsdayContainers.TARGET_MODID, path);
            if (!BuiltInRegistries.BLOCK.containsKey(id)) {
                missing++;
                DoomsdayContainers.LOGGER.warn("末日装饰里没有方块 {}（版本不符？）—— 已跳过", id);
                continue;
            }
            Block block = BuiltInRegistries.BLOCK.get(id);
            SLOTS.put(block, slots);
            CONFIGURED.put(id, slots);
            ContainerSounds.register(block, soundCategory, soundOpen, soundClose);
            SOUND_CATEGORIES.add(ContainerSounds.categoryOf(block));

            // 注意：运行时不能用 instanceof EntityBlock 判断 —— 我们这个附属正是给所有目标补上了这个接口。
            // 是否"原模组自带方块实体"由生成清单时对源码的分析结果（native 字段）决定。
            if (nativeContainer) {
                existing++;
                SKIPPED_EXISTING.put(id, slots);
                DoomsdayContainers.LOGGER.info("{} 原模组自带方块实体，附属只做交互/界面修补", id);
                continue;
            }

            // 到这里说明这个方块是"原本只是装饰、由本附属补上 EntityBlock 的"，
            // 所以运行时 instanceof EntityBlock 必然成立，不需要、也不能据此判断原生与否。
            //
            // 方块实体类型必须"自带"类型信息，不能靠传入的 BlockState 反查：
            // 区块序列化重建方块实体时，若该位置已经变成空气（例如方块被破坏后残留的方块实体 NBT），
            // 传进来的 state 会是空气，反查就会拿到 null，进而在 BlockEntity 构造里 NPE。
            // 所以这里用 holder 把类型自己捕获进去，并在 state 不可用时回退到方块默认状态。
            BlockEntityType<?>[] holder = new BlockEntityType<?>[1];
            BlockEntityType<GenericContainerBlockEntity> type = BlockEntityType.Builder.of(
                    (pos, state) -> new GenericContainerBlockEntity(holder[0], pos,
                            state.getBlock() == block ? state : block.defaultBlockState()),
                    block).build(null);
            holder[0] = type;
            event.<BlockEntityType<?>>register(BuiltInRegistries.BLOCK_ENTITY_TYPE.key(), id, () -> type);
            TYPES.put(block, type);
            TYPES_BY_ID.put(id, type);
            ok++;
        }
        DoomsdayContainers.LOGGER.info("容器化目标：注册 {} 个新容器，{} 个原模组自带方块实体，{} 个未找到（共 {} 条清单）",
                ok, existing, missing, targets.size());
        DoomsdayContainers.LOGGER.info("开关音效类别 {} 种：{}", SOUND_CATEGORIES.size(), String.join("、", SOUND_CATEGORIES));
    }

    private static int normaliseSlots(int slots) {
        int rows = Math.max(1, Math.min(6, (slots + 8) / 9));
        return rows * 9;
    }

    // ------------------------------------------------------------------ 查询

    public static boolean isTarget(Block block) {
        return SLOTS.containsKey(block);
    }

    public static int slotsFor(Block block) {
        Integer i = SLOTS.get(block);
        return i == null ? 27 : i;
    }

    public static BlockEntityType<GenericContainerBlockEntity> typeFor(Block block) {
        return TYPES.get(block);
    }

    public static Collection<BlockEntityType<GenericContainerBlockEntity>> registeredTypes() {
        return TYPES_BY_ID.values();
    }

    public static Map<ResourceLocation, Integer> existingContainers() {
        return SKIPPED_EXISTING;
    }

    /** 清单里配置的全部目标（含原模组自带方块实体的），id -> 格子数。 */
    public static Map<ResourceLocation, Integer> configuredTargets() {
        return CONFIGURED;
    }
}
