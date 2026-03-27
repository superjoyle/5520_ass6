package edu.northeastern.wea26sp_team3;

public class WeatherItem {
    public final String time;
    public final double temperature;
    public final int weatherCode;
    public final double windSpeed;
    public final double humidity;
    public final double feelsLike;

    public WeatherItem(String time,
                       double temperature,
                       int weatherCode,
                       double windSpeed,
                       double humidity,
                       double feelsLike) {
        this.time = time;
        this.temperature = temperature;
        this.weatherCode = weatherCode;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.feelsLike = feelsLike;
    }
}