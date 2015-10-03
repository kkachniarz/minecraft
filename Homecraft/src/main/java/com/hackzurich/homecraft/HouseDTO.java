package com.hackzurich.homecraft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

public class HouseDTO {
	public double latitude;	// Relative to user
	public double longitude; // Relative to user
	public int latitude_absolute;
	public int longitude_absolute;
	public int numberRooms;
	public int floor;
	public double sellingPrice;
	public ArrayList<String> pictures;
	public boolean lift;
	public boolean balcony;
	public String description;
	public String title;

	public Location getLocation() {
		return new Location(Bukkit.getWorld("Homecraft"), this.latitude_absolute, 3, this.longitude_absolute);
	}
	
	public void createBook(World world) {
		Block block = world.getBlockAt(this.latitude_absolute, 4, this.longitude_absolute);
		block.setType(Material.CHEST);
		
		// Add the book
		Chest chest = (Chest)block.getState();
		Inventory inventory = chest.getBlockInventory();

		ItemStack stack = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta meta = (BookMeta)stack.getItemMeta();

		// Setup book comment
		String firstPage = this.title;
		String thirdPage = "Properties: \n\n Lift: " + (this.lift ? "Yes" : "No") ;
		thirdPage = thirdPage + "\n Balcony: " + (this.lift ? "Yes" : "No");
		thirdPage = thirdPage + "\n Number of Rooms: " + this.numberRooms;
		thirdPage = thirdPage + "\n Floor: " + this.floor;
		if (this.sellingPrice != 0) {
			thirdPage = thirdPage + " \n\n Price: " + this.sellingPrice;
		}
		meta.setPages(Arrays.asList(firstPage, thirdPage));
		meta.setAuthor("homegate.ch");
		meta.setTitle("Offer");

		stack.setItemMeta(meta);
		inventory.addItem(stack);
		chest.update();
	}
}
