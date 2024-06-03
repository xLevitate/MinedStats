package me.levitate.minedstats;

import me.levitate.minedstats.data.PlayerStorage;
import me.levitate.minedstats.listener.PlayerListener;
import me.levitate.minedstats.manager.BlockManager;
import me.levitate.minedstats.papi.Placeholder;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinedStats extends JavaPlugin {
    private PlayerStorage playerStorage;
    private Placeholder placeholder;

    @Override
    public void onEnable() {
        final BlockManager blockManager = new BlockManager();
        this.playerStorage = new PlayerStorage(this, blockManager);

        this.placeholder = new Placeholder(blockManager);
        placeholder.register();

        new PlayerListener(this, blockManager);
    }

    @Override
    public void onDisable() {
        this.playerStorage.saveData();

        if (placeholder != null)
            placeholder.unregister();
    }
}
