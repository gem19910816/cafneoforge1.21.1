package net.mcreator.dyairdrop.world;

/** Persistent deadlines measured in game ticks, independent of the daylight clock. */
public final class AirdropSchedule {
   private long interval;
   private long nextTrigger;

   public AirdropSchedule() {
      this(0L, 0L);
   }

   public AirdropSchedule(long interval, long nextTrigger) {
      this.interval = interval;
      this.nextTrigger = nextTrigger;
   }

   public long interval() {
      return interval;
   }

   public long nextTrigger() {
      return nextTrigger;
   }

   public boolean poll(long gameTime, long configuredInterval) {
      if (configuredInterval <= 0L) {
         throw new IllegalArgumentException("Airdrop interval must be positive");
      }
      long followingDeadline = gameTime > Long.MAX_VALUE - configuredInterval
         ? Long.MAX_VALUE : gameTime + configuredInterval;
      // Missing data, a changed interval or a restored clock starts a full wait.
      if (interval != configuredInterval || nextTrigger <= 0L || nextTrigger > followingDeadline) {
         interval = configuredInterval;
         nextTrigger = followingDeadline;
         return false;
      }
      if (gameTime < nextTrigger) {
         return false;
      }
      // Advance before spawning. A missed deadline produces at most one event.
      nextTrigger = followingDeadline;
      return true;
   }
}
