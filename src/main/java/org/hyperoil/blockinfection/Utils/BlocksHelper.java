package org.hyperoil.blockinfection.Utils;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hyperoil.blockinfection.Blocks.ChargedInfectionCore;
import org.hyperoil.blockinfection.Blocks.InfectionBlock;
import org.hyperoil.blockinfection.Blocks.UnchargedInfectionCore;

import static org.hyperoil.blockinfection.hyperoil.MODID;

public class BlocksHelper {
    private static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(MODID);

    public static final DeferredBlock<InfectionBlock> INFECTION_BLOCK = BLOCKS.registerBlock("infection_block",
            registryName -> new InfectionBlock(
                    registryName
                            .instabreak()
                            .explosionResistance(Float.MAX_VALUE)
            ));
    public static final DeferredBlock<UnchargedInfectionCore> UNCHARGED_INFECTION_CORE = BLOCKS.registerBlock("uncharged_infection_core",
            registryName -> new UnchargedInfectionCore(registryName
                    .strength(Float.MAX_VALUE, Float.MAX_VALUE))
    );
    public static final DeferredBlock<ChargedInfectionCore> CHARGED_INFECTION_CORE = BLOCKS.registerBlock("charged_infection_core",
            registryName -> new ChargedInfectionCore(registryName
                    .strength(Float.MAX_VALUE, Float.MAX_VALUE)));
    public static final DeferredBlock<Block> INFECTION_RESISTANT_BLOCK = BLOCKS.registerSimpleBlock("infection_resistant_block",
            BlockBehaviour.Properties.of()
                    .explosionResistance(Float.MAX_VALUE)
                    .destroyTime(120));

    public static void register(IEventBus bus) {
        BLOCKS.register(bus);
    }
}
