package com.hackzurich.homecraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

public class StreetBuilder {
	
	private Location start;
	private int direction;
	private int distance;

	/**
	 * Direction 
	 * @param start
	 * @param direction 0=x, 1=z, 2=-x, 3=-z
	 * @param distance
	 */
	public StreetBuilder(Location start, int direction, int distance) {
		this.start = start;
		this.direction = direction;
		this.distance = distance;
	}
	
	/**
	 * 
	 * @param position
	 * @param direction 0 for x and 1 for z
	 */
	public void buildOneElement(Location position, int direction) {
		Material matOutside = Material.STONE;
		Material matCenter = Material.COBBLESTONE;
		
		Location loc = position.clone();
		World world = Bukkit.getServer().getWorld("Homecraft");
		Block block = world.getBlockAt(loc);
		block.setType(matCenter);
		
		if (direction == 0) {
			loc = loc.add(1, 0, 0);
			world.getBlockAt(loc).setType(matOutside);
			loc = loc.subtract(2, 0, 0);
			world.getBlockAt(loc).setType(matOutside);
		}
		else {
			loc = loc.add(0, 0, 1);
			world.getBlockAt(loc).setType(matOutside);
			loc = loc.subtract(0, 0, 2);
			world.getBlockAt(loc).setType(matOutside);
		}
	}
	
	public void build() 
	{
		Location loc = this.start;
		Bukkit.getLogger().info("Building street from " + loc.toString());
		int dir = (this.direction == 0 || this.direction == 2) ? 1 : 0;
		while (this.distance > 0) {
			this.distance = this.distance - 1;
			this.buildOneElement(loc, dir);
			loc = this.getNextLocation(loc);
		}
		Bukkit.getLogger().info("Finished Building street.");
	}
	
	public Location getNextLocation(Location loc) {
		switch (this.direction) {
		case 0:
			loc.add(1, 0, 0);
			break;
		case 1:
			loc.add(0, 0, 1);
			break;
		case 2:
			loc.subtract(1, 0, 0);
			break;
		case 3:
			loc.subtract(0, 0, 1);
			break;
		default:
			break;
		}
		
		return loc;
	}
}
