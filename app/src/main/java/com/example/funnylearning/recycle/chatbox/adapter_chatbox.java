package com.example.funnylearning.recycle.chatbox;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;

import java.util.ArrayList;

public class adapter_chatbox extends RecyclerView.Adapter<adapter_chatbox.ViewHolder>{
    private final ArrayList<model_chatbox> chatList;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView item_chatbox_title, item_chatbox_message, item_chatbox_time, item_chatbox_alert_num;

        public ViewHolder(View view) {
            super(view);
            item_chatbox_message = view.findViewById(R.id.item_chatbox_message);
            item_chatbox_time = view.findViewById(R.id.item_chatbox_time);
            item_chatbox_alert_num = view.findViewById(R.id.item_chatbox_alert_num);
            item_chatbox_title = view.findViewById(R.id.item_chatbox_title);
        }
    }


    public adapter_chatbox(ArrayList<model_chatbox> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_chatbox, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        model_chatbox list = chatList.get(position);

        viewHolder. item_chatbox_title.setText(list.name);
        viewHolder. item_chatbox_message.setText(list.messageTop);
        viewHolder. item_chatbox_alert_num.setText(list.alert_num);
        viewHolder. item_chatbox_time.setText(list.time);
    }


    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
