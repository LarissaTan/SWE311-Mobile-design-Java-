package com.example.funnylearning.register;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funnylearning.R;
//import com.example.funnylearning.recycle.math_level.adapter_math_level;
//import com.example.funnylearning.recycle.math_level.model_math_level;

import java.util.ArrayList;


public class Math_levelFragment extends Fragment {

    //private RecyclerView m_level;
    //RecyclerView.LayoutManager layoutManager;
    //private ArrayList<model_math_level> mList = new ArrayList<model_math_level>();
    //private int imageResourceID;

    public Math_levelFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_math_level, container, false);
    }

    /*@Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
       // dataInitialize();

        m_level = view.findViewById(R.id.recyclerView_math_level);
        m_level.setLayoutManager(new LinearLayoutManager(getContext()));
        m_level.setHasFixedSize(true);
        adapter_math_level adapter = new adapter_math_level(mList);
        m_level.setAdapter(adapter);
    }/*

    /*private void dataInitialize() {

        imageResourceID = R.drawable.math_level_sheep;

        mList.add(new model_math_level(imageResourceID));

    }*/
}