package com.Logaaan.AetherGen;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    static FileConfiguration config;

    static int    aether_height;
    static double aether_spawn_rate;
    static double aether_frequency;
    static double aether_amplitude;
    static double aether_scale;
    static int    aether_octaves;

    static int    coal_chance;
    static int    coal_size;
    static int    iron_chance;
    static int    iron_size;
    static int    gold_chance;
    static int    gold_size;
    static int    lapis_chance;
    static int    lapis_size;
    static int    redstone_chance;
    static int    redstone_size;
    static int    diamond_chance;
    static int    diamond_size;
    static int    emerald_chance;
    static int    emerald_size;
    static int    dirt_chance;
    static int    dirt_size;
    static int    gravel_chance;
    static int    gravel_size;

    static void load_config(FileConfiguration conf) {
        config = conf;

        aether_height     =    conf.getInt("aether_height");
        aether_spawn_rate = conf.getDouble("aether_spawn_rate");
        aether_frequency  = conf.getDouble("aether_frequency");
        aether_amplitude  = conf.getDouble("aether_amplitude");
        aether_scale      = conf.getDouble("aether_scale");
        aether_octaves    =    conf.getInt("aether_octaves");

        coal_chance       = conf.getInt("coal_chance");
        coal_size         = conf.getInt("coal_size");
        iron_chance       = conf.getInt("iron_chance");
        iron_size         = conf.getInt("iron_size");
        gold_chance       = conf.getInt("gold_chance");
        gold_size         = conf.getInt("gold_size");
        lapis_chance      = conf.getInt("lapis_chance");
        lapis_size        = conf.getInt("lapis_size");
        redstone_chance   = conf.getInt("redstone_chance");
        redstone_size     = conf.getInt("redstone_size");
        diamond_chance    = conf.getInt("diamond_chance");
        diamond_size      = conf.getInt("diamond_size");
        emerald_chance    = conf.getInt("emerald_chance");
        emerald_size      = conf.getInt("emerald_size");
        dirt_chance       = conf.getInt("dirt_chance");
        dirt_size         = conf.getInt("dirt_size");
        gravel_chance     = conf.getInt("gravel_chance");
        gravel_size       = conf.getInt("gravel_size");
    }


}
