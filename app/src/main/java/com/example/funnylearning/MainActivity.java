package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.funnylearning.Bean.DayRecordBean;
import com.example.funnylearning.Bean.DeliverGoodBean;
import com.example.funnylearning.Bean.FindWordsBean;
import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Bean.model.CourseType;
import com.example.funnylearning.Database.CartoonsDao;
import com.example.funnylearning.Database.CourseTypeDao;
import com.example.funnylearning.Database.DeliverGoodDao;
import com.example.funnylearning.Database.FindWordsDao;
import com.example.funnylearning.Database.UserDao;
import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.Database.UserDayRecordDao;
import com.example.funnylearning.Database.UserGoalLevelDao;
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
import com.example.funnylearning.others.other;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.deleteDatabase("funny_learning.db");

        @SuppressLint("WrongViewCast") AppCompatTextView title = findViewById(R.id.title_app);
        Typeface typeface = Typeface.createFromAsset(getAssets(),"YujiBoku-Regular.ttf");
        title.setTypeface(typeface);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Intent it = new Intent(MainActivity.this, EnterPage.class);
                startActivity(it);
                finish();
            }
        }, 3000);


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