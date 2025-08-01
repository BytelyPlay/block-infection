package org.hyperoil.blockinfection.Blocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import org.hyperoil.blockinfection.Utils.BlocksHelper;
import org.hyperoil.blockinfection.Utils.InfectionManager;
import org.hyperoil.blockinfection.Utils.TranslationKeys;
import org.hyperoil.blockinfection.hyperoil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnchargedInfectionCore extends Block {
    private static final Logger log = LoggerFactory.getLogger(UnchargedInfectionCore.class);

    public UnchargedInfectionCore(Properties properties) {
		super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        return InteractionResult.TRY_WITH_EMPTY_HAND;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level instanceof ServerLevel serverLevel && player instanceof ServerPlayer serverPlayer) {
            int infectionID = InfectionManager.addInfection(pos);

            BlockPos belowPos = pos.below();
            serverLevel.setBlock(belowPos, BlocksHelper.INFECTION_BLOCK.get().defaultBlockState(), Block.UPDATE_CLIENTS);
            BlockEntity blockEntity = serverLevel.getBlockEntity(belowPos);
            if (blockEntity instanceof InfectionBlockEntity infectionBlockEntity) {
                infectionBlockEntity.setInfectionID(infectionID);
            } else {
                log.warn("InfectionBlock not a InfectionBlockEntity? Infection cannot spread.");
            }
            serverLevel.setBlock(pos, BlocksHelper.CHARGED_INFECTION_CORE.get().defaultBlockState(), Block.UPDATE_CLIENTS);

            serverPlayer.sendSystemMessage(TranslationKeys.MESSAGE_UNCHARGED_INFECTION_CORE_USE);
        }
        return InteractionResult.PASS;
    }
}
