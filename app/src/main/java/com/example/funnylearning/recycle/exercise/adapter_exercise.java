package com.example.funnylearning.recycle.exercise;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;

import java.util.ArrayList;

public class adapter_exercise extends RecyclerView.Adapter<adapter_exercise.ViewHolder> {
    private final ArrayList<model_exercise> exerciseList;

    //定义点击事件
    private OnItemClickListener onItemClickListener;


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
        viewHolder. item_exercise_title.setText(list.name);

        //点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(), Temp_head.class);
                it.putExtra("tag","4");
                view.getContext().startActivity(it);
            }
        });

    }


    //定义点击接口
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    //点击方法
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public int getItemCount() {
        return exerciseList.size();
    }
}
