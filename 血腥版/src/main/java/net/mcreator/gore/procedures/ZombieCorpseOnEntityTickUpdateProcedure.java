package net.mcreator.gore.procedures;

import net.mcreator.gore.configuration.GoreEditionSoundsConfigurationConfiguration;
import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class ZombieCorpseOnEntityTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity instanceof ZombieCorpseEntity corpse && corpse.getPersistentData().getBoolean("gore_downed")) {
         tickDownedCorpse(corpse);
      }
      if (entity != null && entity.getPersistentData().getBoolean("fast_death")) {
         if (entity.getPersistentData().getDouble("tiimer") == 0.0 && entity instanceof ZombieCorpseEntity) {
            ((ZombieCorpseEntity)entity).setAnimation("zombie.fast_cut");
         }

         entity.getPersistentData().putDouble("tiimer", entity.getPersistentData().getDouble("tiimer") + 1.0);
         if (entity.getPersistentData().getDouble("tiimer") == 4.0 && world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_brutal_hurt_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BRUTAL_HURT_SOUND.get()).doubleValue(),
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:gore_brutal_hurt_sound")),
                  SoundSource.AMBIENT,
                  (float)((Double)GoreEditionSoundsConfigurationConfiguration.GORE_BRUTAL_HURT_SOUND.get()).doubleValue(),
                  1.0F,
                  false
               );
            }
         }

         if (entity.getPersistentData().getDouble("tiimer") == 9.0) {
            entity.kill();
         }
      }
   }

   private static void tickDownedCorpse(ZombieCorpseEntity corpse) {
      int ticks = corpse.getPersistentData().getInt("gore_downed_ticks");
      if (ticks == 48) {
         corpse.setAnimation("empty");
      }
      if (ticks > 0) {
         corpse.getPersistentData().putInt("gore_downed_ticks", ticks - 1);
      }
      if (ticks > 0) {
         return;
      }

      if (corpse.level() instanceof net.minecraft.server.level.ServerLevel level
         && corpse.getPersistentData().hasUUID("gore_downed_corpse")) {
         Entity original = level.getEntity(corpse.getPersistentData().getUUID("gore_downed_corpse"));
         if (original instanceof net.minecraft.world.entity.Mob mob && !mob.isRemoved()) {
            mob.setNoAi(corpse.getPersistentData().getBoolean("gore_downed_old_no_ai"));
            mob.setInvisible(corpse.getPersistentData().getBoolean("gore_downed_old_invisible"));
            mob.getPersistentData().remove("gore_downed");
            mob.getPersistentData().remove("gore_downed_corpse");
         }
      }
      corpse.remove(Entity.RemovalReason.DISCARDED);
   }
}
