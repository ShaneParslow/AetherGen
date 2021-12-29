package com.Logaaan.AetherGen;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CustomGen extends ChunkGenerator {
    public Main p;
    public String wn;
    SimplexOctaveGenerator generator_float;
    private boolean i = false;

    public CustomGen(String wn, Main m) {
        p = m;
        this.wn = wn;
    }

    private void init(World world) {
        // Is this unique for each world created?
        // In other words, is a CustomGen created for each world created, or only on first startup?
        generator_float = new SimplexOctaveGenerator(world, p.ao);
        generator_float.setScale(0.005);
        i = true;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        //return Arrays.asList((BlockPopulator)new TreePopulator(p), (BlockPopulator) new PopulatorCaves2(p), new PopulatorOre(p));
        return Arrays.asList((BlockPopulator) new PopulatorOre(p));
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int chunkX, int chunkZ, BiomeGrid biome) {
        if (!i) {
            init(world);
        }

        // World coordinate of the 0,0 block in the chunk being generated
        int worldBaseX = chunkX * 16;
        int worldBaseZ = chunkZ * 16;

        ChunkData chunk = createChunkData(world);

        for (int X = 0; X < 16; X++) {
            for (int Z = 0; Z < 16; Z++) {
                // World coordinate of current block
                int worldX = worldBaseX + X;
                int worldZ = worldBaseZ + Z;

                Biome b = world.getBiome(worldX, worldZ);

                double noise = generator_float.noise(worldX, worldZ, p.f, p.a);
                int currentHeight = (int) (noise * 7D + p.ah);

                for (int Y = currentHeight; Y > p.ah - (noise * 7D - 13D); Y--) {
                    if (noise > p.sp) {
                        // On top block
                        if (Y == currentHeight) {
                            if (world.getBiome(worldX, worldZ).equals(Biome.DESERT)) {
                                chunk.setBlock(X, Y, Z, Material.SAND);
                            } else {
                                chunk.setBlock(X, Y, Z, Material.GRASS_BLOCK);

                            }
                        // On second to top block
                        } else if (Y == currentHeight - 1) {
                            chunk.setBlock(X, Y, Z, Material.DIRT);
                        // On some other block, for loop ensures within sane y limits
                        } else {
                            chunk.setBlock(X, Y, Z, new Random().nextBoolean() ? Material.COBBLESTONE : Material.STONE);
                        }
                    }
                }
            }
        }
        return chunk;
    }
}
