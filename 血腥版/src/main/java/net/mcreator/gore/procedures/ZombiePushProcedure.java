package net.mcreator.gore.procedures;

import java.util.List;
import java.lang.reflect.Method;
import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.monster.Husk;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.EntityInteract;

/**
 * Handles the short area knockdown used by the Gore Edition zombie shove.
 * The original mob stays alive and is hidden, so its AI, equipment, health,
 * custom data, and resource-pack texture are preserved exactly.
 */
@EventBusSubscriber
public final class ZombiePushProcedure {
   private static final String DOWNED_KEY = "gore_downed";
   private static final String CORPSE_KEY = "gore_downed_corpse";
   private static final String PUSH_COOLDOWN_KEY = "gore_push_until";
   private static final int PUSH_COOLDOWN_TICKS = 14;
   private static final int DOWNED_TICKS = 60;
   private static final double RADIUS = 3.25D;

   private ZombiePushProcedure() {
   }

   @SubscribeEvent
   public static void onRightClickEntity(EntityInteract event) {
      if (event.getHand() != event.getEntity().getUsedItemHand()
         || !(event.getEntity() instanceof Player player)
         || player.level().isClientSide()
         || !(player.level() instanceof ServerLevel level)
         || !isMeleeOrEmpty(event.getItemStack())
         || !isZombieTarget(event.getTarget())) {
         return;
      }

      long now = level.getGameTime();
      if (player.getPersistentData().getLong(PUSH_COOLDOWN_KEY) > now) {
         return;
      }

      int pushed = pushNearby(level, player, event.getTarget());
      if (pushed > 0) {
         player.getPersistentData().putLong(PUSH_COOLDOWN_KEY, now + PUSH_COOLDOWN_TICKS);
         event.setCanceled(true);
      }
   }

   private static int pushNearby(ServerLevel level, Player player, Entity clicked) {
      AABB area = clicked.getBoundingBox().inflate(RADIUS, 1.5D, RADIUS);
      List<Mob> targets = level.getEntitiesOfClass(Mob.class, area, entity ->
         isZombieTarget(entity) && !entity.getPersistentData().getBoolean(DOWNED_KEY));

      int pushed = 0;
      for (Mob target : targets) {
         if (knockDown(level, player, target)) {
            pushed++;
         }
      }
      return pushed;
   }

   private static boolean knockDown(ServerLevel level, Player player, Mob target) {
      if (target.isRemoved() || target.isDeadOrDying() || target.getPersistentData().getBoolean(DOWNED_KEY)) {
         return false;
      }

      ZombieCorpseEntity corpse = GoreEditionModEntities.ZOMBIE_CORPSE.get().create(level);
      if (corpse == null) {
         return false;
      }

      corpse.moveTo(target.getX(), target.getY(), target.getZ(), target.getYRot(), target.getXRot());
      corpse.setYBodyRot(target.getYRot());
      corpse.setYHeadRot(target.getYRot());
      corpse.yRotO = target.getYRot();
      corpse.xRotO = target.getXRot();
      corpse.setTexture(textureFor(target));
      corpse.setAnimation("zombie.spawn");
      corpse.setInvulnerable(true);
      corpse.setNoAi(true);
      corpse.getPersistentData().putBoolean(DOWNED_KEY, true);
      corpse.getPersistentData().putInt("gore_downed_ticks", DOWNED_TICKS);
      corpse.getPersistentData().putUUID(CORPSE_KEY, target.getUUID());
      corpse.getPersistentData().putBoolean("gore_downed_old_no_ai", target.isNoAi());
      corpse.getPersistentData().putBoolean("gore_downed_old_invisible", target.isInvisible());

      Vec3 away = target.position().subtract(player.position());
      Vec3 horizontal = new Vec3(away.x, 0.0D, away.z);
      if (horizontal.lengthSqr() < 0.0001D) {
         horizontal = player.getLookAngle().multiply(-1.0D, 0.0D, -1.0D);
      }
      horizontal = horizontal.normalize().scale(0.55D);
      corpse.setDeltaMovement(horizontal.x, 0.18D, horizontal.z);
      target.setDeltaMovement(Vec3.ZERO);
      target.setNoAi(true);
      target.setInvisible(true);
      target.getPersistentData().putBoolean(DOWNED_KEY, true);
      target.getPersistentData().putUUID(CORPSE_KEY, corpse.getUUID());

      level.addFreshEntity(corpse);
      return true;
   }

   private static boolean isMeleeOrEmpty(ItemStack stack) {
      return stack.isEmpty()
         || stack.is(ItemTags.SWORDS)
         || stack.is(ItemTags.AXES)
         || stack.is(ItemTags.PICKAXES)
         || stack.is(ItemTags.SHOVELS)
         || stack.is(ItemTags.HOES)
         || stack.is(ItemTags.MACE_ENCHANTABLE);
   }

   private static boolean isZombieTarget(Entity entity) {
      if (!(entity instanceof Mob)) {
         return false;
      }
      if (entity instanceof Zombie || entity instanceof Husk || entity instanceof Drowned) {
         return true;
      }
      ResourceLocation id = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType());
      String path = id.getPath();
      if (path.contains("corpse")) {
         return false;
      }
      return id.getNamespace().equals("gore_edition")
         && (path.contains("zombie") || path.contains("husk") || path.contains("drowned"));
   }

   private static String textureFor(Entity entity) {
      // Gore's own zombie variants expose their current texture through the
      // generated getTexture() method. Keep that exact texture for TZP and
      // other resource packs instead of forcing the vanilla zombie skin.
      try {
         Method method = entity.getClass().getMethod("getTexture");
         Object texture = method.invoke(entity);
         if (texture instanceof String value && !value.isBlank()) {
            return value;
         }
      } catch (ReflectiveOperationException ignored) {
         // Vanilla zombie classes do not have getTexture().
      }
      if (entity instanceof Husk) {
         return "minecraft:textures/entity/zombie/husk";
      }
      if (entity instanceof Drowned) {
         return "minecraft:textures/entity/zombie/drowned";
      }
      return "minecraft:textures/entity/zombie/zombie";
   }
}
