package net.mcreator.dyairdrop.procedures;

import java.util.Locale;
import net.mcreator.dyairdrop.compat.MapCompat;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.commands.CommandSource;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

public class MobairdropticksProcedure {
   public MobairdropticksProcedure() {
   }

   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         String all = "";
         String loot = "";
         String blockid = "";
         String title = "";
         String name = "";
         double i = 0.0;
         double ay = 0.0;
         double color = 0.0;
         String[] parts = new String[0];
         if (entity.getPersistentData().getDouble("timer") == 1.0) {
            entity.getPersistentData().putString("cuname", entity.getDisplayName().getString());
            entity.setCustomName(Component.literal("空投"));
            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 100000, 1, false, false));
            }
         } else if (entity.getPersistentData().getDouble("timer") >= 1.0 && (entity.onGround() || entity.isInWater())) {
            all = entity.getPersistentData().getString("cuname");
            parts = all.split(",", 3);
            if (parts.length > 2) {
               blockid = all.split(",", 3)[0];
               loot = all.split(",", 3)[1];
            } else {
               blockid = "dyairdrop:airdroplarge";
               loot = "dyairdrop:largeairdrop1";
            }

            if (entity.onGround()) {
               if (world.isEmptyBlock(BlockPos.containing(x, y, z))) {
                  ay = y;
               } else if ((Boolean)AirdropconfigConfiguration.INCOMPLETE_BLOCK_DESTRUCTION.get()) {
                  ay = y;
               } else {
                  ay = y + 1.0;
               }
            } else if (entity.isInWater()) {
               ay = y + 1.0;
            } else {
               ay = y;
            }

            if (blockid.contains("locked")) {
               // world mutation: server only (the client also ticks this entity)
               if (!world.isClientSide()) {
                  world.setBlock(
                     BlockPos.containing(x, ay, z),
                     ((Block)BuiltInRegistries.BLOCK.get(ResourceLocation.parse(blockid.toLowerCase(Locale.ENGLISH)))).defaultBlockState(),
                     3
                  );
               }
               if (world instanceof Level _level && !_level.isClientSide()) {
                  _level.updateNeighborsAt(BlockPos.containing(x, ay, z), _level.getBlockState(BlockPos.containing(x, ay, z)).getBlock());
               }

               if (!world.isClientSide()) {
                  BlockPos _bp = BlockPos.containing(x, ay, z);
                  BlockEntity _blockEntity = world.getBlockEntity(_bp);
                  BlockState _bs = world.getBlockState(_bp);
                  if (_blockEntity != null) {
                     _blockEntity.getPersistentData().putString("loot", loot);
                  }

                  if (world instanceof Level _level) {
                     _level.sendBlockUpdated(_bp, _bs, _bs, 3);
                  }
               }
            } else if (world instanceof ServerLevel _level) {
               _level.getServer()
                  .getCommands()
                  .performPrefixedCommand(
                     new CommandSourceStack(
                           CommandSource.NULL, new Vec3(x, ay, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null
                        )
                        .withSuppressedOutput(),
                     "setblock ~ ~ ~ " + blockid + "{LootTable:\"" + loot + "\"} destroy"
                  );
            }

            if (entity.getPersistentData().getBoolean("dymap")) {
               name = new ItemStack((ItemLike)BuiltInRegistries.ITEM.get(ResourceLocation.parse(blockid.replace("locked", "").toLowerCase(Locale.ENGLISH))))
                  .getDisplayName()
                  .getString();
               color = 6.0;
               if (world instanceof ServerLevel _level) {
                  // only the helper command that actually exists is used; the waypoint
                  // itself is delivered through Xaero's own share format instead
                  if (MapCompat.hasCommand(_level, "addwaypointxaero")) {
                     MapCompat.runHelperCommand(
                        new CommandSourceStack(
                           CommandSource.NULL, new Vec3(x, ay, z), Vec2.ZERO, _level, 4, "", Component.literal(""), _level.getServer(), null),
                        "addwaypointxaero @a "
                           + Math.round(x) + " "
                           + Math.round(ay) + " "
                           + Math.round(z) + " "
                           + "\"" + name + "\" "
                           + "\"" + name + "\" "
                           + Math.round(color) + " 2 true");
                  }
                  MapCompat.sendXaeroWaypoint(
                     _level, (int)Math.round(x), (int)Math.round(ay), (int)Math.round(z), name, (int)Math.round(color));
               }
            }

            if (!entity.level().isClientSide()) {
               entity.discard();
            }
         }

         entity.getPersistentData().putDouble("timer", entity.getPersistentData().getDouble("timer") + 1.0);
      }
   }
}
