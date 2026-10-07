package net.gem19910816.dyairdrop.block.listener;

import net.gem19910816.dyairdrop.block.renderer.AirdroplargeTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.AirdropmedicalTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.AirdropweaponTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.LockedairdroplargeTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.LockedairdroplargeopenTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.LockedairdropmedicalTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.LockedairdropmedicalopenTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.LockedairdropweaponTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.LockedairdropweaponopenTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.Safe2TileRenderer;
import net.gem19910816.dyairdrop.block.renderer.SafeTileRenderer;
import net.gem19910816.dyairdrop.block.renderer.SafeopenTileRenderer;
import net.gem19910816.dyairdrop.init.DyairdropModBlockEntities;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent.RegisterRenderers;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.EventBusSubscriber.Bus;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientListener {
   @OnlyIn(Dist.CLIENT)
   @SubscribeEvent
   public static void registerRenderers(RegisterRenderers event) {
      event.registerBlockEntityRenderer((BlockEntityType)DyairdropModBlockEntities.AIRDROPLARGE.get(), context -> new AirdroplargeTileRenderer());
      event.registerBlockEntityRenderer((BlockEntityType)DyairdropModBlockEntities.AIRDROPMEDICAL.get(), context -> new AirdropmedicalTileRenderer());
      event.registerBlockEntityRenderer((BlockEntityType)DyairdropModBlockEntities.AIRDROPWEAPON.get(), context -> new AirdropweaponTileRenderer());
      event.registerBlockEntityRenderer((BlockEntityType)DyairdropModBlockEntities.SAFE_2.get(), context -> new Safe2TileRenderer());
      event.registerBlockEntityRenderer((BlockEntityType)DyairdropModBlockEntities.SAFE.get(), context -> new SafeTileRenderer());
      event.registerBlockEntityRenderer((BlockEntityType)DyairdropModBlockEntities.LOCKEDAIRDROPLARGE.get(), context -> new LockedairdroplargeTileRenderer());
      event.registerBlockEntityRenderer(
         (BlockEntityType)DyairdropModBlockEntities.LOCKEDAIRDROPLARGEOPEN.get(), context -> new LockedairdroplargeopenTileRenderer()
      );
      event.registerBlockEntityRenderer((BlockEntityType)DyairdropModBlockEntities.SAFEOPEN.get(), context -> new SafeopenTileRenderer());
      event.registerBlockEntityRenderer((BlockEntityType)DyairdropModBlockEntities.LOCKEDAIRDROPWEAPON.get(), context -> new LockedairdropweaponTileRenderer());
      event.registerBlockEntityRenderer(
         (BlockEntityType)DyairdropModBlockEntities.LOCKEDAIRDROPMEDICAL.get(), context -> new LockedairdropmedicalTileRenderer()
      );
      event.registerBlockEntityRenderer(
         (BlockEntityType)DyairdropModBlockEntities.LOCKEDAIRDROPMEDICALOPEN.get(), context -> new LockedairdropmedicalopenTileRenderer()
      );
      event.registerBlockEntityRenderer(
         (BlockEntityType)DyairdropModBlockEntities.LOCKEDAIRDROPWEAPONOPEN.get(), context -> new LockedairdropweaponopenTileRenderer()
      );
   }
}
