package com.ceosilvajr.android.weatherapp.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ceosilvajr.android.weatherapp.R;
import com.ceosilvajr.android.weatherapp.models.Weather;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ceosilvajr on 20/02/16.
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private List<Weather> mValues;

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.iv_icon)
        ImageView ivIcon;
        @Bind(R.id.tv_date_item)
        TextView tvDate;
        @Bind(R.id.tv_temp_min)
        TextView tvTempMin;
        @Bind(R.id.tv_temp_max)
        TextView tvTempMax;
        @Bind(R.id.tv_description)
        TextView tvDescription;
        @Bind(R.id.tv_wind_speed)
        TextView tvWindSpeed;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public Weather getValueAt(int position) {
        return mValues.get(position);
    }

    public WeatherAdapter(List<Weather> items) {
        this.mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.container_forecast_item,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Weather weather = mValues.get(position);
        holder.tvDate.setText(weather.getFormattedForecastDate());
        holder.tvDescription.setText(weather.getDescription());
        holder.tvTempMin.setText(weather.getFormattedTemperatureMin());
        holder.tvTempMax.setText(weather.getFormattedTemperatureMax());
        holder.tvWindSpeed.setText(weather.getFormattedWindSpeed());
        Glide.with(holder.ivIcon.getContext()).load(weather.getIconUrl())
                .placeholder(R.drawable.img_weather_clear)
                .into(holder.ivIcon);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }
}
