package net.mcreator.dyairdrop.world;

import net.minecraft.nbt.CompoundTag;

public final class AirdropScheduleTest {
   private static final long TEN_DAYS = 240000L;

   public static void main(String[] args) {
      AirdropSchedule fresh = new AirdropSchedule();
      check(!fresh.poll(0L, TEN_DAYS), "No airdrop at world creation");
      check(!fresh.poll(100L, TEN_DAYS), "No airdrop at the old 100-tick trigger");
      check(!fresh.poll(TEN_DAYS - 1L, TEN_DAYS), "Wait for a complete interval");
      check(fresh.poll(TEN_DAYS, TEN_DAYS), "First periodic event");
      check(!fresh.poll(TEN_DAYS, TEN_DAYS), "No duplicate event on the same tick");
      check(fresh.poll(TEN_DAYS * 2L, TEN_DAYS), "Second periodic event");
      check(fresh.poll(TEN_DAYS * 3L, TEN_DAYS), "Third periodic event");

      AirdropSchedule existingWorld = new AirdropSchedule();
      check(!existingWorld.poll(1234567L, TEN_DAYS), "Installation in an old save waits");
      long savedInterval = existingWorld.interval();
      long savedDeadline = existingWorld.nextTrigger();
      AirdropSchedule restored = new AirdropSchedule(savedInterval, savedDeadline);
      check(!restored.poll(1234568L, TEN_DAYS), "Reload does not trigger an event");
      check(restored.nextTrigger() == savedDeadline, "Reload keeps progress");
      check(restored.poll(savedDeadline, TEN_DAYS), "Saved deadline still fires");

      AirdropSchedule late = new AirdropSchedule(TEN_DAYS, TEN_DAYS);
      check(late.poll(TEN_DAYS * 20L, TEN_DAYS), "An overdue deadline is handled");
      check(!late.poll(TEN_DAYS * 20L, TEN_DAYS), "No catch-up burst");
      check(late.nextTrigger() == TEN_DAYS * 21L, "Next deadline lies in the future");

      AirdropSchedule changed = new AirdropSchedule(TEN_DAYS, TEN_DAYS);
      check(!changed.poll(1000L, 24000L), "Changing the interval restarts the wait");
      check(changed.nextTrigger() == 25000L, "Changed interval uses the current clock");
      AirdropSchedule rollback = new AirdropSchedule(TEN_DAYS, TEN_DAYS * 10L);
      check(!rollback.poll(100L, TEN_DAYS), "Clock rollback waits");
      check(rollback.nextTrigger() == TEN_DAYS + 100L, "Clock rollback does not stall forever");

      AirdropScheduleData data = new AirdropScheduleData();
      check(!data.poll(1000L, TEN_DAYS), "New saved data waits");
      check(data.isDirty(), "Initial deadline is marked for saving");
      data.setDirty(false);
      check(!data.poll(1001L, TEN_DAYS), "Ordinary tick waits");
      check(!data.isDirty(), "Ordinary ticks do not dirty saved data");
      CompoundTag saved = data.save(new CompoundTag(), null);
      check(saved.getLong("interval") == TEN_DAYS, "Interval is stored in NBT");
      check(saved.getLong("nextTrigger") == 241000L, "Deadline is stored in NBT");
      AirdropScheduleData loaded = AirdropScheduleData.load(saved, null);
      check(!loaded.poll(240999L, TEN_DAYS), "NBT reload preserves the remaining wait");
      check(loaded.poll(241000L, TEN_DAYS), "NBT reload fires at the saved deadline");
      check(loaded.isDirty(), "Next deadline is marked for saving");
      check(!loaded.poll(241000L, TEN_DAYS), "NBT reload cannot duplicate the event");
      AirdropScheduleData legacy = AirdropScheduleData.load(new CompoundTag(), null);
      check(!legacy.poll(999999L, TEN_DAYS), "Missing legacy fields start a full wait");

      System.out.println("Airdrop schedule regression checks passed.");
   }

   private static void check(boolean condition, String message) {
      if (!condition) {
         throw new AssertionError(message);
      }
   }
}
