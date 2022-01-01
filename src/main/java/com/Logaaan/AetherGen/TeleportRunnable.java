package com.Logaaan.AetherGen;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

// This Runnable teleports players in aether worlds to "world" or the string provided in the constructor when they fall
// out of the aether.
public class TeleportRunnable extends BukkitRunnable implements Listener {
    private final ArrayList<World> aether_worlds = new ArrayList<>();
    private final String tp_world_name;
    private World tp_world;

    public TeleportRunnable() {
        this("world");
    }

    public TeleportRunnable(String tp) {
        tp_world_name = tp;
    }

    // Teleport players if beneath Y level
    private void check_players(World w) {
        for (Player p : w.getPlayers()) {
            if (p.getLocation().getY() < 70) {
                tp_player(p);
            }
        }
    }

    private void tp_player(Player p) {
        Location tp = p.getLocation();
        tp.setWorld(tp_world);
        tp.setY(tp_world.getMaxHeight());
        p.teleport(tp);
    }

    @Override
    public void run() {
        // This is to handle the case where the Runnable is run before the worlds are loaded,
        // but that doesn't seem to happen.
        if (tp_world == null) {
            return;
        }
        for (World world : aether_worlds) {
            check_players(world);
        }
    }

    @EventHandler
    private void world_load_handler(WorldLoadEvent event) {
        World world = event.getWorld();
        // Watch for tp world, and save it
        if (world.getName().equals(tp_world_name)) {
            tp_world = world;
        }
        // Make a list of aether worlds to run this Runnable on
        // This is maybe better than making an api call and iterating on each run()
        if (world.getGenerator() instanceof AetherGen) {
            aether_worlds.add(world);
        }
    }
}
