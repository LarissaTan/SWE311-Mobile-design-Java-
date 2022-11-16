package com.example.funnylearning.temp_head;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funnylearning.R;

public class VideoFragment extends Fragment {


    public VideoFragment() {
        // Required empty public constructor
    }


    public static VideoFragment newInstance(String param1, String param2) {
        VideoFragment fragment = new VideoFragment();


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
        return inflater.inflate(R.layout.fragment_video, container, false);
    }
}