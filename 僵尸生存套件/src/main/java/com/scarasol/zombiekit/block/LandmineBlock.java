package com.scarasol.zombiekit.block;

import com.scarasol.zombiekit.entity.projectile.LandmineEntity;
import com.scarasol.zombiekit.init.ZombieKitBlocks;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Tuple;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LandmineBlock extends Block implements BombBlock {

    public List<Tuple<Tuple<String, Integer>, Integer>> areaEffectCloudEffect = new ArrayList<>();

    public LandmineBlock(Properties properties, List<Tuple<Tuple<String, Integer>, Integer>> areaEffectCloudEffect) {
        // 1.21 移除了可覆盖的 getPistonPushReaction，改由 Properties 携带（原语义 PushReaction.DESTROY）
        super(properties.pushReaction(PushReaction.DESTROY));
        this.areaEffectCloudEffect.addAll(areaEffectCloudEffect);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true;
    }

    @Override
    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    @Override
    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        Vec3 offset = state.getOffset(world, pos);
        return box(0, 0, 0, 16, 1, 16).move(offset.x, offset.y, offset.z);
    }

    @Override
    public boolean canSurvive(BlockState blockstate, LevelReader worldIn, BlockPos pos) {
        if (worldIn instanceof LevelAccessor world) {
            return world.getBlockState(pos.below()).canOcclude();
        }
        return super.canSurvive(blockstate, worldIn, pos);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        return !state.canSurvive(world, currentPos) ? Blocks.AIR.defaultBlockState() : super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }

    public PathType getBlockPathType(BlockState state, BlockGetter world, BlockPos pos, Mob entity) {
        return PathType.WALKABLE;
    }

    @Override
    public int getExplodeLevel() {
        return areaEffectCloudEffect.isEmpty() ? 4 : 1;
    }

    public boolean canHarvestBlock(BlockState state, BlockGetter world, BlockPos pos, Player player) {
        // 1.20.1 语义为 tier.getLevel() >= 1（石铲及以上）；1.21 Tier 不再暴露 getLevel()
        if (player.getInventory().getSelected().getItem() instanceof ShovelItem tieredItem)
            return tieredItem.getTier() != Tiers.WOOD;
        return false;
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootParams.Builder builder) {
        List<ItemStack> dropsOriginal = super.getDrops(state, builder);
        if (!dropsOriginal.isEmpty())
            return dropsOriginal;
        return Collections.singletonList(new ItemStack(this, 1));
    }

    public boolean onDestroyedByPlayer(BlockState blockstate, Level world, BlockPos pos, Player entity, boolean willHarvest, FluidState fluid) {
        // 1.21 未打补丁的 Block 没有该方法，这里内联 NeoForge IBlockExtension 的默认实现
        boolean retval = world.isClientSide() ? world.setBlock(pos, fluid.createLegacyBlock(), 11) : world.removeBlock(pos, false);
        if (entity.getMainHandItem().getItem() instanceof ShovelItem || entity.isCreative())
            return retval;
        exploded(world, pos.getX(), pos.getY(), pos.getZ(), getExplodeLevel());
        return retval;
    }

    @Override
    public void wasExploded(Level world, BlockPos pos, Explosion e) {
        super.wasExploded(world, pos, e);
        exploded(world, pos.getX(), pos.getY(), pos.getZ(), getExplodeLevel());
        if (!areaEffectCloudEffect.isEmpty())
            spawnAreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void attack(BlockState blockstate, Level world, BlockPos pos, Player entity) {
        super.attack(blockstate, world, pos, entity);
        if (entity.getMainHandItem().getItem() instanceof ShovelItem || entity.isCreative())
            return;
        exploded(world, pos.getX(), pos.getY(), pos.getZ(), getExplodeLevel());
        if (!areaEffectCloudEffect.isEmpty())
            spawnAreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    public void entityInside(BlockState blockstate, Level world, BlockPos pos, Entity entity) {
        super.entityInside(blockstate, world, pos, entity);
        if (entity instanceof LandmineEntity)
            return;
        exploded(world, pos.getX(), pos.getY(), pos.getZ(), getExplodeLevel());
        if (!areaEffectCloudEffect.isEmpty())
            spawnAreaEffectCloud(world, pos.getX(), pos.getY(), pos.getZ());
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState blockstate, Level world, BlockPos pos, Player entity, InteractionHand hand, BlockHitResult hit) {
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        exploded(world, x, y, z, getExplodeLevel());
        if (!areaEffectCloudEffect.isEmpty())
            spawnAreaEffectCloud(world, x, y, z);
        return InteractionResult.SUCCESS;
    }


    public void spawnAreaEffectCloud(Level level, int x, int y, int z){
        AreaEffectCloud areaEffectCloud = new AreaEffectCloud(level, x, y, z);
        areaEffectCloud.setDuration(200);
        areaEffectCloud.setRadius(4);
        for (Tuple<Tuple<String, Integer>, Integer> effectInstance : areaEffectCloudEffect){
            Holder<MobEffect> effect = BuiltInRegistries.MOB_EFFECT.getHolder(ResourceLocation.parse(effectInstance.getA().getA())).orElse(null);
            if (effect != null) {
                areaEffectCloud.addEffect(new MobEffectInstance(effect, effectInstance.getA().getB(), effectInstance.getB(), false, false));
            }

        }
        level.addFreshEntity(areaEffectCloud);
    }

}
