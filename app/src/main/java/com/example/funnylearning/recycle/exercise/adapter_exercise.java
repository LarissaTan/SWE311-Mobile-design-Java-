package com.example.funnylearning.recycle.exercise;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;

import java.util.ArrayList;

public class adapter_exercise extends RecyclerView.Adapter<adapter_exercise.ViewHolder>{//bug
    private final ArrayList<model_exercise> exerciseList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_exercise_title;

        public ViewHolder(View view) {
            super(view);
            item_exercise_title  = view.findViewById(R.id.item_exercise_title);
        }
    }


    public adapter_exercise(ArrayList<model_exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        model_exercise list = exerciseList.get(position);

        viewHolder. item_exercise_title.setText(list.name);//bug

    }


    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
