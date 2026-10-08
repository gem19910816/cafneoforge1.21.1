package net.mcreator.gore.entity;

import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.procedures.MagmaGrenadeProjectileProjectileHitsBlockProcedure;
import net.mcreator.gore.procedures.MagmaGrenadeProjectileProjectileHitsLivingEntityProcedure;
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
public class MagmaGrenadeProjectileEntity extends AbstractArrow implements ItemSupplier {
   public static final ItemStack PROJECTILE_ITEM = new ItemStack((ItemLike)GoreEditionModItems.NETHER_GRENADE.get());

   public MagmaGrenadeProjectileEntity(EntityType<? extends MagmaGrenadeProjectileEntity> type, Level world) {
      super(type, world);
   }

   public MagmaGrenadeProjectileEntity(EntityType<? extends MagmaGrenadeProjectileEntity> type, double x, double y, double z, Level world) {
      super(type, x, y, z, world, PROJECTILE_ITEM, null);
   }

   public MagmaGrenadeProjectileEntity(EntityType<? extends MagmaGrenadeProjectileEntity> type, LivingEntity entity, Level world) {
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
      MagmaGrenadeProjectileProjectileHitsLivingEntityProcedure.execute(this.level(), this.getX(), this.getY(), this.getZ(), this);
   }

   public void onHitBlock(BlockHitResult blockHitResult) {
      super.onHitBlock(blockHitResult);
      MagmaGrenadeProjectileProjectileHitsBlockProcedure.execute(
         this.level(),
         (double)blockHitResult.getBlockPos().getX(),
         (double)blockHitResult.getBlockPos().getY(),
         (double)blockHitResult.getBlockPos().getZ(),
         this
      );
   }

   public void tick() {
      super.tick();
      if (this.inGround) {
         this.discard();
      }
   }

   public static MagmaGrenadeProjectileEntity shoot(Level world, LivingEntity entity, RandomSource source) {
      return shoot(world, entity, source, 0.7F, 11.0, 0);
   }

   public static MagmaGrenadeProjectileEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
      return shoot(world, entity, source, pullingPower * 0.7F, 11.0, 0);
   }

   public static MagmaGrenadeProjectileEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
      MagmaGrenadeProjectileEntity entityarrow = new MagmaGrenadeProjectileEntity(
         (EntityType<? extends MagmaGrenadeProjectileEntity>)GoreEditionModEntities.MAGMA_GRENADE_PROJECTILE.get(), entity, world
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
         (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
         SoundSource.PLAYERS,
         1.0F,
         1.0F / (random.nextFloat() * 0.5F + 1.0F) + power / 2.0F
      );
      return entityarrow;
   }

   public static MagmaGrenadeProjectileEntity shoot(LivingEntity entity, LivingEntity target) {
      MagmaGrenadeProjectileEntity entityarrow = new MagmaGrenadeProjectileEntity(
         (EntityType<? extends MagmaGrenadeProjectileEntity>)GoreEditionModEntities.MAGMA_GRENADE_PROJECTILE.get(), entity, entity.level()
      );
      double dx = target.getX() - entity.getX();
      double dy = target.getY() + (double)target.getEyeHeight() - 1.1;
      double dz = target.getZ() - entity.getZ();
      entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 1.4F, 12.0F);
      entityarrow.setSilent(true);
      entityarrow.setBaseDamage(11.0);
      entityarrow.setCritArrow(false);
      entity.level().addFreshEntity(entityarrow);
      entity.level()
         .playSound(
            null,
            entity.getX(),
            entity.getY(),
            entity.getZ(),
            (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("item.firecharge.use")),
            SoundSource.PLAYERS,
            1.0F,
            1.0F / (RandomSource.create().nextFloat() * 0.5F + 1.0F)
         );
      return entityarrow;
   }
}
