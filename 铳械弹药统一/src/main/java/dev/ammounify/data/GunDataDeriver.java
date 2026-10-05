package dev.ammounify.data;

import com.tacz.guns.resource.index.CommonGunIndex;
import com.tacz.guns.resource.pojo.data.gun.BulletData;
import com.tacz.guns.resource.pojo.data.gun.ExtraDamage;
import com.tacz.guns.resource.pojo.data.gun.ExplosionData;
import com.tacz.guns.resource.pojo.data.gun.GunData;
import com.tacz.guns.resource.pojo.data.gun.Ignite;
import com.tacz.guns.resource.CommonAssetsManager;
import dev.ammounify.AmmoUnify;
import dev.ammounify.rule.AmmoRules;
import dev.ammounify.rule.Ballistics;
import net.minecraft.resources.ResourceLocation;

import javax.annotation.Nullable;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 派生 GunData。
 *
 * <p>为一把枪计算"换了通用弹药之后"的 {@link GunData} 副本，并按 {@link CommonGunIndex} 实例缓存。
 * 缓存以实例为键（{@code CommonGunIndex} 没有重写 equals/hashCode，天然是身份语义），
 * 所以热路径上只做一次哈希查找。</p>
 *
 * <h2>为什么不从 mixin 里回调 {@code getGunData()}</h2>
 * <p>本类的入口是从 {@code CommonGunIndex.getGunData()} 的 HEAD 注入点调用的。
 * 如果在派生过程中再去调 {@code index.getGunData()}，就会重新进入同一个注入点造成无限递归。
 * 因此这里直接反射读取 {@code CommonGunIndex.gunData} 私有字段，绕开被改写的方法。</p>
 */
public final class GunDataDeriver {

    /** 枪 index 实例 → 它的注册 id。TaCZ 的 CommonGunIndex 自己不记录 gunId，只能反查。 */
    private static final Map<CommonGunIndex, ResourceLocation> ID_BY_INDEX = new IdentityHashMap<>();

    /** 已派生好的数据。 */
    private static final Map<CommonGunIndex, GunData> DERIVED = new ConcurrentHashMap<>();

    /** 明确判定为"不需要处理"的枪，避免每次重复判定。 */
    private static final Set<CommonGunIndex> NOT_APPLICABLE = ConcurrentHashMap.newKeySet();

    private static final Object LOCK = new Object();

    private static volatile boolean idMapBuilt;
    private static volatile boolean readFailureLogged;

    private GunDataDeriver() {
    }

    /**
     * 取得派生数据。
     *
     * @return 替换用的 {@link GunData}；返回 null 表示这把枪不参与统一，应保持原样
     */
    @Nullable
    public static GunData derived(CommonGunIndex index) {
        if (index == null) {
            return null;
        }
        GunData hit = DERIVED.get(index);
        if (hit != null) {
            return hit;
        }
        if (NOT_APPLICABLE.contains(index)) {
            return null;
        }
        if (!AmmoRules.get().isActive()) {
            return null;
        }
        synchronized (LOCK) {
            hit = DERIVED.get(index);
            if (hit != null) {
                return hit;
            }
            if (NOT_APPLICABLE.contains(index)) {
                return null;
            }
            GunData built = build(index, AmmoRules.get());
            if (built == null) {
                NOT_APPLICABLE.add(index);
            } else {
                DERIVED.put(index, built);
            }
            return built;
        }
    }

