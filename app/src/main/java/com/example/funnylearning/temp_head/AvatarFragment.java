package com.example.funnylearning.temp_head;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.R;
import com.example.funnylearning.recycle.avatar.adapter_avatar;
import com.example.funnylearning.recycle.avatar.model_avatar;
import com.example.funnylearning.recycle.mood.adapter_mood;


import java.util.ArrayList;


public class AvatarFragment extends Fragment {

    private RecyclerView optList;
    private final ArrayList<model_avatar> avatarList = new ArrayList<model_avatar>();

    public AvatarFragment() {

        // Required empty public constructor
    }


    public static AvatarFragment newInstance(String param1, String param2) {
        AvatarFragment fragment = new AvatarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_avatar, container, false);

        optList = (RecyclerView) view.findViewById(R.id.recyclerview_avatar);

        avatarList.clear();
        avatarList.add(new model_avatar("Option 1","Keywords about this photo","Dark black, handsome, cool tones",R.drawable.user_avatar1));
        avatarList.add(new model_avatar("Option 2","Keywords about this photo","blue, cute, cartoon",R.drawable.user_avatar2));
        avatarList.add(new model_avatar("Option 3","Keywords about this photo","little boy, cute, cat ears",R.drawable.user_avatar3));
        avatarList.add(new model_avatar("Option 4","Keywords about this photo","black, sunglasses, adult male",R.drawable.user_avatar4));
        avatarList.add(new model_avatar("Option 5","Keywords about this photo","warm colors, puppy, cute",R.drawable.user_avatar5));

        optList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter_avatar customAdapterAvatar = new adapter_avatar(avatarList);
        optList.setAdapter(customAdapterAvatar);
        return view;
    }
}