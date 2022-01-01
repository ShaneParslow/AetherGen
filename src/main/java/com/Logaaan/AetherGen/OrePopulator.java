package com.Logaaan.AetherGen;

import org.bukkit.Material;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class OrePopulator extends BlockPopulator {

    WorldInfo worldinfo;
    Random rand;
    LimitedRegion region;

    public OrePopulator() {
    }

    private boolean is_stone(int x, int y, int z) {
        Material mat = region.getType(x, y, z);
        return mat == Material.STONE || mat == Material.COBBLESTONE;
    }

    private void spawn_ore_vein(Material ore, int size, int x, int y, int z) {
        region.setType(x, y, z, ore);
        for (int i = size; i > 0; i--) {
            int rx = rand.nextInt(2) - 1;
            int rz = rand.nextInt(2) - 1;
            int ry = rand.nextInt(2) - 1;
            if (is_stone(x + rx, y + ry, z + rz)) {
                region.setType(x + rx, y + ry, z + rz, ore);
            }
        }
    }

    @Override
    public void populate(@NotNull WorldInfo w, @NotNull Random r, int chunkX, int chunkZ, @NotNull LimitedRegion lr) {
        // Bounds are not currently checked with lr. This could be a problem. It probably isn't.
        worldinfo = w;
        rand = r;
        region = lr;

        int worldBaseX = chunkX * 16;
        int worldBaseZ = chunkZ * 16;
        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                int worldX = worldBaseX + X;
                int worldZ = worldBaseZ + Z;

                // THESE BOUNDS ARE A HACK
                for (int Y = worldinfo.getMaxHeight() - 20; Y >= worldinfo.getMinHeight() + 20; Y--) {
                    if (is_stone(worldX, Y, worldZ)) {
                        if (rand.nextInt(Config.coal_chance) == 1) {
                            spawn_ore_vein(Material.COAL_ORE, Config.coal_size, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(Config.iron_chance) == 1) {
                            spawn_ore_vein(Material.IRON_ORE, Config.iron_size, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(Config.gold_chance) == 1) {
                            spawn_ore_vein(Material.GOLD_ORE, Config.gold_size, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(Config.lapis_chance) == 1) {
                            spawn_ore_vein(Material.LAPIS_ORE, Config.lapis_size, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(Config.redstone_chance) == 1) {
                            spawn_ore_vein(Material.REDSTONE_ORE, Config.redstone_size, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(Config.diamond_chance) == 1) {
                            spawn_ore_vein(Material.DIAMOND_ORE, Config.diamond_size, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(Config.emerald_chance) == 1) {
                            spawn_ore_vein(Material.EMERALD_ORE, Config.emerald_size, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(Config.dirt_chance) == 1) {
                            spawn_ore_vein(Material.DIRT, Config.dirt_size, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(Config.gravel_chance) == 1) {
                            spawn_ore_vein(Material.GRAVEL, Config.gravel_size, worldX, Y, worldZ);
                        }
                    }
                }
            }
        }
    }
}
