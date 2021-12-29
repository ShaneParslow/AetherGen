package com.Logaaan.AetherGen;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CustomGen extends ChunkGenerator {
    public Main p;
    public String wn;
    int currentHeight = 50;
    SimplexOctaveGenerator generator_float;
    SimplexOctaveGenerator generator_float_2;
    private boolean i = false;

    public CustomGen(String wn, Main m) {
        p = m;
        this.wn = wn;
    }

    private void init(World world) {
        generator_float = new SimplexOctaveGenerator(world, p.ao);
        generator_float.setScale(0.005);

        generator_float_2 = new SimplexOctaveGenerator(new Random(world.getSeed()), p.ao2);
        generator_float_2.setScale(p.sa2);
        i = true;
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        //return Arrays.asList((BlockPopulator)new TreePopulator(p), (BlockPopulator) new PopulatorCaves2(p), new PopulatorOre(p));
        return Collections.emptyList();
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

        if (world.getEnvironment().equals(Environment.NORMAL)) {
            for (int X = 0; X < 16; X++) {
                for (int Z = 0; Z < 16; Z++) {
                    for (int Y = chunk.getMinHeight(); Y <= chunk.getMaxHeight(); Y++) {
                        // World coordinate of current block
                        int worldX = worldBaseX + X;
                        int worldZ = worldBaseZ + Z;

                        double noise = generator_float.noise(worldX, worldZ, Y, p.f, p.a, true);

                        if (noise > 0.3) {
                            chunk.setBlock(X, Y, Z, Material.COBBLESTONE);
                        }
                    }
                }
            }
        }

        if (world.getEnvironment().equals(Environment.NETHER)) {
            if (chunkX % 1 == 0 && chunkZ % 1 == 0) {
                for (int X = 0; X < 16; X++) {
                    for (int Z = 0; Z < 16; Z++) {
                        currentHeight = (int) (generator_float.noise(chunkX * 16 + X, chunkZ * 16 + Z, 1.4D, 0.95D) * 7D + 50D);
                        chunk.setBlock(X, currentHeight, Z, Material.NETHERRACK);
                        chunk.setBlock(X, currentHeight - 1, Z, Material.NETHERRACK);
                        for (int i = currentHeight - 2; i > 0; i--)
                            chunk.setBlock(X, i, Z, new Random().nextBoolean() ? Material.NETHERRACK : new Random().nextBoolean() ? Material.SOUL_SAND : Material.GRAVEL);
                        chunk.setBlock(X, 0, Z, Material.BEDROCK);
                    }
                }
            }

        }
        return chunk;
    }
}
