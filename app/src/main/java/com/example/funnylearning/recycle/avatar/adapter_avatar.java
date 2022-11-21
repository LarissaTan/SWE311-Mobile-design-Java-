package com.example.funnylearning.recycle.avatar;

import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;


import java.util.ArrayList;

public class adapter_avatar extends RecyclerView.Adapter<com.example.funnylearning.recycle.avatar.adapter_avatar.ViewHolder>{//bug
    private final ArrayList<model_avatar> avatarList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_option;
        TextView item_keyword;
        TextView item_description;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View view) {
            super(view);

            item_option  = view.findViewById(R.id.item_avatar_tv1);
            item_keyword = view.findViewById(R.id.item_avatar_tv2);
            item_description = view.findViewById(R.id.item_avatar_tv3);
            imageView = view.findViewById(R.id.item_avatar_imgView1);
            cardView = view.findViewById(R.id.item_avatar_card);
        }
    }

    public adapter_avatar(ArrayList<model_avatar> avatarList) {
        this.avatarList = avatarList;
    }

    @NonNull
    @Override
    public com.example.funnylearning.recycle.avatar.adapter_avatar.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mood, viewGroup, false);

        return new com.example.funnylearning.recycle.avatar.adapter_avatar.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(com.example.funnylearning.recycle.avatar.adapter_avatar.ViewHolder viewHolder, final int position) {
        model_avatar list = avatarList.get(position);
        viewHolder.item_option.setText(list.name);//bug
        viewHolder.item_keyword.setText(list.ky);
        viewHolder.item_description.setText(list.des);
        viewHolder.imageView.setImageResource(list.drawable);
    }

    @Override
    public int getItemCount() {
        return avatarList.size();
    }
}
