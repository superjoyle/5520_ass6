package edu.northeastern.wea26sp_team3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class WeatherParser {

    public static WeatherItem parseCurrent(String json) throws Exception {
        JSONObject root = new JSONObject(json);
        JSONObject current = root.getJSONObject("current");

        String time = current.getString("time");
        double temperature = current.getDouble("temperature_2m");
        int weatherCode = current.getInt("weather_code");
        double windSpeed = current.getDouble("wind_speed_10m");

        return new WeatherItem(time, temperature, weatherCode, windSpeed);
    }
}