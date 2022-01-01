package com.Logaaan.AetherGen;

import org.bukkit.Material;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.LimitedRegion;
import org.bukkit.generator.WorldInfo;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

// I think that all of this can be put into OrePopulator
public class StonePopulator extends BlockPopulator {
    @Override
    public void populate(@NotNull WorldInfo w_info, @NotNull Random r, int chunkX, int chunkZ, @NotNull LimitedRegion lr) {
        int worldBaseX = chunkX * 16;
        int worldBaseZ = chunkZ * 16;
        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                int worldX = worldBaseX + X;
                int worldZ = worldBaseZ + Z;
                for (int Y = w_info.getMaxHeight() - 20; Y >= w_info.getMinHeight() + 20; Y--) {
                    if (lr.getType(worldX, Y, worldZ).equals(Material.STONE)) {
                        if (r.nextBoolean()) {
                            lr.setType(worldX, Y, worldZ, Material.COBBLESTONE);
                        }
                    }
                }
            }
        }
    }
}
