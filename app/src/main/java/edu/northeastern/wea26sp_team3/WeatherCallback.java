package edu.northeastern.wea26sp_team3;

import java.util.ArrayList;

public interface WeatherCallback {
    void onSuccess(String cityName, WeatherItem currentWeather, ArrayList<DailyWeatherItem> dailyForecast);
    void onError(String errorMessage);
}