package com.hackzurich.homecraft;

import java.net.URL;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import org.bukkit.Bukkit;
import org.json.JSONArray;
import org.json.JSONObject;

public final class HomegateManager {
	public HomegateManager() {}
	
	public ArrayList<HouseDTO> getData() throws Exception {
		URL url = new URL("https://api-2445581357976.apicast.io:443/rs/real-estates?language=en&chooseType=purchflat");
		
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("auth", "6b01599472a2225736789f76a39bcc73");
		connection.setRequestProperty("Accept", "application/json");

		InputStream is = connection.getInputStream();
		
	    BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
	    StringBuilder responseStrBuilder = new StringBuilder();

	    String inputStr;
	    while ((inputStr = streamReader.readLine()) != null)
	        responseStrBuilder.append(inputStr);

	    Bukkit.getServer().getLogger().info(responseStrBuilder.toString());
	    
	    JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
		
		return getDataFromJson(jsonObject);
	}
	
	private ArrayList<HouseDTO> getDataFromJson(JSONObject jsonObject) throws Exception {
		ArrayList<HouseDTO> houses = new ArrayList<HouseDTO>();
		JSONArray array = jsonObject.getJSONArray("items");
		for(int i = 0 ; i < array.length() ; i++){
			HouseDTO house = new HouseDTO();
			JSONObject obj = array.getJSONObject(i);

			if(obj.has("balcony")) {
				house.balcony = obj.getBoolean("balcony");
			}			
			if(obj.has("lift")) {
				house.lift = obj.getBoolean("lift");
			}		
			if(obj.has("numberRooms")) {
				house.numberRooms = obj.getInt("numberRooms");
			}
			if(obj.has("floor")) {
				house.floor = obj.getInt("floor");
			}
			if(obj.has("sellingPrice")) {
				house.sellingPrice = obj.getDouble("sellingPrice");
			}
			if(obj.has("adDescription")) {
				house.description = obj.getString("adDescription");
			}			
			if(obj.has("title")) {
				house.title = obj.getString("title");
			}			
			if(obj.has("geoLocation")) {
				String[] parts = obj.getString("geoLocation").split(",");
				house.latitude = Double.parseDouble(parts[0]);
				house.longitude = Double.parseDouble(parts[1]);
			}
			
			if(obj.has("pictures")) {	
				Object item = obj.get("pictures"); 
				ArrayList<String> pictures = new ArrayList<String>();
			    if (item instanceof JSONArray)
			    {
					JSONArray picturesJson = obj.getJSONArray("pictures");
					for(int j = 0 ; j < picturesJson.length() ; j++){
						pictures.add(picturesJson.getString(j));
					}
			    }
			    else
			    {
			    	pictures.add(item.toString());
			    }
				house.pictures = pictures;
			}

			houses.add(house);
		}
		return houses;		
	}
}
