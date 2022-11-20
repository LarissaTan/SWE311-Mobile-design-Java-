package com.example.funnylearning.recycle.weather;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;

import java.util.ArrayList;

public class adapter_weather extends RecyclerView.Adapter<com.example.funnylearning.recycle.weather.adapter_weather.ViewHolder>{//bug
    private final ArrayList<model_weather> weatherList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);

            imageView = view.findViewById(R.id.record_weather_image);
        }
    }

    public adapter_weather(ArrayList<model_weather> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public adapter_weather.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record_weather, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(adapter_weather.ViewHolder viewHolder, final int position) {
        model_weather list = weatherList.get(position);

        viewHolder.imageView.setImageResource(list.drawable);

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
