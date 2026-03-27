package edu.northeastern.wea26sp_team3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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

        // Placeholder icon mapping for now
        holder.weatherIcon.setImageResource(android.R.drawable.ic_menu_compass);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ForecastViewHolder extends RecyclerView.ViewHolder {
        ImageView weatherIcon;
        TextView dayText;
        TextView highLowText;
        TextView conditionText;

        ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            weatherIcon = itemView.findViewById(R.id.weatherIcon);
            dayText = itemView.findViewById(R.id.dayText);
            highLowText = itemView.findViewById(R.id.highLowText);
            conditionText = itemView.findViewById(R.id.forecastConditionText);
        }
    }
}