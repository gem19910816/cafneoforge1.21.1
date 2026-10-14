package com.scarasol.zombiekit.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.scarasol.zombiekit.init.ZombieKitItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.cauldron.CauldronInteraction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractCauldronBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.items.ItemHandlerHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class SaltpeterCauldronBlock extends AbstractCauldronBlock {

    // 1.21 AbstractCauldronBlock 需要 codec；交互表不参与序列化，反序列化时回退为空交互表
    public static final MapCodec<SaltpeterCauldronBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            propertiesCodec()
    ).apply(instance, properties -> new SaltpeterCauldronBlock(properties, CauldronInteraction.EMPTY)));

    private double contentHeight = 0.9375;

    public SaltpeterCauldronBlock(Properties properties, CauldronInteraction.InteractionMap interactionMap) {
        super(properties, interactionMap);
    }

    @Override
    protected MapCodec<? extends AbstractCauldronBlock> codec() {
        return CODEC;
    }


    @Override
    protected ItemInteractionResult useItemOn(ItemStack itemStack, BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (itemStack.is(Items.BUCKET)){
            if (!player.getAbilities().instabuild){
                itemStack.setCount(itemStack.getCount() - 1);
            }
            ItemStack setstack = new ItemStack(ZombieKitItems.SALTPETER_BUCKET.get());
            setstack.setCount(1);
            ItemHandlerHelper.giveItemToPlayer(player, setstack);
            level.setBlock(blockPos, Blocks.CAULDRON.defaultBlockState(), 3);
            level.playSound(null, blockPos, SoundEvents.BUCKET_FILL, SoundSource.BLOCKS, 1, 1);
            return ItemInteractionResult.SUCCESS;
        }
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected boolean canReceiveStalactiteDrip(Fluid fluid) {
        return false;
    }

    @Override
    protected double getContentHeight(BlockState blockState) {
        return contentHeight;
    }

    @Override
    public boolean isFull(BlockState blockState) {
        return true;
    }

    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        // 1.20.1 语义为 tier.getLevel() >= 0，即任意镐均可采集；1.21 Tier 不再暴露 getLevel()
        return player.getInventory().getSelected().getItem() instanceof PickaxeItem;
    }

    @Override
    public void tick(BlockState blockState, ServerLevel serverLevel, BlockPos blockPos, RandomSource random) {
        BlockState blockStateBelow = serverLevel.getBlockState(blockPos.below());
        Block blockBelow = blockStateBelow.getBlock();
        if ((blockBelow == Blocks.CAMPFIRE || blockBelow == Blocks.SOUL_CAMPFIRE) && blockStateBelow.getValue(BlockStateProperties.LIT)){
            double x = blockPos.getX();
            double y = blockPos.getY();
            double z = blockPos.getZ();
            int num = 2;
            double sum = random.nextDouble();
            if (sum < 0.2){
                num = 1;
            }else if (sum > 0.8){
                num = 3;
            }
            for (int i = 0; i < num; i++){
                ItemEntity entityToSpawn = new ItemEntity(serverLevel, x, y, z, new ItemStack(ZombieKitItems.SALTPETER.get()));
                entityToSpawn.setPickUpDelay(10);
                serverLevel.addFreshEntity(entityToSpawn);
            }
            serverLevel.setBlock(blockPos, Blocks.CAULDRON.defaultBlockState(), 3);
        }
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(Blocks.CAULDRON));
    }

}

