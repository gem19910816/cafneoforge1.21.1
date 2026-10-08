package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.LevelTickEvent.Post;

@EventBusSubscriber
public class AshesWindProcedureProcedure {
   @SubscribeEvent
   public static void onWorldTick(Post event) {
      execute(event, event.getLevel());
   }

   public static void execute(LevelAccessor world) {
      execute(null, world);
   }

   private static void execute(@Nullable Event event, LevelAccessor world) {
      if ((world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD))
            == ResourceKey.create(Registries.DIMENSION, ResourceLocation.parse("gore_edition:the_xash"))
         && Math.random() < 8.0E-4
         && world instanceof Level _level) {
         if (!_level.isClientSide()) {
            _level.playSound(
               null,
               new BlockPos(0, 0, 0),
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:ambient.ashes_wind")),
               SoundSource.AMBIENT,
               0.1F,
               1.0F
            );
         } else {
            _level.playLocalSound(
               0.0,
               0.0,
               0.0,
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:ambient.ashes_wind")),
               SoundSource.AMBIENT,
               0.1F,
               1.0F,
               false
            );
         }
      }
   }
}
