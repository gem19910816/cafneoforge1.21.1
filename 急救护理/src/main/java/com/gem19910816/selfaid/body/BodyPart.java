package com.gem19910816.selfaid.body;

/**
 * 六个身体部位。maxHealthFraction 决定该部位在玩家总生命值（默认 20）中所占的比例。
 */
public enum BodyPart {
    HEAD(0.20F),
    TORSO(0.30F),
    LEFT_ARM(0.10F),
    RIGHT_ARM(0.10F),
    LEFT_LEG(0.15F),
    RIGHT_LEG(0.15F);

    public static final BodyPart[] VALUES = values();
    public static final int COUNT = VALUES.length;

    private final float maxHealthFraction;

    BodyPart(float maxHealthFraction) {
        this.maxHealthFraction = maxHealthFraction;
    }

    public float maxHealthFraction() {
        return maxHealthFraction;
    }

    public float maxHealthFor(float playerMaxHealth) {
        return playerMaxHealth * maxHealthFraction;
    }

    public static BodyPart byIndex(int index) {
        return VALUES[Math.floorMod(index, COUNT)];
    }

    public boolean isLimb() {
        return this == LEFT_ARM || this == RIGHT_ARM || this == LEFT_LEG || this == RIGHT_LEG;
    }
}
