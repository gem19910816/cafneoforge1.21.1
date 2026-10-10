package net.mcreator.survivalinstinct.entity;

import net.mcreator.survivalinstinct.init.SurvivalInstinctModEntities;
import net.mcreator.survivalinstinct.init.SurvivalInstinctModItems;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

@OnlyIn(
   value = Dist.CLIENT,
   _interface = ItemSupplier.class
)
public class NailProyectileEntity extends AbstractArrow implements ItemSupplier {
   public static final ItemStack PROJECTILE_ITEM = new ItemStack(SurvivalInstinctModItems.NAIL.get());

public NailProyectileEntity(EntityType<? extends NailProyectileEntity> type, Level world) {
      super(type, world);
   }

   public NailProyectileEntity(EntityType<? extends NailProyectileEntity> type, double x, double y, double z, Level world) {
      super(type, x, y, z, world, PROJECTILE_ITEM.copy(), null);
   }

   public NailProyectileEntity(EntityType<? extends NailProyectileEntity> type, LivingEntity entity, Level world) {
      super(type, entity, world, PROJECTILE_ITEM.copy(), null);
   }

   

   @OnlyIn(Dist.CLIENT)
   @Override
   public ItemStack getItem() {
      return PROJECTILE_ITEM;
   }

   @Override
   protected ItemStack getDefaultPickupItem() {
      return PROJECTILE_ITEM;
   }

   @Override
   protected void doPostHurtEffects(LivingEntity entity) {
      super.doPostHurtEffects(entity);
      entity.setArrowCount(entity.getArrowCount() - 1);
   }

   @Override
   public void tick() {
      super.tick();
      if (this.inGround) {
         this.discard();
      }
   }

   public static NailProyectileEntity shoot(Level world, LivingEntity entity, RandomSource source) {
      return shoot(world, entity, source, 1.4F, 1.9, 0);
   }

   public static NailProyectileEntity shoot(Level world, LivingEntity entity, RandomSource source, float pullingPower) {
      return shoot(world, entity, source, pullingPower * 1.4F, 1.9, 0);
   }

   public static NailProyectileEntity shoot(Level world, LivingEntity entity, RandomSource random, float power, double damage, int knockback) {
      NailProyectileEntity entityarrow = new NailProyectileEntity(SurvivalInstinctModEntities.NAIL_PROYECTILE.get(), entity, world);
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
         BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("survival_instinct:nailgun_shoot")),
         SoundSource.PLAYERS,
         1.0F,
         1.0F / (random.nextFloat() * 0.5F + 1.0F) + power / 2.0F
      );
      return entityarrow;
   }

   public static NailProyectileEntity shoot(LivingEntity entity, LivingEntity target) {
      NailProyectileEntity entityarrow = new NailProyectileEntity(SurvivalInstinctModEntities.NAIL_PROYECTILE.get(), entity, entity.level());
      double dx = target.getX() - entity.getX();
      double dy = target.getY() + (double)target.getEyeHeight() - 1.1;
      double dz = target.getZ() - entity.getZ();
      entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 2.8F, 12.0F);
      entityarrow.setSilent(true);
      entityarrow.setBaseDamage(1.9);
      entityarrow.setCritArrow(false);
      entity.level().addFreshEntity(entityarrow);
      entity.level()
         .playSound(
            null,
            entity.getX(),
            entity.getY(),
            entity.getZ(),
            BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("survival_instinct:nailgun_shoot")),
            SoundSource.PLAYERS,
            1.0F,
            1.0F / (RandomSource.create().nextFloat() * 0.5F + 1.0F)
         );
      return entityarrow;
   }
}
