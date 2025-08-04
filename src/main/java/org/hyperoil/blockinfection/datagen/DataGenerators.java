package org.hyperoil.blockinfection.datagen;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.hyperoil.blockinfection.hyperoil;

@EventBusSubscriber(modid = hyperoil.MODID)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherDataEventClient(GatherDataEvent.Client event) {
        gatherDataEventCommon(event);
    }
    @SubscribeEvent
    public static void gatherDataEventServer(GatherDataEvent.Server event) {
        gatherDataEventCommon(event);
    }

    private static void gatherDataEventCommon(GatherDataEvent event) {
        event.createProvider(ModRecipeProvider.Runner::new);
    }
}
