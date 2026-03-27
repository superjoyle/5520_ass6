package edu.northeastern.wea26sp_team3;

public class DailyWeatherItem {
    public final String date;
    public final int weatherCode;
    public final double maxTemp;
    public final double minTemp;

    public DailyWeatherItem(String date, int weatherCode, double maxTemp, double minTemp) {
        this.date = date;
        this.weatherCode = weatherCode;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }
}