package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.funnylearning.Bean.model.UserLevel;
import com.example.funnylearning.Database.CourseTypeDao;
import com.example.funnylearning.Database.UserGoalLevelDao;

import java.util.ArrayList;

public class MathLevel extends AppCompatActivity {

    UserGoalLevelDao userGoalLevelDao = new UserGoalLevelDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math_level);
        Bundle extras = getIntent().getExtras();
        int userId =0;
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }
        int finalUserId = userId;

        ToggleButton mathlvl1, mathlvl2, mathlvl3, mathlvl4;
        Button btn;

        btn = findViewById(R.id.math_level_btn1);
        mathlvl1 = findViewById(R.id.math_level_tbtn1);
        mathlvl2 = findViewById(R.id.math_level_tbtn2);
        mathlvl3 = findViewById(R.id.math_level_tbtn3);
        mathlvl4 = findViewById(R.id.math_level_tbtn4);

        Boolean[] mathLevel = {false, false, false, false};
        final int[] mathId = {1, 3, 2, 4};

        mathlvl1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mathLevel[0] = isChecked;
            }
        });

        mathlvl2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mathLevel[1] = isChecked;
            }
        });

        mathlvl3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mathLevel[2] = isChecked;
            }
        });

        mathlvl4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mathLevel[3] = isChecked;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                for(int i=0; i<4; i++){
                    if(mathLevel[i]==true){
                        UserLevel userLevel = new UserLevel();
                        userLevel.setUserId(finalUserId);
                        userLevel.setTypeId(mathId[i]);
                        userGoalLevelDao.insertLevel(userLevel);
                    }
                }

                Intent it = new Intent(MathLevel.this, Age.class);
                it.putExtra("userId",finalUserId);
                startActivity(it);
            }
        });
    }
}