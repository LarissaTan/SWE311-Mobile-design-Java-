package com.example.funnylearning.recycle.mood;

import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.Database.UserDayRecordDao;
import com.example.funnylearning.R;
import com.example.funnylearning.recycle.weather.adapter_weather;

import java.util.ArrayList;

public class adapter_mood extends RecyclerView.Adapter<com.example.funnylearning.recycle.mood.adapter_mood.ViewHolder>{//bug
    private final ArrayList<model_mood> moodList;
    private int point = -1;

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

        final adapter_mood.ViewHolder holder = new adapter_mood.ViewHolder(view);

        /*
        添加选中的打勾显示
         */
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = holder.getAdapterPosition();
                holder.cardView.setCardBackgroundColor(-934116);
                notifyDataSetChanged();

                model_mood list = moodList.get(point);

                UserDayRecordDao dao = new UserDayRecordDao(v.getContext());
                dao.open();

                switch(point){
                    case 0:
                        dao.changeMood("sad", list.id);
                        break;
                    case 1:
                        dao.changeMood("happy", list.id);
                        break;
                    case 2:
                        dao.changeMood("angry", list.id);
                        break;
                    case 3:
                        dao.changeMood("sleepy", list.id);
                        break;
                    default :
                        break;
                }
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(adapter_mood.ViewHolder viewHolder, final int position) {
        model_mood list = moodList.get(position);

        viewHolder.item_mood_title.setText(list.name);//bug
        viewHolder.imageView.setImageResource(list.drawable);
        //viewHolder.cardView.setCardBackgroundColor(Color.parseColor(list.color));

        if (position == point) {
            viewHolder.cardView.setCardBackgroundColor(-7251798);
        } else {
            viewHolder.cardView.setCardBackgroundColor(Color.parseColor(list.color));
        }
    }

    @Override
    public int getItemCount() {
        return moodList.size();
    }
}