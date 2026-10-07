package net.gem19910816.dyairdrop.core;

import net.gem19910816.dyairdrop.entity.FlareEntity;
import net.gem19910816.dyairdrop.init.DyairdropModEntities;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;

/**
 * 投射物构造工具：取代 MCreator 生成的 {@code getArrow} 匿名类。
 *
 * <p>属性设置顺序与旧实现一致（owner → baseDamage → silent），信号弹的弹道与静音行为不变。
 */
public final class Projectiles {

    private Projectiles() {
    }

    /** 信号弹（{@code dyairdrop:flare}），伤害与静音标记与旧实现相同。 */
    @SuppressWarnings("unchecked")
    public static AbstractArrow flare(Level level, Entity shooter, float damage) {
        AbstractArrow arrow = new FlareEntity((EntityType<? extends FlareEntity>) DyairdropModEntities.FLARE.get(), level);
        arrow.setOwner(shooter);
        arrow.setBaseDamage(damage);
        arrow.setSilent(true);
        return arrow;
    }
}
