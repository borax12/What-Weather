package com.borax.whatweather;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

import android.content.Context;

public class FetchData {
	
	private static final String OPEN_WEATHER_MAP_API = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric";
	
	public static JSONObject getJSON(Context context, String city)
	{
		try
		{
			URL url = new URL(String.format(OPEN_WEATHER_MAP_API, city));
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			
			connection.addRequestProperty("x-api-key", context.getString(R.string.openweather_map_id));
			
			BufferedReader bufferedReader=  new BufferedReader(new InputStreamReader(connection.getInputStream()));
			
			StringBuffer json= new StringBuffer(1024);
			String tmp="";
			while((tmp=bufferedReader.readLine())!=null)
			{
				json.append(tmp).append("\n");
				
			}
			bufferedReader.close();
			
			JSONObject data= new JSONObject(json.toString());
			
			if(data.getInt("cod") != 200)
			{
				return null;
			}
			
			return data;
		}
		catch(Exception e)
		{
			return null;
		}
	}

}
