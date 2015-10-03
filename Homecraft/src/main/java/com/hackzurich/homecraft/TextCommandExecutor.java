package com.hackzurich.homecraft;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TextCommandExecutor implements CommandExecutor {
	private final Homecraft plugin;
	
	public TextCommandExecutor(Homecraft plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}
		
		Player player = (Player)sender;
		
		player.sendMessage("Message was send to the landlord (joe@doe.com)");
		
		return false;
	}
}
