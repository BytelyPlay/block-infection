package org.hyperoil.blockinfection.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.AABB;
import org.hyperoil.blockinfection.Utils.BlocksHelper;
import org.hyperoil.blockinfection.hyperoil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

public class InfectionBlock extends Block {
    public InfectionBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        BlockPos[] adjacentPos = {
                pos.above(),
                pos.below(),
                pos.north(),
                pos.west(),
                pos.east(),
                pos.south()
        };

        for (BlockPos loopPos : adjacentPos) {
            BlockState loopState = level.getBlockState(loopPos);
            if (loopState.isAir() || loopState.getBlock() == this) continue;
            level.setBlock(loopPos,
                    BlocksHelper.INFECTION_BLOCK.get().defaultBlockState(),
                    Block.UPDATE_CLIENTS);
        }
    }

    @Override
    protected void onPlace(@NotNull BlockState state, Level level, @NotNull BlockPos pos, @NotNull BlockState oldState, boolean movedByPiston) {
        level.scheduleTick(pos, this, ThreadLocalRandom.current().nextInt(2, 6));
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, @Nullable Orientation orientation, boolean movedByPiston) {
        if (level instanceof ServerLevel) {
            tick(state, (ServerLevel) level, pos, level.getRandom());
        }
    }
}
