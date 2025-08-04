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
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.AABB;
import org.hyperoil.blockinfection.Utils.BlocksHelper;
import org.hyperoil.blockinfection.Utils.InfectionManager;
import org.hyperoil.blockinfection.hyperoil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class InfectionBlock extends Block {
    private static final Logger log = LoggerFactory.getLogger(InfectionBlock.class);

    public InfectionBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void tick(@NotNull BlockState state, @NotNull ServerLevel level, @NotNull BlockPos pos, @NotNull RandomSource random) {
        if (!InfectionManager.isInfectionStillActive()) return;
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
            // Couldn't find a better way as it will always be unbound if i define it globally and statically or for the instance when it's constructed
            if (loopState.isAir() || List.of(BlocksHelper.CHARGED_INFECTION_CORE.get(),
                            BlocksHelper.UNCHARGED_INFECTION_CORE.get(),
                            BlocksHelper.INFECTION_BLOCK.get(),
                            BlocksHelper.INFECTION_RESISTANT_BLOCK.get(),
                            Blocks.LIGHT, BlocksHelper.UNCHARGER_GROWER.get())
                    .contains(loopState.getBlock()) || level.getBlockEntity(loopPos) != null) continue;
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
