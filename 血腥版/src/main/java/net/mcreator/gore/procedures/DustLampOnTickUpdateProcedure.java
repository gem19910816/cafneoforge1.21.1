package net.mcreator.gore.procedures;

import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import net.mcreator.gore.configuration.GeSpiralsConfiguration;
import net.mcreator.gore.init.GoreEditionModBlocks;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level.ExplosionInteraction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.capabilities.Capabilities.ItemHandler;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

public class DustLampOnTickUpdateProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z) {
      if ((new Object() {
         public ItemStack getItemStack(LevelAccessor world, BlockPos pos, int slotid) {
            AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
            BlockEntity _ent = world.getBlockEntity(pos);
            if (_ent != null) {
               IItemHandler capability = world instanceof Level _lvl ? (IItemHandler)ItemHandler.BLOCK.getCapability(_lvl, pos, null, null, null) : null;
               if (capability != null) {
                  _retval.set(capability.getStackInSlot(slotid).copy());
               }
            }

            return _retval.get();
         }
      }).getItemStack(world, BlockPos.containing(x, y, z), 1).getItem() == ((Block)GoreEditionModBlocks.ASHED_UNKNOWN_SKULL.get()).asItem()) {
         if (!world.isClientSide()) {
            BlockPos _bp = BlockPos.containing(x, y, z);
            BlockEntity _blockEntity = world.getBlockEntity(_bp);
            BlockState _bs = world.getBlockState(_bp);
            if (_blockEntity != null) {
               _blockEntity.getPersistentData().putBoolean("skull", true);
            }

            if (world instanceof Level _level) {
               _level.sendBlockUpdated(_bp, _bs, _bs, 3);
            }
         }
      } else if (!world.isClientSide()) {
         BlockPos _bpx = BlockPos.containing(x, y, z);
         BlockEntity _blockEntityx = world.getBlockEntity(_bpx);
         BlockState _bsx = world.getBlockState(_bpx);
         if (_blockEntityx != null) {
            _blockEntityx.getPersistentData().putBoolean("skull", false);
         }

         if (world instanceof Level _level) {
            _level.sendBlockUpdated(_bpx, _bsx, _bsx, 3);
         }
      }

