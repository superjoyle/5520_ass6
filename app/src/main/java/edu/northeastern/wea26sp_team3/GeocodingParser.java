package edu.northeastern.wea26sp_team3;

import org.json.JSONArray;
import org.json.JSONObject;

public class GeocodingParser {

    public static GeoResult parseFirstResult(String json) throws Exception {
        JSONObject root = new JSONObject(json);

        if (!root.has("results")) {
            throw new Exception("No matching city found.");
        }

        JSONArray results = root.getJSONArray("results");
        if (results.length() == 0) {
            throw new Exception("No matching city found.");
        }

        JSONObject first = results.getJSONObject(0);

        String name = first.optString("name", "Unknown");
        double latitude = first.getDouble("latitude");
        double longitude = first.getDouble("longitude");
        String country = first.optString("country", "");

        return new GeoResult(name, latitude, longitude, country);
    }
}