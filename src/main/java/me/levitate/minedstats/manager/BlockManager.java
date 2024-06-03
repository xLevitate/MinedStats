package me.levitate.minedstats.manager;

import java.util.HashMap;
import java.util.UUID;

public class BlockManager {
    public HashMap<UUID, Long> blocksBroken;

    public BlockManager() {
        this.blocksBroken = new HashMap<>();
    }

    /**
     * Adds a new block to the blocks broken.
     * @param playerUUID The player to add the block for.
     */
    public void addBlock(UUID playerUUID) {
        final Long brokenValue = this.blocksBroken.getOrDefault(playerUUID, 0L);

        this.blocksBroken.put(playerUUID, brokenValue + 1);
    }

    /**
     * Gets the blocks broken of a player.
     * @param playerUUID The player's UUID.
     * @return The blocks broken or 0 if not found.
     */
    public Long getBlocksBroken(UUID playerUUID) {
        return this.blocksBroken.getOrDefault(playerUUID, 0L);
    }
}
