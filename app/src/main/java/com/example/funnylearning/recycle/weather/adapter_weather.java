package com.example.funnylearning.recycle.weather;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;

import java.util.ArrayList;

public class adapter_weather extends RecyclerView.Adapter<com.example.funnylearning.recycle.weather.adapter_weather.ViewHolder>{//bug
    private final ArrayList<model_weather> weatherList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        CardView card;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.record_weather_image);
            card = view.findViewById(R.id.record_weather_card);
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

        //点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.card.setCardBackgroundColor(-1777665);
            }
        });

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
