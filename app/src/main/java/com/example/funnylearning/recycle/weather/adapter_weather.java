package com.example.funnylearning.recycle.weather;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.Database.UserDayRecordDao;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;

import java.util.ArrayList;

public class adapter_weather extends RecyclerView.Adapter<com.example.funnylearning.recycle.weather.adapter_weather.ViewHolder>{//bug
    private final ArrayList<model_weather> weatherList;
    private int point = -1;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, click;
        CardView card;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.record_weather_image);
            card = view.findViewById(R.id.record_weather_card);
            click = view.findViewById(R.id.record_weather_onclick);
        }
    }

    public adapter_weather(ArrayList<model_weather> weatherList) {
        this.weatherList = weatherList;
    }

    @NonNull
    @Override
    public adapter_weather.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_record_weather, viewGroup, false);

        final ViewHolder holder = new ViewHolder(view);

        /*
        添加选中的打勾显示
         */
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将点击的位置传出去
                point = holder.getAdapterPosition();
                //在点击监听里最好写入setVisibility(View.VISIBLE);这样可以避免效果会闪
                holder.click.setVisibility(View.VISIBLE);
                //刷新界面 notify 通知Data 数据set设置Changed变化
                //在这里运行notifyDataSetChanged 会导致下面的onBindViewHolder 重新加载一遍
                notifyDataSetChanged();

                model_weather list = weatherList.get(point);

                UserDayRecordDao dao = new UserDayRecordDao(v.getContext());
                dao.open();

                switch(point){
                    case 0:
                        dao.changeWeather("sunny", list.id);
                        break;
                    case 1:
                        dao.changeWeather("overcast", list.id);
                        break;
                    case 2:
                        dao.changeWeather("cloudy", list.id);
                        break;
                    case 3:
                        dao.changeWeather("sunshower", list.id);
                        break;
                    case 4:
                        dao.changeWeather("rain", list.id);
                        break;
                    case 5:
                        dao.changeWeather("thunder", list.id);
                        break;

                    default :
                        break;
                }
            }
        });


        return holder;
    }

    @Override
    public void onBindViewHolder(adapter_weather.ViewHolder viewHolder, final int position) {
        model_weather list = weatherList.get(position);

        viewHolder.imageView.setImageResource(list.drawable);

        /*
        onBindViewHolder 方法可能是在class里for添加了其他视图
        引入point与当前的position判断，判断在点击的位置上显示打勾图片，在其他位置上不显示打勾
         */
        if (position == point) {
            viewHolder.click.setVisibility(View.VISIBLE);
        } else {
            viewHolder.click.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }
}