    @Nullable
    private static GunData build(CommonGunIndex index, AmmoRules rules) {
        ResourceLocation gunId = gunIdOf(index);
        if (gunId == null) {
            return null;
        }

        String target = rules.ammoFor(gunId.toString(), index.getType());
        if (target == null) {
            return null;
        }
        ResourceLocation ammoId = ResourceLocation.tryParse(target);
        if (ammoId == null) {
            AmmoUnify.LOG.warn("[Ammo Unify] 规则里的弹药 id 不合法：{}（枪 {}）", target, gunId);
            return null;
        }

        // 反射直读原始数据，绕开被本模组改写过的 getGunData()，避免递归
        Object raw = Refl.get(CommonGunIndex.class, "gunData", index);
        if (!(raw instanceof GunData original)) {
            if (!readFailureLogged) {
                readFailureLogged = true;
                AmmoUnify.LOG.error("[Ammo Unify] 无法读取 CommonGunIndex.gunData，本模组将不生效。"
                        + "这通常意味着 TaCZ 版本不兼容（需要 1.1.8 系列）。");
            }
            return null;
        }

        GunData copy = Refl.shallowCopy(original, GunData.class);
        if (copy == null) {
            AmmoUnify.LOG.error("[Ammo Unify] 无法派生 GunData（枪 {}）", gunId);
            return null;
        }
        if (!Refl.set(GunData.class, "ammoId", copy, ammoId)) {
            AmmoUnify.LOG.error("[Ammo Unify] 无法写入 GunData.ammoId（枪 {}）", gunId);
            return null;
        }

        Ballistics ballistics = rules.ballisticsFor(target);
        if (ballistics != null) {
            BulletData patched = patchBullet(original.getBulletData(), ballistics);
            if (patched != null) {
                Refl.set(GunData.class, "bulletData", copy, patched);
            }
        }

        AmmoUnify.LOG.debug("[Ammo Unify] {} (type={}) → {}", gunId, index.getType(), ammoId);
        return copy;
    }

    // ---- 弹道覆写 ----

    @Nullable
    private static BulletData patchBullet(@Nullable BulletData original, Ballistics b) {
        if (original == null) {
            return null;
        }
        BulletData copy = Refl.shallowCopy(original, BulletData.class);
        if (copy == null) {
            return null;
        }

        setIfPresent(BulletData.class, "damageAmount", copy, b.damage, 0.0F);
        setIfPresent(BulletData.class, "speed", copy, b.speed, 0.0F);
        setIfPresent(BulletData.class, "gravity", copy, b.gravity, 0.0F);
        setIfPresent(BulletData.class, "friction", copy, b.friction, 0.0F);
        setIfPresent(BulletData.class, "knockback", copy, b.knockback, 0.0F);
        setIfPresent(BulletData.class, "lifeSecond", copy, b.lifeSecond, 0.05F);

        if (b.pierce != null) {
            Refl.set(BulletData.class, "pierce", copy, Math.max(1, b.pierce));
        }
        if (b.bulletAmount != null) {
            Refl.set(BulletData.class, "bulletAmount", copy, Math.max(1, b.bulletAmount));
        }
        if (b.tracer != null) {
            // TaCZ 用 tracerCountInterval <= 0 表示每发都是曳光弹
            Refl.set(BulletData.class, "tracerCountInterval", copy, b.tracer ? 0 : -1);
        }
        if (b.igniteSeconds != null) {
            Refl.set(BulletData.class, "igniteEntityTime", copy, Math.max(0, b.igniteSeconds));
        }

        patchIgnite(copy, original, b);
        patchExplosion(copy, original, b);
        patchExtraDamage(copy, original, b);
        return copy;
    }

    private static void patchIgnite(BulletData copy, BulletData original, Ballistics b) {
        if (b.igniteEntity == null && b.igniteBlock == null) {
            return;
        }
        Ignite old = original.getIgnite();
        boolean entity = b.igniteEntity != null ? b.igniteEntity : old != null && old.isIgniteEntity();
        boolean block = b.igniteBlock != null ? b.igniteBlock : old != null && old.isIgniteBlock();
        Refl.set(BulletData.class, "ignite", copy, new Ignite(entity, block));
    }

