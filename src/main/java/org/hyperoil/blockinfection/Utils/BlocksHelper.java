package org.hyperoil.blockinfection.Utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.EventBus;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hyperoil.blockinfection.Blocks.InfectionBlock;

import static org.hyperoil.blockinfection.hyperoil.MODID;

public class BlocksHelper {
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<InfectionBlock> INFECTION_BLOCK = BLOCKS.registerBlock("infection_block",
            registryName -> new InfectionBlock(
                    registryName
                            .instabreak()
                            .explosionResistance(Float.MAX_VALUE)
            ));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
