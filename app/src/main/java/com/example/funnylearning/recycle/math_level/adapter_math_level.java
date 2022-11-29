package com.example.funnylearning.recycle.math_level;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;

import java.util.ArrayList;
import java.util.List;

public class adapter_math_level extends RecyclerView.Adapter<adapter_math_level.ViewHolder> {

    private final ArrayList<model_math_level> mathLevelList;

    public adapter_math_level(ArrayList<model_math_level> mathLevelList) {
        this.mathLevelList = mathLevelList;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_math_level_title;

        public ViewHolder(View view) {
            super(view);
            item_math_level_title = view.findViewById(R.id.item_math_level_title);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_math_level, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        model_math_level list = mathLevelList.get(position);
        viewHolder.item_math_level_title.setText(list.name);
    }

    @Override
    public int getItemCount() {
        return mathLevelList.size();
    }

    public List<model_math_level> getList()
    {
        return mathLevelList;
    }

}
