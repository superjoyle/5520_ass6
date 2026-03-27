package edu.northeastern.wea26sp_team3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.os.Handler;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    // Input UI
    private EditText cityInput;
    private RadioGroup weatherTypeGroup;
    private RadioButton currentWeatherRadio;
    private ImageView currentWeatherIcon;
    private RadioButton forecastRadio;
    private Switch unitSwitch;
    private Button searchButton;
    private TextView statusText;

    // Current weather UI
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
            if (!isLoading) {
                return;
            }

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

        bindViews();
        setupRecyclerView();
        setupListeners();
    }

    private void bindViews() {
        cityInput = findViewById(R.id.cityInput);
        weatherTypeGroup = findViewById(R.id.weatherTypeGroup);
        currentWeatherRadio = findViewById(R.id.currentWeatherRadio);
        currentWeatherIcon = findViewById(R.id.currentWeatherIcon);
        forecastRadio = findViewById(R.id.forecastRadio);
        unitSwitch = findViewById(R.id.unitSwitch);
        searchButton = findViewById(R.id.searchButton);
        statusText = findViewById(R.id.statusText);

        currentWeatherCard = findViewById(R.id.currentWeatherCard);
        cityNameText = findViewById(R.id.cityNameText);
        tempText = findViewById(R.id.tempText);
        conditionText = findViewById(R.id.conditionText);

        forecastRecyclerView = findViewById(R.id.forecastRecyclerView);
    }

    private void setupRecyclerView() {
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupListeners() {
        searchButton.setOnClickListener(v -> {
            String city = cityInput.getText().toString().trim();

            if (city.isEmpty()) {
                showError("Please enter a city.");
                return;
            }

            startLoading();

            // Demo only for UI testing.
            searchButton.postDelayed(() -> {
                if (forecastRadio.isChecked()) {
                    List<ForecastItem> items = new ArrayList<>();

                    items.add(new ForecastItem(
                            "Monday",
                            buildHighLowText(22, 13),
                            "Sunny"
                    ));

                    items.add(new ForecastItem(
                            "Tuesday",
                            buildHighLowText(19, 11),
                            "Cloudy"
                    ));

                    items.add(new ForecastItem(
                            "Wednesday",
                            buildHighLowText(16, 9),
                            "Rain"
                    ));

                    showForecast(items);
                } else {
                    String currentTemp = formatTemperature(21);
                    showCurrentWeather(city, currentTemp, "Partly Cloudy");
                }
            }, 2000);
        });
    }

    private void startLoading() {
        isLoading = true;
        loadingDotCount = 0;

        statusText.setVisibility(View.VISIBLE);
        searchButton.setEnabled(false);

        currentWeatherCard.setVisibility(View.GONE);
        forecastRecyclerView.setVisibility(View.GONE);

        loadingHandler.post(loadingRunnable);
    }

    private void stopLoading() {
        isLoading = false;
        loadingHandler.removeCallbacks(loadingRunnable);
        searchButton.setEnabled(true);
    }

    private void showCurrentWeather(String city, String temp, String condition) {
        stopLoading();

        statusText.setVisibility(View.GONE);
        forecastRecyclerView.setVisibility(View.GONE);
        currentWeatherCard.setVisibility(View.VISIBLE);

        cityNameText.setText(city);
        tempText.setText(temp);
        conditionText.setText(condition);

        currentWeatherIcon.setImageResource(getIconForCondition(condition));
    }

    private void showForecast(List<ForecastItem> forecastItems) {
        stopLoading();

        statusText.setVisibility(View.GONE);
        currentWeatherCard.setVisibility(View.GONE);
        forecastRecyclerView.setVisibility(View.VISIBLE);

        ForecastAdapter adapter = new ForecastAdapter(forecastItems);
        forecastRecyclerView.setAdapter(adapter);
    }

    private void showError(String message) {
        stopLoading();

        currentWeatherCard.setVisibility(View.GONE);
        forecastRecyclerView.setVisibility(View.GONE);

        statusText.setVisibility(View.VISIBLE);
        statusText.setText(message);
    }

    private String formatTemperature(double celsiusTemp) {
        if (unitSwitch.isChecked()) {
            double fahrenheit = (celsiusTemp * 9.0 / 5.0) + 32.0;
            return String.format(Locale.US, "%.0f°F", fahrenheit);
        } else {
            return String.format(Locale.US, "%.0f°C", celsiusTemp);
        }
    }

    private String buildHighLowText(double highCelsius, double lowCelsius) {
        return "H: " + formatTemperature(highCelsius) + "  L: " + formatTemperature(lowCelsius);
    }

    private int getIconForCondition(String condition) {
        String lower = condition.toLowerCase();

        if (lower.contains("rain")) {
            return R.drawable.ic_rain;
        } else if (lower.contains("cloud")) {
            return R.drawable.ic_cloud;
        } else if (lower.contains("sun") || lower.contains("clear")) {
            return R.drawable.ic_sunny;
        } else {
            return R.drawable.ic_cloud;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loadingHandler.removeCallbacks(loadingRunnable);
    }
}