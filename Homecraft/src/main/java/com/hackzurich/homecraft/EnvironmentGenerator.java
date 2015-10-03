package com.hackzurich.homecraft;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.block.Block;

public class EnvironmentGenerator {
	
	private Homecraft plugin;
	private Location center;
	
	/** The environment is built around the center. */
	public EnvironmentGenerator(Homecraft plugin, Location center) {
		this.plugin = plugin;
		this.center = center;
	}
	
	
	public void build()
	{
		int size = HouseBuilder.fieldSize * HouseBuilder.numberOfFieldsInDimension;
		int x_min = center.getBlockX() - size/2;
		int x_max = center.getBlockX() + size/2;
		int y_min = center.getBlockZ() - size/2;
		int y_max = center.getBlockZ() + size/2;
		
		int size_x = Math.abs(x_max - x_min) / 2;
		int size_y = Math.abs(y_max - y_min) / 2;
		
		this.generateTrees(x_min - size_x, x_max + size_x, y_min - size_y, y_max + size_y);
	}
	
	public void generateTrees(int x_min, int x_max, int y_min, int y_max) {
		// Tree location 
		Random generator = new Random();
		World world = Bukkit.getWorld("Homecraft");
		
		int i = 0;
		while (i < 400) {
			int x = generator.nextInt(x_max - x_min) + x_min;
			int y = generator.nextInt(y_max - y_min) + y_min;
			
			Block block = world.getBlockAt(x, 3, y);
			if (block.getType() == Material.GRASS) {
				// Plant a tree
				Location loc = new Location(world, x, 4, y);
				world.generateTree(loc, this.getRandomTreeType());
				i++;
			}
		}
	}
	
	public TreeType getRandomTreeType() {
		Random generator = new Random();
		int x = generator.nextInt(100);
		
		if (x < 20) {
			return TreeType.BIG_TREE;
		}
		else if (x < 40) {
			return TreeType.ACACIA;
		}
		else {
			return TreeType.TREE;
		}
	}

}
