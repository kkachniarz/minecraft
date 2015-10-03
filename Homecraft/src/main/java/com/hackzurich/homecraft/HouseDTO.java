package com.hackzurich.homecraft;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;

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
}
