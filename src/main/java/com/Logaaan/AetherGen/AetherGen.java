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

public class AetherGen extends ChunkGenerator {
    @Override
    public @NotNull List<BlockPopulator> getDefaultPopulators(@NotNull World world) {
        return Arrays.asList(new OrePopulator(), new StonePopulator());
    }

    // Yeah, this basically puts all the world generation in a single function.
    // I could split it up by making an unordered dynamic queue thing indexed by chunk coordinates,
    // and filling that with the noise. That would be super complicated for no reason though.
    // TODO: * 7D can maybe be replaced by changing p.a
    @Override
    public void generateNoise(@NotNull WorldInfo worldInfo, @NotNull Random random, int chunkX, int chunkZ, @NotNull ChunkGenerator.ChunkData chunkData) {
        SimplexOctaveGenerator gen = new SimplexOctaveGenerator(new Random(worldInfo.getSeed()), Config.aether_octaves);
        SimplexOctaveGenerator lower_gen = new SimplexOctaveGenerator(new Random(worldInfo.getSeed() + 1), Config.aether_octaves);
        // This can potentially be eliminated by changing generator frequency
        gen.setScale(0.005);
        lower_gen.setScale(0.005);

        int worldBaseX = chunkX * 16;
        int worldBaseZ = chunkZ * 16;

        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                int worldX = worldBaseX + X;
                int worldZ = worldBaseZ + Z;

                double noise = gen.noise(worldX, worldZ, Config.aether_frequency, Config.aether_amplitude);
                int currentHeight = (int) (noise * 7D + Config.aether_height);
                // A small offset to the lower bound of islands, in order to make sure they are not perfectly mirrored.
                double offset_noise = lower_gen.noise(worldX, worldZ, Config.aether_frequency, Config.aether_amplitude);
                int lower_offset = (int) (offset_noise * 7D);
                int lower_y = (int) (Config.aether_height - (noise * 7D - 13D) + lower_offset);

                for (int Y = currentHeight; Y > lower_y; Y--) {
                    Biome biome = chunkData.getBiome(X, Y, Z);
                    if (noise > Config.aether_spawn_rate) {
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
                            chunkData.setBlock(X, Y, Z, Material.STONE);
                        }
                    }
                }
            }
        }
    }

    // Yeah, vanilla cave generation is cool, but ive seen random blocks as a result of biome interaction
    @Override
    public boolean shouldGenerateCaves() {
        return true;
    }
}
