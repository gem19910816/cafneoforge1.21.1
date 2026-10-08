package net.mcreator.gore.procedures;

import java.util.Comparator;
import javax.annotation.Nullable;
import net.mcreator.gore.configuration.GoreEditionModeSettingsConfiguration;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;

@EventBusSubscriber
public class BloodInScreenActivateI1Procedure {
   @SubscribeEvent
   public static void onEntityDeath(LivingDeathEvent event) {
      if (event != null && event.getEntity() != null) {
         execute(event, event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), event.getEntity());
      }
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      execute(null, world, x, y, z, entity);
   }

   private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null
         && (
            !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(entity.getX(), entity.getY(), entity.getZ()), 4.0, 4.0, 4.0), e -> true).isEmpty()
               || !world.getEntitiesOfClass(ServerPlayer.class, AABB.ofSize(new Vec3(entity.getX(), entity.getY(), entity.getZ()), 4.0, 4.0, 4.0), e -> true)
                  .isEmpty()
         )
         && !entity.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("gore_edition:ashes_natives")))
         && (Boolean)GoreEditionModeSettingsConfiguration.BLOOD_IN_SCREEN.get()) {
         Entity _nearest = world.getEntitiesOfClass(ServerPlayer.class, AABB.ofSize(new Vec3(x, y, z), 30.0, 30.0, 30.0), e -> true)
            .stream()
            .sorted((new Object() {
               Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                  return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
               }
            }).compareDistOf(x, y, z))
            .findFirst()
            .orElse(null);
         if (_nearest == null) {
            return;
         }

         if ((_nearest instanceof LivingEntity _entGetArmor ? _entGetArmor.getItemBySlot(EquipmentSlot.HEAD) : ItemStack.EMPTY).getItem()
               != Blocks.CARVED_PUMPKIN.asItem()
            && GoreEditionModVariables.getPlayerVariables(_nearest).blood_in_screen_i_amount < 10.0) {
            double _setvalx = GoreEditionModVariables.getPlayerVariables(_nearest).blood_in_screen_i_amount + 1.0;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(_nearest);
            capability.blood_in_screen_i_amount = _setvalx;
            capability.syncPlayerVariables(_nearest);
         }
      }
   }
}
