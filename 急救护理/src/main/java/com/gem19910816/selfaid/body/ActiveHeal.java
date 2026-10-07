package com.gem19910816.selfaid.body;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

/**
 * 一条持续生效的治疗计划：每隔 intervalTicks 给部位 partIndex 回复一段生命值，
 * 直到 remainingAmount 耗尽。
 */
public record ActiveHeal(int partIndex, float remainingAmount, float amountPerInterval, int intervalTicks,
        int intervalCountdown) {

    public static final int DEFAULT_INTERVAL = 10;

    public ActiveHeal(int partIndex, float totalAmount, int durationTicks) {
        this(partIndex, totalAmount, amountPerIntervalFor(totalAmount, durationTicks), DEFAULT_INTERVAL,
                DEFAULT_INTERVAL);
    }

    private static float amountPerIntervalFor(float totalAmount, int durationTicks) {
        int intervals = Math.max(1, durationTicks / DEFAULT_INTERVAL);
        return totalAmount / intervals;
    }

    /** 推进一个 tick，到点则扣除间隔计数；由 BodyHealthEvents 负责实际回血并决定是否移除。 */
    public ActiveHeal tick() {
        int countdown = intervalCountdown - 1;
        if (countdown <= 0) {
            return new ActiveHeal(partIndex, remainingAmount, amountPerInterval, intervalTicks, intervalTicks);
        }
        return new ActiveHeal(partIndex, remainingAmount, amountPerInterval, intervalTicks, countdown);
    }

    public boolean dueNow() {
        return intervalCountdown >= intervalTicks;
    }

    public ActiveHeal withRemaining(float newRemaining) {
        return new ActiveHeal(partIndex, newRemaining, amountPerInterval, intervalTicks, intervalCountdown);
    }

    public static final Codec<ActiveHeal> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.INT.fieldOf("part").forGetter(ActiveHeal::partIndex),
            Codec.FLOAT.fieldOf("remaining").forGetter(ActiveHeal::remainingAmount),
            Codec.FLOAT.fieldOf("per_interval").forGetter(ActiveHeal::amountPerInterval),
            Codec.INT.fieldOf("interval").forGetter(ActiveHeal::intervalTicks),
            Codec.INT.fieldOf("countdown").forGetter(ActiveHeal::intervalCountdown))
            .apply(instance, ActiveHeal::new));
}
