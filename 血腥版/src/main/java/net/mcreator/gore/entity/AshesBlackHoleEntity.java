package net.mcreator.gore.entity;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.procedures.AshesBlackHoleProjectileHitsLivingEntityProcedure;
import net.mcreator.gore.procedures.AshesBlackHoleWhileProjectileFlyingTickProcedure;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
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
public class AshesBlackHoleEntity extends AbstractArrow implements ItemSupplier {
   public static final ItemStack PROJECTILE_ITEM = new ItemStack((ItemLike)GoreEditionModItems.TEXTURE_OF_ASHES_BLACK_HOLE.get());

   public AshesBlackHoleEntity(EntityType<? extends AshesBlackHoleEntity> type, Level world) {
      super(type, world);
   }

   public AshesBlackHoleEntity(EntityType<? extends AshesBlackHoleEntity> type, double x, double y, double z, Level world) {
      super(type, x, y, z, world, PROJECTILE_ITEM, null);
   }

   public AshesBlackHoleEntity(EntityType<? extends AshesBlackHoleEntity> type, LivingEntity entity, Level world) {
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
      AshesBlackHoleProjectileHitsLivingEntityProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ());
   }

   public void onHitBlock(BlockHitResult blockHitResult) {
      super.onHitBlock(blockHitResult);
      AshesBlackHoleProjectileHitsLivingEntityProcedure.execute(
         this.level(), (double)blockHitResult.getBlockPos().getX(), (double)blockHitResult.getBlockPos().getY(), (double)blockHitResult.getBlockPos().getZ()
      );
   }

   public void tick() {
      super.tick();
      AshesBlackHoleWhileProjectileFlyingTickProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
      if (this.inGround) {
         this.discard();
      }
   }

   public static AshesBlackHoleEntity shoot(Level world, LivingEntity entity, RandomSource source) {
      return shoot(world, entity, source, 0.4F, 30.0, 0);
   }

   public static AshesBlackHoleEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
      return shoot(world, entity, source, pullingPower * 0.4F, 30.0, 0);
   }

   public static AshesBlackHoleEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
      AshesBlackHoleEntity entityarrow = new AshesBlackHoleEntity(
         (EntityType<? extends AshesBlackHoleEntity>)GoreEditionModEntities.ASHES_BLACK_HOLE.get(), entity, world
      );
      entityarrow.shoot(entity.getViewVector(1.0F).x, entity.getViewVector(1.0F).y, entity.getViewVector(1.0F).z, power * 2.0F, 0.0F);
      entityarrow.setSilent(true);
      entityarrow.setCritArrow(false);
      entityarrow.setBaseDamage(damage);
      world.addFreshEntity(entityarrow);
      world.playSound(
         null,
         entity.getX(),
         entity.getY(),
         entity.getZ(),
         (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.arrow.shoot")),
         SoundSource.PLAYERS,
         1.0F,
         1.0F / (random.nextFloat() * 0.5F + 1.0F) + power / 2.0F
      );
      return entityarrow;
   }

   public static AshesBlackHoleEntity shoot(LivingEntity entity, LivingEntity target) {
      AshesBlackHoleEntity entityarrow = new AshesBlackHoleEntity(
         (EntityType<? extends AshesBlackHoleEntity>)GoreEditionModEntities.ASHES_BLACK_HOLE.get(), entity, entity.level()
      );
      double dx = target.getX() - entity.getX();
      double dy = target.getY() + (double)target.getEyeHeight() - 1.1;
      double dz = target.getZ() - entity.getZ();
      entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 0.8F, 12.0F);
      entityarrow.setSilent(true);
      entityarrow.setBaseDamage(30.0);
      entityarrow.setCritArrow(false);
      entity.level().addFreshEntity(entityarrow);
      entity.level()
         .playSound(
            null,
            entity.getX(),
            entity.getY(),
            entity.getZ(),
            (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.arrow.shoot")),
            SoundSource.PLAYERS,
            1.0F,
            1.0F / (RandomSource.create().nextFloat() * 0.5F + 1.0F)
         );
      return entityarrow;
   }
}
