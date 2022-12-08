package com.example.funnylearning.navigation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.example.funnylearning.Bean.model.CourseVideo;
import com.example.funnylearning.Database.CourseVideoDao;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.recycle.course_video.adapter_course_video;
import com.example.funnylearning.recycle.course_video.model_course_video;
import com.example.funnylearning.recycle.game.adapter_game;
import com.example.funnylearning.recycle.game.model_game;
import com.example.funnylearning.register.Math_game_1;
import com.example.funnylearning.register.Math_game_2;

import java.util.ArrayList;


public class MathFragment extends Fragment {

    private RecyclerView cvList;
    private final ArrayList<model_course_video> courseList = new ArrayList<model_course_video>();

    private RecyclerView glist;
    private final ArrayList<model_game> gameList = new ArrayList<model_game>();

    public MathFragment() {
        // Required empty public constructor
    }

    public static final String EXTRA_NAME = "tag";
    public String tag;
    public String tagm;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*FrameLayout video = view.findViewById(R.id.math_video);

        FrameLayout feed = view.findViewById(R.id.feed);
        FrameLayout sheep = view.findViewById(R.id.count_sheep);

        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tag = "2";
                Intent it = new Intent(getContext(), Temp_head.class);
                it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
            }
        });

        feed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tagm = "2";
                System.out.println("tagr = "+tag);
                Intent it = new Intent(getContext(), Math_game_2.class);
                it.putExtra("tagm",tagm);
                startActivity(it);
            }
        });

        sheep.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                tagm = "1";
                System.out.println("tagr = "+tag);
                Intent it = new Intent(getContext(), Math_game_1.class);
                it.putExtra("tagm",tagm);
                startActivity(it);

            }*/
        /*});*/
    }

    public static MathFragment newInstance(String param1, String param2) {
        MathFragment fragment = new MathFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint({"NotifyDataSetChanged", "MissingInflatedId"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math, container, false);
        Integer userId = getArguments().getInt("userId");
        cvList = (RecyclerView) view.findViewById(R.id.recyclerview_math_course);
        glist = (RecyclerView) view.findViewById(R.id.recyclerview_math_game);

        CourseVideoDao courseVideoDao = new CourseVideoDao(view.getContext());
        courseVideoDao.open();

        ArrayList<CourseVideo> videoList = new ArrayList<>();
        videoList = courseVideoDao.getAllCourseVideoByTypeName("Math");

        courseList.clear();
        for (int i = 0; i < videoList.size(); i++){
            System.out.println(videoList.get(i).getCourseName());
            courseList.add(new model_course_video(userId, videoList.get(i).getCourseId(),videoList.get(i).getVideoId(), videoList.get(i).getCoursePicture(),videoList.get(i).getCourseName(),videoList.get(i).getLevel(),10));
        }

        gameList.clear();
        gameList.add(new model_game(1,"com.example.funnylearning.register.Math_game_1", R.drawable.math_sheep,"Count the sheep",1));
        gameList.add(new model_game(2,"com.example.funnylearning.register.Math_game_2", R.drawable.math_cat, "Feed the cat", 4));

        adapter_course_video adapter = new adapter_course_video(courseList);
        adapter.notifyDataSetChanged();
        adapter_game customAdapter = new adapter_game(gameList);
        customAdapter.notifyDataSetChanged();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        cvList.setLayoutManager(linearLayoutManager);
        cvList.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        glist.setLayoutManager(linearLayoutManager1);
        glist.setAdapter(customAdapter);

        return view;
    }
}