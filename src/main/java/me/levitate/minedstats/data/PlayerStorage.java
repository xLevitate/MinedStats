package me.levitate.minedstats.data;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.levitate.minedstats.manager.BlockManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class PlayerStorage {
    private final Plugin plugin;
    private final BlockManager blockManager;
    private final File dataFile;
    private final Gson gson;

    public PlayerStorage(Plugin plugin, BlockManager blockManager) {
        this.plugin = plugin;
        this.blockManager = blockManager;

        if (!plugin.getDataFolder().exists())
            plugin.getDataFolder().mkdirs();

        this.dataFile = new File(this.plugin.getDataFolder(), "data.json");
        if (!dataFile.exists()) {
            try {
                dataFile.createNewFile();
            }
            catch (IOException e) {
                this.plugin.getLogger().severe(e.getMessage());
            }
        }

        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        this.loadData();

        // Save the data every few minutes.
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, this::saveData, 6000L, 6000L);
    }

    public void saveData() {
        try (FileWriter writer = new FileWriter(dataFile)) {
            gson.toJson(blockManager.blocksBroken, writer);
        }
        catch (IOException e) {
            this.plugin.getLogger().severe(e.getMessage());
        }
    }

    public void loadData() {
        if (!dataFile.exists()) {
            return;
        }

        try (FileReader reader = new FileReader(dataFile)) {
            final HashMap<UUID, Long> jsonData = gson.fromJson(reader, new TypeToken<HashMap<UUID, Long>>() {}.getType());
            if (jsonData == null || jsonData.isEmpty())
                return;

            blockManager.blocksBroken.clear();
            blockManager.blocksBroken.putAll(jsonData);
        }
        catch (IOException e) {
            this.plugin.getLogger().severe(e.getMessage());
        }
    }
}
