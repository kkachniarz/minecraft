package com.hackzurich.homecraft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

public class Building {
	
	static void ReplaceMaterial(int startX, int startY, int startZ, int endX, int endY, int endZ, Material mat_old, Material mat_new) {
		int x;
		int y;
		int z;
		
		x = Math.abs(endX - startX);
		y = Math.abs(endY - startY);
		z = Math.abs(endZ - startZ);
		
		Bukkit.getServer().getLogger().info("x: "+x+ "y: "+y+"z: "+z);
		
		for(int X = 0; X < x; X++)
		{
			for(int Y = 0; Y < y; Y++)
			{
				for(int Z = 0; Z < z; Z++)
				{
					Bukkit.getServer().getLogger().info(X + " " +  Y + " " + Z);
					
					Block currentBlock = Bukkit.getServer().getOnlinePlayers()[0].getWorld().getBlockAt(startX + X, startY + Y, startZ + Z);
					Material test = currentBlock.getType();
					if(test == mat_old)
					{
						Bukkit.getServer().getLogger().info("BLOCK CHANGED!!!!!!!!!!!!!!!!!!!!!");
						currentBlock.setType(mat_new);
					}
				}
				Bukkit.getServer().getLogger().info("Z finished");
			}
			Bukkit.getServer().getLogger().info("Y finished");
		}
		Bukkit.getServer().getLogger().info("X finished");
	}
}
