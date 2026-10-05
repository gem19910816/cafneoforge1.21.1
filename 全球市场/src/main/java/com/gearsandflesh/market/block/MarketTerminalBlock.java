package com.gearsandflesh.market.block;

import com.gearsandflesh.market.network.MarketNetwork;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Market Terminal. Has no item form, so it is unobtainable even in creative
 * mode (no creative tab entry, /give fails); admins place it with /setblock.
 * Right-clicking opens the global market.
 */
public final class MarketTerminalBlock extends Block {
    public MarketTerminalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(
            BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        if (player instanceof ServerPlayer serverPlayer) {
            MarketNetwork.open(serverPlayer);
        }
        return InteractionResult.CONSUME;
    }
}
