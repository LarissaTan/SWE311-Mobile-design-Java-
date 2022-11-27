package com.example.funnylearning.navigation;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.funnylearning.ForgotPswEmail;
import com.example.funnylearning.ForgotPswEnter;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;


public class MathFragment extends Fragment {

 public MathFragment() {
        // Required empty public constructor
    }

    public static final String EXTRA_NAME = "tag";
    public String tag;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FrameLayout video = view.findViewById(R.id.math_video);
        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tag = "2";
                Intent it = new Intent(getContext(), Temp_head.class);
                it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
            }
        });
    }

    public static MathFragment newInstance(String param1, String param2) {
        MathFragment fragment = new MathFragment();
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
        return inflater.inflate(R.layout.fragment_math, container, false);
    }
}