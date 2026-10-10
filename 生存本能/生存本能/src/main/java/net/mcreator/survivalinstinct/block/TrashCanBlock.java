package net.mcreator.survivalinstinct.block;

import io.netty.buffer.Unpooled;
import net.mcreator.survivalinstinct.block.entity.TrashCanBlockEntity;
import net.mcreator.survivalinstinct.world.inventory.TrashCanGUIMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour.Properties;
import net.minecraft.world.level.block.state.StateDefinition.Builder;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class TrashCanBlock extends Block implements EntityBlock {
   public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

   public TrashCanBlock() {
      super(
         Properties.of()
            .instrument(NoteBlockInstrument.BASEDRUM)
            .sound(SoundType.COPPER)
            .strength(4.0F, 10.0F)
            .requiresCorrectToolForDrops()
            .noOcclusion()
            .isRedstoneConductor((bs, br, bp) -> false)
      );
      this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
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
      return switch ((Direction)state.getValue(FACING)) {
         case NORTH -> Shapes.or(
         box(2.0, 0.0, 2.0, 14.0, 11.0, 14.0),
         box(1.0, 11.0, 1.0, 15.0, 14.0, 15.0),
         box(2.0, 14.0, 2.0, 14.0, 15.0, 14.0),
         box(5.0, 14.0, 7.5, 11.0, 17.0, 8.5)
      );
         case EAST -> Shapes.or(
         box(2.0, 0.0, 2.0, 14.0, 11.0, 14.0),
         box(1.0, 11.0, 1.0, 15.0, 14.0, 15.0),
         box(2.0, 14.0, 2.0, 14.0, 15.0, 14.0),
         box(7.5, 14.0, 5.0, 8.5, 17.0, 11.0)
      );
         case WEST -> Shapes.or(
         box(2.0, 0.0, 2.0, 14.0, 11.0, 14.0),
         box(1.0, 11.0, 1.0, 15.0, 14.0, 15.0),
         box(2.0, 14.0, 2.0, 14.0, 15.0, 14.0),
         box(7.5, 14.0, 5.0, 8.5, 17.0, 11.0)
      );
         default -> Shapes.or(
         box(2.0, 0.0, 2.0, 14.0, 11.0, 14.0),
         box(1.0, 11.0, 1.0, 15.0, 14.0, 15.0),
         box(2.0, 14.0, 2.0, 14.0, 15.0, 14.0),
         box(5.0, 14.0, 7.5, 11.0, 17.0, 8.5)
      );
      };
   }

   @Override
   protected void createBlockStateDefinition(Builder<Block, BlockState> builder) {
      builder.add(FACING);
   }

   @Override
   public BlockState getStateForPlacement(BlockPlaceContext context) {
      return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
   }

   @Override
   public BlockState rotate(BlockState state, Rotation rot) {
      return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
   }

   @Override
   public BlockState mirror(BlockState state, Mirror mirrorIn) {
      return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
   }

   @Override
   protected InteractionResult useWithoutItem(BlockState blockstate, Level world, BlockPos pos, Player entity, BlockHitResult hit) {
      if (entity instanceof ServerPlayer player) {
         MenuProvider provider = getMenuProvider(blockstate, world, pos);
         if (provider != null) player.openMenu(provider, pos);
      }
      return InteractionResult.sidedSuccess(world.isClientSide());
   }

   @Override
   public MenuProvider getMenuProvider(BlockState state, Level worldIn, BlockPos pos) {
      return worldIn.getBlockEntity(pos) instanceof MenuProvider menuProvider ? menuProvider : null;
   }

   @Override
   public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
      return new TrashCanBlockEntity(pos, state);
   }

   @Override
   public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int eventID, int eventParam) {
      super.triggerEvent(state, world, pos, eventID, eventParam);
      BlockEntity blockEntity = world.getBlockEntity(pos);
      return blockEntity == null ? false : blockEntity.triggerEvent(eventID, eventParam);
   }

   @Override
   public void onRemove(BlockState state, Level world, BlockPos pos, BlockState newState, boolean isMoving) {
      if (state.getBlock() != newState.getBlock()) {
         if (world.getBlockEntity(pos) instanceof TrashCanBlockEntity be) {
            Containers.dropContents(world, pos, be);
            world.updateNeighbourForOutputSignal(pos, this);
         }

         super.onRemove(state, world, pos, newState, isMoving);
      }
   }

   @Override
   public boolean hasAnalogOutputSignal(BlockState state) {
      return true;
   }

   @Override
   public int getAnalogOutputSignal(BlockState blockState, Level world, BlockPos pos) {
      return world.getBlockEntity(pos) instanceof TrashCanBlockEntity be ? AbstractContainerMenu.getRedstoneSignalFromContainer(be) : 0;
   }
}
