package edu.northeastern.wea26sp_team3;

public class HourlyWeatherItem {
    public final String time;
    public final double temperature;
    public final int weatherCode;
    public final double windSpeed;

    public HourlyWeatherItem(String time, double temperature, int weatherCode, double windSpeed) {
        this.time = time;
        this.temperature = temperature;
        this.weatherCode = weatherCode;
        this.windSpeed = windSpeed;
    }
}