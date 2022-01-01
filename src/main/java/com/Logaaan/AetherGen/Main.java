package com.Logaaan.AetherGen;

import org.bukkit.event.Listener;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class Main extends JavaPlugin implements Listener {

    public void onEnable() {
        Config.load_config(this.getConfig());

        // Timer to teleport players who drop out of aether
		new TeleportRunnable().runTaskTimer(this, 10L, 10L);
    }

    public void onDisable() {

    }

    @Override
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, String id) {
        return new AetherGen();
    }
}
