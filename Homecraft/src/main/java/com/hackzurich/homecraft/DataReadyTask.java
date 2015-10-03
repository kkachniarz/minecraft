package com.hackzurich.homecraft;

import java.util.ArrayList;

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
		Normalizer normLat = new Normalizer(minLat, maxLat, 
				-HouseBuilder.fieldSize * HouseBuilder.numberOfFieldsInDimension, 
				 HouseBuilder.fieldSize * HouseBuilder.numberOfFieldsInDimension);
		
		Normalizer normLon = new Normalizer(minLon, maxLon, 
				-HouseBuilder.fieldSize * HouseBuilder.numberOfFieldsInDimension, 
				 HouseBuilder.fieldSize * HouseBuilder.numberOfFieldsInDimension);
		
		//ArrayList<ArrayList<boolean>> reservations = new ArrayList<ArrayList<boolean>>();
		
		for (HouseDTO house : this.data) {
			// Just build all these houses
			house.latitude = normLat.Normalize(house.latitude);
			house.longitude = normLon.Normalize(house.longitude);
			
			this.plugin.getLogger().info("Build house in (offset): (" + (int)house.latitude + ", " + (int)house.longitude + ")");
			
			Location loc = player.getLocation().clone();
			loc.add((int)house.latitude, 0, (int)house.longitude);
			this.plugin.getLogger().info("Build hous in (absolute): (" + (int) loc.getBlockX() + ", " + (int) loc.getBlockZ() + ")");
			HouseBuilder builder = new HouseBuilder(this.plugin, loc, house);
			builder.build();
		}
	}

}
