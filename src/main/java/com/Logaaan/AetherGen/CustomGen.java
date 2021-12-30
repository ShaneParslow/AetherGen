package com.Logaaan.AetherGen;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.generator.WorldInfo;
import org.bukkit.util.noise.SimplexOctaveGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CustomGen extends ChunkGenerator {
    public Main p;
    public String wn;

    public CustomGen(String wn, Main m) {
        p = m;
        this.wn = wn;
    }

    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        //return Arrays.asList((BlockPopulator)new TreePopulator(p), (BlockPopulator) new PopulatorCaves2(p), new PopulatorOre(p));
        return Arrays.asList((BlockPopulator) new PopulatorOre(p));
    }

    // Yeah, this basically puts all the world generation in a single function.
    // I could split it up by making an unordered dynamic queue thing indexed by chunk coords,
    // and filling that with the noise. That would be super complicated for no reason though.
    // TODO: add slight noise to lower island bound, so that it isn't an exact mirror
    // TODO: * 7D can maybe be replaced by changing p.a
    @Override
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
        SimplexOctaveGenerator gen = new SimplexOctaveGenerator(new Random(worldInfo.getSeed()), p.ao);
        SimplexOctaveGenerator lower_gen = new SimplexOctaveGenerator(new Random(worldInfo.getSeed()+1), p.ao);
        // This can potentially be eliminated by changing generator frequency
        gen.setScale(0.005);
        lower_gen.setScale(0.005);

        int worldBaseX = chunkX * 16;
        int worldBaseZ = chunkZ * 16;

        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                int worldX = worldBaseX + X;
                int worldZ = worldBaseZ + Z;

                double noise = gen.noise(worldX, worldZ, p.f, p.a);
                int currentHeight = (int) (noise * 7D + p.ah);
                // A small offset to the lower bound of islands, in order to make sure they are not perfectly mirrored.
                double offset_noise = lower_gen.noise(worldX, worldZ, p.f, p.a);
                int lower_offset = (int) (offset_noise * 7D);
                int lower_y = (int) (p.ah - (noise * 7D - 13D) + lower_offset);

                for (int Y = currentHeight; Y > lower_y; Y--) {
                    Biome biome = chunkData.getBiome(X, Y, Z);
                    if (noise > p.sp) {
                        // On top block
                        if (Y == currentHeight) {
                            if (biome.equals(Biome.DESERT)) {
                                chunkData.setBlock(X, Y, Z, Material.SAND);
                            } else {
                                chunkData.setBlock(X, Y, Z, Material.GRASS_BLOCK);
                            }
                        // On second to top block
                        } else if (Y == currentHeight - 1) {
                            chunkData.setBlock(X, Y, Z, Material.DIRT);
                        // On some other block, for loop ensures within sane y limits
                        } else {
                            chunkData.setBlock(X, Y, Z, random.nextBoolean() ? Material.COBBLESTONE : Material.STONE);
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean shouldGenerateCaves() {
        return true;
    }
}
