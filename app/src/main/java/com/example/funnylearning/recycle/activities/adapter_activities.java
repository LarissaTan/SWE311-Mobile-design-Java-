package com.example.funnylearning.recycle.activities;

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
import com.example.funnylearning.recycle.mood.adapter_mood;

import java.util.ArrayList;

public class adapter_activities extends RecyclerView.Adapter<adapter_activities.ViewHolder>{//bug
    private final ArrayList<model_activities> activitiesList;
    private int point = -1;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_activities_title;
        ImageView imageView;
        CardView card;

        public ViewHolder(View view) {
            super(view);
            item_activities_title = view.findViewById(R.id.record_activities_title);
            imageView = view.findViewById(R.id.record_activities_image);
            card = view.findViewById(R.id.record_activities_card);
        }
    }


    public adapter_activities(ArrayList<model_activities> activitiesList) {
        this.activitiesList = activitiesList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record_activities, viewGroup, false);

        final adapter_activities.ViewHolder holder = new adapter_activities.ViewHolder(view);


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                point = holder.getAdapterPosition();
                holder.card.setCardBackgroundColor(-934116);
                notifyDataSetChanged();

                model_activities list = activitiesList.get(point);

                UserDayRecordDao dao = new UserDayRecordDao(v.getContext());
                dao.open();

                switch(point){
                    case 0:
                        dao.changeActivity("Party", list.id);
                        break;
                    case 1:
                        dao.changeActivity("Travel", list.id);
                        break;
                    case 2:
                        dao.changeActivity("Beach", list.id);
                        break;
                    default :
                        break;
                }
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        model_activities list = activitiesList.get(position);

        viewHolder.item_activities_title.setText(list.name);//bug
        viewHolder.imageView.setImageResource(list.drawable);

        if (position == point) {
            viewHolder.card.setCardBackgroundColor(-7251798);
        } else {
            viewHolder.card.setCardBackgroundColor(-1183238);
        }

    }

    @Override
    public int getItemCount() {
        return activitiesList.size();
    }
}
