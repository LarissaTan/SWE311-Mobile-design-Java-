package com.example.funnylearning.register;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funnylearning.R;

public class Reading_level_2_Fragment extends Fragment {

    public Reading_level_2_Fragment() {
        // Required empty public constructor
    }


    public static Reading_level_2_Fragment newInstance(String param1, String param2) {
        Reading_level_2_Fragment fragment = new Reading_level_2_Fragment();
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
        return inflater.inflate(R.layout.fragment_reading_level_2_, container, false);
    }
}