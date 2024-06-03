package me.levitate.minedstats.listener;

import me.levitate.minedstats.manager.BlockManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;

import java.util.UUID;

public class PlayerListener implements Listener {
    private final BlockManager blockManager;

    public PlayerListener(Plugin plugin, BlockManager blockManager) {
        this.blockManager = blockManager;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBlockBreak(BlockBreakEvent event) {
        final UUID playerUUID = event.getPlayer().getUniqueId();

        if (!event.isCancelled())
            blockManager.addBlock(playerUUID);
    }
}
