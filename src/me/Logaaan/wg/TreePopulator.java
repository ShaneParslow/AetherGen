package me.Logaaan.wg;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.Biome;
import org.bukkit.block.Chest;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Leaves;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class TreePopulator extends BlockPopulator {
	
	public Main p;
	
	public TreePopulator(Main m) {
		p = m;
	}

	public boolean stopwater = false;
	public int stopc = 0;
	@Override
	public void populate(World world, Random random, Chunk chunk) {
		// TODO Auto-generated method stub
		SimplexOctaveGenerator generator = new SimplexOctaveGenerator(new Random(world.getSeed()), 12);
		int cr= 0;
		if (world.getEnvironment().equals(Environment.NORMAL)) {
		if (chunk.getX() % 1 == 0 && chunk.getZ() % 1 == 0) {
		//if (chunk.getX() == 0) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int xx = chunk.getX() * 16 + x;
				int zz = chunk.getZ() * 16 + z;
				for (int y = p.ah + 20; y > 1; y--) {
					if (chunk.getBlock(x, y, z).getType().equals(Material.AIR)) {
						if (stopwater == true) {
							if (y <= p.ah -5) {
								stopc = 0;
								chunk.getBlock(x, y, z).setType(Material.STATIONARY_WATER);
							}
						}
					}
					if (chunk.getBlock(x, y, z).getType().equals(Material.MYCEL)) {
						if (new Random().nextInt(40) == 21) {
							world.generateTree(new Location(world,xx,y+1,zz), new Random().nextBoolean() ? TreeType.RED_MUSHROOM : TreeType.BROWN_MUSHROOM);
						}
						
						if (new Random().nextInt(20) == 10) {
							chunk.getBlock(x, y+1, z).setType(new Random().nextBoolean() ? Material.BROWN_MUSHROOM : Material.RED_MUSHROOM);
						}
						
					}
					if (chunk.getBlock(x, y, z).getType().equals(Material.GRASS)) {
						cr = y;
						if (world.getBiome(xx,zz).equals(Biome.RIVER) || world.getBiome(xx,zz).equals(Biome.FROZEN_RIVER)) {
							chunk.getBlock(x , y, z).setType(Material.AIR);
							chunk.getBlock(x , y-2, z).setType(Material.GRAVEL);
						}
						if (y >= p.ah-5) {
							if (stopwater == true) {
								stopc++;
								if (stopc >= 5000) {
									stopwater = false;
								}
							}
						}
						if (y == p.ah-5) {
							if (new Random().nextInt(100) < 3) {
								if (stopwater == false) {
									stopwater = true;
									stopc = 0;
								}
							}
						}
						if (stopwater == false) {
							if (new Random().nextInt(3) == 1) {
								chunk.getBlock(x , y+1, z).setTypeIdAndData(Material.LONG_GRASS.getId(), (byte) 1, false);
							}
							if (world.getBiome(xx,zz).equals(Biome.ICE_PLAINS) || world.getBiome(xx,zz).equals(Biome.ICE_MOUNTAINS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.REDWOOD);
								}
								chunk.getBlock(x , y+1, z).setTypeIdAndData(Material.SNOW.getId(), (byte) new Random().nextInt(4), false);
							}
							if (world.getBiome(xx,zz).equals(Biome.OCEAN) || world.getBiome(xx,zz).equals(Biome.DEEP_OCEAN)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.TALL_REDWOOD);
								}
								if (new Random().nextInt(25) < 2) {
									int r = new Random().nextInt(3);
									if (r == 1) {
										chunk.getBlock(x, y, z).setType(new Random().nextBoolean() ? Material.COBBLESTONE : Material.STONE);
									}
									if (r == 2) {
										chunk.getBlock(x, y + 1, z).setType(new Random().nextBoolean() ? Material.SNOW : Material.SNOW);
										
									}
									if (r == 3) {
										chunk.getBlock(x, y, z).setType(new Random().nextBoolean() ? Material.COBBLESTONE : Material.SANDSTONE);
										
									}
								}
							}
							if (world.getBiome(xx,zz).equals(Biome.EXTREME_HILLS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), new Random().nextBoolean() ? TreeType.TREE : TreeType.REDWOOD);
								}
							}
							if (world.getBiome(xx,zz).equals(Biome.SWAMPLAND) || world.getBiome(xx,zz).equals(Biome.SWAMPLAND_MOUNTAINS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.SWAMP);
								}
							}
							if (world.getBiome(xx,zz).equals(Biome.FOREST) || world.getBiome(xx,zz).equals(Biome.FOREST_HILLS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.TREE);
								}
								if (new Random().nextInt(300) == 1) {
									generateLog(world,xx,y+1,zz, new Random().nextBoolean());
								}
							}
							
							if (world.getBiome(xx,zz).equals(Biome.ROOFED_FOREST) || world.getBiome(xx,zz).equals(Biome.ROOFED_FOREST_MOUNTAINS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.BIG_TREE);
								}
							}
							
							if (world.getBiome(xx,zz).equals(Biome.SAVANNA_MOUNTAINS) || world.getBiome(xx,zz).equals(Biome.SAVANNA) || world.getBiome(xx,zz).equals(Biome.SAVANNA_PLATEAU) || world.getBiome(xx,zz).equals(Biome.SAVANNA_PLATEAU_MOUNTAINS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), new Random().nextBoolean() ? TreeType.ACACIA : TreeType.TREE);
								}
							}
							
							if (world.getBiome(xx,zz).equals(Biome.JUNGLE) || world.getBiome(xx,zz).equals(Biome.JUNGLE_HILLS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.SMALL_JUNGLE);
								}
								if (new Random().nextInt(400) == 11) {
									chunk.getBlock(x, y + 1, z).setType(Material.MOSSY_COBBLESTONE);
									chunk.getBlock(x, y + 2, z).setType(Material.MOSSY_COBBLESTONE);
									chunk.getBlock(x, y + 3, z).setType(Material.MOSSY_COBBLESTONE);chunk.getBlock(x, y + 1, z + 1).setType(Material.MOSSY_COBBLESTONE);chunk.getBlock(x, y + 1, z - 1).setType(Material.MOSSY_COBBLESTONE);
									chunk.getBlock(x + 1, y + 1, z).setType(Material.MOSSY_COBBLESTONE);chunk.getBlock(x - 1, y + 1, z).setType(Material.MOSSY_COBBLESTONE);
								}
							}
							if (world.getBiome(xx,zz).equals(Biome.JUNGLE_EDGE_MOUNTAINS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.JUNGLE);
								}
							}
							
							if (world.getBiome(xx,zz).equals(Biome.JUNGLE_EDGE) || world.getBiome(xx,zz).equals(Biome.JUNGLE_MOUNTAINS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.JUNGLE_BUSH);
								}
							}
							
							if (world.getBiome(xx,zz).equals(Biome.BIRCH_FOREST) || world.getBiome(xx,zz).equals(Biome.BIRCH_FOREST_HILLS)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.BIRCH);
								}
							}
							
							if (world.getBiome(xx,zz).equals(Biome.COLD_TAIGA_HILLS) || world.getBiome(xx,zz).equals(Biome.COLD_TAIGA)) {
								if (new Random().nextInt(32) == 21) {
									world.generateTree(new Location(world,xx,y+1,zz), TreeType.REDWOOD);
								}
							}
							
							if (world.getBiome(xx,zz).equals(Biome.PLAINS) || world.getBiome(xx,zz).equals(Biome.SUNFLOWER_PLAINS)) {
								if (new Random().nextInt(3) == 2) {
									chunk.getBlock(x, y+1, z).setTypeId(new Random().nextBoolean() ? Material.YELLOW_FLOWER.getId() :  Material.RED_ROSE.getId());
								}
								if (new Random().nextInt(500) == 324) {
									chunk.getBlock(x, y+1, z).setTypeId(Material.PUMPKIN.getId());
								}
								if (new Random().nextInt(100) == 20) {
									chunk.getBlock(x, y+1, z).setTypeId(Material.BROWN_MUSHROOM.getId());
								}
								if (new Random().nextInt(400) == 11) {
									chunk.getBlock(x, y + 1, z).setType(Material.STONE);
									chunk.getBlock(x, y + 2, z).setType(Material.STONE);
									chunk.getBlock(x, y + 3, z).setType(Material.STONE);chunk.getBlock(x, y + 1, z + 1).setType(Material.STONE);chunk.getBlock(x, y + 1, z - 1).setType(Material.STONE);
									chunk.getBlock(x + 1, y + 1, z).setType(Material.STONE);chunk.getBlock(x - 1, y + 1, z).setType(Material.STONE);
								}
								if (p.aether && p.goodies) {
								if (p.ah <= y) {
									if (new Random().nextInt(1200) == 271) {
										chunk.getBlock(x, y+1, z).setTypeId(Material.CHEST.getId());
										Chest c = (Chest) chunk.getBlock(x, y+1, z).getState();
										for (int i = 1; i  < new Random().nextInt(6); i++) {
											int rr = new Random().nextInt(428);
											c.getBlockInventory().addItem(new ItemStack(Material.getMaterial(rr)));
										}
									}
								}
								}

							}
							
						}  else {
							if (y <= 45) {
								chunk.getBlock(x, y, z).setType(Material.SAND);
														}
							}
					}
	                
					if (chunk.getBlock(x, y, z).getType().equals(Material.STONE)) {
						if (new Random().nextInt(700) < 3) {
							
						}
						if (new Random().nextInt(1500) < 3) {
							if (y <= 16) {
								chunk.getBlock(x , y, z).setType(Material.DIAMOND_ORE);
								chunk.getBlock(x, y, z + 1).setType(Material.DIAMOND_ORE);
							}
						}
						if (new Random().nextInt(500) < 4) {
							if (y <= 25) {
								chunk.getBlock(x , y, z).setType(Material.LAPIS_ORE);
								chunk.getBlock(x, y + 1, z+1).setType(Material.LAPIS_ORE);
							}
						}
						if (new Random().nextInt(700) < 1) {
							if (y <= 35) {
								chunk.getBlock(x, y, z).setType(Material.COAL_ORE);
								chunk.getBlock(x, y, z + 1).setType(Material.COAL_ORE);
								chunk.getBlock(x , y, z).setType(Material.COAL_ORE);
								chunk.getBlock(x, y + 1, z + 1).setType(Material.COAL_ORE);
								chunk.getBlock(x, y - 1, z + 1).setType(Material.COAL_ORE);
								chunk.getBlock(x , y + 1, z - 1).setType(Material.COAL_ORE);
								chunk.getBlock(x , y + 2, z + 1).setType(Material.COAL_ORE);
								chunk.getBlock(x , y , z - 1).setType(Material.COAL_ORE);
								chunk.getBlock(x , y + 2, z).setType(Material.IRON_ORE);
							}
						}
						
						if (new Random().nextInt(1200) < 1) {
							if (y <= 32) {
								if (new Random().nextInt(3) == 1) {
									chunk.getBlock(x, y, z).setType(Material.IRON_ORE);
									chunk.getBlock(x, y, z + 1).setType(Material.IRON_ORE);
									chunk.getBlock(x , y, z).setType(Material.GOLD_ORE);
									chunk.getBlock(x, y + 1, z + 1).setType(Material.REDSTONE_ORE);
									chunk.getBlock(x, y - 1, z + 1).setType(Material.GOLD_ORE);
									chunk.getBlock(x  , y + 1, z - 1).setType(Material.COAL_ORE);
									chunk.getBlock(x , y + 2, z + 1).setType(Material.COAL_ORE);
									chunk.getBlock(x , y , z - 1).setType(Material.GOLD_ORE);
									chunk.getBlock(x , y + 2, z).setType(Material.IRON_ORE);
								}
								if (new Random().nextInt(3) == 2) {
									chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
									chunk.getBlock(x, y, z + 1).setType(Material.REDSTONE_ORE);
									chunk.getBlock(x , y, z).setType(Material.GOLD_ORE);
									chunk.getBlock(x, y + 1, z + 1).setType(Material.REDSTONE_ORE);
									chunk.getBlock(x, y - 1, z + 1).setType(Material.REDSTONE_ORE);
									chunk.getBlock(x , y + 1, z - 1).setType(Material.COAL_ORE);
									chunk.getBlock(x , y + 2, z + 1).setType(Material.COAL_ORE);
									chunk.getBlock(x , y , z - 1).setType(Material.GOLD_ORE);
									chunk.getBlock(x , y + 2, z).setType(Material.IRON_ORE);
								}
								if (new Random().nextInt(3) == 1) {
									chunk.getBlock(x, y + 1, z).setType(Material.IRON_ORE);
									chunk.getBlock(x, y, z - 1).setType(Material.IRON_ORE);
									chunk.getBlock(x , y+ 1, z).setType(Material.GOLD_ORE);
									chunk.getBlock(x, y - 1, z + 1).setType(Material.REDSTONE_ORE);
									chunk.getBlock(x, y  + 2, z + 1).setType(Material.GOLD_ORE);
									chunk.getBlock(x , y + 1, z - 1).setType(Material.COAL_ORE);
									chunk.getBlock(x , y + 2, z + 1).setType(Material.COAL_ORE);
									chunk.getBlock(x , y , z - 1).setType(Material.GOLD_ORE);
									chunk.getBlock(x , y + 2, z).setType(Material.IRON_ORE);
								}
							}
						}
					}
				}
			}
		}
	}
		}
		//}
		
		if (world.getEnvironment().equals(Environment.NETHER)) {
		if (chunk.getX() % 3 == 0 && chunk.getZ() % 3 == 0) {
		for (int x = 0; x < 16; x++) {
			for (int z = 0; z < 16; z++) {
				int xx = chunk.getX() * 16 + x;
				int zz = chunk.getZ() * 16 + z;
				for (int y = 256; y > 1; y--) {
					if (chunk.getBlock(x, y, z).getType().equals(Material.AIR)) {
						if (stopwater == true) {
							if (y <= 45) {
								stopc = 0;
								chunk.getBlock(x, y, z).setType(Material.STATIONARY_LAVA);
							}
						}
					}
					if (chunk.getBlock(x, y, z).getType().equals(Material.NETHERRACK)) {
						if (y >= 45) {
							if (stopwater == true) {
								stopc++;
								if (stopc >= 200) {
									stopwater = false;
								}
							}
						}
						if (y == 45) {
							if (new Random().nextInt(100) < 3) {
								if (stopwater == false) {
									stopwater = true;
									stopc = 0;
								}
							}
						}
						if (stopwater == false) {
							if (new Random().nextInt(3) == 1) {
								chunk.getBlock(x, y+1, z).setType(Material.FIRE);
							}
						}
					}
				}
			}
		}
	}
		}
	}

	
	void generateLog(World w, int x, int y, int z, boolean shroom) {
		int dir = new Random().nextInt(2);
		if (dir == 1) {
			for (int i = 0; i < 4; i++) {
				if (w.getBlockAt(new Location(w,x,y-1,z+i)).getType().equals(Material.AIR)) {
					break;
				} else {
					w.getBlockAt(new Location(w,x,y,z+i)).setType(Material.LOG);
					if (shroom) {
						if (new Random().nextInt(5) == 3) {
							w.getBlockAt(new Location(w,x,y,z+i)).setType(new Random().nextBoolean() ? Material.BROWN_MUSHROOM : Material.RED_MUSHROOM);
						}
					}
				}
			}
		}
		
		if (dir == 1) {
			for (int i = 0; i < 4; i++) {
				if (w.getBlockAt(new Location(w,x+i,y-1,z)).getType().equals(Material.AIR)) {
					break;
				} else {
					w.getBlockAt(new Location(w,x+i,y,z)).setType(Material.LOG);
					if (shroom) {
						if (new Random().nextInt(5) == 3) {
							w.getBlockAt(new Location(w,x+1,y,z)).setType(new Random().nextBoolean() ? Material.BROWN_MUSHROOM : Material.RED_MUSHROOM);
						}
					}
				}
			}
		}
	}
}
