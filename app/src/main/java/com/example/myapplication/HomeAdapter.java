package com.example.myapplication;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
Activity activity;
List<Weather> weatherList;
ConvertDateTime cv = new ConvertDateTime();
ConvertIconImage cvim = new ConvertIconImage();
    public HomeAdapter(Activity activity, List<Weather> weatherList) {
        this.activity = activity;
        this.weatherList = weatherList;
    }
    public  void reloadData(List<Weather> list){
        this.weatherList = list;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = activity.getLayoutInflater().inflate(R.layout.item_weather_view,parent,false);
        HomeHolder hd = new HomeHolder(itemView);
        return hd;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        HomeHolder hd = (HomeHolder) holder;
        Weather weather = weatherList.get(position);
        hd.tvTime.setText(cv.convertTime(weather.getDateTime()));
        hd.tvTemperature.setText(String.valueOf(weather.getTemperature().getValue()));
        Glide.with(activity).load(cvim.convertIcon(weather.getWeatherIcon())).into(hd.ivIcon);
    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
    public class HomeHolder extends RecyclerView.ViewHolder {
        TextView tvTime,tvTemperature;
        ImageView ivIcon;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvTemperature = itemView.findViewById(R.id.tvItemTemperature);
            ivIcon = itemView.findViewById(R.id.ivItemIcon);
        }
    }
}
