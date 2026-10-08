package net.mcreator.gore.procedures;

import java.util.Optional;
import javax.annotation.Nullable;
import net.minecraft.core.Holder;
import net.minecraft.core.Holder.Reference;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class ExplodeCorpseEnchantmentWhenCorpseDiesProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getSource().getEntity());
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity sourceentity) {
      execute(null, world, x, y, z, sourceentity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity sourceentity) {
      if (sourceentity != null) {
         int _enchLvl = getExplosiveContactLevel(world, sourceentity);
         if (_enchLvl != 0) {
            if (_enchLvl == 1 && world instanceof Level _level && !_level.isClientSide()) {
               _level.explode(null, x, y, z, 0.1F, ExplosionInteraction.MOB);
            }

            if (_enchLvl == 2 && world instanceof Level _level && !_level.isClientSide()) {
               _level.explode(null, x, y, z, 0.27F, ExplosionInteraction.MOB);
            }

            if (_enchLvl == 3 && world instanceof Level _level && !_level.isClientSide()) {
               _level.explode(null, x, y, z, 0.5F, ExplosionInteraction.MOB);
            }

            if (_enchLvl == 4 && world instanceof Level _level && !_level.isClientSide()) {
               _level.explode(null, x, y, z, 0.7F, ExplosionInteraction.MOB);
            }

            if (_enchLvl >= 5 && world instanceof Level _level && !_level.isClientSide()) {
               _level.explode(null, x, y, z, 1.0F, ExplosionInteraction.MOB);
            }
         }
      }
   }

   private static int getExplosiveContactLevel(LevelAccessor world, Entity sourceentity) {
      if (sourceentity instanceof LivingEntity liv) {
         Optional<Reference<Enchantment>> holder = world.registryAccess()
            .registryOrThrow(Registries.ENCHANTMENT)
            .getHolder(ResourceKey.create(Registries.ENCHANTMENT, ResourceLocation.fromNamespaceAndPath("gore_edition", "explosive_contact")));
         return holder.isEmpty() ? 0 : EnchantmentHelper.getItemEnchantmentLevel((Holder)holder.get(), liv.getMainHandItem());
      } else {
         return 0;
      }
   }
}
