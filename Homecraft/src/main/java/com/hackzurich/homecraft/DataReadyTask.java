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
		Location loc = player.getLocation().clone();
		for (HouseDTO house : this.data) {
			// Just build all these houses
			loc.add(5, 0, 0);
			HouseBuilder builder = new HouseBuilder(this.plugin, loc, house);
			builder.build();
		}
	}

}
