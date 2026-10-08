package net.mcreator.gore.procedures;

import net.minecraft.world.entity.Entity;

public class EXARRACKDEMONAGGROCONFIGProcedure {
   public static boolean execute(Entity entity) {
      return entity == null ? false : entity.getPersistentData().getBoolean("exarrack_demon_targeting_screamer");
   }
}
