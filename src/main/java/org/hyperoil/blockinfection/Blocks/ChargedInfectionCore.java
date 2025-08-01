package org.hyperoil.blockinfection.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.hyperoil.blockinfection.Utils.BlocksHelper;
import org.hyperoil.blockinfection.Utils.InfectionManager;
import org.hyperoil.blockinfection.Utils.ItemsHelper;

public class ChargedInfectionCore extends Block {
    public ChargedInfectionCore(Properties properties) {
		super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() == ItemsHelper.UNCHARGING_STAFF.get()) {
            level.removeBlock(pos, false);
            player.getInventory().removeItem(stack);
            player.getInventory().add(new ItemStack(ItemsHelper.STAFF_OF_UNLIMITED_POWER.get()));
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        InfectionManager.killInfection(pos);
    }
}
