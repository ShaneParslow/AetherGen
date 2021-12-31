package com.Logaaan.AetherGen;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class TeleportRunnable  extends BukkitRunnable {
    private World tp_world;

    public TeleportRunnable() {
        // As much as I would love for this to work, this gets called before worlds are loaded.
        // Can change tick offset in main to call this later
        // TODO: assuming this isn't null is reckless
        tp_world = Bukkit.getWorld("world");
    }

    private void check_players(World w) {
        for(Player p: w.getPlayers()) {
            if(p.getLocation().getY() < 70) {
                tp_player(p);
            }
        }
    }

    private void tp_player(Player p) {
        Location tp = p.getLocation();
        tp.setWorld(this.tp_world);
        tp.setY(tp_world.getMaxHeight());
        p.teleport(tp);
    }

    @Override
    public void run() {
        tp_world = Bukkit.getWorld("world");
        // This could probably be called once to generate an initial list, then again only when a new aether gen is done.
        for (World world: Bukkit.getWorlds()) {
            if(world.getGenerator() instanceof AetherGen) {
                check_players(world);
            }
        }
    }
}
