package com.chaosz.tarkovstamina.entity;

import com.chaosz.tarkovstamina.item.StaminaEntities;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;

/**
 * 屎投掷物实体
 * <p>
 * 右键 caf:shit（未蹲下）投掷，命中造成 1 点伤害 + 反胃 10 秒。
 * </p>
 */
public class ShitballEntity extends ThrowableItemProjectile {
    private static final ResourceLocation SHIT_ITEM =
            ResourceLocation.fromNamespaceAndPath("caf", "shit");
    private static final int NAUSEA_TICKS = 200;
    private static final float IMPACT_DAMAGE = 1.0F;

    public ShitballEntity(EntityType<? extends ShitballEntity> type, Level level) {
        super(type, level);
    }

    public ShitballEntity(Level level, LivingEntity owner) {
        super(StaminaEntities.SHITBALL.get(), owner, level);
    }

    @Override
    protected Item getDefaultItem() {
        // 1.21.1：注册表查询不再返回 null，未注册的 id 拿到的是 Items.AIR。
        Item item = BuiltInRegistries.ITEM.get(SHIT_ITEM);
        if (item != Items.AIR) {
            return item;
        }
        return Items.SNOWBALL;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        if (this.level().isClientSide()) return;

        Entity target = result.getEntity();
        target.hurt(this.damageSources().thrown(this, this.getOwner()),
                IMPACT_DAMAGE);

        if (target instanceof LivingEntity living) {
            Entity ownerEnt = this.getOwner();
            living.addEffect(
                    new MobEffectInstance(MobEffects.CONFUSION, NAUSEA_TICKS, 0),
                    ownerEnt instanceof LivingEntity livingOwner ? livingOwner : null);
        }
    }
}
