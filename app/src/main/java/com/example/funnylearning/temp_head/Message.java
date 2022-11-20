package com.example.funnylearning.temp_head;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funnylearning.R;
import com.example.funnylearning.recycle.chatbox.adapter_chatbox;
import com.example.funnylearning.recycle.chatbox.model_chatbox;

import java.util.ArrayList;

public class Message extends Fragment {

    private RecyclerView messageList;
    private final ArrayList<model_chatbox> chatList = new ArrayList<>();


    public Message() {
        // Required empty public constructor
    }


    public static Message newInstance(String param1, String param2) {
        Message fragment = new Message();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        messageList = (RecyclerView) view.findViewById(R.id.recyclerview_message);

        chatList.clear();
        System.out.println("message is working");
        chatList.add(new model_chatbox("Mr. Ong Cong Kin","Okay","09:41 AM","2"));
        chatList.add(new model_chatbox("Mr. Cheh Shu Ze","good!","08:20 AM","5"));
        //image will add latter

        System.out.println("message is working");
        messageList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_chatbox customAdapter = new adapter_chatbox(chatList);
        System.out.println("layout manager is working");
        messageList.setAdapter(customAdapter);
        // Inflate the layout for this fragment

        System.out.println("setAdapter is working");
        return view;
    }


}