    private static void patchExplosion(BulletData copy, BulletData original, Ballistics b) {
        boolean touches = b.explosive != null || b.explosionRadius != null
                || b.explosionDamage != null || b.explosionDestroyBlock != null;
        if (!touches) {
            return;
        }
        ExplosionData old = original.getExplosionData();
        boolean explode = b.explosive != null ? b.explosive : old != null && old.isExplode();
        float radius = b.explosionRadius != null ? Math.max(0.0F, b.explosionRadius)
                : old != null ? old.getRadius() : 0.0F;
        float damage = b.explosionDamage != null ? Math.max(0.0F, b.explosionDamage)
                : old != null ? old.getDamage() : 0.0F;
        boolean knockback = old != null && old.isKnockback();
        boolean destroyBlock = b.explosionDestroyBlock != null ? b.explosionDestroyBlock
                : old != null && old.isDestroyBlock();
        float delay = old != null ? old.getDelay() : 0.0F;
        Refl.set(BulletData.class, "explosionData", copy,
                new ExplosionData(explode, radius, damage, knockback, delay, destroyBlock));
    }

    private static void patchExtraDamage(BulletData copy, BulletData original, Ballistics b) {
        boolean touches = b.armorIgnore != null || b.headshotMultiplier != null
                || (b.damageFalloff != null && !b.damageFalloff.isEmpty());
        if (!touches) {
            return;
        }
        ExtraDamage old = original.getExtraDamage();

        ExtraDamage fresh = new ExtraDamage();
        float armorIgnore = old != null ? old.getArmorIgnore() : 0.0F;
        float headshot = old != null ? old.getHeadShotMultiplier() : 1.0F;

        LinkedList<ExtraDamage.DistanceDamagePair> falloff = new LinkedList<>();
        if (b.damageFalloff != null && !b.damageFalloff.isEmpty()) {
            for (Ballistics.FalloffPoint p : b.damageFalloff) {
                if (p != null) {
                    falloff.add(new ExtraDamage.DistanceDamagePair(p.distance, p.damage));
                }
            }
        } else if (old != null && old.getDamageAdjust() != null) {
            falloff.addAll(old.getDamageAdjust());
        }

        if (b.armorIgnore != null) {
            armorIgnore = Math.clamp(b.armorIgnore, 0.0F, 1.0F);
        }
        if (b.headshotMultiplier != null) {
            headshot = Math.max(0.0F, b.headshotMultiplier);
        }

        Refl.set(ExtraDamage.class, "armorIgnore", fresh, armorIgnore);
        Refl.set(ExtraDamage.class, "headShotMultiplier", fresh, headshot);
        Refl.set(ExtraDamage.class, "damageAdjust", fresh, falloff);
        Refl.set(BulletData.class, "extraDamage", copy, fresh);
    }

    private static void setIfPresent(Class<?> type, String name, Object target, Float value, float floor) {
        if (value != null) {
            Refl.set(type, name, target, Math.max(floor, value));
        }
    }

    // ---- gunId 反查 ----

    @Nullable
    private static ResourceLocation gunIdOf(CommonGunIndex index) {
        if (!idMapBuilt) {
            synchronized (LOCK) {
                if (!idMapBuilt) {
                    buildIdMap();
                }
            }
        }
        return ID_BY_INDEX.get(index);
    }

    private static void buildIdMap() {
        try {
            CommonAssetsManager manager = CommonAssetsManager.getInstance();
            if (manager != null) {
                for (Map.Entry<ResourceLocation, CommonGunIndex> entry : manager.getAllGuns()) {
                    ID_BY_INDEX.put(entry.getValue(), entry.getKey());
                }
            }
        } catch (Throwable t) {
            AmmoUnify.LOG.error("[Ammo Unify] 建立枪 id 索引失败", t);
        }
        idMapBuilt = true;
        AmmoUnify.LOG.debug("[Ammo Unify] 已索引 {} 把枪", ID_BY_INDEX.size());
    }

    /** 枪包重载后调用：清空全部缓存与派生数据。 */
    public static void invalidate() {
        synchronized (LOCK) {
            ID_BY_INDEX.clear();
            DERIVED.clear();
            NOT_APPLICABLE.clear();
            idMapBuilt = false;
            readFailureLogged = false;
        }
    }

    /** 仅用于调试：当前缓存的派生条目数。 */
    public static int cacheSize() {
        return DERIVED.size();
    }
}
