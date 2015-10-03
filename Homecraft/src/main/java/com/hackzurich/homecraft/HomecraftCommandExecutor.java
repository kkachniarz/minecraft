package com.hackzurich.homecraft;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HomecraftCommandExecutor implements CommandExecutor {
	private final Homecraft plugin;
	
	public HomecraftCommandExecutor(Homecraft plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}
		
		HomegateManager manager = new HomegateManager();
		HomegateManagerTask task = new HomegateManagerTask(this.plugin, manager);
		task.runTaskAsynchronously(this.plugin);
		
		return false;
	}
}
