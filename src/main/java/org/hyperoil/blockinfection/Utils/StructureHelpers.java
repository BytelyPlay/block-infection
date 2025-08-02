package org.hyperoil.blockinfection.Utils;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.hyperoil.blockinfection.Structures.InfectionCorePyramid;
import org.hyperoil.blockinfection.hyperoil;
import org.lwjgl.system.Struct;

public class StructureHelpers {
    private static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister
            .create(Registries.STRUCTURE_TYPE, hyperoil.MODID);
    public static final DeferredHolder<StructureType<?>, StructureType<InfectionCorePyramid>> INFECTION_CORE_PYRAMID = STRUCTURE_TYPES
            .register("infection_pyramid_structure", () -> explictStructureNaming(InfectionCorePyramid.CODEC));
    private static <T extends Structure> StructureType<T> explictStructureNaming(MapCodec<T> codec) {
        return () -> codec;
    }
}
