package com.example.funnylearning.recycle.math_level;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

/*public class adapter_math_level extends RecyclerView.Adapter<adapter_math_level.ViewHolder> {

    Context context;
    ArrayList<model_math_level> mList;
    public adapter_math_level(ArrayList<model_math_level> mList) {

        this.context = context;
        this.mList = mList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.item_math_level,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        model_math_level list = mList.get(position);

        holder.titleImage.setImageResource(list.titleImage);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ShapeableImageView titleImage;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);
           titleImage = itemView.findViewById(R.id.title_image);
       }
   }
}*/
