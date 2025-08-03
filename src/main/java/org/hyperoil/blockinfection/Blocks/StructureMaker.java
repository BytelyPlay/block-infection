package org.hyperoil.blockinfection.Blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.hyperoil.blockinfection.Utils.ItemsHelper;

public class StructureMaker extends Block {
    public StructureMaker(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() == ItemsHelper.STRUCTURE_CATALYST.get()) {
            if (stack.getCount() >= 64) {
                if (player.getInventory().add(new ItemStack(ItemsHelper.STRUCTURE_PLACER.get()))) {
                    if (stack.getCount() == 64) {
                        player.getInventory().removeItem(stack);
                    } else {
                        stack.consume(64, player);
                    }
                }
            }
        }
        return InteractionResult.SUCCESS;
    }
}
