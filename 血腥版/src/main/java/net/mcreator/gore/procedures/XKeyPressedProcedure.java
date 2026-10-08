package net.mcreator.gore.procedures;

import dev.kosmx.playerAnim.api.layered.IAnimation;
import dev.kosmx.playerAnim.api.layered.KeyframeAnimationPlayer;
import dev.kosmx.playerAnim.api.layered.ModifierLayer;
import dev.kosmx.playerAnim.core.data.KeyframeAnimation;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationAccess;
import dev.kosmx.playerAnim.minecraftApi.PlayerAnimationRegistry;
import java.util.Comparator;
import java.util.List;
import net.mcreator.gore.entity.HuskAboutToDieEntity;
import net.mcreator.gore.entity.ZombieAboutToDieEntity;
import net.mcreator.gore.entity.ZombieCorpseEntity;
import net.mcreator.gore.init.GoreEditionModEntities;
import net.mcreator.gore.init.GoreEditionModItems;
import net.mcreator.gore.init.GoreEditionModMobEffects;
import net.mcreator.gore.network.GoreEditionModVariables;
import net.mcreator.gore.utils.ItemTagHelper;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.network.PacketDistributor;

public class XKeyPressedProcedure {
   public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
      if (entity != null) {
         if (!GoreEditionModVariables.getPlayerVariables(entity).cooldown_i_l) {
            if (!world.getEntitiesOfClass(ZombieAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true).isEmpty()
               && (
                  (entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMainHandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
                     || (entity instanceof LivingEntity _livEntxx ? _livEntxx.getMainHandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
                     || (entity instanceof LivingEntity _livEntx ? _livEntx.getOffhandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
                     || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
               )) {
               if (world instanceof ServerLevel _level) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.VERTICAL_CUTTED_ZOMBIE.get())
                     .spawn(
                        _level,
                        BlockPos.containing(
                           ((ZombieAboutToDieEntity)world.getEntitiesOfClass(
                                    ZombieAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true
                                 )
                                 .stream()
                                 .sorted((new Object() {
                                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                       return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                                    }
                                 }).compareDistOf(x, y, z))
                                 .findFirst()
                                 .orElse(null))
                              .getX(),
                           ((ZombieAboutToDieEntity)world.getEntitiesOfClass(
                                    ZombieAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true
                                 )
                                 .stream()
                                 .sorted((new Object() {
                                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                       return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                                    }
                                 }).compareDistOf(x, y, z))
                                 .findFirst()
                                 .orElse(null))
                              .getY(),
                           ((ZombieAboutToDieEntity)world.getEntitiesOfClass(
                                    ZombieAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true
                                 )
                                 .stream()
                                 .sorted((new Object() {
                                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                       return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                                    }
                                 }).compareDistOf(x, y, z))
                                 .findFirst()
                                 .orElse(null))
                              .getZ()
                        ),
                        MobSpawnType.MOB_SUMMONED
                     );
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (!((ZombieAboutToDieEntity)world.getEntitiesOfClass(ZombieAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true)
                     .stream()
                     .sorted((new Object() {
                        Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                           return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                        }
                     }).compareDistOf(x, y, z))
                     .findFirst()
                     .orElse(null))
                  .level()
                  .isClientSide()) {
                  ((ZombieAboutToDieEntity)world.getEntitiesOfClass(ZombieAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true)
                        .stream()
                        .sorted((new Object() {
                           Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                              return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                           }
                        }).compareDistOf(x, y, z))
                        .findFirst()
                        .orElse(null))
                     .discard();
               }
            }

            if (!world.getEntitiesOfClass(HuskAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true).isEmpty()
               && (
                  (entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMainHandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
                     || (entity instanceof LivingEntity _livEntxx ? _livEntxx.getMainHandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
                     || (entity instanceof LivingEntity _livEntx ? _livEntx.getOffhandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
                     || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
               )) {
               if (world instanceof ServerLevel _levelx) {
                  Entity entityToSpawn = ((EntityType)GoreEditionModEntities.VERTICALCUTTED_HUSK.get())
                     .spawn(
                        _levelx,
                        BlockPos.containing(
                           ((HuskAboutToDieEntity)world.getEntitiesOfClass(HuskAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true)
                                 .stream()
                                 .sorted((new Object() {
                                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                       return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                                    }
                                 }).compareDistOf(x, y, z))
                                 .findFirst()
                                 .orElse(null))
                              .getX(),
                           ((HuskAboutToDieEntity)world.getEntitiesOfClass(HuskAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true)
                                 .stream()
                                 .sorted((new Object() {
                                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                       return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                                    }
                                 }).compareDistOf(x, y, z))
                                 .findFirst()
                                 .orElse(null))
                              .getY(),
                           ((HuskAboutToDieEntity)world.getEntitiesOfClass(HuskAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true)
                                 .stream()
                                 .sorted((new Object() {
                                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                                       return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                                    }
                                 }).compareDistOf(x, y, z))
                                 .findFirst()
                                 .orElse(null))
                              .getZ()
                        ),
                        MobSpawnType.MOB_SUMMONED
                     );
                  if (entityToSpawn != null) {
                     entityToSpawn.setYRot(world.getRandom().nextFloat() * 360.0F);
                  }
               }

               if (!((HuskAboutToDieEntity)world.getEntitiesOfClass(HuskAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true)
                     .stream()
                     .sorted((new Object() {
                        Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                           return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                        }
                     }).compareDistOf(x, y, z))
                     .findFirst()
                     .orElse(null))
                  .level()
                  .isClientSide()) {
                  ((HuskAboutToDieEntity)world.getEntitiesOfClass(HuskAboutToDieEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true)
                        .stream()
                        .sorted((new Object() {
                           Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                              return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                           }
                        }).compareDistOf(x, y, z))
                        .findFirst()
                        .orElse(null))
                     .discard();
               }
            }

            if (!world.getEntitiesOfClass(ZombieCorpseEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true).isEmpty()
               && (
                  (entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMainHandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
                     || (entity instanceof LivingEntity _livEntxx ? _livEntxx.getMainHandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
                     || (entity instanceof LivingEntity _livEntx ? _livEntx.getOffhandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
                     || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
                        .is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
               )) {
               ((ZombieCorpseEntity)world.getEntitiesOfClass(ZombieCorpseEntity.class, AABB.ofSize(new Vec3(x, y, z), 1.2, 1.2, 1.2), e -> true)
                     .stream()
                     .sorted((new Object() {
                        Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                           return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                        }
                     }).compareDistOf(x, y, z))
                     .findFirst()
                     .orElse(null))
                  .getPersistentData()
                  .putBoolean("fast_death", true);
            }
         }

         if ((entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMainHandItem() : ItemStack.EMPTY)
               .is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
            || (entity instanceof LivingEntity _livEntxx ? _livEntxx.getMainHandItem() : ItemStack.EMPTY)
               .is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))
            || (entity instanceof LivingEntity _livEntx ? _livEntx.getOffhandItem() : ItemStack.EMPTY)
               .is(ItemTags.create(ResourceLocation.parse("minecraft:axes")))
            || (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY)
               .is(ItemTags.create(ResourceLocation.parse("minecraft:swords")))) {
            boolean _setval = true;
            GoreEditionModVariables.PlayerVariables capability = GoreEditionModVariables.getPlayerVariables(entity);
            capability.cooldown_i_l = _setval;
            capability.syncPlayerVariables(entity);
            if (world.isClientSide() && entity instanceof AbstractClientPlayer player) {
               ModifierLayer<IAnimation> animation = (ModifierLayer<IAnimation>)PlayerAnimationAccess.getPlayerAssociatedData(player)
                  .get(ResourceLocation.fromNamespaceAndPath("gore_edition", "player_animation"));
               if (animation != null) {
                  animation.setAnimation(
                     new KeyframeAnimationPlayer(
                        (KeyframeAnimation)PlayerAnimationRegistry.getAnimation(ResourceLocation.fromNamespaceAndPath("gore_edition", "verticalcut"))
                     )
                  );
               }
            }

            if (!world.isClientSide() && entity instanceof Player && world instanceof ServerLevel srvLvl_) {
               List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
               synchronized (connections) {
                  for (Connection connection : connections) {
                     if (!connection.isConnecting() && connection.isConnected()) {
                        PacketDistributor.sendToPlayer(
                           (ServerPlayer)entity,
                           new SetupAnimationsProcedure.GoreEditionModAnimationMessage(Component.literal("verticalcut"), entity.getId(), true),
                           new CustomPacketPayload[0]
                        );
                     }
                  }
               }
            }
         }

         if ((entity instanceof LivingEntity _livEntx ? _livEntx.getMainHandItem() : ItemStack.EMPTY).getItem()
               == GoreEditionModItems.GRENADE_OF_ASHES_ACID.get()
            && !(ItemTagHelper.getDouble(entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY, "click") >= 1.0)) {
            ItemTagHelper.putDouble(
               entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getMainHandItem() : ItemStack.EMPTY,
               "click",
               ItemTagHelper.getDouble(entity instanceof LivingEntity _livEntxx ? _livEntxx.getMainHandItem() : ItemStack.EMPTY, "click") + 1.0
            );
         }

         if ((entity instanceof LivingEntity _livEntx ? _livEntx.getOffhandItem() : ItemStack.EMPTY).getItem()
               == GoreEditionModItems.GRENADE_OF_ASHES_ACID.get()
            && !(ItemTagHelper.getDouble(entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY, "click") >= 1.0)) {
            ItemTagHelper.putDouble(
               entity instanceof LivingEntity _livEntxxx ? _livEntxxx.getOffhandItem() : ItemStack.EMPTY,
               "click",
               ItemTagHelper.getDouble(entity instanceof LivingEntity _livEntxx ? _livEntxx.getOffhandItem() : ItemStack.EMPTY, "click") + 1.0
            );
         }

         if (!GoreEditionModVariables.getPlayerVariables(entity).warning_screen_toggle) {
            boolean _setvalx = true;
            GoreEditionModVariables.PlayerVariables capabilityx = GoreEditionModVariables.getPlayerVariables(entity);
            capabilityx.warning_screen_toggle = _setvalx;
            capabilityx.syncPlayerVariables(entity);
         }

         if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem()
            == GoreEditionModItems.NEEDLE_SCYTHE_CORDYCEPS.get()) {
            Vec3 _center = new Vec3(x, y, z);

            for (Entity entityiterator : world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(63.0), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
               .toList()) {
               if (entityiterator.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_cordyceps")))
                  && entity == (entityiterator instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null)
                  && GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c != 3.0) {
                  entityiterator.getPersistentData().putDouble("orders", GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c);
                  entityiterator.getPersistentData().putBoolean("cattack", GoreEditionModVariables.getPlayerVariables(entity).nsc_cattack);
               }
            }

            Vec3 _centerB = new Vec3(x, y, z);

            for (Entity entityiteratorx : world.getEntitiesOfClass(Entity.class, new AABB(_centerB, _centerB).inflate(2.5), e -> true)
               .stream()
               .sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_centerB)))
               .toList()) {
               if (entityiteratorx.getType().is(TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.parse("forge:ge_cordyceps")))
                  && entity == (entityiteratorx instanceof TamableAnimal _tamEnt ? _tamEnt.getOwner() : null)
                  && GoreEditionModVariables.getPlayerVariables(entity).nsc_order_for_c == 3.0) {
                  entityiteratorx.hurt(
                     new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.PLAYER_ATTACK), entity),
                     (float)((double)(entityiteratorx instanceof LivingEntity _livEntx ? _livEntx.getMaxHealth() : -1.0F) * 1.1)
                  );
               }
            }

            if (entity instanceof LivingEntity _entMainHand87 && _entMainHand87.getMainArm() == HumanoidArm.RIGHT) {
               if (world.isClientSide() && entity instanceof AbstractClientPlayer playerx) {
                  ModifierLayer<IAnimation> animation = (ModifierLayer<IAnimation>)PlayerAnimationAccess.getPlayerAssociatedData(playerx)
                     .get(ResourceLocation.fromNamespaceAndPath("gore_edition", "player_animation"));
                  if (animation != null) {
                     animation.setAnimation(
                        new KeyframeAnimationPlayer(
                           (KeyframeAnimation)PlayerAnimationRegistry.getAnimation(
                              ResourceLocation.fromNamespaceAndPath("gore_edition", "cordyceps_scythe_right_arm")
                           )
                        )
                     );
                  }
               }

               if (!world.isClientSide() && entity instanceof Player && world instanceof ServerLevel srvLvl_) {
                  List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
                  synchronized (connections) {
                     for (Connection connectionx : connections) {
                        if (!connectionx.isConnecting() && connectionx.isConnected()) {
                           PacketDistributor.sendToPlayer(
                              (ServerPlayer)entity,
                              new SetupAnimationsProcedure.GoreEditionModAnimationMessage(Component.literal("cordyceps_scythe_right_arm"), entity.getId(), true),
                              new CustomPacketPayload[0]
                           );
                        }
                     }
                  }
               }
            }

            if (entity instanceof LivingEntity _entMainHand89 && _entMainHand89.getMainArm() == HumanoidArm.LEFT) {
               if (world.isClientSide() && entity instanceof AbstractClientPlayer playerxx) {
                  ModifierLayer<IAnimation> animation = (ModifierLayer<IAnimation>)PlayerAnimationAccess.getPlayerAssociatedData(playerxx)
                     .get(ResourceLocation.fromNamespaceAndPath("gore_edition", "player_animation"));
                  if (animation != null) {
                     animation.setAnimation(
                        new KeyframeAnimationPlayer(
                           (KeyframeAnimation)PlayerAnimationRegistry.getAnimation(
                              ResourceLocation.fromNamespaceAndPath("gore_edition", "cordyceps_scythe_left_arm")
                           )
                        )
                     );
                  }
               }

               if (!world.isClientSide() && entity instanceof Player && world instanceof ServerLevel srvLvl_) {
                  List<Connection> connections = srvLvl_.getServer().getConnection().getConnections();
                  synchronized (connections) {
                     for (Connection connectionxx : connections) {
                        if (!connectionxx.isConnecting() && connectionxx.isConnected()) {
                           PacketDistributor.sendToPlayer(
                              (ServerPlayer)entity,
                              new SetupAnimationsProcedure.GoreEditionModAnimationMessage(Component.literal("cordyceps_scythe_left_arm"), entity.getId(), true),
                              new CustomPacketPayload[0]
                           );
                        }
                     }
                  }
               }
            }
         }

         ThirdhandZClickedProcedure.execute(entity);
         if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() == GoreEditionModItems.SPIRAL.get()) {
            if (entity instanceof LivingEntity _livEnt93 && _livEnt93.hasEffect(GoreEditionModMobEffects.SPIRAL_TWIST)) {
               return;
            }

            ItemStack _ist = entity instanceof LivingEntity _livEntx ? _livEntx.getMainHandItem() : ItemStack.EMPTY;
            if (ItemTagHelper.damageItemAmount(_ist, 2, entity)) {
               _ist.shrink(1);
               _ist.setDamageValue(0);
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(GoreEditionModMobEffects.SPIRAL_TWIST, 600, 1));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 80, 0));
            }

            if (entity instanceof LivingEntity _entity && !_entity.level().isClientSide()) {
               _entity.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 120, 0));
            }
         }
      }
   }
}
