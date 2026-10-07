package net.gem19910816.dyairdrop.entity;

import net.gem19910816.dyairdrop.init.DyairdropModItems;
import net.gem19910816.dyairdrop.init.DyairdropModEntities;
import net.gem19910816.dyairdrop.procedures.FlareburstProcedure;
import net.gem19910816.dyairdrop.procedures.FlareticksProcedure;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class FlareEntity extends AbstractArrow implements ItemSupplier {
   public static final ItemStack PROJECTILE_ITEM = new ItemStack(DyairdropModItems.FLAREGUN0.get());

   public FlareEntity(EntityType<? extends FlareEntity> type, Level world) {
      super(type, world);
   }

   public FlareEntity(EntityType<? extends FlareEntity> type, double x, double y, double z, Level world) {
      super(type, world);
      this.setPos(x, y, z);
   }

   public FlareEntity(EntityType<? extends FlareEntity> type, LivingEntity entity, Level world) {
      super(type, world);
      this.setOwner(entity);
   }


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
      FlareburstProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), entityHitResult.getEntity(), this);
   }

   public void onHitBlock(BlockHitResult blockHitResult) {
      super.onHitBlock(blockHitResult);
      FlareburstProcedure.execute(
         this.level(),
         blockHitResult.getBlockPos().getX(),
         blockHitResult.getBlockPos().getY(),
         blockHitResult.getBlockPos().getZ(),
         this.getOwner(),
         this
      );
   }

   public void tick() {
      super.tick();
      FlareticksProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this.getOwner(), this);
      if (this.inGround) {
         this.discard();
      }
   }

   public static FlareEntity shoot(Level world, LivingEntity entity, RandomSource source) {
      return shoot(world, entity, source, 1.1F, 0.0, 0);
   }

   public static FlareEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
      FlareEntity entityarrow = new FlareEntity(DyairdropModEntities.FLARE.get(), entity, world);
      entityarrow.shoot(entity.getViewVector(1.0F).x, entity.getViewVector(1.0F).y, entity.getViewVector(1.0F).z, power * 2.0F, 0.0F);
      entityarrow.setSilent(true);
      entityarrow.setCritArrow(false);
      entityarrow.setBaseDamage(damage);
      
      entityarrow.igniteForSeconds(100);
      world.addFreshEntity(entityarrow);
      world.playSound(
         null,
         entity.getX(),
         entity.getY(),
         entity.getZ(),
         SoundEvents.FIREWORK_ROCKET_LAUNCH,
         SoundSource.PLAYERS,
         1.0F,
         1.0F / (random.nextFloat() * 0.5F + 1.0F) + power / 2.0F
      );
      return entityarrow;
   }

   public static FlareEntity shoot(LivingEntity entity, LivingEntity target) {
      FlareEntity entityarrow = new FlareEntity(DyairdropModEntities.FLARE.get(), entity, entity.level());
      double dx = target.getX() - entity.getX();
      double dy = target.getY() + target.getEyeHeight() - 1.1;
      double dz = target.getZ() - entity.getZ();
      entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 2.2F, 12.0F);
      entityarrow.setSilent(true);
      entityarrow.setBaseDamage(0.0);
      
      entityarrow.setCritArrow(false);
      entityarrow.igniteForSeconds(100);
      entity.level().addFreshEntity(entityarrow);
      entity.level()
         .playSound(
            null,
            entity.getX(),
            entity.getY(),
            entity.getZ(),
            SoundEvents.FIREWORK_ROCKET_LAUNCH,
            SoundSource.PLAYERS,
            1.0F,
            1.0F / (RandomSource.create().nextFloat() * 0.5F + 1.0F)
         );
      return entityarrow;
   }
}
