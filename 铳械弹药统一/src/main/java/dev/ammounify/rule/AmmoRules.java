package dev.ammounify.rule;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.FieldNamingPolicy;
import dev.ammounify.AmmoUnify;
import net.neoforged.fml.loading.FMLPaths;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * 弹药统一规则。
 *
 * <p>与配置文件一一对应。查找顺序（先命中先返回）：</p>
 * <ol>
 *   <li>{@code ignore_guns} 命中 → 不处理（让这把枪保持原样）</li>
 *   <li>{@code by_gun} 命中 → 用指定弹药</li>
 *   <li>{@code by_type} 命中 → 按枪的类别用通用弹药</li>
 *   <li>都没命中 → 不处理</li>
 * </ol>
 */
public final class AmmoRules {

    /** 规则文件路径：config/ammo_unify/rules.json */
    public static final Path FILE = FMLPaths.CONFIGDIR.get()
            .resolve(AmmoUnify.MOD_ID)
            .resolve("rules.json");

    private static final Gson GSON = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .disableHtmlEscaping()
            .create();

    private static volatile AmmoRules INSTANCE = defaults();

    // ---- 配置字段（字段名 → JSON 的 lower_case_with_underscores）----

    /** 总开关。关掉后本模组完全不介入。 */
    private boolean enabled = true;

    /** 按枪的类别（gun index 的 type 字段）映射到通用弹药。 */
    private Map<String, String> byType = new LinkedHashMap<>();

    /** 按具体枪 id 映射，优先级高于 byType。 */
    private Map<String, String> byGun = new LinkedHashMap<>();

    /** 不参与统一的枪 id 列表，优先级最高。 */
    private Set<String> ignoreGuns = new LinkedHashSet<>();

    /** 不参与统一的弹药 id 列表：用这些弹药的枪会保持原样。 */
    private Set<String> ignoreAmmo = new LinkedHashSet<>();

    /** 按弹药 id 覆写弹道数值。键是弹药 id。 */
    private Map<String, Ballistics> ballistics = new LinkedHashMap<>();

    private AmmoRules() {
    }

    // ---- 默认值 ----

    public static AmmoRules defaults() {
        AmmoRules r = new AmmoRules();
        r.enabled = true;
        r.byType = new LinkedHashMap<>(Map.of(
                "pistol", "ammo_unify:cartridge_pistol",
                "smg", "ammo_unify:cartridge_pistol",
                "rifle", "ammo_unify:cartridge_rifle",
                "mg", "ammo_unify:cartridge_rifle",
                "shotgun", "ammo_unify:shell_shotgun",
                "sniper", "ammo_unify:cartridge_sniper",
                "rpg", "ammo_unify:shell_barrel",
                "fuel", "ammo_unify:tank_fuel"
        ));
        r.byGun = new LinkedHashMap<>();
        r.ignoreGuns = new LinkedHashSet<>();
        r.ignoreAmmo = new LinkedHashSet<>();
        r.ballistics = new LinkedHashMap<>();
        return r;
    }

    // ---- 访问 ----

    public static AmmoRules get() {
        return INSTANCE;
    }

    public boolean isActive() {
        return enabled && (!byType.isEmpty() || !byGun.isEmpty());
    }

    public int totalRules() {
        return byType.size() + byGun.size();
    }

    public Map<String, Ballistics> ballistics() {
        return ballistics;
    }

    @Nullable
    public Ballistics ballisticsFor(String ammoId) {
        Ballistics b = ballistics.get(ammoId);
        return b == null || b.isEmpty() ? null : b;
    }

    /**
     * 决定一把枪应该使用哪种弹药。
     *
     * @param gunId   枪的注册 id（字符串形式）
     * @param gunType gun index 里的 type 字段
     * @return 目标弹药 id；返回 null 表示这把枪不参与统一
     */
    @Nullable
    public String ammoFor(String gunId, @Nullable String gunType) {
        if (!enabled) {
            return null;
        }
        if (ignoreGuns.contains(gunId)) {
            return null;
        }
        String direct = byGun.get(gunId);
        if (direct != null) {
            return ignoreAmmo.contains(direct) ? null : direct;
        }
        if (gunType != null) {
            String byClass = byType.get(gunType.toLowerCase(Locale.ROOT));
            if (byClass != null) {
                return ignoreAmmo.contains(byClass) ? null : byClass;
            }
        }
        return null;
    }

    // ---- 读写 ----

    /** 从磁盘读取规则；文件不存在时写出一份带默认值的模板。读取失败则保留旧值。 */
    public static void reload() {
        try {
            if (Files.notExists(FILE)) {
                writeTemplate();
            }
            try (Reader reader = Files.newBufferedReader(FILE, StandardCharsets.UTF_8)) {
                AmmoRules loaded = GSON.fromJson(reader, AmmoRules.class);
                if (loaded != null) {
                    loaded.normalize();
                    INSTANCE = loaded;
                    return;
                }
                AmmoUnify.LOG.warn("[Ammo Unify] {} 内容为空，沿用默认规则", FILE);
            }
        } catch (IOException | RuntimeException e) {
            AmmoUnify.LOG.error("[Ammo Unify] 读取 {} 失败，沿用默认规则", FILE, e);
        }
    }

    private static void writeTemplate() throws IOException {
        Files.createDirectories(FILE.getParent());
        try (Writer writer = Files.newBufferedWriter(FILE, StandardCharsets.UTF_8)) {
            GSON.toJson(defaults(), writer);
        }
        AmmoUnify.LOG.info("[Ammo Unify] 已生成默认规则文件 {}", FILE);
    }

    /** 补齐可能缺失的字段，避免 NPE。 */
    private void normalize() {
        if (byType == null) {
            byType = new LinkedHashMap<>();
        }
        if (byGun == null) {
            byGun = new LinkedHashMap<>();
        }
        if (ignoreGuns == null) {
            ignoreGuns = new LinkedHashSet<>();
        }
        if (ignoreAmmo == null) {
            ignoreAmmo = new LinkedHashSet<>();
        }
        if (ballistics == null) {
            ballistics = new LinkedHashMap<>();
        }
    }
}
