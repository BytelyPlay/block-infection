package org.hyperoil.blockinfection.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.LevelResource;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.server.ServerStartedEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.neoforge.event.server.ServerStoppingEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;
import org.hyperoil.blockinfection.hyperoil;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

@EventBusSubscriber(modid = hyperoil.MODID)
public class InfectionManager {
    public static final int DEFAULT_INFECTION_ID = -1;

    private static int tickCounter = 0;
    private static ConcurrentHashMap<BlockPos, Integer> integerAndCoreBlock = new ConcurrentHashMap<>();
    public static boolean dirty = false;
    private static MinecraftServer server = null;
    private static boolean isInfectionAlive;

    public static boolean isInfectionStillActive(Integer integer) {
        return integerAndCoreBlock.containsValue(integer);
    }
    public static boolean isInfectionStillActive() {
        return isInfectionAlive;
    }
    public static void killInfection(BlockPos pos) {
        integerAndCoreBlock.remove(pos);
        dirty = true;
        if (integerAndCoreBlock.isEmpty()) isInfectionAlive = false;
    }
    public static int addInfection(BlockPos pos) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int intToUse = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        while (intToUse == DEFAULT_INFECTION_ID || integerAndCoreBlock.containsValue(intToUse)) {
            intToUse = random.nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        integerAndCoreBlock.put(pos, intToUse);
        dirty = true;
        isInfectionAlive = true;

        return intToUse;
    }
    private static void init() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            integerAndCoreBlock = mapper.readValue(new FileReader(getSaveFile().toString()), new TypeReference<>() {});
        } catch (FileNotFoundException ignored) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void saveData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Path saveFile = getSaveFile();
            if (!Files.exists(saveFile)) Files.createFile(saveFile);
            mapper.writeValue(new FileWriter(saveFile.toString()), integerAndCoreBlock);
            dirty = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @SubscribeEvent
    public static void serverStopping(ServerStoppingEvent event) {
        saveData();
    }
    @SubscribeEvent
    public static void tick(ServerTickEvent.Post e) {
        if (tickCounter >= 300) {
            if (dirty) new Thread(InfectionManager::saveData).start();
            tickCounter = 0;
        } else {
            tickCounter++;
        }
    }
    @SubscribeEvent
    public static void serverStarting(ServerStartedEvent event) {
        server = event.getServer();
        init();
    }
    private static Path getSaveFile() {
        return server.getWorldPath(LevelResource.ROOT).resolve("data/infectionData.json");
    }
}
