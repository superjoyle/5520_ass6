package edu.northeastern.wea26sp_team3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvResult;
    private Button btnTestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        btnTestApi = findViewById(R.id.btnTestApi);

        btnTestApi.setOnClickListener(v -> {
            tvResult.setText("Loading...");

            WeatherRepository.fetchWeatherByCity("Boston", new WeatherCallback() {
                @Override
                public void onSuccess(String cityName, WeatherItem currentWeather, ArrayList<DailyWeatherItem> dailyForecast) {
                    runOnUiThread(() -> {
                        StringBuilder msg = new StringBuilder();
                        msg.append("City: ").append(cityName).append("\n");
                        msg.append("Current Temp: ").append(currentWeather.temperature).append("\n");
                        msg.append("Forecast count: ").append(dailyForecast.size()).append("\n\n");

                        for (DailyWeatherItem item : dailyForecast) {
                            msg.append(item.date)
                                    .append("  Max: ").append(item.maxTemp)
                                    .append("  Min: ").append(item.minTemp)
                                    .append("  Code: ").append(item.weatherCode)
                                    .append("\n");
                        }

                        tvResult.setText(msg.toString());
                    });
                }

                @Override
                public void onError(String errorMessage) {
                    runOnUiThread(() -> tvResult.setText("Error: " + errorMessage));
                }
            });
        });
    }
}