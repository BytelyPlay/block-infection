package org.hyperoil.blockinfection;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.hyperoil.blockinfection.utils.BlocksHelper;
import org.hyperoil.blockinfection.utils.ItemsHelper;

@Mod(hyperoil.MODID)
public class hyperoil {
    public static final String MODID = "blockinfection";

    public hyperoil(IEventBus eventBus, ModContainer container) {
        BlocksHelper.register(eventBus);
        ItemsHelper.register(eventBus);

        eventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}
