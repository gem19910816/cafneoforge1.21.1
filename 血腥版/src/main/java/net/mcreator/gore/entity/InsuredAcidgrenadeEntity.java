package net.mcreator.gore.entity;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.procedures.ASFWEHF9ewquasdgProcedure;
import net.mcreator.gore.procedures.SAKBKJASbjsfProcedure;
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
public class InsuredAcidgrenadeEntity extends AbstractArrow implements ItemSupplier {
   public static final ItemStack PROJECTILE_ITEM = new ItemStack((ItemLike)GoreEditionModItems.GRENADE_OF_ASHES_ACID.get());

   public InsuredAcidgrenadeEntity(EntityType<? extends InsuredAcidgrenadeEntity> type, Level world) {
      super(type, world);
   }

   public InsuredAcidgrenadeEntity(EntityType<? extends InsuredAcidgrenadeEntity> type, double x, double y, double z, Level world) {
      super(type, x, y, z, world, PROJECTILE_ITEM, null);
   }

   public InsuredAcidgrenadeEntity(EntityType<? extends InsuredAcidgrenadeEntity> type, LivingEntity entity, Level world) {
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
      SAKBKJASbjsfProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
   }

   public void onHitBlock(BlockHitResult blockHitResult) {
      super.onHitBlock(blockHitResult);
      ASFWEHF9ewquasdgProcedure.execute(
         this.level(), (double)blockHitResult.getBlockPos().getX(), (double)blockHitResult.getBlockPos().getY(), (double)blockHitResult.getBlockPos().getZ()
      );
   }

   public void tick() {
      super.tick();
      if (this.inGround) {
         this.discard();
      }
   }

   public static InsuredAcidgrenadeEntity shoot(Level world, LivingEntity entity, RandomSource source) {
      return shoot(world, entity, source, 1.0F, 1.0, 0);
   }

   public static InsuredAcidgrenadeEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
      return shoot(world, entity, source, pullingPower * 1.0F, 1.0, 0);
   }

   public static InsuredAcidgrenadeEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
      InsuredAcidgrenadeEntity entityarrow = new InsuredAcidgrenadeEntity(
         (EntityType<? extends InsuredAcidgrenadeEntity>)GoreEditionModEntities.GRENADE_OF_ACID.get(), entity, world
      );
      entityarrow.shoot(entity.getViewVector(1.0F).x, entity.getViewVector(1.0F).y, entity.getViewVector(1.0F).z, power * 2.0F, 0.0F);
      entityarrow.setSilent(true);
      entityarrow.setCritArrow(false);
      entityarrow.setBaseDamage(damage);
      world.addFreshEntity(entityarrow);
      return entityarrow;
   }

   public static InsuredAcidgrenadeEntity shoot(LivingEntity entity, LivingEntity target) {
      InsuredAcidgrenadeEntity entityarrow = new InsuredAcidgrenadeEntity(
         (EntityType<? extends InsuredAcidgrenadeEntity>)GoreEditionModEntities.GRENADE_OF_ACID.get(), entity, entity.level()
      );
      double dx = target.getX() - entity.getX();
      double dy = target.getY() + (double)target.getEyeHeight() - 1.1;
      double dz = target.getZ() - entity.getZ();
      entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 2.0F, 12.0F);
      entityarrow.setSilent(true);
      entityarrow.setBaseDamage(1.0);
      entityarrow.setCritArrow(false);
      entity.level().addFreshEntity(entityarrow);
      return entityarrow;
   }
}
