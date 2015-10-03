package com.hackzurich.homecraft;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HomecraftCommandExecutor implements CommandExecutor {
	private final Homecraft plugin;
	
	public HomecraftCommandExecutor(Homecraft plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		sender.sendMessage("Foooooo!");
		
		return false;
	}
	
}
