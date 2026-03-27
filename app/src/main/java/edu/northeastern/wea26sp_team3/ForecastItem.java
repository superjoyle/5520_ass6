package edu.northeastern.wea26sp_team3;

public class ForecastItem {
    public final String day;
    public final String highLow;
    public final String condition;

    public ForecastItem(String day, String highLow, String condition) {
        this.day = day;
        this.highLow = highLow;
        this.condition = condition;
    }
}
