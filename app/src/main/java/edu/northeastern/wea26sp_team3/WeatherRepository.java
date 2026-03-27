package edu.northeastern.wea26sp_team3;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class WeatherRepository {

    public static void fetchWeatherByCity(String city, WeatherCallback callback) {
        new Thread(() -> {
            try {
                String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());

                String geoUrl = "https://geocoding-api.open-meteo.com/v1/search?name="
                        + encodedCity
                        + "&count=1&language=en&format=json";

                String geoJson = ApiClient.get(geoUrl);
                GeoResult geo = GeocodingParser.parseFirstResult(geoJson);

                String weatherUrl = "https://api.open-meteo.com/v1/forecast?latitude="
                        + geo.latitude
                        + "&longitude="
                        + geo.longitude
                        + "&current=temperature_2m,weather_code,wind_speed_10m"
                        + "&hourly=temperature_2m,weather_code,wind_speed_10m"
                        + "&timezone=auto";

                String weatherJson = ApiClient.get(weatherUrl);
                ArrayList<WeatherItem> items = WeatherParser.parseHourly(weatherJson);

                callback.onSuccess(geo.name + (geo.country.isEmpty() ? "" : ", " + geo.country), items);

            } catch (Exception e) {
                callback.onError(e.getMessage());
            }
        }).start();
    }
}