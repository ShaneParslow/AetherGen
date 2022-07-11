package com.Logaaan.AetherGen;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;

// This Runnable teleports entities in aether worlds to "world" or the string provided in the constructor when they fall
// out of the aether.
public class TeleportRunnable extends BukkitRunnable implements Listener {
    private final String tp_world_name;
    private World tp_world;

    public TeleportRunnable() {
        this("world");
    }

    public TeleportRunnable(String tp) {
        tp_world_name = tp;
    }

    // Teleport players if beneath Y level
    private void check_entities(World w) {
        if (w == null) {
            return;
        }
        for (Entity e : w.getEntities()) {
            if (e.getLocation().getY() < 70) {
                tp_entity(e);
            }
        }
    }

    private void tp_entity(Entity e) {
        Location tp = e.getLocation();
        tp.setWorld(tp_world);
        tp.setY(tp_world.getMaxHeight());
        e.teleport(tp);
    }

    @Override
    public void run() {
        for (World world : Bukkit.getWorlds()) {
            // Keep an eye out for the world we want to tp to
            // We don't know exactly when the world will become available
            if (tp_world == null && world.getName().equals(tp_world_name)) {
                tp_world = world;
            }
            if (world.getGenerator() instanceof AetherGen) {
                check_entities(world);
            }
        }
    }
}
