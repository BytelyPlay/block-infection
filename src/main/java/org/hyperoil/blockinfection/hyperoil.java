package org.hyperoil.blockinfection;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hyperoil.blockinfection.Blocks.InfectionBlock;
import org.hyperoil.blockinfection.Utils.BlocksHelper;

@Mod(hyperoil.MODID)
public class hyperoil {
    public static final String MODID = "blockinfection";

    public hyperoil(IEventBus eventBus, ModContainer container) {
        BlocksHelper.register(eventBus);

        eventBus.addListener(this::commonSetup);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
    }
}
