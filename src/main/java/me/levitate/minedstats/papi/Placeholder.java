package me.levitate.minedstats.papi;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.levitate.minedstats.manager.BlockManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class Placeholder extends PlaceholderExpansion {
    private final BlockManager blockManager;

    public Placeholder(BlockManager blockManager) {
        this.blockManager = blockManager;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "minedstats";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Levitate";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, String params) {
        if(params.equalsIgnoreCase("broken")) {
            if (offlinePlayer != null) {
                Player player = (Player) offlinePlayer;

                return String.valueOf(blockManager.getBlocksBroken(player.getUniqueId()));
            }
        }

        return null;
    }
}
