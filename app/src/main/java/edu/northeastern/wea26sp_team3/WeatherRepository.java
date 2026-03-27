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
                        + "&current=temperature_2m,weather_code,wind_speed_10m,relative_humidity_2m,apparent_temperature"
                        + "&daily=weather_code,temperature_2m_max,temperature_2m_min"
                        + "&hourly=temperature_2m,weather_code,wind_speed_10m"
                        + "&timezone=auto"
                        + "&forecast_days=7";

                String weatherJson = ApiClient.get(weatherUrl);

                WeatherItem currentWeather = WeatherParser.parseCurrent(weatherJson);
                ArrayList<DailyWeatherItem> dailyForecast = DailyWeatherParser.parseDaily(weatherJson);
                ArrayList<HourlyWeatherItem> hourlyForecast = HourlyWeatherParser.parseHourly(weatherJson);

                callback.onSuccess(
                        geo.name + (geo.country.isEmpty() ? "" : ", " + geo.country),
                        currentWeather,
                        dailyForecast,
                        hourlyForecast
                );

            } catch (Exception e) {
                e.printStackTrace();
                callback.onError(e.toString());
            }
        }).start();
    }
}