package com.example.funnylearning.navigation;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Database.CartoonsDao;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.recycle.chatbox.adapter_chatbox;
import com.example.funnylearning.recycle.exercise.adapter_exercise;
import com.example.funnylearning.recycle.exercise.model_exercise;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

public class ExerciseFragment extends Fragment {

    private RecyclerView execList;
    private final ArrayList<model_exercise> exerciseList = new ArrayList<model_exercise>();

    public ExerciseFragment() {
        // Required empty public constructor
    }

    public static ExerciseFragment newInstance(String param1, String param2) {
        ExerciseFragment fragment = new ExerciseFragment();
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

        View view = inflater.inflate(R.layout.fragment_exercise, container, false);

        CartoonsDao dao=new CartoonsDao(view.getContext());
        //打开数据库
        dao.open();

        ArrayList<Cartoons> cartoonData=dao.getAllCartoons();

        execList = (RecyclerView) view.findViewById(R.id.recyclerview_exercise);

        exerciseList.clear();
        System.out.println("message is working");
        for (int i = 0; i < cartoonData.size(); i++)
            exerciseList.add(new model_exercise(cartoonData.get(i).Name,cartoonData.get(i).image,cartoonData.get(i).Level));
        //image will add latter

        execList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter_exercise customAdapter = new adapter_exercise(exerciseList);
        execList.setAdapter(customAdapter);


        return view;
    }


}