package net.mcreator.dyairdrop.world;

import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;

public final class AirdropScheduleData extends SavedData {
   private static final String DATA_NAME = "dyairdrop_schedule";
   private final AirdropSchedule schedule;

   public AirdropScheduleData() {
      this(new AirdropSchedule());
   }

   private AirdropScheduleData(AirdropSchedule schedule) {
      this.schedule = schedule;
   }

   public static AirdropScheduleData get(ServerLevel overworld) {
      return overworld.getDataStorage().computeIfAbsent(
         new SavedData.Factory<>(AirdropScheduleData::new, AirdropScheduleData::load), DATA_NAME);
   }

   static AirdropScheduleData load(CompoundTag tag, HolderLookup.Provider provider) {
      return new AirdropScheduleData(new AirdropSchedule(tag.getLong("interval"), tag.getLong("nextTrigger")));
   }

   @Override
   public CompoundTag save(CompoundTag tag, HolderLookup.Provider provider) {
      tag.putLong("interval", schedule.interval());
      tag.putLong("nextTrigger", schedule.nextTrigger());
      return tag;
   }

   public boolean poll(long gameTime, long interval) {
      long previousInterval = schedule.interval();
      long previousDeadline = schedule.nextTrigger();
      boolean due = schedule.poll(gameTime, interval);
      if (previousInterval != schedule.interval() || previousDeadline != schedule.nextTrigger()) {
         setDirty();
      }
      return due;
   }
}
