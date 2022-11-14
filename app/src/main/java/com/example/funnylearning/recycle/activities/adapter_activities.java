package com.example.funnylearning.recycle.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;

import java.util.ArrayList;

public class adapter_activities extends RecyclerView.Adapter<adapter_activities.ViewHolder>{//bug
    private final ArrayList<model_activities> activitiesList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_activities_title;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            item_activities_title = view.findViewById(R.id.record_activities_title);
            imageView = view.findViewById(R.id.record_activities_image);
        }
    }


    public adapter_activities(ArrayList<model_activities> activitiesList) {
        this.activitiesList = activitiesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record_activities, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        model_activities list = activitiesList.get(position);

        viewHolder.item_activities_title.setText(list.name);//bug
        viewHolder.imageView.setImageResource(list.drawable);

    }

    @Override
    public int getItemCount() {
        return activitiesList.size();
    }
}
