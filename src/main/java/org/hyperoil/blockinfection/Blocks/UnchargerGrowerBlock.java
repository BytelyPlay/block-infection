package org.hyperoil.blockinfection.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.hyperoil.blockinfection.Utils.ItemsHelper;
import org.jetbrains.annotations.NotNull;

public class UnchargerGrowerBlock extends Block {
    public static final IntegerProperty GROWTH = IntegerProperty.create("growth", 1, 8);
    public UnchargerGrowerBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        int growth = state.getValue(GROWTH);
        if (growth > 8) return;
        state.setValue(GROWTH, growth + 1);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(GROWTH);
    }

    @Override
    protected @NotNull InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        return rightClicked(state, player);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return rightClicked(state, player);
    }

    private @NotNull InteractionResult rightClicked(BlockState state, Player player) {
        if (state.getValue(GROWTH) >= 8) {
            if (player.getInventory().add(new ItemStack(ItemsHelper.UNCHARGING_STAFF.get()))) {
                state.setValue(GROWTH, 1);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
