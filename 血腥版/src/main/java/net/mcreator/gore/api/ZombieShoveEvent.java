package net.mcreator.gore.api;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.Event;
import net.neoforged.bus.api.ICancellableEvent;

/**
 * Posted on NeoForge.EVENT_BUS on the server for each eligible shove target.
 * Skills may adjust the chance, strength, and stagger duration or cancel it.
 */
public final class ZombieShoveEvent extends Event implements ICancellableEvent {
   private final Player player;
   private final Mob target;
   private double knockdownChance;
   private double pushStrength;
   private int staggerTicks;

   public ZombieShoveEvent(Player player, Mob target, double chance, double strength, int staggerTicks) {
      this.player = player;
      this.target = target;
      setKnockdownChance(chance);
      setPushStrength(strength);
      setStaggerTicks(staggerTicks);
   }

   public Player getPlayer() { return player; }
   public Mob getTarget() { return target; }
   public double getKnockdownChance() { return knockdownChance; }
   public double getPushStrength() { return pushStrength; }
   public int getStaggerTicks() { return staggerTicks; }

   public void setKnockdownChance(double chance) {
      knockdownChance = Double.isFinite(chance) ? Mth.clamp(chance, 0.0D, 1.0D) : 0.0D;
   }

   public void setPushStrength(double strength) {
      pushStrength = Double.isFinite(strength) ? Mth.clamp(strength, 0.0D, 1.5D) : 0.0D;
   }

   public void setStaggerTicks(int ticks) {
      staggerTicks = Mth.clamp(ticks, 10, 60);
   }
}
