package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

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
        timer.schedule(new TimerTask() {
            public void run() {
                Intent it = new Intent(MainActivity.this, Homepage.class);
                startActivity(it);
            }
        }, 3000);
    }
}