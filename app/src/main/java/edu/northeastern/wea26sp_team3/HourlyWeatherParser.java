package edu.northeastern.wea26sp_team3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HourlyWeatherParser {

    public static ArrayList<HourlyWeatherItem> parseHourly(String json) throws Exception {
        ArrayList<HourlyWeatherItem> items = new ArrayList<>();

        JSONObject root = new JSONObject(json);
        JSONObject hourly = root.getJSONObject("hourly");

        JSONArray times = hourly.getJSONArray("time");
        JSONArray temps = hourly.getJSONArray("temperature_2m");
        JSONArray codes = hourly.getJSONArray("weather_code");
        JSONArray winds = hourly.getJSONArray("wind_speed_10m");

        int count = Math.min(24, times.length());

        for (int i = 0; i < count; i++) {
            items.add(new HourlyWeatherItem(
                    times.getString(i),
                    temps.getDouble(i),
                    codes.getInt(i),
                    winds.getDouble(i)
            ));
        }

        return items;
    }
}