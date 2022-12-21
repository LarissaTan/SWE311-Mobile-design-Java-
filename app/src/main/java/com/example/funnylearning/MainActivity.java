package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.funnylearning.data.DataCartoons;
import com.example.funnylearning.data.DataCourseComment;
import com.example.funnylearning.data.DataCourseType;
import com.example.funnylearning.data.DataCourseVideo;
import com.example.funnylearning.data.DataDeliverGood;
import com.example.funnylearning.data.DataFindWords;
import com.example.funnylearning.data.DataGame;
import com.example.funnylearning.data.DataUserAccount;
import com.example.funnylearning.data.DataUserData;
import com.example.funnylearning.data.DataUserDayRecord;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        @SuppressLint("WrongViewCast") AppCompatTextView title = findViewById(R.id.title_app);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"YujiBoku-Regular.ttf");
        title.setTypeface(typeface);
        Timer timer = new Timer();

        // make the animation working for 3 sec
        timer.schedule(new TimerTask() {
            public void run() {
                Intent it = new Intent(MainActivity.this, EnterPage.class);
                startActivity(it);
                finish();
            }
        }, 3000);


        // insert data in sqlite
        DataCartoons.start(this);

        DataFindWords.start(this);

        DataUserDayRecord.start(this);

        DataCourseType.start(this);

        DataUserAccount.start(this);

        DataUserData.start(this);

        DataCourseVideo.start(this);

        DataCourseComment.start(this);

        DataGame.start(this);

        DataDeliverGood.start(this);
    }
}