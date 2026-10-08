package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEntityUseItemEvent.Finish;

@EventBusSubscriber
public class AdgProcedure {
   @SubscribeEvent
   public static void onUseItemFinish(Finish event) {
      if (event != null && event.getEntity() != null) {
         execute(
            event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity(), event.getItem()
         );
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      execute(null, world, x, y, z, entity, itemstack);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity, ItemStack itemstack) {
      if (entity != null
         && itemstack.getItem() == Items.POTION
         && (!(entity instanceof Player _plr) || !_plr.getAbilities().instabuild)
         && world instanceof Level _level) {
         if (!_level.isClientSide()) {
            _level.playSound(
               null,
               BlockPos.containing(x, y, z),
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.splash_potion.break")),
               SoundSource.AMBIENT,
               3.0F,
               1.5F
            );
         } else {
            _level.playLocalSound(
               x,
               y,
               z,
               (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("entity.splash_potion.break")),
               SoundSource.AMBIENT,
               3.0F,
               1.5F,
               false
            );
         }
      }
   }
}
