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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Message#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Message extends Fragment {

    private RecyclerView messageList;
    private final ArrayList<model_chatbox> chatList = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Message() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Message.
     */
    // TODO: Rename and change types and number of parameters
    public static Message newInstance(String param1, String param2) {
        Message fragment = new Message();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_message, container, false);
        messageList = (RecyclerView) view.findViewById(R.id.recyclerview_message);

        chatList.clear();
        chatList.add(new model_chatbox("image","Mr. Ong Cong Kin","Okay","09:41 AM",0));

        messageList.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter_chatbox customAdapter = new adapter_chatbox(chatList);
        messageList.setAdapter(customAdapter);
        // Inflate the layout for this fragment
        return view;
    }


}