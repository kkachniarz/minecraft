package com.hackzurich.homecraft;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.io.IOException;
import java.io.InputStream;

public final class HomegateManager {
	private HomegateManager() {}
	public static ArrayList<HouseDTO> getData() throws IOException {
		String url = "https://api-2445581357976.apicast.io:443/rs/real-estates/";
		String charset = "UTF-8";  // Or in Java 7 and later, use the constant: java.nio.charset.StandardCharsets.UTF_8.name()
//		String param1 = "value1";
//		String param2 = "value2";
//		 ...

//		String query = String.format("param1=%s&param2=%s", 
//		     URLEncoder.encode(param1, charset), 
//		     URLEncoder.encode(param2, charset));
		URLConnection connection = new URL(url).openConnection();
//		connection.setRequestProperty("Accept-Charset", charset);
		connection.setRequestProperty("Auth", "6b01599472a2225736789f76a39bcc73");
		connection.setRequestProperty("Accept", "application/json");

		InputStream response = connection.getInputStream();
		
		return new ArrayList<HouseDTO>();
	}
}
