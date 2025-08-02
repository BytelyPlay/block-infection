package org.hyperoil.blockinfection.Utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hyperoil.blockinfection.Items.StaffOfUnlimitedPower;
import org.hyperoil.blockinfection.Items.UnchargingStaff;
import org.hyperoil.blockinfection.hyperoil;

import java.util.UUID;

public class ItemsHelper {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(hyperoil.MODID);
    public static final DeferredItem<? extends BlockItem> INFECTION_BLOCK_ITEM = ITEMS.registerSimpleBlockItem("infection_block",
            BlocksHelper.INFECTION_BLOCK);
    public static final DeferredItem<? extends BlockItem> INFECTION_RESISTANT_BLOCK = ITEMS.registerSimpleBlockItem("infection_resistant_block",
            BlocksHelper.INFECTION_RESISTANT_BLOCK);
    public static final DeferredItem<? extends BlockItem> UNCHARGED_INFECTION_CORE = ITEMS.registerSimpleBlockItem("uncharged_infection_core",
            BlocksHelper.UNCHARGED_INFECTION_CORE);
    public static final DeferredItem<? extends BlockItem> CHARGED_INFECTION_CORE = ITEMS.registerSimpleBlockItem("charged_infection_core",
            BlocksHelper.CHARGED_INFECTION_CORE);
    public static final DeferredItem<UnchargingStaff> UNCHARGING_STAFF = ITEMS.registerItem("uncharging_staff",
            registryName -> new UnchargingStaff(registryName
                    .stacksTo(1)));
    public static final DeferredItem<StaffOfUnlimitedPower> STAFF_OF_UNLIMITED_POWER = ITEMS.registerItem("staff_of_unlimited_power",
            registryName -> new StaffOfUnlimitedPower(registryName
                    .stacksTo(1)));

    public static void register(IEventBus bus) {
        ITEMS.register(bus);

        bus.addListener(ItemsHelper::buildCreativeModeTabContents);
    }

    public static void buildCreativeModeTabContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(INFECTION_BLOCK_ITEM);
            event.accept(INFECTION_RESISTANT_BLOCK);
            event.accept(UNCHARGING_STAFF);

            event.accept(CHARGED_INFECTION_CORE);
            event.accept(UNCHARGED_INFECTION_CORE);
        }
        if (event.getTabKey() == CreativeModeTabs.COMBAT) {
            event.accept(STAFF_OF_UNLIMITED_POWER);
        }
    }
}
