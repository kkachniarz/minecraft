package com.hackzurich.homecraft;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;

import javax.net.ssl.HttpsURLConnection;

public final class HomegateManager {
	private HomegateManager() {}
	public static ArrayList<HouseDTO> getData() throws IOException {
		URL url = new URL("https://api-2445581357976.apicast.io:443/rs/real-estates");
		
		HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
		connection.setRequestProperty("auth", "6b01599472a2225736789f76a39bcc73");
		connection.setRequestProperty("Accept", "application/json");

		InputStream is = connection.getInputStream();
		
		return new ArrayList<HouseDTO>();
	}
}
