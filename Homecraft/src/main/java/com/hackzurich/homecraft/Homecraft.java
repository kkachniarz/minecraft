package com.hackzurich.homecraft;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

public class Homecraft extends JavaPlugin {
	@Override
	public void onEnable() {
		// Insert logic that is run when plugin is started.
		getLogger().info("onEnable has been invoked");
		try {
			HomegateManager.getData();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void onDisable() {
		// Logic which is run before shutdown.
		getLogger().info("onDisable has been invoked");
	}
	
	
}
