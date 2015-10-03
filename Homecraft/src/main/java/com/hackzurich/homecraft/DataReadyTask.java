package com.hackzurich.homecraft;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class DataReadyTask extends BukkitRunnable {
	private Homecraft plugin;
	private ArrayList<HouseDTO> data;
	
	public  DataReadyTask(Homecraft plugin, ArrayList<HouseDTO> data) {
		this.plugin = plugin;
		this.data = data;
	}

	@Override
	public void run() {
		this.plugin.getLogger().info("Got data! ");
		this.plugin.getLogger().info(this.data.toString());
			
		Player player = this.plugin.getServer().getOnlinePlayers()[0];
		
		double minLat = Double.MAX_VALUE, maxLat = Double.MIN_VALUE, 
			   minLon = Double.MAX_VALUE, maxLon = Double.MIN_VALUE;
		
		for (HouseDTO house : this.data) {
			if(house.latitude > maxLat)
				maxLat = house.latitude;
			else if(house.latitude < minLat)
				minLat = house.latitude;
			
			if(house.longitude > maxLon)
				maxLon = house.longitude;
			else if(house.longitude < minLon)
				minLon = house.longitude;
		}
		
		this.plugin.getLogger().info("minLat: " + minLat + " maxLat " + maxLat);
		this.plugin.getLogger().info("minLon: " + minLon + " maxLon " + maxLon);
		Normalizer normLat = new Normalizer(minLat, maxLat, 0, HouseBuilder.numberOfFieldsInDimension);
		
		Normalizer normLon = new Normalizer(minLon, maxLon, 0, HouseBuilder.numberOfFieldsInDimension);
		
		boolean[][] reservations = new boolean[HouseBuilder.numberOfFieldsInDimension][HouseBuilder.numberOfFieldsInDimension];
        for (int row = 0; row < HouseBuilder.numberOfFieldsInDimension; row ++)
            for (int col = 0; col < HouseBuilder.numberOfFieldsInDimension; col++)
            	reservations[row][col] = false;
        
        // reserve player position
        reservations[(int)HouseBuilder.numberOfFieldsInDimension/2 + 1][(int)HouseBuilder.numberOfFieldsInDimension/2 + 1] = true;
        
		Random rand = new Random();
		
		for (HouseDTO house : this.data) {
			// Just build all these houses
			house.latitude = normLat.Normalize(house.latitude);
			house.longitude = normLon.Normalize(house.longitude);
			
			int coordX = (int)house.latitude, coordY = (int)house.longitude;
			
			coordX = Math.min(HouseBuilder.numberOfFieldsInDimension - 1, Math.max(coordX, 0));
			coordY = Math.min(HouseBuilder.numberOfFieldsInDimension - 1, Math.max(coordY, 0));
			this.plugin.getLogger().info(coordX + " " + coordY); // !
			while(reservations[coordX][coordY] != false)
			{
				coordX = Math.min(HouseBuilder.numberOfFieldsInDimension - 1, Math.max(coordX + rand.nextInt(4) - 2, 0));
				coordY = Math.min(HouseBuilder.numberOfFieldsInDimension - 1, Math.max(coordY + rand.nextInt(4) - 2, 0));
			}
			reservations[coordX][coordY] = true;
			
			this.plugin.getLogger().info("Build house in: (" + coordX + ", " + coordY + ")");
			
			Location loc = player.getLocation().clone();
			loc.add((coordX - HouseBuilder.numberOfFieldsInDimension/2) * HouseBuilder.fieldSize, 
					0, 
					(coordY - HouseBuilder.numberOfFieldsInDimension/2) * HouseBuilder.fieldSize);
			
			this.plugin.getLogger().info("Build hous in (absolute): (" + (int) loc.getBlockX() + ", " + (int) loc.getBlockZ() + ")");
			HouseBuilder builder = new HouseBuilder(this.plugin, loc, house);
			builder.build();
		}
	}

}