      if ((new Object() {
         public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
            AtomicInteger _retval = new AtomicInteger(0);
            BlockEntity _ent = world.getBlockEntity(pos);
            if (_ent != null) {
               IItemHandler capability = world instanceof Level _lvl ? (IItemHandler)ItemHandler.BLOCK.getCapability(_lvl, pos, null, null, null) : null;
               if (capability != null) {
                  _retval.set(capability.getStackInSlot(slotid).getCount());
               }
            }

            return _retval.get();
         }
      }).getAmount(world, BlockPos.containing(x, y, z), 0) > 0) {
         if (!world.isClientSide()) {
            BlockPos _bpxx = BlockPos.containing(x, y, z);
            BlockEntity _blockEntityxx = world.getBlockEntity(_bpxx);
            BlockState _bsxx = world.getBlockState(_bpxx);
            if (_blockEntityxx != null) {
               _blockEntityxx.getPersistentData().putBoolean("charged", true);
            }

            if (world instanceof Level _level) {
               _level.sendBlockUpdated(_bpxx, _bsxx, _bsxx, 3);
            }
         }
      } else if (!world.isClientSide()) {
         BlockPos _bpxxx = BlockPos.containing(x, y, z);
         BlockEntity _blockEntityxxx = world.getBlockEntity(_bpxxx);
         BlockState _bsxxx = world.getBlockState(_bpxxx);
         if (_blockEntityxxx != null) {
            _blockEntityxxx.getPersistentData().putBoolean("charged", false);
         }

         if (world instanceof Level _level) {
            _level.sendBlockUpdated(_bpxxx, _bsxxx, _bsxxx, 3);
         }
      }

      if ((new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "charged")) {
         if ((new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.getBlockEntity(pos);
               return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
            }
         }).getValue(world, BlockPos.containing(x, y, z), "charged_ambient_sound_tick") == 0.0 && Math.random() < 0.9 && world instanceof Level _level) {
            if (!_level.isClientSide()) {
               _level.playSound(
                  null,
                  BlockPos.containing(x, y, z),
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:block.ashtray_cycle.charged")),
                  SoundSource.BLOCKS,
                  1.0F,
                  1.0F
               );
            } else {
               _level.playLocalSound(
                  x,
                  y,
                  z,
                  (SoundEvent)BuiltInRegistries.SOUND_EVENT.get(ResourceLocation.parse("gore_edition:block.ashtray_cycle.charged")),
                  SoundSource.BLOCKS,
                  1.0F,
                  1.0F,
                  false
               );
            }
         }

         if (!world.isClientSide()) {
            BlockPos _bpxxxx = BlockPos.containing(x, y, z);
            BlockEntity _blockEntityxxxx = world.getBlockEntity(_bpxxxx);
            BlockState _bsxxxx = world.getBlockState(_bpxxxx);
            if (_blockEntityxxxx != null) {
               _blockEntityxxxx.getPersistentData().putDouble("charged_ambient_sound_tick", (new Object() {
                  public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                     BlockEntity blockEntity = world.getBlockEntity(pos);
                     return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
                  }
               }).getValue(world, BlockPos.containing(x, y, z), "charged_ambient_sound_tick") + 1.0);
            }

            if (world instanceof Level _levelx) {
               _levelx.sendBlockUpdated(_bpxxxx, _bsxxxx, _bsxxxx, 3);
            }
         }

         if ((new Object() {
            public double getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.getBlockEntity(pos);
               return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
            }
         }).getValue(world, BlockPos.containing(x, y, z), "charged_ambient_sound_tick") >= 40.0 && !world.isClientSide()) {
            BlockPos _bpxxxxx = BlockPos.containing(x, y, z);
            BlockEntity _blockEntityxxxxx = world.getBlockEntity(_bpxxxxx);
            BlockState _bsxxxxx = world.getBlockState(_bpxxxxx);
            if (_blockEntityxxxxx != null) {
               _blockEntityxxxxx.getPersistentData().putDouble("charged_ambient_sound_tick", 0.0);
            }

            if (world instanceof Level _levelx) {
               _levelx.sendBlockUpdated(_bpxxxxx, _bsxxxxx, _bsxxxxx, 3);
            }
         }

         if (!(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.getBlockEntity(pos);
               return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
            }
         }).getValue(world, BlockPos.containing(x, y, z), "fully_charged") && !(new Object() {
            public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
               BlockEntity blockEntity = world.getBlockEntity(pos);
               return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
            }
         }).getValue(world, BlockPos.containing(x, y, z), "inestable_explode") && Math.random() < 0.001 && !world.isClientSide()) {
            BlockPos _bpxxxxxx = BlockPos.containing(x, y, z);
            BlockEntity _blockEntityxxxxxx = world.getBlockEntity(_bpxxxxxx);
            BlockState _bsxxxxxx = world.getBlockState(_bpxxxxxx);
            if (_blockEntityxxxxxx != null) {
               _blockEntityxxxxxx.getPersistentData().putBoolean("inestable_explode", true);
            }

            if (world instanceof Level _levelx) {
               _levelx.sendBlockUpdated(_bpxxxxxx, _bsxxxxxx, _bsxxxxxx, 3);
            }
         }

         Vec3 _center = new Vec3(x, y, z);

         for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8.0), e -> true)
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
            .toList()) {
            if (entityiterator instanceof LivingEntity) {
               LivingEntity _entity = (LivingEntity)entityiterator;
               if (!_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 0));
               }
            }
         }
      }

      if ((new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "inestable_explode")) {
         if (world instanceof Level _levelx && !_levelx.isClientSide()) {
            _levelx.explode(null, x, y, z, 4.0F, ExplosionInteraction.BLOCK);
         }

         if (!world.isClientSide()) {
            BlockPos _bpxxxxxxx = BlockPos.containing(x, y, z);
            BlockEntity _blockEntityxxxxxxx = world.getBlockEntity(_bpxxxxxxx);
            BlockState _bsxxxxxxx = world.getBlockState(_bpxxxxxxx);
            if (_blockEntityxxxxxxx != null) {
               _blockEntityxxxxxxx.getPersistentData().putBoolean("inestable_explode", false);
            }

            if (world instanceof Level _levelx) {
               _levelx.sendBlockUpdated(_bpxxxxxxx, _bsxxxxxxx, _bsxxxxxxx, 3);
            }
         }
      }

      if ((double)(new Object() {
         public int getAmount(LevelAccessor world, BlockPos pos, int slotid) {
            AtomicInteger _retval = new AtomicInteger(0);
            BlockEntity _ent = world.getBlockEntity(pos);
            if (_ent != null) {
               IItemHandler capability = world instanceof Level _lvl ? (IItemHandler)ItemHandler.BLOCK.getCapability(_lvl, pos, null, null, null) : null;
               if (capability != null) {
                  _retval.set(capability.getStackInSlot(slotid).getCount());
               }
            }

            return _retval.get();
         }
      }).getAmount(world, BlockPos.containing(x, y, z), 0) >= (Double)GeSpiralsConfiguration.REQUIRED_CHARGE.get() && (new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "skull")) {
         Vec3 _center = new Vec3(x, y, z);

         for (Entity entityiteratorx : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(8.0), e -> true)
            .stream()
            .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
            .toList()) {
            if (entityiteratorx instanceof LivingEntity) {
               LivingEntity _entity = (LivingEntity)entityiteratorx;
               if (!_entity.level().isClientSide()) {
                  _entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 100, 0));
               }
            }
         }
      }

      if ((new Object() {
         public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getBoolean(tag) : false;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "fully_charged") && !world.isClientSide()) {
         BlockPos _bpxxxxxxxx = BlockPos.containing(x, y, z);
         BlockEntity _blockEntityxxxxxxxx = world.getBlockEntity(_bpxxxxxxxx);
         BlockState _bsxxxxxxxx = world.getBlockState(_bpxxxxxxxx);
         if (_blockEntityxxxxxxxx != null) {
            _blockEntityxxxxxxxx.getPersistentData().putDouble("fully_charged_timer", (new Object() {
               public double getValue(LevelAccessor world, BlockPos pos, String tag) {
                  BlockEntity blockEntity = world.getBlockEntity(pos);
                  return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
               }
            }).getValue(world, BlockPos.containing(x, y, z), "fully_charged_timer") + 1.0);
         }

         if (world instanceof Level _levelx) {
            _levelx.sendBlockUpdated(_bpxxxxxxxx, _bsxxxxxxxx, _bsxxxxxxxx, 3);
         }
      }

      if ((new Object() {
         public double getValue(LevelAccessor world, BlockPos pos, String tag) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            return blockEntity != null ? blockEntity.getPersistentData().getDouble(tag) : -1.0;
         }
      }).getValue(world, BlockPos.containing(x, y, z), "fully_charged_timer") == 300.0) {
         BlockEntity _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
         if (_ent != null) {
            int _slotid = 0;
            IItemHandler capability = world instanceof Level _lvl
               ? (IItemHandler)ItemHandler.BLOCK.getCapability(_lvl, BlockPos.containing(x, y, z), null, null, null)
               : null;
            if (capability != null && capability instanceof IItemHandlerModifiable) {
               ((IItemHandlerModifiable)capability).setStackInSlot(0, ItemStack.EMPTY);
            }
         }

         _ent = world.getBlockEntity(BlockPos.containing(x, y, z));
         if (_ent != null) {
            int _slotid = 1;
            IItemHandler capability = world instanceof Level _lvl
               ? (IItemHandler)ItemHandler.BLOCK.getCapability(_lvl, BlockPos.containing(x, y, z), null, null, null)
               : null;
            if (capability != null && capability instanceof IItemHandlerModifiable) {
               ((IItemHandlerModifiable)capability).setStackInSlot(1, ItemStack.EMPTY);
            }
         }

         if (Math.random() < 0.8) {
            if (world instanceof ServerLevel _levelx) {
               Entity entityToSpawn = ((EntityType)GoreEditionModEntities.HENGEYON.get())
                  .spawn(_levelx, BlockPos.containing(x, y, z), MobSpawnType.MOB_SUMMONED);
               if (entityToSpawn != null) {
                  entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
               }
            }
         } else if (world instanceof ServerLevel _levelxx) {
            ItemEntity entityToSpawn = new ItemEntity(_levelxx, x, y + 1.0, z, new ItemStack((ItemLike)GoreEditionModItems.LIGHTNING_ORB.get()));
            entityToSpawn.setPickUpDelay(10);
            _levelxx.addFreshEntity(entityToSpawn);
         }

         world.setBlock(BlockPos.containing(x, y, z), ((Block)GoreEditionModBlocks.ASH_SAND.get()).defaultBlockState(), 3);
      }
   }
}
