package com.example.funnylearning.navigation;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.funnylearning.R;
import com.example.funnylearning.recycle.readmathcourse.model_readmath_course;
import com.example.funnylearning.recycle.readmathcourse.adapter_readmath_course;

import java.util.ArrayList;

public class ReadingFragment extends Fragment {

    private RecyclerView couList;
    private final ArrayList<model_readmath_course> courseList = new ArrayList<model_readmath_course>();


    public ReadingFragment() {
        // Required empty public constructor
    }

    public static ReadingFragment newInstance(String param1, String param2) {
        ReadingFragment fragment = new ReadingFragment();

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
        View view = inflater.inflate(R.layout.fragment_reading, container, false);

        couList = (RecyclerView) view.findViewById(R.id.recyclerview_reading_course);

        courseList.clear();
        System.out.println("message is working");
        courseList.add(new model_readmath_course("Free", "Learn the basic words (1)", R.drawable.reading1_image, 1, "8MIN"));

        System.out.println("message is working");
        couList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter_readmath_course customAdapterCourse = new adapter_readmath_course(courseList);
        System.out.println("layout manager is working");
        couList.setAdapter(customAdapterCourse);

        return view;
    }
}