package com.example.funnylearning.recycle.course_video;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

public class adapter_course_video extends RecyclerView.Adapter<adapter_course_video.ViewHolder> {

    private ArrayList<model_course_video> courseList;

    public adapter_course_video(ArrayList<model_course_video> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public adapter_course_video.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course_video, parent, false);

        return new adapter_course_video.ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull adapter_course_video.ViewHolder holder, int position) {
        model_course_video list = courseList.get(position);

        holder.image.setImageResource(list.drawable);
        holder.title.setText(list.title);
        holder.rating.setRating((float) list.rating);
        holder.duration.setText(list.duration + " Min");

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent it = new Intent(view.getContext(), Temp_head.class);
                it.putExtra("userId",list.userId);
                it.putExtra("vidId",list.vidId);
                it.putExtra("courseId",list.courseId);
                it.putExtra("tag", "2");
                view.getContext().startActivity(it);
            }
        });

    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        RatingBar rating;
        TextView duration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.item_course_video_image);
            title = itemView.findViewById(R.id.item_course_video_title);
            rating = itemView.findViewById(R.id.item_course_video_ratingbar);
            duration = itemView.findViewById(R.id.item_course_video_duration);

        }
    }
}
