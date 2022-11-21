package com.example.funnylearning.temp_head;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funnylearning.R;
import com.example.funnylearning.recycle.avatar.model_avatar;
import com.example.funnylearning.recycle.mood.model_mood;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AvatarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
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
        return inflater.inflate(R.layout.fragment_avatar, container, false);

        mdList = (RecyclerView) view.findViewById(R.id.recyclerview_record_mood);
    }
}