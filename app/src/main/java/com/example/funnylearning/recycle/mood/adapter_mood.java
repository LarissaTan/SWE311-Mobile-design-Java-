package com.example.funnylearning.recycle.mood;

import android.content.Context;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;

import java.util.ArrayList;

public class adapter_mood extends RecyclerView.Adapter<com.example.funnylearning.recycle.mood.adapter_mood.ViewHolder>{//bug
    private final ArrayList<model_mood> moodList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_mood_title;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            item_mood_title  = view.findViewById(R.id.record_mood_text);
            imageView = view.findViewById(R.id.record_mood_image);
            cardView = view.findViewById(R.id.record_mood_card);
        }
    }

    public adapter_mood(ArrayList<model_mood> moodList) {
        this.moodList = moodList;
    }

    @NonNull
    @Override
    public adapter_mood.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mood, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(adapter_mood.ViewHolder viewHolder, final int position) {
        model_mood list = moodList.get(position);

        viewHolder.item_mood_title.setText(list.name);//bug
        viewHolder.imageView.setImageResource(list.drawable);
        viewHolder.cardView.setCardBackgroundColor(Color.parseColor(list.color));
    }

    @Override
    public int getItemCount() {
        return moodList.size();
    }
}