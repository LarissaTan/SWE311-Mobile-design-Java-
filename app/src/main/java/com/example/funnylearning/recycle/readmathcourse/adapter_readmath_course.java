package com.example.funnylearning.recycle.readmathcourse;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;

import java.util.ArrayList;

public class adapter_readmath_course extends RecyclerView.Adapter<com.example.funnylearning.recycle.readmathcourse.adapter_readmath_course.ViewHolder>{//bug
    private final ArrayList<model_readmath_course>courseList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_course_type;
        TextView item_course_title;
        ImageView course_image;
        RatingBar course_rating_bar;
        TextView course_time;

        public ViewHolder(View view) {
            super(view);

            item_course_type = view.findViewById(R.id.readmath_course_type);
            item_course_title = view.findViewById(R.id.readmath_course_title);
            course_image = view.findViewById(R.id.readmath_course_image);
            course_rating_bar = view.findViewById(R.id.readmath_course_ratingbar);
            course_time = view.findViewById(R.id.readmath_course_time);
        }
    }

    public adapter_readmath_course(ArrayList<model_readmath_course> courseList) {
        this.courseList = courseList;
    }

    @NonNull
    @Override
    public adapter_readmath_course.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_readmath_course, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(adapter_readmath_course.ViewHolder viewHolder, final int position) {
        model_readmath_course list = courseList.get(position);

        viewHolder.item_course_type.setText(list.type);
        viewHolder.item_course_title.setText(list.title);//bug
        viewHolder.course_image.setImageResource(list.drawable);
        viewHolder.course_rating_bar.setRating(list.star);
        viewHolder.course_time.setText(list.time);
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }
}