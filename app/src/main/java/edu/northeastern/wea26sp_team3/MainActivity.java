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
                public void onSuccess(String cityName, ArrayList<WeatherItem> items) {
                    runOnUiThread(() -> {
                        if (items == null || items.isEmpty()) {
                            tvResult.setText("Success, but no items returned.");
                        } else {
                            WeatherItem first = items.get(0);
                            String msg = "Success!\n"
                                    + "City: " + cityName + "\n"
                                    + "Items: " + items.size() + "\n"
                                    + "First time: " + first.time + "\n"
                                    + "Temp: " + first.temperature + "\n"
                                    + "Code: " + first.weatherCode + "\n"
                                    + "Wind: " + first.windSpeed;
                            tvResult.setText(msg);
                        }
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