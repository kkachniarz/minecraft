package com.hackzurich.homecraft;

import org.bukkit.plugin.java.JavaPlugin;

public class Homecraft extends JavaPlugin {
	@Override
	public void onEnable() {
		// Insert logic that is run when plugin is started.
		getLogger().info("onEnable has been invoked");
	}
	
	@Override
	public void onDisable() {
		// Logic which is run before shutdown.
		getLogger().info("onDisable has been invoked");
	}

}
