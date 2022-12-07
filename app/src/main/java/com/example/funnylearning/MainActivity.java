package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import com.example.funnylearning.Bean.FindWordsBean;
import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Bean.model.CourseType;
import com.example.funnylearning.Database.CartoonsDao;
import com.example.funnylearning.Database.CourseTypeDao;
import com.example.funnylearning.Database.FindWordsDao;
import com.example.funnylearning.Database.UserDao;
import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.Database.UserGoalLevelDao;
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
        c.image = "https://i.328888.xyz/img/2022/12/01/ibDft.png";

        long tmp = dao.insertCartoon(c);

        c.id = 2;
        c.Name ="Alphabet letters";
        c.Duration = "28MIN";
        c.Level = 1;
        c.Url = "pFU--VNjxFw";
        c.Summary = "This video mainly talks about 26 digits character alphabet! What`s more, it is quite easy and good for everyone. ";
        c.Key1 = "The main character, the cute girl uses her cute voice to introduce the 26 digits character alphabet! ";
        c.Key2 = "This video uses the easiest words to teaching alphabet. And this is quite interesting for children to teach.";
        c.image = "https://i.328888.xyz/img/2022/12/01/ib4oX.png";

        tmp = dao.insertCartoon(c);

        c.id = 3;
        c.Name ="Coin Operated";
        c.Duration = "5MIN";
        c.Level = 1;
        c.Url = "5L4DQfVIcdg";
        c.Summary = "Coin Operated is an award-winning 5 minute animated short film that spans 70 years in the life of one naive explorer.";
        c.Key1 = "Beautifully directed, absolutely amazing output and animations. Loved the whole short.";
        c.Key2 = "This is a super cute, and heart felt short film. It deserved every last one of those awards.";
        c.image = "https://i.328888.xyz/img/2022/12/01/ibyRP.png";

        tmp = dao.insertCartoon(c);

        c.id = 4;
        c.Name ="Destiny";
        c.Duration = "5MIN";
        c.Level = 1;
        c.Url = "wEKLEeY_WeQ";
        c.Summary = "The lesson I gained from this was: there’s no need to live life perfectly to the minute. Things go wrong all the time. ";
        c.Key1 = "Moral of the story : Even those who believe in destiny, look both sides of the road before crossing";
        c.Key2 = "Easy for children to understand. And this is just a relax video. It does not have any lines.";
        c.image = "https://i.328888.xyz/img/2022/12/01/iO1j8.png";

        tmp = dao.insertCartoon(c);


        /***
         * 插入words 数据
         */
        FindWordsDao wordsDao=new FindWordsDao(this);
        wordsDao.open();
        FindWordsBean f = new FindWordsBean();

        f.id = 1;
        f.correct = "Teacher";
        f.wrong1 = "Taecher";
        f.wrong2 = "Teacer";

        long temp = wordsDao.insertFindWords(f);

        f.id = 2;
        f.correct = "Correct";
        f.wrong1 = "Corect";
        f.wrong2 = "Corract";

        temp = wordsDao.insertFindWords(f);

        f.id = 3;
        f.correct = "True";
        f.wrong1 = "Trua";
        f.wrong2 = "Tuer";

        temp = wordsDao.insertFindWords(f);

        f.id = 4;
        f.correct = "Mobile";
        f.wrong1 = "Moblie";
        f.wrong2 = "Moilbe";

        temp = wordsDao.insertFindWords(f);

        f.id = 5;
        f.correct = "Successful";
        f.wrong1 = "Sucessful";
        f.wrong2 = "Succesful";

        temp = wordsDao.insertFindWords(f);

        f.id = 6;
        f.correct = "Minute";
        f.wrong1 = "Mintue";
        f.wrong2 = "Minue";

        temp = wordsDao.insertFindWords(f);

        f.id = 7;
        f.correct = "Delicious";
        f.wrong1 = "Delcious";
        f.wrong2 = "Decious";

        temp = wordsDao.insertFindWords(f);

        f.id = 8;
        f.correct = "Contribution";
        f.wrong1 = "Contribation";
        f.wrong2 = "Contrabution";

        temp = wordsDao.insertFindWords(f);

        f.id = 9;
        f.correct = "Bulletproof";
        f.wrong1 = "Buletproof";
        f.wrong2 = "Bulletprof";

        temp = wordsDao.insertFindWords(f);

        f.id = 10;
        f.correct = "Computer";
        f.wrong1 = "Komputer";
        f.wrong2 = "comprute";

        temp = wordsDao.insertFindWords(f);

        CourseTypeDao courseTypeDao=new CourseTypeDao(this);
        courseTypeDao.open();
        CourseType course = new CourseType();

        course.setTypeName("Addition within Ten");
        course.setType("Math");

        Boolean courseTmp = courseTypeDao.insertCourseType(course);

        course.setTypeName("Addition within One Hundred");
        course.setType("Math");

        courseTmp = courseTypeDao.insertCourseType(course);

        course.setTypeName("Subtraction within Ten");
        course.setType("Math");

        courseTmp = courseTypeDao.insertCourseType(course);

        course.setTypeName("Subtraction within One Hundred");
        course.setType("Math");

        courseTmp = courseTypeDao.insertCourseType(course);

        course.setTypeName("Multiplication within ten");
        course.setType("Math");

        courseTmp = courseTypeDao.insertCourseType(course);

        course.setTypeName("Division within ten");
        course.setType("Math");

        courseTmp = courseTypeDao.insertCourseType(course);

        course.setTypeName("Learn the Basic Words");
        course.setType("Reading");

        courseTmp = courseTypeDao.insertCourseType(course);

        course.setTypeName("Learn the Basic Sentences");
        course.setType("Reading");

        courseTmp = courseTypeDao.insertCourseType(course);

        course.setTypeName("Learn the Basic Grammar");
        course.setType("Reading");

        courseTmp = courseTypeDao.insertCourseType(course);

        UserDao userDao = new UserDao(this);
        userDao.open();

        courseTmp = userDao.insertEmailPassword("swe2009514@xmu.edu.my", other.SHA("1234"));

        courseTmp = userDao.insertEmailPassword("test@gmail.com", other.SHA("1234"));

        UserDataDao userDataDao = new UserDataDao(this);
        userDataDao.open();

        courseTmp = userDataDao.insertEmailName("test@gmail.com", "Ong Cong Kin");
        courseTmp = userDataDao.updateGender(true, 1);
        courseTmp = userDataDao.updateAge(12, 1);
        courseTmp = userDataDao.updatePhoto(R.drawable.profile_photo_3, 1);
        Time time = new Time(0,30,0);
        courseTmp = userDataDao.updateLearningGoal(time, 1);
    }
}