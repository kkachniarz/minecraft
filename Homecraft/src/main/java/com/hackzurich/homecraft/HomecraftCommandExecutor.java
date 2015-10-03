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
		Player player = (Player)sender;
		
		Location playerLocation = player.getLocation();
		int x = playerLocation.getBlockX();
		int y = playerLocation.getBlockY();
		int z = playerLocation.getBlockZ();
		Block currentBlock = player.getWorld().getBlockAt(x+5, y, z);
		currentBlock.setType(Material.DIAMOND_BLOCK);
		
		return false;
	}
}
