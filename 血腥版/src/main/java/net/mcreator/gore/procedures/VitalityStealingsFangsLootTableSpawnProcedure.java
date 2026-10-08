package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class VitalityStealingsFangsLootTableSpawnProcedure {
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
      if (entity != null && sourceentity != null && entity instanceof Bat) {
         if (ItemTagHelper.getEnchantLevel(
               sourceentity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY, Enchantments.LOOTING, world
            )
            == 0) {
            if (Math.random() < 0.085 && world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
                        .withSuppressedOutput(),
                     "loot spawn ~ ~ ~ loot gore_edition:entities/vitality_stealings_fangs_loot_table"
                  );
            }
         } else if (Math.random()
               < 0.085
                  * (double)ItemTagHelper.getEnchantLevel(
                     sourceentity instanceof LivingEntity _livEntx ? _livEntx.getMainHandItem() : ItemStack.EMPTY, Enchantments.LOOTING, world
                  )
                  * 0.7
            && world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
                     .withSuppressedOutput(),
                  "loot spawn ~ ~ ~ loot gore_edition:entities/vitality_stealings_fangs_loot_table"
               );
         }
      }
   }
}
