package net.mcreator.gore.entity;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.procedures.ProjectileWitherSkullProjectileHitsBlockProcedure;
import net.mcreator.gore.procedures.ProjectileWitherSkullProjectileHitsLivingEntityProcedure;
import net.mcreator.gore.procedures.ProjectileWitherSkullWhileProjectileFlyingTickProcedure;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(
   value = Dist.CLIENT,
   _interface = ItemSupplier.class
)
public class ProjectileWitherSkullEntity extends AbstractArrow implements ItemSupplier {
   public static final ItemStack PROJECTILE_ITEM = new ItemStack((ItemLike)GoreEditionModItems.THROWABLE_WITHER_SKULL.get());

   public ProjectileWitherSkullEntity(EntityType<? extends ProjectileWitherSkullEntity> type, Level world) {
      super(type, world);
   }

   public ProjectileWitherSkullEntity(EntityType<? extends ProjectileWitherSkullEntity> type, double x, double y, double z, Level world) {
      super(type, x, y, z, world, PROJECTILE_ITEM, null);
   }

   public ProjectileWitherSkullEntity(EntityType<? extends ProjectileWitherSkullEntity> type, LivingEntity entity, Level world) {
      super(type, entity, world, PROJECTILE_ITEM, null);
   }

   @OnlyIn(Dist.CLIENT)
   public ItemStack getItem() {
      return PROJECTILE_ITEM;
   }

   protected ItemStack getDefaultPickupItem() {
      return PROJECTILE_ITEM;
   }

   protected void doPostHurtEffects(LivingEntity entity) {
      super.doPostHurtEffects(entity);
      entity.setArrowCount(entity.getArrowCount() - 1);
   }

   public void onHitEntity(EntityHitResult entityHitResult) {
      super.onHitEntity(entityHitResult);
      ProjectileWitherSkullProjectileHitsLivingEntityProcedure.execute(
         this.level(), this.getX(), this.getY(), this.getZ(), entityHitResult.getEntity(), this, this.getOwner()
      );
   }

   public void onHitBlock(BlockHitResult blockHitResult) {
      super.onHitBlock(blockHitResult);
      ProjectileWitherSkullProjectileHitsBlockProcedure.execute(
         this.level(),
         (double)blockHitResult.getBlockPos().getX(),
         (double)blockHitResult.getBlockPos().getY(),
         (double)blockHitResult.getBlockPos().getZ(),
         this
      );
   }

   public void tick() {
      super.tick();
      ProjectileWitherSkullWhileProjectileFlyingTickProcedure.execute(this);
      if (this.inGround) {
         this.discard();
      }
   }

   public static ProjectileWitherSkullEntity shoot(Level world, LivingEntity entity, RandomSource source) {
      return shoot(world, entity, source, 0.5F, 8.0, 0);
   }

   public static ProjectileWitherSkullEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
      return shoot(world, entity, source, pullingPower * 0.5F, 8.0, 0);
   }

   public static ProjectileWitherSkullEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
      ProjectileWitherSkullEntity entityarrow = new ProjectileWitherSkullEntity(
         (EntityType<? extends ProjectileWitherSkullEntity>)GoreEditionModEntities.PROJECTILE_WITHER_SKULL.get(), entity, world
      );
      entityarrow.shoot(entity.getViewVector(1.0F).x, entity.getViewVector(1.0F).y, entity.getViewVector(1.0F).z, power * 2.0F, 0.0F);
      entityarrow.setSilent(true);
      entityarrow.setCritArrow(false);
      entityarrow.setBaseDamage(damage);
      world.addFreshEntity(entityarrow);
      return entityarrow;
   }

   public static ProjectileWitherSkullEntity shoot(LivingEntity entity, LivingEntity target) {
      ProjectileWitherSkullEntity entityarrow = new ProjectileWitherSkullEntity(
         (EntityType<? extends ProjectileWitherSkullEntity>)GoreEditionModEntities.PROJECTILE_WITHER_SKULL.get(), entity, entity.level()
      );
      double dx = target.getX() - entity.getX();
      double dy = target.getY() + (double)target.getEyeHeight() - 1.1;
      double dz = target.getZ() - entity.getZ();
      entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1.0F, 12.0F);
      entityarrow.setSilent(true);
      entityarrow.setBaseDamage(8.0);
      entityarrow.setCritArrow(false);
      entity.level().addFreshEntity(entityarrow);
      return entityarrow;
   }
}
