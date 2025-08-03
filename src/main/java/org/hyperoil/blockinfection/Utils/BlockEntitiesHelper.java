package org.hyperoil.blockinfection.Utils;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hyperoil.blockinfection.Blocks.BlockEntities.ChargedInfectionCoreBlockEntity;

import static org.hyperoil.blockinfection.hyperoil.MODID;

public class BlockEntitiesHelper {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, MODID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<ChargedInfectionCoreBlockEntity>> CHARGED_INFECTION_CORE_BLOCK_ENTITY =
            BLOCK_ENTITY_TYPES.register("charged_infection_core",
            () -> new BlockEntityType<>(
                    ChargedInfectionCoreBlockEntity::new,
                    false,
                    BlocksHelper.CHARGED_INFECTION_CORE.get()
            )
    );

    public static void register(IEventBus bus) {
        BLOCK_ENTITY_TYPES.register(bus);
    }
}
