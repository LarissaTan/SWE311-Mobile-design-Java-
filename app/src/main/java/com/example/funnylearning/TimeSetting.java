package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.onBoarding.onBoarding;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class TimeSetting extends AppCompatActivity {

    SeekBar learningGoal;
    UserDataDao userDataDao = new UserDataDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_setting);
        Bundle extras = getIntent().getExtras();
        int userId =0;
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }
        int finalUserId = userId;

        learningGoal = findViewById(R.id.qmuiSlider);

        Button btn;

        btn = findViewById(R.id.set_goal_finish_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int chosenTime = learningGoal.getProgress();
                Time time = new Time(0,chosenTime,0);

                Boolean insert = userDataDao.updateLearningGoal(time, finalUserId);

                if(insert == true){
                    Intent it = new Intent(TimeSetting.this, onBoarding.class);
                    it.putExtra("userId",finalUserId);
                    startActivity(it);
                }else {
                    Toast.makeText(TimeSetting.this, "Time choosing fail", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}