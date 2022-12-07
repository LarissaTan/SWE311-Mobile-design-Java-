package com.example.funnylearning.recycle.game;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.recycle.math_level.adapter_math_level;

import java.util.ArrayList;

public class adapter_game extends RecyclerView.Adapter<adapter_game.ViewHolder>{

    private final ArrayList<model_game> gameList;

    public adapter_game(ArrayList<model_game> gameList) {
        this.gameList = gameList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        model_game list = gameList.get(position);

        holder.image.setImageResource(list.drawable);
        holder.title.setText(list.title);
        holder.rating.setRating((float) list.rating);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class<?> cl = null;
                try{
                    cl = Class.forName(list.link);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                Intent it = new Intent(view.getContext(), cl);
                it.putExtra("gameId",list.gameId);
                view.getContext().startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        RatingBar rating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_game_image);
            title = itemView.findViewById(R.id.item_game_title);
            rating = itemView.findViewById(R.id.item_game_rating_bar);
        }
    }
}
