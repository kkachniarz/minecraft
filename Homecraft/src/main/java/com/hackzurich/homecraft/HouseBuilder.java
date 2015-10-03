package com.hackzurich.homecraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class HouseBuilder {
	
	public static int fieldSize = 31;
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
		
		for (int i = 0; i < roomCount; i++) {
			Block currentBlock = this.world.getBlockAt(x, y, z+i);
			currentBlock.setType(Material.GOLD_BLOCK);
		}
	}
}
