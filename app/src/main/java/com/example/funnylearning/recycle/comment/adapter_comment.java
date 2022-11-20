package com.example.funnylearning.recycle.comment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;

import java.util.ArrayList;

public class adapter_comment extends RecyclerView.Adapter<adapter_comment.ViewHolder>{//bug
    private final ArrayList<model_comment> commentList;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView comment_profile;
        TextView comment_name;
        TextView comment_duration;
        TextView comment_comment;


        public ViewHolder(View view) {
            super(view);

            comment_profile = view.findViewById(R.id.video_comment_profile);
            comment_name = view.findViewById(R.id.video_comment_name);
            comment_duration = view.findViewById(R.id.video_comment_duration);
            comment_comment = view.findViewById(R.id.video_comment_comment);
        }
    }


    public adapter_comment(ArrayList<model_comment> commentList) {
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_video_comments, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        model_comment list = commentList.get(position);

        viewHolder.comment_profile.setImageResource(list.profile);//bug
        viewHolder.comment_name.setText(list.name);
        viewHolder.comment_duration.setText(list.duration);
        viewHolder.comment_comment.setText(list.comment);

    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }
}
