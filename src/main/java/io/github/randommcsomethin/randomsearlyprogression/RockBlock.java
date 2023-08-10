package io.github.randommcsomethin.randomsearlyprogression;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class RockBlock extends HorizontalFacingBlock {
    private static final VoxelShape X_SHAPE;
    private static final VoxelShape Z_SHAPE;

    public RockBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState;
        blockState = this.getDefaultState().with(FACING, ctx.getHorizontalPlayerFacing());

        if (blockState.canPlaceAt(ctx.getWorld(), ctx.getBlockPos())) {
            return blockState;
        }
        return null;
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (state.get(FACING).getAxis() == Direction.Axis.X) {
            return X_SHAPE;
        }
        return Z_SHAPE;
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    static {
        X_SHAPE = Block.createCuboidShape(6.0D, 0.0D, 5.0D, 10.0D, 2.0D, 11.0D);
        Z_SHAPE = Block.createCuboidShape(5.0D, 0.0D, 6.0D, 11.0D, 2.0D, 10.0D);
    }
}
