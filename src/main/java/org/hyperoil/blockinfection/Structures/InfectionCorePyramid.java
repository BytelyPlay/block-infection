package org.hyperoil.blockinfection.Structures;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.placement.StructurePlacement;
import net.minecraft.world.level.levelgen.structure.pools.JigsawPlacement;
import org.hyperoil.blockinfection.Utils.StructureHelpers;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class InfectionCorePyramid extends Structure {
    public static final MapCodec<InfectionCorePyramid> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(settingsCodec(instance))
                    .apply(instance, InfectionCorePyramid::new));
    private static final Logger log = LoggerFactory.getLogger(InfectionCorePyramid.class);

    protected InfectionCorePyramid(StructureSettings settings) {
        super(settings);
    }

    @Override
    protected @NotNull Optional<GenerationStub> findGenerationPoint(@NotNull GenerationContext context) {
        ChunkPos pos = context.chunkPos();

        if (pos.x % 10000 == 0 && pos.z == pos.x) {
            Optional<GenerationStub> stub = Optional.of(
                    JigsawPlacement.addPieces(
                            context,

                    )
            );
        } else {
            return Optional.empty();
        }
    }

    @Override
    public @NotNull StructureType<?> type() {
        return StructureHelpers.INFECTION_CORE_PYRAMID.get();
    }
}
