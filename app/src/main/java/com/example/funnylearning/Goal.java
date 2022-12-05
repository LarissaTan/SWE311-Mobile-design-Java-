package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.funnylearning.Bean.model.CourseType;
import com.example.funnylearning.Database.CourseTypeDao;

import java.util.ArrayList;

public class Goal extends AppCompatActivity {

    CourseTypeDao courseTypeDao = new CourseTypeDao(this);
    ArrayList<CourseType> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        Button goal1, goal2, goal3;
        Button btn;

        btn = findViewById(R.id.set_goal_btn);
        goal1 = findViewById(R.id.goal1);
        goal2 = findViewById(R.id.goal2);
        goal3 = findViewById(R.id.goal3);

        //courseList = courseTypeDao.getAllCourse();

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(Goal.this, ChoosePhoto.class);
                startActivity(it);
            }
        });
    }
}