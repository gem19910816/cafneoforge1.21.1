package net.mcreator.gore.procedures;

import javax.annotation.Nullable;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.tick.EntityTickEvent.Pre;

@EventBusSubscriber
public class ExarrackdemontargetingscreamerOnEntityTickUpdateProcedure {
   @SubscribeEvent
   public static void onEntityTick(Pre event) {
      execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (entity.getPersistentData().getBoolean("exarrack_demon_targeting_screamer")) {
            entity.getPersistentData().putDouble("edtsTimer", entity.getPersistentData().getDouble("edtsTimer") + 1.0);
         }

         if (entity.getPersistentData().getDouble("edtsTimer") == 1.0 && world instanceof ServerLevel _level) {
            _level.getServer()
               .getCommands()
               .performPrefixedCommand(
                  new CommandSourceStack(CommandSource.NULL, new Vec3(x, y, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null)
                     .withSuppressedOutput(),
                  "playsound gore_edition:entity.the_exarrack_monster_scream hostile @a[distance=...1] ~ ~ ~ 1 1"
               );
         }

         if (entity.getPersistentData().getDouble("edtsTimer") == 10.0) {
            entity.getPersistentData().putBoolean("exarrack_demon_targeting_screamer", false);
            entity.getPersistentData().putDouble("edtsTimer", 0.0);
         }
      }
   }
}
