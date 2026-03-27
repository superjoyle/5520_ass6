package edu.northeastern.wea26sp_team3;

import org.json.JSONObject;

public class WeatherParser {

    public static WeatherItem parseCurrent(String json) throws Exception {
        JSONObject root = new JSONObject(json);
        JSONObject current = root.getJSONObject("current");

        String time = current.getString("time");
        double temperature = current.getDouble("temperature_2m");
        int weatherCode = current.getInt("weather_code");
        double windSpeed = current.getDouble("wind_speed_10m");
        double humidity = current.getDouble("relative_humidity_2m");
        double feelsLike = current.getDouble("apparent_temperature");

        return new WeatherItem(
                time,
                temperature,
                weatherCode,
                windSpeed,
                humidity,
                feelsLike
        );
    }
}