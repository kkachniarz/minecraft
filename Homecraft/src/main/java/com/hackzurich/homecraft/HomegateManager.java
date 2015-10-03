package com.hackzurich.homecraft;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONException;
import org.json.JSONObject;

public final class HomegateManager {
	private HomegateManager() {}
	public static ArrayList<HouseDTO> getData() throws IOException, JSONException {
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

	    JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());
		
		return new ArrayList<HouseDTO>();
	}
}
