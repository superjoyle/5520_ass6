package edu.northeastern.wea26sp_team3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ForecastViewHolder> {

    private final List<ForecastItem> items;

    public ForecastAdapter(List<ForecastItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ForecastViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_forecast, parent, false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ForecastViewHolder holder, int position) {
        ForecastItem item = items.get(position);

        holder.dayText.setText(item.day);
        holder.highLowText.setText(item.highLow);
        holder.conditionText.setText(item.condition);

        holder.weatherIcon.setImageResource(getIconForCondition(item.condition));
    }

    @Override
    public int getItemCount() {
        return items.size();
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

    static class ForecastViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherIcon;
        TextView dayText;
        TextView highLowText;
        TextView conditionText;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherIcon = itemView.findViewById(R.id.weatherIcon);
            dayText = itemView.findViewById(R.id.dayText);
            highLowText = itemView.findViewById(R.id.highLowText);
            conditionText = itemView.findViewById(R.id.forecastConditionText);
        }
    }
}