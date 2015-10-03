package com.hackzurich.homecraft;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;

public class HouseBuilder {
	
	public static int fieldSize = 5;
	public static int numberOfFieldsInDimension = 10;
	
	private HouseDTO house;
	private Homecraft plugin;
	private int x, y, z;
	private World world;
	
	private void init() {
		this.world = this.plugin.getServer().getWorld("Homecraft");
	}
	
	public HouseBuilder(Homecraft plugin, int x, int y, int z, HouseDTO house) {
		this.plugin = plugin;
		this.init();
		this.house = house;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public HouseBuilder(Homecraft plugin, Location location, HouseDTO house) {
		this.plugin = plugin;
		this.init();
		this.house = house;
		this.x = location.getBlockX();
		this.y = location.getBlockY();
		this.z = location.getBlockZ();
	}
	
	/**
	 * Build the actual house
	 */
	public void build() {
		int roomCount = this.house.numberRooms;
		
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
}
