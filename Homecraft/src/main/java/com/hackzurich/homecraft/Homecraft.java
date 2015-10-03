package com.hackzurich.homecraft;

import org.bukkit.plugin.java.JavaPlugin;

public class Homecraft extends JavaPlugin {
	@Override
	public void onEnable() {
	
		// Setup the HomecraftCommandExecutor
		getCommand("homecraft").setExecutor(new HomecraftCommandExecutor(this));
		
		// Setup the Text...
		getCommand("text").setExecutor(new TextCommandExecutor(this));
	}
	
	@Override
	public void onDisable() {
		// Logic which is run before shutdown.
		getLogger().info("onDisable has been invoked");
	}
}
