package edu.northeastern.wea26sp_team3;

public class GeoResult {
    public final String name;
    public final double latitude;
    public final double longitude;
    public final String country;

    public GeoResult(String name, double latitude, double longitude, String country) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.country = country;
    }
}