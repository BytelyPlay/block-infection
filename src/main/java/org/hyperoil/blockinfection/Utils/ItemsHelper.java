package org.hyperoil.blockinfection.Utils;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hyperoil.blockinfection.hyperoil;

public class ItemsHelper {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(hyperoil.MODID);
    public static final DeferredItem<? extends BlockItem> INFECTION_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("infection_block",
            BlocksHelper.INFECTION_BLOCK);
    public static final DeferredItem<? extends BlockItem> INFECTION_RESISTANT_BLOCK = ITEMS.registerSimpleBlockItem("infection_resistant_block",
            BlocksHelper.INFECTION_RESISTANT_BLOCK);

    public static void register(IEventBus bus) {
        ITEMS.register(bus);

        bus.addListener(ItemsHelper::buildCreativeModeTabContents);
    }

    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(INFECTION_BLOCK_ITEM);
            event.accept(INFECTION_RESISTANT_BLOCK);
        }
    }
}
