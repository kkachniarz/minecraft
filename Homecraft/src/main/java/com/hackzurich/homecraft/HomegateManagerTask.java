package com.hackzurich.homecraft;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;

public class HomegateManagerTask extends BukkitRunnable {
	
	private HomegateManager manager;
	private Homecraft plugin;
	
	public HomegateManagerTask(Homecraft plugin ,HomegateManager manager) {
		this.manager = manager;
		this.plugin = plugin;
	}

	@Override
	public void run() {
		ArrayList<HouseDTO> houses;
		try {
			houses = manager.getData();
		} catch (Exception e) {
			// Just log the error.
			this.plugin.getLogger().info("Failed to get data");
			this.plugin.getLogger().info(e.getStackTrace().toString());
			houses = new ArrayList<HouseDTO>();
		}
		
		// Schedule the DataReadyTask
		DataReadyTask task = new DataReadyTask(this.plugin, houses);
		task.runTask(this.plugin);
	}

}
