package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Bean.model.CourseType;
import com.example.funnylearning.Bean.model.UserLevel;
import com.example.funnylearning.Database.CourseTypeDao;
import com.example.funnylearning.Database.UserGoalLevelDao;

import java.util.ArrayList;

public class Goal extends AppCompatActivity {

    CourseTypeDao courseTypeDao = new CourseTypeDao(this);
    UserGoalLevelDao userGoalLevelDao = new UserGoalLevelDao(this);
    ArrayList<CourseType> courseList;
    int goalCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        Bundle extras = getIntent().getExtras();
        int userId =0;
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }
        int finalUserId = userId;

        courseList = courseTypeDao.getGoalList(finalUserId);

        Button goal1, goal2, goal3;
        Button btn;
        CourseType[] course = {null, null, null};
        CourseType[] chosenGoal = {null, null, null};
        TextView[] chosenGoalText = {null, null, null};
        ImageView cancelGoal1, cancelGoal2, cancelGoal3;
        goalCount = 0;

        course[0] = updateGoal();
        course[1] = updateGoal();
        course[2] = updateGoal();

        btn = findViewById(R.id.set_goal_btn);
        goal1 = findViewById(R.id.goal1);
        goal2 = findViewById(R.id.goal2);
        goal3 = findViewById(R.id.goal3);

        goal1.setText(course[0].getTypeName());
        goal2.setText(course[1].getTypeName());
        goal3.setText(course[2].getTypeName());

        chosenGoalText[0] = findViewById(R.id.chosen_goal_1);
        chosenGoalText[1] = findViewById(R.id.chosen_goal_2);
        chosenGoalText[2] = findViewById(R.id.chosen_goal_3);

        cancelGoal1 = findViewById(R.id.chosen_goal_1_cancel);
        cancelGoal2 = findViewById(R.id.chosen_goal_2_cancel);
        cancelGoal3 = findViewById(R.id.chosen_goal_3_cancel);

        goal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goalCount < 3){
                    if(course[0] == null)
                    {
                        course[0] = updateGoal();
                    }

                    if(course[0] != null){
                        chosenGoal[goalCount]=course[0];
                        chosenGoalText[goalCount].setText(chosenGoal[goalCount].getTypeName());
                        goalCount++;

                        course[0] = updateGoal();
                        if(course[0] == null)
                        {
                            goal1.setText("Currently no more goal");
                        }else{
                            goal1.setText(course[0].getTypeName());
                        }
                    }else {
                        Toast.makeText(Goal.this, "Sorry! No more goal!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Goal.this, "Too much goal!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goalCount < 3){
                    if(course[1] == null)
                    {
                        course[1] = updateGoal();
                    }

                    if(course[1] != null){
                        chosenGoal[goalCount]=course[1];
                        chosenGoalText[goalCount].setText(chosenGoal[goalCount].getTypeName());
                        goalCount++;

                        course[1] = updateGoal();
                        if(course[1] == null)
                        {
                            goal2.setText("Currently no more goal");
                        }else{
                            goal2.setText(course[1].getTypeName());
                        }
                    }else {
                        Toast.makeText(Goal.this, "Sorry! No more goal!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Goal.this, "Too much goal!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        goal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goalCount < 3){
                    if(course[2] == null)
                    {
                        course[2] = updateGoal();
                    }

                    if(course[2] != null){
                        chosenGoal[goalCount]=course[2];
                        chosenGoalText[goalCount].setText(chosenGoal[goalCount].getTypeName());
                        goalCount++;

                        course[2] = updateGoal();
                        if(course[2] == null)
                        {
                            goal3.setText("Currently no more goal");
                        }else{
                            goal3.setText(course[2].getTypeName());
                        }
                    }else {
                        Toast.makeText(Goal.this, "Sorry! No more goal!", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(Goal.this, "Too much goal!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelGoal1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goalCount > 0) {
                    returnGoal(chosenGoal[0]);
                    chosenGoal[0] = null;
                    goalCount--;

                    if(goalCount == 0){
                        chosenGoalText[goalCount].setText("Select a goal...");
                    }else{
                        for(int i=0; i<goalCount; i++) {
                            chosenGoal[i] = chosenGoal[i+1];
                            chosenGoalText[i].setText(chosenGoal[i].getTypeName());
                        }

                        chosenGoal[goalCount] = null;
                        chosenGoalText[goalCount].setText("Select a goal...");
                    }
                }
            }
        });

        cancelGoal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goalCount>0){
                    returnGoal(chosenGoal[1]);
                    chosenGoal[1] = null;
                    goalCount--;

                    if(goalCount == 1){
                        chosenGoalText[goalCount].setText("Select a goal...");
                    }else{
                        for(int i=1; i<goalCount; i++) {
                            chosenGoal[i] = chosenGoal[i+1];
                            chosenGoalText[i].setText(chosenGoal[i].getTypeName());
                        }

                        chosenGoal[goalCount] = null;
                        chosenGoalText[goalCount].setText("Select a goal...");
                    }
                }
            }
        });

        cancelGoal3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(goalCount>0){
                    returnGoal(chosenGoal[2]);
                    chosenGoal[2] = null;
                    goalCount--;

                    if(goalCount == 2){
                        chosenGoalText[goalCount].setText("Select a goal...");
                    }else{
                        for(int i=2; i<goalCount; i++) {
                            chosenGoal[i] = chosenGoal[i+1];
                            chosenGoalText[i].setText(chosenGoal[i].getTypeName());
                        }

                        chosenGoal[goalCount] = null;
                        chosenGoalText[goalCount].setText("Select a goal...");
                    }
                }
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                for(int i=0; i<3; i++){
                    if(chosenGoal[i]!=null){
                        UserLevel userLevel = new UserLevel();
                        userLevel.setUserId(finalUserId);
                        userLevel.setTypeId(chosenGoal[i].getTypeId());
                        userGoalLevelDao.insertGoal(userLevel);
                    }
                }

                Intent it = new Intent(Goal.this, ChoosePhoto.class);
                it.putExtra("userId",finalUserId);
                startActivity(it);
                finish();
            }
        });
    }

    private CourseType updateGoal()
    {
        if(courseList.size()> 0){
            CourseType courseType = courseList.get(0);
            courseList.remove(0);

            return courseType;
        }else {
            return null;
        }
    }

    private void returnGoal(CourseType courseType)
    {
        courseList.add(courseType);
    }
}