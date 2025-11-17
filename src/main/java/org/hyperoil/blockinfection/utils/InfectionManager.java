package org.hyperoil.blockinfection.utils;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.saveddata.SavedDataType;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.fml.common.EventBusSubscriber;
import org.hyperoil.blockinfection.hyperoil;

import java.nio.file.Path;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@EventBusSubscriber(modid = hyperoil.MODID)
public class InfectionManager extends SavedData {
    public static final Codec<InfectionManager> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.unboundedMap(Codec.STRING.xmap(
                            s -> {
                                String[] coords = s.split(",");
                                return new BlockPos(
                                        Integer.parseInt(coords[0]),
                                        Integer.parseInt(coords[1]),
                                        Integer.parseInt(coords[2])
                                );
                            }, pos -> {
                                return pos.getX() + "," + pos.getY() + "," + pos.getZ();
                            }
                            ), Codec.INT)
                            .fieldOf("idAndCoreBlock")
                            .forGetter(inst -> inst.idAndCoreBlock),
                    Codec.BOOL
                            .fieldOf("isInfectionAlive")
                            .forGetter(inst -> inst.isInfectionAlive)
            ).apply(instance, InfectionManager::new)
    );
    public static final SavedDataType<InfectionManager> TYPE =
            new SavedDataType<>(hyperoil.MODID, InfectionManager::new, InfectionManager.CODEC);
    public final int DEFAULT_INFECTION_ID = -1;

    private static ConcurrentHashMap<ServerLevel, InfectionManager> instances =
            new ConcurrentHashMap<>();

    private ConcurrentHashMap<BlockPos, Integer> idAndCoreBlock = new ConcurrentHashMap<>();
    private MinecraftServer server = null;
    private boolean isInfectionAlive;

    private InfectionManager(Map<BlockPos, Integer> map, boolean infectionAlive) {
        idAndCoreBlock.putAll(map);
        this.isInfectionAlive = infectionAlive;
    }
    private InfectionManager() {}

    public static InfectionManager getInstanceForLevel(ServerLevel level) {
        return instances.computeIfAbsent(level, lvl ->
                lvl.getDataStorage().computeIfAbsent(TYPE));
    }

    public boolean isInfectionStillActive(Integer integer) {
        return idAndCoreBlock.containsValue(integer);
    }
    public boolean isInfectionStillActive() {
        return isInfectionAlive;
    }
    public void killInfection(BlockPos pos) {
        idAndCoreBlock.remove(pos);
        setDirty();
        if (idAndCoreBlock.isEmpty()) isInfectionAlive = false;
    }
    public int addInfection(BlockPos pos) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int intToUse = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        while (intToUse == DEFAULT_INFECTION_ID || idAndCoreBlock.containsValue(intToUse)) {
            intToUse = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        idAndCoreBlock.put(pos, intToUse);
        setDirty();
        isInfectionAlive = true;

        return intToUse;
    }
    private Path getSaveFile() {
        return server.getWorldPath(LevelResource.ROOT).resolve("data/infectionData.json");
    }
}
