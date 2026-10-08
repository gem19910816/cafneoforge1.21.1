package net.mcreator.gore.command;

import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.mcreator.gore.procedures.PlayerBloodCommandBeeProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandBlazeProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandElderGuardianProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandEndermanProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandGenericProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandGuardianProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandIronGolemProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandMagmaCubeProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandPhantomProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandSkeletonProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandSlimeProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandSpiderProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandWardenProcedure;
import net.mcreator.gore.procedures.PlayerBloodCommandWitherProcedure;
import net.mcreator.gore.procedures.PlayerYCenterCommandNumberProcedure;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.util.FakePlayerFactory;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

@EventBusSubscriber
public class PlayerBloodCommandCommand {
   @SubscribeEvent
   public static void registerCommand(RegisterCommandsEvent event) {
      event.getDispatcher()
         .register(
            (LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal("playergore")
                  .then(
                     ((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)((LiteralArgumentBuilder)Commands.literal(
                                                                  "blood"
                                                               )
                                                               .then(Commands.literal("generic").executes(arguments -> {
                                                                  Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                                  double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                                  double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                                  double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                                  Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                                  if (entity == null && world instanceof ServerLevel _servLevel) {
                                                                     entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                                  }

                                                                  Direction direction = Direction.DOWN;
                                                                  if (entity != null) {
                                                                     direction = entity.getDirection();
                                                                  }

                                                                  PlayerBloodCommandGenericProcedure.execute(world, x, y, z, entity);
                                                                  return 0;
                                                               })))
                                                            .then(Commands.literal("spider").executes(arguments -> {
                                                               Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                               double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                               double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                               double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                               Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                               if (entity == null && world instanceof ServerLevel _servLevel) {
                                                                  entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                               }

                                                               Direction direction = Direction.DOWN;
                                                               if (entity != null) {
                                                                  direction = entity.getDirection();
                                                               }

                                                               PlayerBloodCommandSpiderProcedure.execute(world, x, y, z, entity);
                                                               return 0;
                                                            })))
                                                         .then(Commands.literal("ender").executes(arguments -> {
                                                            Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                            double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                            double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                            double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                            Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                            if (entity == null && world instanceof ServerLevel _servLevel) {
                                                               entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                            }

                                                            Direction direction = Direction.DOWN;
                                                            if (entity != null) {
                                                               direction = entity.getDirection();
                                                            }

                                                            PlayerBloodCommandEndermanProcedure.execute(world, x, y, z, entity);
                                                            return 0;
                                                         })))
                                                      .then(Commands.literal("spectral").executes(arguments -> {
                                                         Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                         double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                         double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                         double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                         Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                         if (entity == null && world instanceof ServerLevel _servLevel) {
                                                            entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                         }

                                                         Direction direction = Direction.DOWN;
                                                         if (entity != null) {
                                                            direction = entity.getDirection();
                                                         }

                                                         PlayerBloodCommandPhantomProcedure.execute(world, x, y, z, entity);
                                                         return 0;
                                                      })))
                                                   .then(Commands.literal("warden").executes(arguments -> {
                                                      Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                      double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                      double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                      double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                      Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                      if (entity == null && world instanceof ServerLevel _servLevel) {
                                                         entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                      }

                                                      Direction direction = Direction.DOWN;
                                                      if (entity != null) {
                                                         direction = entity.getDirection();
                                                      }

                                                      PlayerBloodCommandWardenProcedure.execute(world, x, y, z, entity);
                                                      return 0;
                                                   })))
                                                .then(Commands.literal("bee").executes(arguments -> {
                                                   Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                   double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                   double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                   double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                   Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                   if (entity == null && world instanceof ServerLevel _servLevel) {
                                                      entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                   }

                                                   Direction direction = Direction.DOWN;
                                                   if (entity != null) {
                                                      direction = entity.getDirection();
                                                   }

                                                   PlayerBloodCommandBeeProcedure.execute(world, x, y, z, entity);
                                                   return 0;
                                                })))
                                             .then(Commands.literal("skeleton").executes(arguments -> {
                                                Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                                double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                                double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                                double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                                Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                                if (entity == null && world instanceof ServerLevel _servLevel) {
                                                   entity = FakePlayerFactory.getMinecraft(_servLevel);
                                                }

                                                Direction direction = Direction.DOWN;
                                                if (entity != null) {
                                                   direction = entity.getDirection();
                                                }

                                                PlayerBloodCommandSkeletonProcedure.execute(world, x, y, z, entity);
                                                return 0;
                                             })))
                                          .then(Commands.literal("wither").executes(arguments -> {
                                             Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                             double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                             double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                             double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                             Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                             if (entity == null && world instanceof ServerLevel _servLevel) {
                                                entity = FakePlayerFactory.getMinecraft(_servLevel);
                                             }

                                             Direction direction = Direction.DOWN;
                                             if (entity != null) {
                                                direction = entity.getDirection();
                                             }

                                             PlayerBloodCommandWitherProcedure.execute(world, x, y, z, entity);
                                             return 0;
                                          })))
                                       .then(Commands.literal("blaze").executes(arguments -> {
                                          Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                          double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                          double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                          double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                          Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                          if (entity == null && world instanceof ServerLevel _servLevel) {
                                             entity = FakePlayerFactory.getMinecraft(_servLevel);
                                          }

                                          Direction direction = Direction.DOWN;
                                          if (entity != null) {
                                             direction = entity.getDirection();
                                          }

                                          PlayerBloodCommandBlazeProcedure.execute(world, x, y, z, entity);
                                          return 0;
                                       })))
                                    .then(Commands.literal("slime").executes(arguments -> {
                                       Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                       double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                       double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                       double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                       Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                       if (entity == null && world instanceof ServerLevel _servLevel) {
                                          entity = FakePlayerFactory.getMinecraft(_servLevel);
                                       }

                                       Direction direction = Direction.DOWN;
                                       if (entity != null) {
                                          direction = entity.getDirection();
                                       }

                                       PlayerBloodCommandSlimeProcedure.execute(world, x, y, z, entity);
                                       return 0;
                                    })))
                                 .then(Commands.literal("magma_cube").executes(arguments -> {
                                    Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                    double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                    double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                    double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                    Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                    if (entity == null && world instanceof ServerLevel _servLevel) {
                                       entity = FakePlayerFactory.getMinecraft(_servLevel);
                                    }

                                    Direction direction = Direction.DOWN;
                                    if (entity != null) {
                                       direction = entity.getDirection();
                                    }

                                    PlayerBloodCommandMagmaCubeProcedure.execute(world, x, y, z, entity);
                                    return 0;
                                 })))
                              .then(Commands.literal("iron_golem").executes(arguments -> {
                                 Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                                 double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                                 double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                                 double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                                 Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                                 if (entity == null && world instanceof ServerLevel _servLevel) {
                                    entity = FakePlayerFactory.getMinecraft(_servLevel);
                                 }

                                 Direction direction = Direction.DOWN;
                                 if (entity != null) {
                                    direction = entity.getDirection();
                                 }

                                 PlayerBloodCommandIronGolemProcedure.execute(world, x, y, z, entity);
                                 return 0;
                              })))
                           .then(Commands.literal("guardian").executes(arguments -> {
                              Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                              double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                              double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                              double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                              Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                              if (entity == null && world instanceof ServerLevel _servLevel) {
                                 entity = FakePlayerFactory.getMinecraft(_servLevel);
                              }

                              Direction direction = Direction.DOWN;
                              if (entity != null) {
                                 direction = entity.getDirection();
                              }

                              PlayerBloodCommandGuardianProcedure.execute(world, x, y, z, entity);
                              return 0;
                           })))
                        .then(Commands.literal("elder_guardian").executes(arguments -> {
                           Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                           double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                           double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                           double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                           Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                           if (entity == null && world instanceof ServerLevel _servLevel) {
                              entity = FakePlayerFactory.getMinecraft(_servLevel);
                           }

                           Direction direction = Direction.DOWN;
                           if (entity != null) {
                              direction = entity.getDirection();
                           }

                           PlayerBloodCommandElderGuardianProcedure.execute(world, x, y, z, entity);
                           return 0;
                        }))
                  ))
               .then(Commands.literal("y").then(Commands.argument("y", DoubleArgumentType.doubleArg()).executes(arguments -> {
                  Level world = ((CommandSourceStack)arguments.getSource()).getUnsidedLevel();
                  double x = ((CommandSourceStack)arguments.getSource()).getPosition().x();
                  double y = ((CommandSourceStack)arguments.getSource()).getPosition().y();
                  double z = ((CommandSourceStack)arguments.getSource()).getPosition().z();
                  Entity entity = ((CommandSourceStack)arguments.getSource()).getEntity();
                  if (entity == null && world instanceof ServerLevel _servLevel) {
                     entity = FakePlayerFactory.getMinecraft(_servLevel);
                  }

                  Direction direction = Direction.DOWN;
                  if (entity != null) {
                     direction = entity.getDirection();
                  }

                  PlayerYCenterCommandNumberProcedure.execute(arguments, entity);
                  return 0;
               })))
         );
   }
}
