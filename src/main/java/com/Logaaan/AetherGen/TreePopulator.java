package me.Logaaan.wg;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.World.Environment;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.type.Snow;
import org.bukkit.block.Biome;
import org.bukkit.block.Chest;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.inventory.ItemStack;
import org.bukkit.material.Leaves;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.noise.SimplexOctaveGenerator;

import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.session.ClipboardHolder;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.math.transform.AffineTransform;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.function.operation.Operation;

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
					int ii = p.ah;
					if (new Random().nextInt(34000*p.dung) < 2) {
						if (chunk.getBlock(x, ii, z).getType().equals(Material.AIR)) {
							paste(world,xx,ii,zz,"dungeon1");
						}
					}
					for (int y = world.getHighestBlockYAt(xx,zz) + 1; y > 1; y--) {
						if (chunk.getBlock(x, y, z).getType().equals(Material.MYCELIUM)) {
							if (new Random().nextInt(40) == 21) {
								world.generateTree(new Location(world,xx,y+1,zz), new Random().nextBoolean() ? TreeType.RED_MUSHROOM : TreeType.BROWN_MUSHROOM);
							}
							
							if (new Random().nextInt(20) == 10) {
								chunk.getBlock(x, y+1, z).setType(new Random().nextBoolean() ? Material.BROWN_MUSHROOM : Material.RED_MUSHROOM);
							}
							
						}
						if (chunk.getBlock(x, y, z).getType().equals(Material.GRASS) || chunk.getBlock(x, y, z).getType().equals(Material.SAND)) {
							cr = y;
							if (world.getBiome(xx,zz).equals(Biome.RIVER) || world.getBiome(xx,zz).equals(Biome.FROZEN_RIVER)) {
								chunk.getBlock(x , y, z).setType(Material.AIR);
								chunk.getBlock(x , y-2, z).setType(Material.GRAVEL);
							}
							if (stopwater == false) {
								if (new Random().nextInt(3) == 1) {
									// Was GRASS data 0x1; fern?
									chunk.getBlock(x , y+1, z).setType(Material.FERN);
								}
								if (world.getBiome(xx,zz).equals(Biome.SNOWY_PLAINS)) {
									if (new Random().nextInt(p.treechance + 20) == 21) {
										final int yy = y;
										int pine = new Random().nextInt(p.pinecount-1)+1;
										paste(world,xx,y,zz,"pine"+pine);
									}
									BlockState state = chunk.getBlock(x , y+1, z).getState();
									state.setType(Material.SNOW);
									Snow data = (Snow) Material.SNOW.createBlockData();
									data.setLayers(new Random().nextInt(4));
									state.setBlockData(data);
									state.update();
								}
								if (world.getBiome(xx,zz).equals(Biome.OCEAN) || world.getBiome(xx,zz).equals(Biome.DEEP_OCEAN)) {
									if (new Random().nextInt(p.treechance + 20) == 21) {
										final int yy = y;
										int pine = new Random().nextInt(p.pinecount-1)+1;
										paste(world,xx,y,zz,"pine"+pine);
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
								if (world.getBiome(xx,zz).equals(Biome.BADLANDS) || world.getBiome(xx, zz).equals(Biome.ERODED_BADLANDS) || world.getBiome(xx, zz).equals(Biome.WOODED_BADLANDS)) {
									
									if (new Random().nextInt(p.bigtree - 100) == 51) {
										int big = new Random().nextInt(1)+1;
										paste(world,xx,y-1,zz,"bigtree"+big);
									}
								}
								if (world.getBiome(xx,zz).equals(Biome.WINDSWEPT_HILLS) || world.getBiome(xx, zz).equals(Biome.WINDSWEPT_GRAVELLY_HILLS) || world.getBiome(xx,zz).equals(Biome.WINDSWEPT_FOREST)) {
									if (new Random().nextInt(p.treechance + 10) == 21) {
										if (new Random().nextBoolean()) {
											final int yy = y;
											int pine = new Random().nextInt(p.normalcount-1)+1;
											paste(world,xx,y,zz,"normal"+pine);
										} else {
											final int yy = y;
											int pine = new Random().nextInt(p.pinecount-1)+1;
											paste(world,xx,y,zz,"pine"+pine);
										}
									}
									if (new Random().nextInt(p.bigtree + 50) == 51) {
										int big = new Random().nextInt(1)+1;
										paste(world,xx,y-1,zz,"bigtree"+big);
									}
								}
								if (world.getBiome(xx,zz).equals(Biome.SWAMP)) {
									if (new Random().nextInt(p.treechance + 35) == 21) {
										final int yy = y;
												int pine = new Random().nextInt(p.normalcount-1)+1;
												paste(world,xx,y,zz,"normal"+pine);

									}
								}
								if (world.getBiome(xx,zz).equals(Biome.DESERT)) {
									if (new Random().nextInt(p.treechance + 54) == 21) {
										final int yy = y;
										int pine = new Random().nextInt(3)+1;
										paste(world,xx,y,zz,"deadwood");

									}
								}
								if (world.getBiome(xx,zz).equals(Biome.FOREST)) {
									if (new Random().nextInt(p.treechance + 4) == 21) {
										final int yy = y;
										int pine = new Random().nextInt(p.normalcount-1)+1;
										paste(world,xx,y,zz,"normal"+pine);

									}
									if (new Random().nextInt(300) == 1) {
										generateLog(world,xx,y+1,zz, new Random().nextBoolean());
									}
									if (new Random().nextInt(p.bigtree + 50) == 51) {
										int big = new Random().nextInt(1)+1;
										paste(world,xx,y-1,zz,"bigtree"+big);
									}
								}
								
								if (world.getBiome(xx,zz).equals(Biome.DARK_FOREST)) {
									if (new Random().nextInt(p.treechance + 20) == 21) {
										int pine = new Random().nextInt(p.normalcount)+1;
										paste(world,xx,y,zz,"normal"+pine);

									}
									if (new Random().nextInt(p.bigtree + 20) == 51) {
										int big = new Random().nextInt(1)+1;
										paste(world,xx,y-1,zz,"bigtree"+big);
									}
								}
								
								if (world.getBiome(xx,zz).equals(Biome.WINDSWEPT_SAVANNA) || world.getBiome(xx,zz).equals(Biome.SAVANNA) || world.getBiome(xx,zz).equals(Biome.SAVANNA_PLATEAU)) {
									if (new Random().nextInt(p.treechance + 80) == 21) {
										final int yy = y;
										int pine = new Random().nextInt(p.normalcount-1)+1;
										paste(world,xx,y,zz,"normal"+pine);

									}
									if (new Random().nextInt(p.bigtree + 20) == 51) {
										int big = new Random().nextInt(1)+1;
										paste(world,xx,y-1,zz,"bigtree"+big);
									}
								}
								
								if (world.getBiome(xx,zz).equals(Biome.JUNGLE)) {
									if (new Random().nextInt(p.treechance + 40) == 21) {
										if (new Random().nextBoolean()) {
										final int yy = y;
										int pine = new Random().nextInt(p.junglecount-1)+1;
										paste(world,xx,y,zz,"jungle"+pine);} else {

											int pine = new Random().nextInt(2)+1;
											paste(world,xx,y,zz,"bamboo"+pine);
										}
										if (new Random().nextInt(p.bigtree + 20) == 51) {
											int big = new Random().nextInt(1)+1;
											paste(world,xx,y-1,zz,"bigtree"+big);
										}

									}
									if (new Random().nextInt(400) == 11) {
										chunk.getBlock(x, y + 1, z).setType(Material.MOSSY_COBBLESTONE);
										chunk.getBlock(x, y + 2, z).setType(Material.MOSSY_COBBLESTONE);
										chunk.getBlock(x, y + 3, z).setType(Material.MOSSY_COBBLESTONE);chunk.getBlock(x, y + 1, z + 1).setType(Material.MOSSY_COBBLESTONE);chunk.getBlock(x, y + 1, z - 1).setType(Material.MOSSY_COBBLESTONE);
										chunk.getBlock(x + 1, y + 1, z).setType(Material.MOSSY_COBBLESTONE);chunk.getBlock(x - 1, y + 1, z).setType(Material.MOSSY_COBBLESTONE);
									}
								}
								if (world.getBiome(xx,zz).equals(Biome.BAMBOO_JUNGLE)) {
									if (new Random().nextInt(p.treechance + 20) == 21) {
										final int yy = y;
										int pine = new Random().nextInt(p.junglecount-1)+1;
										paste(world,xx,y,zz,"jungle"+pine);

									}
									if (new Random().nextInt(p.bigtree + 20) == 51) {
										int big = new Random().nextInt(1)+1;
										paste(world,xx,y-1,zz,"bigtree"+big);
									}
								}
								
								if (world.getBiome(xx,zz).equals(Biome.SPARSE_JUNGLE)) {
									if (new Random().nextInt(22) == 21) {
										world.generateTree(new Location(world,xx,y+1,zz), TreeType.JUNGLE_BUSH);
									}
								}
								
								if (world.getBiome(xx,zz).equals(Biome.BIRCH_FOREST) || world.getBiome(xx,zz).equals(Biome.OLD_GROWTH_BIRCH_FOREST)) {
									if (new Random().nextInt(p.treechance + 35) == 21) {
										int pine = new Random().nextInt(p.birchcount-1)+1;
										paste(world,xx,y,zz,"birch"+pine);
									}
								}
								
								if (world.getBiome(xx,zz).equals(Biome.SNOWY_TAIGA)) {
									if (new Random().nextInt(p.treechance + 20) == 21) {
										int pine = new Random().nextInt(p.pinecount-1)+1;
										paste(world,xx,y,zz,"pine"+pine);
									}
								}
								
								// TODO: Still need to add new flowers
								if (world.getBiome(xx,zz).equals(Biome.PLAINS) || world.getBiome(xx,zz).equals(Biome.SUNFLOWER_PLAINS)) {
									if (new Random().nextInt(3) == 2) {
										// This method just absolutely does not work anymore
										// chunk.getBlock(x, y+1, z).setTypeId(new Random().nextBoolean() ? Material.SUNFLOWER.getId() :  Material.POPPY.getId());
										chunk.getBlock(x, y+1, z).setType(Material.SUNFLOWER);
									}
									if (new Random().nextInt(500) == 324) {
										chunk.getBlock(x, y+1, z).setType(Material.PUMPKIN);
									}
									if (new Random().nextInt(100) == 20) {
										chunk.getBlock(x, y+1, z).setType(Material.BROWN_MUSHROOM);
									}
									if (new Random().nextInt(400) == 11) {
										chunk.getBlock(x, y + 1, z).setType(Material.STONE);
										chunk.getBlock(x, y + 2, z).setType(Material.STONE);
										chunk.getBlock(x, y + 3, z).setType(Material.STONE);chunk.getBlock(x, y + 1, z + 1).setType(Material.STONE);chunk.getBlock(x, y + 1, z - 1).setType(Material.STONE);
										chunk.getBlock(x + 1, y + 1, z).setType(Material.STONE);chunk.getBlock(x - 1, y + 1, z).setType(Material.STONE);
									}
									if (new Random().nextInt(p.treechance + 200) == 21) {
										int pine = new Random().nextInt(p.pinecount-1)+1;
										paste(world,xx,y,zz,"birch"+pine);
									}
									if (new Random().nextInt(12000) == 523) {
										int pine = 1;
										paste(world,xx,y,zz,"house"+pine);
									}
									if (p.aether && p.goodies) {
									if (p.ah <= y) {
										if (new Random().nextInt(1200) == 271) {
											// This might not be the best way to set type and data. At least it's atomic? via BlockState.
											BlockState newblock = chunk.getBlock(x, y+1, z).getState();
											newblock.setType(Material.CHEST);
											Chest c = (Chest) newblock.getBlockData();
											for (int i = 1; i  < new Random().nextInt(6); i++) {
												int rr = new Random().nextInt(428);
												// TODO: Randomly select from actual items instead of materials, and mabybe go by normal loot tables.
												// org.bukkit.loot.LootTable?
												//c.getBlockInventory().addItem(new ItemStack(Material.getMaterial(rr)));
											// Still have to commit BlockState and BlockData
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
							if (chunk.getBlock(x, y-1, z).getType().equals(Material.AIR)) {
								if (new Random().nextInt(20) == 5) {
									final int yy = y;
									paste(world,xx,y,zz,"stala_down");
								}

							}
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
								// this might not be right, used to be STATIONARY_LAVA
								chunk.getBlock(x, y, z).setType(Material.LAVA);
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
					// TODO: add new log types
					w.getBlockAt(new Location(w,x,y,z+i)).setType(Material.OAK_LOG);
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
					w.getBlockAt(new Location(w,x+i,y,z)).setType(Material.OAK_LOG);
					if (shroom) {
						if (new Random().nextInt(5) == 3) {
							w.getBlockAt(new Location(w,x+1,y,z)).setType(new Random().nextBoolean() ? Material.BROWN_MUSHROOM : Material.RED_MUSHROOM);
						}
					}
				}
			}
		}
	}
	
	public void paste(World w, int x, int y, int z, String f) {
		Location loc = new Location(w,x,y,z);
		File file = new File(p.getDataFolder()+"/schematics/prefab_"+f+".schematic");
		BlockVector3 v = BlockVector3.at(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()); // loc is your location variable
		EditSession es = WorldEdit.getInstance().getEditSessionFactory().getEditSession((com.sk89q.worldedit.world.World) new BukkitWorld(loc.getWorld()), WorldEdit.getInstance().getConfiguration().maxChangeLimit);
		ClipboardFormat format = ClipboardFormats.findByFile(file);
		Clipboard cc = null;
		//try {
			ClipboardReader reader = format.getReader(new FileInputStream(file));
			cc = reader.read();
		//} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		//}
		//try {
			ClipboardHolder holder = new ClipboardHolder(cc);
			AffineTransform transform = new AffineTransform();
			transform = transform.rotateY(new Random().nextInt(360));
			holder.setTransform(transform);
			Operation op = holder.createPaste(es).to(v).build();
		//}
	}
}
