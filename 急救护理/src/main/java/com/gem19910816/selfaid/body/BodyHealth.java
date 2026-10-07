package com.gem19910816.selfaid.body;

import java.util.ArrayList;
import java.util.List;

import com.gem19910816.selfaid.net.BodySyncPayload;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;

/**
 * 玩家的分部位生命值数据（NeoForge 数据附加）。
 * parts 存绝对值，每个部位上限 = 玩家最大生命值 × 部位比例；
 * 各部位之和约等于玩家的原生生命值，由事件层保持一致。
 */
public final class BodyHealth {

    private final float[] parts = new float[BodyPart.COUNT];
    private final List<ActiveHeal> activeHeals = new ArrayList<>();
    private float maxHealth;
    /** 由事件层设置并消费的“下个 tick 强制死亡”标记，不参与序列化。 */
    public DeathCause pendingDeath = DeathCause.NONE;

    public enum DeathCause {
        NONE, HEAD, TORSO, ALL_PARTS;
    }

    public BodyHealth() {
        fill(20.0F);
    }

    public BodyHealth(List<Float> parts, List<ActiveHeal> heals, float maxHealth) {
        for (int i = 0; i < BodyPart.COUNT && i < parts.size(); i++) {
            this.parts[i] = Math.max(0.0F, parts.get(i));
        }
        this.activeHeals.addAll(heals);
        this.maxHealth = maxHealth;
        if (this.maxHealth <= 0.0F) {
            fill(20.0F);
        }
    }

    public static final Codec<BodyHealth> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.listOf().fieldOf("parts").forGetter(b -> {
                List<Float> list = new ArrayList<>(BodyPart.COUNT);
                for (float v : b.parts) {
                    list.add(v);
                }
                return list;
            }),
            ActiveHeal.CODEC.listOf().fieldOf("active_heals").forGetter(b -> List.copyOf(b.activeHeals)),
            Codec.FLOAT.optionalFieldOf("max_health", 0.0F).forGetter(b -> b.maxHealth))
            .apply(instance, BodyHealth::new));

    /** 全部回满（登录初始化/重生后由事件层调用）。 */
    public void fill(float playerMaxHealth) {
        this.maxHealth = playerMaxHealth;
        for (BodyPart part : BodyPart.VALUES) {
            parts[part.ordinal()] = part.maxHealthFor(playerMaxHealth);
        }
        activeHeals.clear();
        pendingDeath = DeathCause.NONE;
    }

    /** 玩家最大生命值变化时按比例缩放部位血量，保持伤害比例不变。 */
    public void rescale(float playerMaxHealth) {
        if (playerMaxHealth <= 0.0F) {
            return;
        }
        if (this.maxHealth <= 0.0F) {
            fill(playerMaxHealth);
            return;
        }
        if (Mth.equal(this.maxHealth, playerMaxHealth)) {
            return;
        }
        float ratio = playerMaxHealth / this.maxHealth;
        for (BodyPart part : BodyPart.VALUES) {
            float scaled = parts[part.ordinal()] * ratio;
            parts[part.ordinal()] = Mth.clamp(scaled, 0.0F, part.maxHealthFor(playerMaxHealth));
        }
        this.maxHealth = playerMaxHealth;
    }

    public float getPart(BodyPart part) {
        return parts[part.ordinal()];
    }

    public float maxHealth() {
        return maxHealth;
    }

    public float partMax(BodyPart part) {
        return part.maxHealthFor(maxHealth);
    }

    public float partRatio(BodyPart part) {
        float max = partMax(part);
        return max <= 0.0F ? 0.0F : Mth.clamp(parts[part.ordinal()] / max, 0.0F, 1.0F);
    }

    public List<ActiveHeal> activeHeals() {
        return activeHeals;
    }

    public void damagePart(BodyPart part, float amount) {
        parts[part.ordinal()] = Math.max(0.0F, parts[part.ordinal()] - amount);
    }

    public void healPart(BodyPart part, float amount) {
        healPartReturnApplied(part, amount);
    }

    /** 回复部位血量，返回实际加上去的量（被上限截断时小于 amount）。 */
    public float healPartReturnApplied(BodyPart part, float amount) {
        float before = parts[part.ordinal()];
        float target = Mth.clamp(before + amount, 0.0F, partMax(part));
        parts[part.ordinal()] = target;
        return target - before;
    }

    public boolean isPartEmpty(BodyPart part) {
        return parts[part.ordinal()] <= 0.0F;
    }

    public boolean allPartsEmpty() {
        for (float v : parts) {
            if (v > 0.0F) {
                return false;
            }
        }
        return true;
    }

    public boolean anyPartDamaged() {
        for (BodyPart part : BodyPart.VALUES) {
            if (partRatio(part) < 1.0F) {
                return true;
            }
        }
        return false;
    }

    /** 头部/躯干清空或全部清空即死亡（与原版 FirstAid 同类玩法的通用规则）。 */
    public boolean isLethalState(boolean headDeath, boolean torsoDeath) {
        if (headDeath && isPartEmpty(BodyPart.HEAD)) {
            return true;
        }
        if (torsoDeath && isPartEmpty(BodyPart.TORSO)) {
            return true;
        }
        return allPartsEmpty();
    }

    public DeathCause lethalCause(boolean headDeath, boolean torsoDeath) {
        if (headDeath && isPartEmpty(BodyPart.HEAD)) {
            return DeathCause.HEAD;
        }
        if (torsoDeath && isPartEmpty(BodyPart.TORSO)) {
            return DeathCause.TORSO;
        }
        if (allPartsEmpty()) {
            return DeathCause.ALL_PARTS;
        }
        return DeathCause.NONE;
    }

    /** 按部位血量比例从低到高均匀分配治疗量，返回实际分配掉的数量。 */
    public float distributeHeal(float amount) {
        float left = amount;
        while (left > 1.0E-4F) {
            BodyPart lowest = null;
            float lowestRatio = 1.0F;
            for (BodyPart part : BodyPart.VALUES) {
                float ratio = partRatio(part);
                if (ratio < lowestRatio) {
                    lowestRatio = ratio;
                    lowest = part;
                }
            }
            if (lowest == null) {
                break;
            }
            float canTake = partMax(lowest) - getPart(lowest);
            float give = Math.min(left, canTake);
            if (give <= 1.0E-4F) {
                break;
            }
            healPart(lowest, give);
            left -= give;
        }
        return amount - left;
    }

    /** 从比例最高的部位扣除生命值（用于与原生血量对账时的回补修正）。 */
    public float takeFromHealthiest(float amount) {
        float left = amount;
        while (left > 1.0E-4F) {
            BodyPart highest = null;
            float highestRatio = -1.0F;
            for (BodyPart part : BodyPart.VALUES) {
                float ratio = partRatio(part);
                if (ratio > highestRatio) {
                    highestRatio = ratio;
                    highest = part;
                }
            }
            if (highest == null || highestRatio <= 0.0F) {
                break;
            }
            float take = Math.min(left, getPart(highest));
            damagePart(highest, take);
            left -= take;
        }
        return amount - left;
    }

    public float totalHealth() {
        float sum = 0.0F;
        for (float v : parts) {
            sum += v;
        }
        return sum;
    }

    /** 序列化快照（含网络同步用的 6 部位数值，见 {@link BodySyncPayload}）。 */
    public float[] snapshot() {
        return parts.clone();
    }
}
