package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Database.CartoonsDao;

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
                Intent it = new Intent(MainActivity.this, EnterPage.class);
                startActivity(it);
            }
        }, 3000);


        /***
         * 插入cartoons 数据
         */


        CartoonsDao dao=new CartoonsDao(this);
        //打开数据库
        dao.open();

        Cartoons c  = new Cartoons();

        c.id = 1;
        c.Name ="How To Make Pancakes!";
        c.Duration = "36MIN";
        c.Level = 3;
        c.Url = "_2yM8MM9qNs";
        c.Summary = "This video mainly celebrates Pancake day with Peppa Pig and her family! However, children who like to watch this one must be able to read sentences. ";
        c.Key1 = "This video is mainly to promote the harmonious coexistence of the peppa pig family. While teaching children English, they also teach children how to get along with their families.";
        c.Key2 = "The video itself is long, but it contains many topics in it. More time can be arranged for children to watch.";

        long tmp = dao.insertCartoon(c);

        c.id = 2;
        c.Name ="Alphabet letters";
        c.Duration = "28MIN";
        c.Level = 1;
        c.Url = "pFU--VNjxFw";
        c.Summary = "This video mainly talks about 26 digits character alphabet! What`s more, it is quite easy and good for everyone. ";
        c.Key1 = "The main character, the cute girl uses her cute voice to introduce the 26 digits character alphabet! ";
        c.Key2 = "This video uses the easiest words to teaching alphabet. And this is quite interesting for children to teach.";

        tmp = dao.insertCartoon(c);

        c.id = 3;
        c.Name ="Coin Operated";
        c.Duration = "5MIN";
        c.Level = 1;
        c.Url = "5L4DQfVIcdg";
        c.Summary = "Coin Operated is an award-winning 5 minute animated short film that spans 70 years in the life of one naive explorer.";
        c.Key1 = "Beautifully directed, absolutely amazing output and animations. Loved the whole short.";
        c.Key2 = "This is a super cute, and heart felt short film. It deserved every last one of those awards.";

        tmp = dao.insertCartoon(c);

        c.id = 4;
        c.Name ="Destiny";
        c.Duration = "5MIN";
        c.Level = 1;
        c.Url = "wEKLEeY_WeQ";
        c.Summary = "The lesson I gained from this was: there’s no need to live life perfectly to the minute. Things go wrong all the time. ";
        c.Key1 = "Moral of the story : Even those who believe in destiny, look both sides of the road before crossing";
        c.Key2 = "Easy for children to understand. And this is just a relax video. It does not have any lines.";

        tmp = dao.insertCartoon(c);


    }
}