package com.gem19910816.selfaid.body;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.gem19910816.selfaid.client.ClientBodyHealthStore;
import com.gem19910816.selfaid.item.HealingItem;

import net.minecraft.world.entity.player.Player;

/**
 * 分部位血量的统一读取入口：客户端读同步缓存，服务端读数据附加。
 * 同时承担把 HealMode 落地为持续治疗计划的逻辑。
 */
public final class BodyHealthAccess {

    private static final Random RANDOM = new Random();

    /** 返回 6 个部位的血量比例（0~1）。仅用于“还有没有可治部位”的粗略判断。 */
    public static float[] partRatios(Player player) {
        float[] ratios = new float[BodyPart.COUNT];
        if (player.level().isClientSide()) {
            float max = player.getMaxHealth();
            float[] parts = ClientBodyHealthStore.partsOf(player.getUUID());
            for (int i = 0; i < BodyPart.COUNT; i++) {
                float partMax = BodyPart.byIndex(i).maxHealthFor(max);
                ratios[i] = partMax <= 0.0F ? 1.0F : Math.min(1.0F, parts[i] / partMax);
            }
            return ratios;
        }
        BodyHealth health = player.getData(com.gem19910816.selfaid.registry.ModAttachments.BODY_HEALTH.get());
        for (BodyPart part : BodyPart.VALUES) {
            ratios[part.ordinal()] = health.partRatio(part);
        }
        return ratios;
    }

    /**
     * 在服务端为玩家挂上持续治疗计划，返回成功挂上的计划数（0 表示没有可治疗部位，物品将被消耗判定为无效）。
     */
    public static int scheduleHeal(Player player, BodyHealth health, HealingItem.HealMode mode, float totalHeal,
            int durationTicks) {
        List<ActiveHeal> plans = new ArrayList<>();
        switch (mode) {
            case RANDOM_LIMB -> {
                List<BodyPart> candidates = new ArrayList<>();
                for (BodyPart part : BodyPart.VALUES) {
                    if (part.isLimb() && health.partRatio(part) < 1.0F) {
                        candidates.add(part);
                    }
                }
                if (candidates.isEmpty()) {
                    // 四肢都满了就退而求其次：任何未满部位
                    for (BodyPart part : BodyPart.VALUES) {
                        if (health.partRatio(part) < 1.0F) {
                            candidates.add(part);
                        }
                    }
                }
                if (!candidates.isEmpty()) {
                    BodyPart target = candidates.get(RANDOM.nextInt(candidates.size()));
                    plans.add(new ActiveHeal(target.ordinal(), totalHeal, durationTicks));
                }
            }
            case HEAD_TORSO -> {
                BodyPart target = null;
                float worst = 2.0F;
                for (BodyPart part : new BodyPart[] { BodyPart.HEAD, BodyPart.TORSO }) {
                    float ratio = health.partRatio(part);
                    if (ratio < 1.0F && ratio < worst) {
                        worst = ratio;
                        target = part;
                    }
                }
                if (target != null) {
                    plans.add(new ActiveHeal(target.ordinal(), totalHeal, durationTicks));
                }
            }
            case ALL_PARTS -> {
                float totalDeficit = 0.0F;
                float[] deficits = new float[BodyPart.COUNT];
                for (BodyPart part : BodyPart.VALUES) {
                    float deficit = Math.max(0.0F, 1.0F - health.partRatio(part));
                    deficits[part.ordinal()] = deficit;
                    totalDeficit += deficit;
                }
                if (totalDeficit > 1.0E-4F) {
                    for (BodyPart part : BodyPart.VALUES) {
                        float share = totalHeal * deficits[part.ordinal()] / totalDeficit;
                        if (share > 0.05F) {
                            plans.add(new ActiveHeal(part.ordinal(), share, durationTicks));
                        }
                    }
                }
            }
        }
        health.activeHeals().addAll(plans);
        return plans.size();
    }

    private BodyHealthAccess() {
    }
}
