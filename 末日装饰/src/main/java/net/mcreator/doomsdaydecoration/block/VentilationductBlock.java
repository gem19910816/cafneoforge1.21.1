/*
 * Decompiled with CFR 0.152.
 */
package net.mcreator.doomsdaydecoration.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VentilationductBlock
extends Block
implements SimpleWaterloggedBlock {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.AXIS;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public VentilationductBlock() {
        super(BlockBehaviour.Properties.of().sound(SoundType.METAL).strength(1.0f, 10.0f).noOcclusion().isRedstoneConductor((bs, br, bp) -> false));
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(AXIS, Direction.Axis.Y)).setValue((Property)WATERLOGGED, Boolean.valueOf(false)));
    }

    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    public int getLightBlock(BlockState state, BlockGetter worldIn, BlockPos pos) {
        return 0;
    }

    public VoxelShape getVisualShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Shapes.empty();
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return switch ((Direction.Axis)state.getValue(AXIS)) {
            default -> throw new IncompatibleClassChangeError();
            case Direction.Axis.X -> Shapes.or((VoxelShape)VentilationductBlock.box((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)1.0, (double)16.0), (VoxelShape[])new VoxelShape[]{VentilationductBlock.box((double)0.0, (double)1.0, (double)0.0, (double)16.0, (double)15.0, (double)1.0), VentilationductBlock.box((double)0.0, (double)15.0, (double)0.0, (double)16.0, (double)16.0, (double)16.0), VentilationductBlock.box((double)0.0, (double)1.0, (double)15.0, (double)16.0, (double)15.0, (double)16.0)});
            case Direction.Axis.Y -> Shapes.or((VoxelShape)VentilationductBlock.box((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)16.0, (double)1.0), (VoxelShape[])new VoxelShape[]{VentilationductBlock.box((double)0.0, (double)0.0, (double)1.0, (double)1.0, (double)16.0, (double)15.0), VentilationductBlock.box((double)0.0, (double)0.0, (double)15.0, (double)16.0, (double)16.0, (double)16.0), VentilationductBlock.box((double)15.0, (double)0.0, (double)1.0, (double)16.0, (double)16.0, (double)15.0)});
            case Direction.Axis.Z -> Shapes.or((VoxelShape)VentilationductBlock.box((double)0.0, (double)0.0, (double)0.0, (double)16.0, (double)1.0, (double)16.0), (VoxelShape[])new VoxelShape[]{VentilationductBlock.box((double)0.0, (double)1.0, (double)0.0, (double)1.0, (double)15.0, (double)16.0), VentilationductBlock.box((double)0.0, (double)15.0, (double)0.0, (double)16.0, (double)16.0, (double)16.0), VentilationductBlock.box((double)15.0, (double)1.0, (double)0.0, (double)16.0, (double)15.0, (double)16.0)});
        };
    }

    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(new Property[]{AXIS, WATERLOGGED});
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        boolean flag = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
        return (BlockState)((BlockState)this.defaultBlockState().setValue(AXIS, context.getClickedFace().getAxis())).setValue((Property)WATERLOGGED, Boolean.valueOf(flag));
    }

    public BlockState rotate(BlockState state, Rotation rot) {
        if (rot == Rotation.CLOCKWISE_90 || rot == Rotation.COUNTERCLOCKWISE_90) {
            if (state.getValue(AXIS) == Direction.Axis.X) {
                return (BlockState)state.setValue(AXIS, Direction.Axis.Z);
            }
            if (state.getValue(AXIS) == Direction.Axis.Z) {
                return (BlockState)state.setValue(AXIS, Direction.Axis.X);
            }
        }
        return state;
    }

    public FluidState getFluidState(BlockState state) {
        return (Boolean)state.getValue((Property)WATERLOGGED) != false ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor world, BlockPos currentPos, BlockPos facingPos) {
        if (((Boolean)state.getValue((Property)WATERLOGGED)).booleanValue()) {
            world.scheduleTick(currentPos, (Fluid)Fluids.WATER, Fluids.WATER.getTickDelay((LevelReader)world));
        }
        return super.updateShape(state, facing, facingState, world, currentPos, facingPos);
    }
}

