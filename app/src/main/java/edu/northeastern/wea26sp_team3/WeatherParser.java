package edu.northeastern.wea26sp_team3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherParser {

    public static ArrayList<WeatherItem> parseHourly(String json) throws Exception {
        ArrayList<WeatherItem> items = new ArrayList<>();

        JSONObject root = new JSONObject(json);
        JSONObject hourly = root.getJSONObject("hourly");

        JSONArray times = hourly.getJSONArray("time");
        JSONArray temps = hourly.getJSONArray("temperature_2m");
        JSONArray codes = hourly.getJSONArray("weather_code");
        JSONArray winds = hourly.getJSONArray("wind_speed_10m");

        int len = times.length();
        for (int i = 0; i < len; i++) {
            String time = times.getString(i);
            double temp = temps.getDouble(i);
            int code = codes.getInt(i);
            double wind = winds.getDouble(i);

            items.add(new WeatherItem(time, temp, code, wind));
        }

        return items;
    }
}
