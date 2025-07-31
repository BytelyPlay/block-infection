package org.hyperoil.blockinfection.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.AABB;
import org.hyperoil.blockinfection.Utils.BlocksHelper;
import org.hyperoil.blockinfection.hyperoil;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

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
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        super.onPlace(state, level, pos, oldState, movedByPiston);

        level.scheduleTick(pos, this, 4);
    }
}
