package edu.northeastern.wea26sp_team3;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class DailyWeatherParser {

    public static ArrayList<DailyWeatherItem> parseDaily(String json) throws Exception {
        ArrayList<DailyWeatherItem> items = new ArrayList<>();

        JSONObject root = new JSONObject(json);
        JSONObject daily = root.getJSONObject("daily");

        JSONArray dates = daily.getJSONArray("time");
        JSONArray codes = daily.getJSONArray("weather_code");
        JSONArray maxTemps = daily.getJSONArray("temperature_2m_max");
        JSONArray minTemps = daily.getJSONArray("temperature_2m_min");

        for (int i = 0; i < dates.length(); i++) {
            items.add(new DailyWeatherItem(
                    dates.getString(i),
                    codes.getInt(i),
                    maxTemps.getDouble(i),
                    minTemps.getDouble(i)
            ));
        }

        return items;
    }
}