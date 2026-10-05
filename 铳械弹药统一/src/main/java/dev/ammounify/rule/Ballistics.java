package dev.ammounify.rule;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Nullable;
import java.util.List;

/**
 * 一种弹药的弹道覆写。
 *
 * <p>TaCZ 的 {@code index/ammo} 只描述弹药物品本身（名字、图标、堆叠数），
 * 真正的弹道数值在枪的 {@code BulletData} 里。所以"通用弹药"要真的像是不同的子弹，
 * 就必须按弹药去改写枪的 {@code BulletData}——这就是本类存在的理由。</p>
 *
 * <p>所有字段可空：只有显式写了的才会覆盖，没写的沿用原来那把枪的数值。</p>
 */
public final class Ballistics {

    /** 单发伤害。 */
    @SerializedName("damage")
    public Float damage;

    /** 初速。TaCZ 内部还会再乘一个全局系数。 */
    @SerializedName("speed")
    public Float speed;

    /** 重力。 */
    @SerializedName("gravity")
    public Float gravity;

    /** 空气摩擦。 */
    @SerializedName("friction")
    public Float friction;

    /** 击退。 */
    @SerializedName("knockback")
    public Float knockback;

    /** 穿透数。 */
    @SerializedName("pierce")
    public Integer pierce;

    /** 每次射击的弹丸数（霰弹用）。 */
    @SerializedName("bullet_amount")
    public Integer bulletAmount;

    /** 子弹存活秒数。 */
    @SerializedName("life_second")
    public Float lifeSecond;

    /** 护甲穿透率，0~1。 */
    @SerializedName("armor_ignore")
    public Float armorIgnore;

    /** 爆头倍率。 */
    @SerializedName("headshot_multiplier")
    public Float headshotMultiplier;

    /** 距离衰减曲线，会被原样写进 ExtraDamage。 */
    @SerializedName("damage_falloff")
    public List<FalloffPoint> damageFalloff;

    /** 是否曳光弹。 */
    @SerializedName("tracer")
    public Boolean tracer;

    /** 是否爆炸。 */
    @SerializedName("explosive")
    public Boolean explosive;

    /** 爆炸半径。 */
    @SerializedName("explosion_radius")
    public Float explosionRadius;

    /** 爆炸伤害。 */
    @SerializedName("explosion_damage")
    public Float explosionDamage;

    /** 爆炸是否破坏方块。 */
    @SerializedName("explosion_destroy_block")
    public Boolean explosionDestroyBlock;

    /** 是否点燃实体。 */
    @SerializedName("ignite_entity")
    public Boolean igniteEntity;

    /** 是否点燃方块。 */
    @SerializedName("ignite_block")
    public Boolean igniteBlock;

    /** 点燃时长（秒）。 */
    @SerializedName("ignite_seconds")
    public Integer igniteSeconds;

    public boolean isEmpty() {
        return damage == null && speed == null && gravity == null && friction == null
                && knockback == null && pierce == null && bulletAmount == null && lifeSecond == null
                && armorIgnore == null && headshotMultiplier == null
                && (damageFalloff == null || damageFalloff.isEmpty())
                && tracer == null && explosive == null && explosionRadius == null
                && explosionDamage == null && explosionDestroyBlock == null
                && igniteEntity == null && igniteBlock == null && igniteSeconds == null;
    }

    /** 距离衰减曲线上的一个点。 */
    public static final class FalloffPoint {
        @SerializedName("distance")
        public float distance;

        @SerializedName("damage")
        public float damage;

        public FalloffPoint() {
        }

        public FalloffPoint(float distance, float damage) {
            this.distance = distance;
            this.damage = damage;
        }
    }

}
