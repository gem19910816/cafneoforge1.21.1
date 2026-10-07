package net.mcreator.dyairdrop.procedures;

import net.gem19910816.dyairdrop.core.Commands;

import java.util.Locale;
import net.gem19910816.dyairdrop.compat.map.MapMarkerService;
import net.mcreator.dyairdrop.configuration.AirdropconfigConfiguration;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.BuiltInRegistries;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class MobairdropticksProcedure {
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
               world.setBlock(
                  BlockPos.containing(x, ay, z),
                  (BuiltInRegistries.BLOCK.get(ResourceLocation.parse(blockid.toLowerCase(Locale.ENGLISH)))).defaultBlockState(),
                  3
               );
               if (world instanceof Level _level) {
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
               Commands.run(_level, x, ay, z, "setblock ~ ~ ~ " + blockid + "{LootTable:\"" + loot + "\"} destroy"
                  );
            }

            if (entity.getPersistentData().getBoolean("dymap")) {
               // 原实现这里是在服务端执行一条并不存在的命令 `addwaypointxaero`（永远静默失败），
               // 现改为服务端权威地维护地图标记，由 MapMarkerService 下发给装了 Xaero 的玩家。
               name = new ItemStack((ItemLike) BuiltInRegistries.ITEM.get(ResourceLocation.parse(blockid.replace("locked", "").toLowerCase(Locale.ENGLISH))))
                  .getDisplayName()
                  .getString();
               if (world instanceof ServerLevel _level) {
                  MapMarkerService.addMarker(_level, BlockPos.containing(x, ay, z), name, MapMarkerService.colorForBlockId(blockid));
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
