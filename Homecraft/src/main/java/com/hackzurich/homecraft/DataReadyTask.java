package com.hackzurich.homecraft;

import java.util.ArrayList;

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
		
		// TODO: Process the houses!
		
	}

}
