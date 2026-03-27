package edu.northeastern.wea26sp_team3;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.LinearLayout;

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
            // Networking teammate will connect the API call here.
            statusText.setVisibility(TextView.VISIBLE);
            statusText.setText("Ready to search...");
        });
    }
}