package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class GetMusicDiscProcedure {
   @SubscribeEvent
   public static void onEntityDeath(LivingDeathEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event,
            event.getEntity().level(),
            event.getEntity().getX(),
            event.getEntity().getY(),
            event.getEntity().getZ(),
            event.getEntity(),
            event.getSource().getEntity()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
      execute(null, world, x, y, z, entity, sourceentity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, Entity sourceentity) {
      if (entity != null && sourceentity != null && entity instanceof Creeper && sourceentity instanceof Skeleton) {
         if (entity instanceof LivingEntity _livEnt2 && _livEnt2.hasEffect(MobEffects.POISON) && world instanceof ServerLevel _level) {
            ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack((ItemLike)GoreEditionModItems.MUSIC_DISC_POISONED.get()));
            entityToSpawn.setPickUpDelay(10);
            _level.addFreshEntity(entityToSpawn);
         }

         if (entity instanceof LivingEntity _livEnt4 && _livEnt4.hasEffect(MobEffects.WITHER) && world instanceof ServerLevel _level) {
            ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack((ItemLike)GoreEditionModItems.MUSIC_DISC_LIMBO.get()));
            entityToSpawn.setPickUpDelay(10);
            _level.addFreshEntity(entityToSpawn);
         }

         if (entity instanceof LivingEntity _livEnt6 && _livEnt6.hasEffect(GoreEditionModMobEffects.VULNERABILITY) && world instanceof ServerLevel _level) {
            ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack((ItemLike)GoreEditionModItems.MUSIC_DISC_INTERNAL_INSANITY.get()));
            entityToSpawn.setPickUpDelay(10);
            _level.addFreshEntity(entityToSpawn);
         }

         if (entity.isOnFire() && world instanceof ServerLevel _level) {
            ItemEntity entityToSpawn = new ItemEntity(_level, x, y, z, new ItemStack((ItemLike)GoreEditionModItems.MUSIC_DISC_THE_XASH.get()));
            entityToSpawn.setPickUpDelay(10);
            _level.addFreshEntity(entityToSpawn);
         }
      }
   }
}
