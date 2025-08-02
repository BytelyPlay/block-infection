package org.hyperoil.blockinfection.Items;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import org.hyperoil.blockinfection.hyperoil;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StructurePlacer extends Item {
    private static final Logger log = LoggerFactory.getLogger(StructurePlacer.class);

    public StructurePlacer(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext context) {
        BlockPos clickedPos = context.getClickedPos();

        if (context.getLevel() instanceof ServerLevel serverLevel &&
                context.getPlayer() instanceof ServerPlayer serverPlayer) {
            ResourceLocation structure = ResourceLocation.tryBuild(hyperoil.MODID, "infection_pyramid_structure");
            if (structure == null) {
                serverPlayer.sendSystemMessage(Component.literal("Resource Location is null for some reason, logging."));
                log.warn("ResourceLocation was null while placing structure? mod bugged?");
                return InteractionResult.FAIL;
            }
            StructureTemplate template = serverLevel.getStructureManager().get(structure).orElse(null);
            if (template == null) {
                serverPlayer.sendSystemMessage(Component.literal("StructureTemplate is null? mod bugged?, logging."));
                log.warn("StructureTemplate is null? mod bugged?");
                return InteractionResult.FAIL;
            }
            StructurePlaceSettings settings = new StructurePlaceSettings();
            template.placeInWorld(serverLevel, clickedPos, clickedPos, settings, serverLevel.getRandom(), Block.UPDATE_CLIENTS);
            serverPlayer.getInventory().removeItem(context.getItemInHand());
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }
}
