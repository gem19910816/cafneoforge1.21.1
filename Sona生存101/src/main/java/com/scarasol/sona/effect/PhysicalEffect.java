package com.scarasol.sona.effect;

import net.minecraft.world.effect.MobEffectCategory;

// 1.20.1 中此类通过 getCurativeItems() 返回空列表使物理类效果无法被牛奶解除；
// 1.21 的 MobEffect 已移除该 API（NeoForge 改用 EffectCure 机制），此处暂无法表达该语义。
public class PhysicalEffect extends MobEffectBase{
    public PhysicalEffect(MobEffectCategory mobEffectCategory, int integer) {
        super(mobEffectCategory, integer);
    }
}
