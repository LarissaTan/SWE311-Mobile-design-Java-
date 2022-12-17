package com.example.funnylearning.recycle.exercise;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;
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

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class adapter_exercise extends RecyclerView.Adapter<adapter_exercise.ViewHolder> {
    private final ArrayList<model_exercise> exerciseList;

    //定义点击事件
    private OnItemClickListener onItemClickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_exercise_title;
        RatingBar cartoon_level;
        ImageView item_exercise_image;

        public ViewHolder(View view) {
            super(view);
            item_exercise_title  = view.findViewById(R.id.item_exercise_title);
            cartoon_level = view.findViewById(R.id.cartoon_level);
            item_exercise_image = view.findViewById(R.id.item_exercise_image);
        }
    }


    public adapter_exercise(ArrayList<model_exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_exercise, viewGroup, false);

        /*      需要加入这个才能显示图片，是因为在Android 4.0以上，网络连接不能放在主线程上     */
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {
        model_exercise list = exerciseList.get(position);
        viewHolder. item_exercise_title.setText(list.name);
        viewHolder. cartoon_level.setRating(list.num);

        switch(list.image){
            case "pancakes":
                viewHolder.item_exercise_image.setImageResource(R.drawable.pancakes);
                break;
            case "alphabet":
                viewHolder.item_exercise_image.setImageResource(R.drawable.alphabet);
                break;
            case "coin":
                viewHolder.item_exercise_image.setImageResource(R.drawable.coin);
                break;
            case "destiny":
                viewHolder.item_exercise_image.setImageResource(R.drawable.destiny);
                break;

            default :
                break;
        }



        //点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(view.getContext(), Temp_head.class);
                it.putExtra("tag","4");
                it.putExtra("itemNum", String.valueOf(position));
                view.getContext().startActivity(it);
            }
        });

    }

    public Drawable LoadImageFromWebOperations(String url) {

        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (Exception e) {
            System.out.println("Exc=" + e);
            System.out.println("its not working");
            return null;
        }

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
