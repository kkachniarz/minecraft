package com.hackzurich.homecraft;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class HouseBuilder {
	
	public static int floorLevel = 3;
	
	public static int fieldSize = 19;
	public static int numberOfFieldsInDimension = 17;
	
	private HouseDTO house;
	private Homecraft plugin;
	private int x, y, z;
	private World world;
	
	/** The orientation of the house. 0 to 3 */
	private int orientation = 0;
	
	private void init() {
		this.world = this.plugin.getServer().getWorld("Homecraft");
		Random generator = new Random();
		this.orientation = generator.nextInt(4);
	}
	
	public HouseBuilder(Homecraft plugin, int x, int y, int z, HouseDTO house) {
		this.plugin = plugin;
		this.init();
		this.house = house;
		this.x = x;
		this.y = floorLevel; // Force building to be always on ground level 
		this.z = z;
	}
	
	public HouseBuilder(Homecraft plugin, Location location, HouseDTO house) {
		this.plugin = plugin;
		this.init();
		this.house = house;
		this.x = location.getBlockX();
		this.y = floorLevel; // location.getBlockY();
		this.z = location.getBlockZ();
	}
	
	/**
	 * Build the actual house
	 */
	public void build() {

		/*
		 * 		int roomCount = this.house.numberRooms;
		Block signBlock = this.world.getBlockAt(x,  y+1, z);
		this.turnToSign(signBlock);
		
		for (int i = 0; i < roomCount; i++) {
			Block currentBlock = this.world.getBlockAt(x, y, z+i);
			currentBlock.setType(Material.GOLD_BLOCK);
		}
		
		// Fence
		ArrayList<Block> border = this.getBorderBlocks(1);
		for (Block block : border) {
			block.setType(Material.FENCE);
		}
		*/
		
		if(this.house.numberRooms <= 2)
			copySmallBuilding();
		else if(this.house.numberRooms <= 3) 
			copyRichBuilding();
		else if(this.house.numberRooms <= 4) 
			copyBigBuilding();
		else
			copyTetrisBuilding();
		
		Block signBlock = this.world.getBlockAt(x + 5,  y+1, z + 5);
		this.turnToSign(signBlock);
		
		this.house.createBook(this.world);
	}
	
	/**
	 * Get the border blocks. Level is the height above ground. 0 is the ground.
	 * @param level
	 * @return The blocks. They will not be in order.
	 */
	public ArrayList<Block> getBorderBlocks(int level) 
	{
		ArrayList<Block> blocks = new ArrayList<Block>();
		int size = HouseBuilder.fieldSize / 2;
		int absoluteLevel = this.y + level - 1;
		for (int i = 0; i < HouseBuilder.fieldSize; i++) {
			// One dimension
			blocks.add(this.world.getBlockAt(this.x - size + i, absoluteLevel, this.z - size));
			blocks.add(this.world.getBlockAt(this.x - size + i, absoluteLevel, this.z + size));
		}
		
		for (int i = 0; i < HouseBuilder.fieldSize - 1; i++) {
			// The other dimension
			blocks.add(this.world.getBlockAt(this.x - size, absoluteLevel, this.z - size + i));
			blocks.add(this.world.getBlockAt(this.x + size, absoluteLevel, this.z - size + i));
		}
		
		return blocks;
	}
	
	/**
	 * Get the sign containing the description.
	 */
	public void turnToSign(Block block) 
	{
		block.setType(Material.SIGN_POST);
		
		Sign sign = (Sign)block.getState();

		String[] parts = this.house.title.split("(?<=\\G.{12})");
		for (int i = 0; i < 4; i++) {
			if (i < parts.length) {
				sign.setLine(i, parts[i]);
			}
		}

		sign.update();
	}

	public void copyFromArea(int _x, int _y, int _z) {
		this.copyFromArea(_x, _y, _z, true, null, null);
	}
	
	public void copyFromArea(int _x, int _y, int _z, Material old_one, Material new_one)
	{
		this.copyFromArea(_x, _y, _z, true, old_one, new_one);
	}

	public void copyFromArea(int _x, int _y, int _z, boolean copy_air, Material old_one, Material new_one)
	{
		for (int i = -fieldSize / 2; i <= fieldSize / 2; i++) {
			for (int j = -fieldSize / 2; j <= fieldSize / 2; j++) {
				for (int k = 0; k <= 10 * fieldSize; k++) {
					
					Block copyBlock = this.world.getBlockAt(_x + i,  _y + k, _z + j);; 
					Block currentBlock = null;
					
					if (orientation == 0) {
						currentBlock =  this.world.getBlockAt(x + i,  y + k, z + j);
					}
					else if (orientation == 1) {
						currentBlock =  this.world.getBlockAt(x - i,  y + k, z + j);
					}
					else if (orientation == 2) {
						currentBlock =  this.world.getBlockAt(x - i,  y + k, z - j);
					}
					else {
						currentBlock =  this.world.getBlockAt(x + i,  y + k, z - j);
					}

					if (copyBlock.getType() != Material.AIR) {
						if(old_one != null && new_one != null && copyBlock.getType() == old_one)
						{
							currentBlock.setType(new_one);
						}
						else{
							currentBlock.setType(copyBlock.getType());
						}
					}
				}
			}
		}
	}
	
	public void copySmallBuilding()
	{
		copyFromArea(4, floorLevel, 0, Material.STONE, Material.WOOD);
	}
	
	public void copyBigBuilding()
	{
		copyFromArea(103, floorLevel, 0);
	}
	
	public void copyRichBuilding()
	{
		Random ran = new Random();
		int r = ran.nextInt(4);
		
		if(r == 0)
			copyFromArea(198, floorLevel, 0, Material.LAPIS_BLOCK, Material.EMERALD_BLOCK);
		else if(r == 1)
			copyFromArea(198, floorLevel, 0, Material.LAPIS_BLOCK, Material.COAL_BLOCK);
		else if(r == 2)
			copyFromArea(198, floorLevel, 0, Material.LAPIS_BLOCK, Material.REDSTONE_BLOCK);
		else
			copyFromArea(198, floorLevel, 0);
	}
	
	public void copyTetrisBuilding()
	{
		copyFromArea(301, floorLevel, 0);
	}
}
