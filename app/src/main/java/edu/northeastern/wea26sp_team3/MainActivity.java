package edu.northeastern.wea26sp_team3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private EditText cityInput;
    private RadioGroup weatherTypeGroup;
    private RadioButton currentWeatherRadio;
    private RadioButton forecastRadio;
    private Switch unitSwitch;
    private Button searchButton;
    private TextView statusText;

    private LinearLayout currentWeatherCard;
    private TextView cityNameText;
    private TextView tempText;
    private TextView conditionText;

    private RecyclerView forecastRecyclerView;

    private final Handler loadingHandler = new Handler();
    private int loadingDotCount = 0;
    private boolean isLoading = false;

    private final Runnable loadingRunnable = new Runnable() {
        @Override
        public void run() {
            if (!isLoading) return;

            StringBuilder dots = new StringBuilder();
            for (int i = 0; i < loadingDotCount; i++) {
                dots.append(".");
            }
            statusText.setText("Loading" + dots);
            loadingDotCount = (loadingDotCount + 1) % 4;
            loadingHandler.postDelayed(this, 500);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityInput = findViewById(R.id.cityInput);
        weatherTypeGroup = findViewById(R.id.weatherTypeGroup);
        currentWeatherRadio = findViewById(R.id.currentWeatherRadio);
        forecastRadio = findViewById(R.id.forecastRadio);
        unitSwitch = findViewById(R.id.unitSwitch);
        searchButton = findViewById(R.id.searchButton);
        statusText = findViewById(R.id.statusText);

        currentWeatherCard = findViewById(R.id.currentWeatherCard);
        cityNameText = findViewById(R.id.cityNameText);
        tempText = findViewById(R.id.tempText);
        conditionText = findViewById(R.id.conditionText);

        forecastRecyclerView = findViewById(R.id.forecastRecyclerView);
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString().trim();

            if (city.isEmpty()) {
                showError("Please enter a city.");
                return;
            }

            startLoading();

            // Temporary demo only.
            // Your networking teammate can replace this block with real API callback results.
            searchButton.postDelayed(() -> {
                if (forecastRadio.isChecked()) {
                    List<ForecastItem> items = new ArrayList<>();
                    items.add(new ForecastItem("Monday", "H: 72°  L: 56°", "Sunny"));
                    items.add(new ForecastItem("Tuesday", "H: 68°  L: 54°", "Cloudy"));
                    items.add(new ForecastItem("Wednesday", "H: 65°  L: 50°", "Rain"));
                    showForecast(items);
                } else {
                    showCurrentWeather(city, "71°F", "Partly Cloudy");
                }
            }, 2000);
        });
    }

    private void startLoading() {
        isLoading = true;
        loadingDotCount = 0;
        statusText.setVisibility(TextView.VISIBLE);
        searchButton.setEnabled(false);
        currentWeatherCard.setVisibility(LinearLayout.GONE);
        forecastRecyclerView.setVisibility(RecyclerView.GONE);
        loadingHandler.post(loadingRunnable);
    }

    private void stopLoading() {
        isLoading = false;
        loadingHandler.removeCallbacks(loadingRunnable);
        searchButton.setEnabled(true);
    }

    private void showCurrentWeather(String city, String temp, String condition) {
        stopLoading();
        statusText.setVisibility(TextView.GONE);
        forecastRecyclerView.setVisibility(RecyclerView.GONE);
        currentWeatherCard.setVisibility(LinearLayout.VISIBLE);

        cityNameText.setText(city);
        tempText.setText(temp);
        conditionText.setText(condition);
    }

    private void showForecast(List<ForecastItem> forecastItems) {
        stopLoading();
        statusText.setVisibility(TextView.GONE);
        currentWeatherCard.setVisibility(LinearLayout.GONE);
        forecastRecyclerView.setVisibility(RecyclerView.VISIBLE);

        forecastRecyclerView.setAdapter(new ForecastAdapter(forecastItems));
    }

    private void showError(String message) {
        stopLoading();
        currentWeatherCard.setVisibility(LinearLayout.GONE);
        forecastRecyclerView.setVisibility(RecyclerView.GONE);
        statusText.setVisibility(TextView.VISIBLE);
        statusText.setText(message);
    }
}