package org.hyperoil.blockinfection;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.lifecycle.ClientStoppingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import org.hyperoil.blockinfection.Utils.BlocksHelper;
import org.hyperoil.blockinfection.Utils.InfectionManager;
import org.hyperoil.blockinfection.Utils.ItemsHelper;
import org.hyperoil.blockinfection.datagen.DataGenerators;

import java.util.concurrent.ExecutorService;

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

    private void serverStoppingEvent(ServerStoppingEvent event) {
        InfectionManager.saveData();
    }

    private void clientStoppingEvent(ClientStoppingEvent event) {

    }
}
