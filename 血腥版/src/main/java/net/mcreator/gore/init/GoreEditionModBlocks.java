package net.mcreator.gore.init;

import net.mcreator.gore.block.AcidBlock;
import net.mcreator.gore.block.AcidBlockTickBlock;
import net.mcreator.gore.block.AshSandBlock;
import net.mcreator.gore.block.AshesFlowerBlock;
import net.mcreator.gore.block.AshesUnknownSkullBlock;
import net.mcreator.gore.block.CreeperGrassBlock;
import net.mcreator.gore.block.DustLampBlock;
import net.mcreator.gore.block.ExarrackMonsterHeadBlock;
import net.mcreator.gore.block.ExarrackartdowncenterBlock;
import net.mcreator.gore.block.ExarrackartdownleftBlock;
import net.mcreator.gore.block.ExarrackartdownrightBlock;
import net.mcreator.gore.block.ExarrackarttopcenterBlock;
import net.mcreator.gore.block.ExarrackarttopleftBlock;
import net.mcreator.gore.block.ExarrackarttoprightBlock;
import net.mcreator.gore.block.VoidstoneBlock;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GoreEditionModBlocks {
   public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(BuiltInRegistries.BLOCK, "gore_edition");
   public static final DeferredHolder<Block, Block> EXARRACK = REGISTRY.register("exarrack", () -> new VoidstoneBlock());
   public static final DeferredHolder<Block, Block> ACID = REGISTRY.register("acid", () -> new AcidBlock());
   public static final DeferredHolder<Block, Block> ACID_BLOCK_TICK = REGISTRY.register("acid_block_tick", () -> new AcidBlockTickBlock());
   public static final DeferredHolder<Block, Block> CREEPER_GRASS = REGISTRY.register("creeper_grass", () -> new CreeperGrassBlock());
   public static final DeferredHolder<Block, Block> EXARRACK_ART_DOWN_LEFT = REGISTRY.register("exarrack_art_down_left", () -> new ExarrackartdownleftBlock());
   public static final DeferredHolder<Block, Block> EXARRACK_ART_DOWN_CENTER = REGISTRY.register(
      "exarrack_art_down_center", () -> new ExarrackartdowncenterBlock()
   );
   public static final DeferredHolder<Block, Block> EXARRACK_ART_DOWN_RIGHT = REGISTRY.register(
      "exarrack_art_down_right", () -> new ExarrackartdownrightBlock()
   );
   public static final DeferredHolder<Block, Block> EXARRACK_ART_TOP_LEFT = REGISTRY.register("exarrack_art_top_left", () -> new ExarrackarttopleftBlock());
   public static final DeferredHolder<Block, Block> EXARRACK_ART_TOP_CENTER = REGISTRY.register(
      "exarrack_art_top_center", () -> new ExarrackarttopcenterBlock()
   );
   public static final DeferredHolder<Block, Block> EXARRACK_ART_TOP_RIGHT = REGISTRY.register("exarrack_art_top_right", () -> new ExarrackarttoprightBlock());
   public static final DeferredHolder<Block, Block> ASHES_FLOWER = REGISTRY.register("ashes_flower", () -> new AshesFlowerBlock());
   public static final DeferredHolder<Block, Block> EXARRACK_MONSTER_HEAD = REGISTRY.register("exarrack_monster_head", () -> new ExarrackMonsterHeadBlock());
   public static final DeferredHolder<Block, Block> ASHTRAY_CYCLE = REGISTRY.register("ashtray_cycle", () -> new DustLampBlock());
   public static final DeferredHolder<Block, Block> ASH_SAND = REGISTRY.register("ash_sand", () -> new AshSandBlock());
   public static final DeferredHolder<Block, Block> ASHED_UNKNOWN_SKULL = REGISTRY.register("ashed_unknown_skull", () -> new AshesUnknownSkullBlock());

   @EventBusSubscriber(
      bus = Bus.MOD,
      value = {Dist.CLIENT}
   )
   public static class BlocksClientSideHandler {
      @SubscribeEvent
      public static void blockColorLoad(net.neoforged.neoforge.client.event.RegisterColorHandlersEvent.Block event) {
         CreeperGrassBlock.blockColorLoad(event);
      }
   }
}
