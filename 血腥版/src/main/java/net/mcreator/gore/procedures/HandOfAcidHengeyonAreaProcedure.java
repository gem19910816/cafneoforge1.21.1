package net.mcreator.gore.procedures;

import java.util.Comparator;
import javax.annotation.Nullable;
import net.mcreator.gore.entity.HengeyonEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingIncomingDamageEvent;

@EventBusSubscriber
public class HandOfAcidHengeyonAreaProcedure {
   @SubscribeEvent
   public static void onEntityAttacked(LivingIncomingDamageEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
      }
   }

   public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      return execute(null, world, x, y, z, entity);
   }

   private static boolean execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      return entity == null
         ? false
         : world.getEntitiesOfClass(HengeyonEntity.class, AABB.ofSize(new Vec3(x, y, z), 70.0, 70.0, 70.0), e -> true).stream().sorted((new Object() {
            Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
               return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
            }
         }).compareDistOf(x, y, z)).findFirst().orElse(null) == (entity instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null);
   }
}
