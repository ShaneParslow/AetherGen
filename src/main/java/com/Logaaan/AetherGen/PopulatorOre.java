package com.Logaaan.AetherGen;

import org.bukkit.Material;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class PopulatorOre extends BlockPopulator {

    Main p;

    boolean coal;
    boolean iron;
    boolean gold;
    boolean dia;
    boolean red;
    boolean lapis;
    boolean dirt;
    boolean gravel;
    boolean emerald;


    int coalchance;
    int coalsize;
    int ironchance;
    int ironsize;
    int goldchance;
    int goldsize;
    int emeraldchance;
    int emeraldsize;
    int lapischance;
    int lapissize;
    int diachance;
    int diasize;
    int redstonechance;
    int redsize;

    int dirtchance;
    int dirtsize;
    int gravelchance;
    int gravelsize;

    WorldInfo worldinfo;
    Random rand;
    LimitedRegion region;

    public PopulatorOre(Main p) {
        this.p = p;
        coal = p.coal;
        iron = p.iron;
        gold = p.gold;
        dia = p.dia;
        red = p.red;
        lapis = p.lapis;
        dirt = p.dirt;
        gravel = p.gravel;
        emerald = p.eme;


        coalchance = p.coalc;
        coalsize = p.coals;
        ironchance = p.ironc;
        ironsize = p.irons;
        goldchance = p.goldc;
        goldsize = p.golds;
        emeraldchance = p.emec;
        emeraldsize = p.emes;
        lapischance = p.lapisc;
        lapissize = p.lapiss;
        diachance = p.diac;
        diasize = p.dias;
        redstonechance = p.redc;
        redsize = p.reds;

        dirtchance = p.dirtc;
        dirtsize = p.dirts;
        gravelchance = p.gravelc;
        gravelsize = p.gravels;
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

                for (int Y = worldinfo.getMaxHeight() - 20; Y >= worldinfo.getMinHeight() + 20; Y--) {
                    if (is_stone(worldX, Y, worldZ)) {
                        if (rand.nextInt(coalchance) <= 2) {
                            spawn_ore_vein(Material.COAL_ORE, coalsize, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(ironchance) <= 1) {
                            spawn_ore_vein(Material.IRON_ORE, ironsize, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(goldchance) <= 1) {
                            spawn_ore_vein(Material.GOLD_ORE, goldsize, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(emeraldchance) <= 1) {
                            spawn_ore_vein(Material.EMERALD_ORE, emeraldsize, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(redstonechance) <= 1) {
                            spawn_ore_vein(Material.REDSTONE_ORE, redsize, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(diachance) <= 1) {
                            spawn_ore_vein(Material.DIAMOND_ORE, diasize, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(lapischance) <= 1) {
                            spawn_ore_vein(Material.LAPIS_ORE, lapissize, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(dirtchance) <= 1) {
                            spawn_ore_vein(Material.DIRT, dirtsize, worldX, Y, worldZ);
                        }
                        if (rand.nextInt(gravelchance) <= 1) {
                            spawn_ore_vein(Material.GRAVEL, gravelsize, worldX, Y, worldZ);
                        }
                    }
                }
            }
        }
    }
}
