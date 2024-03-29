package com.example.funnylearning.navigation;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Bean.DayRecordBean;
import com.example.funnylearning.Bean.model.UserGameRecord;
import com.example.funnylearning.ChatActivity;
import com.example.funnylearning.Database.CourseTypeDao;
import com.example.funnylearning.Database.GameDao;
import com.example.funnylearning.Database.UserDayRecordDao;
import com.example.funnylearning.Database.UserGameRecordDao;
import com.example.funnylearning.Homepage;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.others.ColumnView;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment{

    private LinearLayout column;
    private RelativeLayout studytime;
    private View view;
    private int userId;
    TextView addFirstRecord, recordDate, activityTitle, recordTime;
    ImageView activityIcon, moodIcon, weatherIcon;
    CardView weatherCard, moodCard;
    ProgressBar timeBar;

    // insert data here
    private void barChart(int num[]) {
        // The first one is empty, it needs a place
        String[] transverse = {"","Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        String[] vertical = {"", " ", " ", " ", " "};

        int[] data = {num[0] , num[1], num[2], num[3], num[4], num[5], num[6]};
        column.addView(new ColumnView(getContext(), transverse, vertical, data));
    }

    public static final String EXTRA_NAME = "tag";

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String tag = "0";
    @Override
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        super.onCreate(savedInstanceState);

        Integer score;
        int gameId, typeId, goal;
        String courseName = null;
        userId = getArguments().getInt("userId");
        System.out.println("user id is = " + userId  + "(home frag");

        /***************** btn init ***********************/
        ImageView read, math, cartoon, chat_icon;
        read = view.findViewById(R.id.home_reading);
        math = view.findViewById(R.id.home_math);
        cartoon = view.findViewById(R.id.home_exer);
        chat_icon = view.findViewById(R.id.chat_icon);

        chat_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(view.getContext(), ChatActivity.class);
                it.putExtra("userId",userId);
                startActivity(it);
            }
        });

        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(view.getContext(), Homepage.class);
                it.putExtra("userId", userId);
                it.putExtra("nav_jump", 1);
                startActivity(it);
            }
        });

        math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(view.getContext(), Homepage.class);
                it.putExtra("userId", userId);
                it.putExtra("nav_jump", 2);
                startActivity(it);
            }
        });

        cartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(view.getContext(), Homepage.class);
                it.putExtra("userId", userId);
                it.putExtra("nav_jump", 3);
                startActivity(it);
            }
        });

        /**************************************************/

        super.onCreate(savedInstanceState);
        column = (LinearLayout) view.findViewById(R.id.column);
        //barChart();

        studytime = (RelativeLayout) view.findViewById(R.id.homepage_studytime);


        //txt_study_time
        addFirstRecord = view.findViewById(R.id.txt_add_first_record);
        recordDate = view.findViewById(R.id.txt_record_time);
        activityIcon = view.findViewById(R.id.home_activity_icon);
        recordTime = view.findViewById(R.id.home_journal_time);
        weatherCard = view.findViewById(R.id.home_card_weather);
        moodCard = view.findViewById(R.id.card_record_mood);
        timeBar = view.findViewById(R.id.home_journal_progressBar);
        activityTitle = view.findViewById(R.id.home_activities_title);
        moodIcon = view.findViewById(R.id.home_mood_icon);
        weatherIcon = view.findViewById(R.id.home_weather_icon);

        updateRecord();

        /******************************************************************************************************/
        CircularProgressBar circularProgressBar = view.findViewById(R.id.home_math_progressBar);
        TextView math_score = view.findViewById(R.id.home_math_score);
        TextView math_title = view.findViewById(R.id.home_math_title);


        UserGameRecordDao userGameRecordDao = new UserGameRecordDao(view.getContext());
        userGameRecordDao.open();
        GameDao gameDao = new GameDao(view.getContext());
        gameDao.open();
        CourseTypeDao courseTypeDao = new CourseTypeDao(view.getContext());
        courseTypeDao.open();

        UserGameRecord recordMath = userGameRecordDao.getRecordByTypeName("Math", userId);

        if(recordMath == null)
        {
            math_score.setText("0");
            math_title.setText("Math Goals");
            circularProgressBar.setProgress(0f);
            circularProgressBar.setProgressMax(100f);

        }else {
            score = recordMath.getScore();
            gameId = recordMath.getGameId();

            typeId = gameDao.getTypeId(gameId);
            goal = gameDao.getGoal(gameId);

            if(typeId != -1)
            {
                courseName = courseTypeDao.getCourseName(typeId);
            }else
            {
                Toast.makeText(view.getContext(), "get typeId fail", Toast.LENGTH_SHORT).show();
            }


            math_score.setText(score.toString());
            math_title.setText(courseName);
            circularProgressBar.setProgressMax((float) goal);
            if(score < goal)
            {
                circularProgressBar.setProgress((float) score);
            }else{
                circularProgressBar.setProgress((float) goal);
            }
        }



        // Set Width
        circularProgressBar.setProgressBarWidth(7f); // in DP
        circularProgressBar.setBackgroundProgressBarWidth(7f); // in DP

        // Other
        circularProgressBar.setRoundBorder(true);
        circularProgressBar.setStartAngle(180f);
        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_LEFT);

    /******************************************************************************************************/

        CircularProgressBar circularProgressBarEnglish = view.findViewById(R.id.home_english_progressBar);
        TextView reading_score = view.findViewById(R.id.home_english_score);
        TextView reading_title = view.findViewById(R.id.home_english_title);

        UserGameRecord recordReading = userGameRecordDao.getRecordByTypeName("Reading", userId);
        if(recordReading == null)
        {
            reading_score.setText("0");
            reading_title.setText("English Goals");
            circularProgressBarEnglish.setProgress(0f);
            circularProgressBarEnglish.setProgressMax(100f);
        }else{
            score = recordReading.getScore();
            gameId = recordReading.getGameId();

            typeId = gameDao.getTypeId(gameId);
            goal = gameDao.getGoal(gameId);

            if(typeId != -1)
            {
                courseName = courseTypeDao.getCourseName(typeId);
            }else
            {
                Toast.makeText(view.getContext(), "get typeId fail", Toast.LENGTH_SHORT).show();
            }

            reading_score.setText(score.toString());
            reading_title.setText(courseName);
            circularProgressBarEnglish.setProgressMax((float) goal);
            if(score < goal)
            {
                circularProgressBarEnglish.setProgress((float) score);
            }else{
                circularProgressBarEnglish.setProgress((float) goal);
            }
        }


        // Set Width
        circularProgressBarEnglish.setProgressBarWidth(7f); // in DP
        circularProgressBarEnglish.setBackgroundProgressBarWidth(7f); // in DP

        // Other
        circularProgressBarEnglish.setRoundBorder(true);
        circularProgressBarEnglish.setStartAngle(180f);
        circularProgressBarEnglish.setProgressDirection(CircularProgressBar.ProgressDirection.TO_LEFT);
        /******************************************************************************************************/

        ImageView addJournal;
        addJournal = view.findViewById(R.id.home_add_journal);

        addJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDayRecordDao dao = new UserDayRecordDao(view.getContext());
                dao.open();
                boolean isRecorded = dao.isDayRecordExist(userId);
                if(!isRecorded) {
                    tag = "3";
                    Intent it = new Intent(getContext(), Temp_head.class);
                    it.putExtra(EXTRA_NAME, tag);
                    it.putExtra("userId", userId);
                    startActivityForResult(it, 2);
                }else{
                    Toast.makeText(v.getContext(), "You have done the record today ~~~", Toast.LENGTH_SHORT).show();
                }
            }
        });

        studytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserDayRecordDao dao = new UserDayRecordDao(view.getContext());
                dao.open();

                boolean isNRecord = dao.isNoRecord(userId);

                if(isNRecord){
                    tag = "3";
                    Intent it = new Intent(getContext(), Temp_head.class);
                    it.putExtra(EXTRA_NAME, tag);
                    it.putExtra("userId", userId);
                    startActivityForResult(it, 2);
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==2)
        {
            updateRecord();
        }
    }

    private void updateRecord(){
        UserDayRecordDao dao = new UserDayRecordDao(view.getContext());
        dao.open();

        boolean isNRecord = dao.isNoRecord(userId);

        if(isNRecord){
            column.setVisibility(View.INVISIBLE);
            addFirstRecord.setVisibility(View.VISIBLE);
            activityIcon.setVisibility(View.INVISIBLE);
            weatherCard.setVisibility(View.INVISIBLE);
            moodCard.setVisibility(View.INVISIBLE);
            recordTime.setVisibility(View.INVISIBLE);
            timeBar.setProgress(0);
            recordDate.setVisibility(View.INVISIBLE);
            activityTitle.setText("No data");

        }else{
            DayRecordBean recordBean = new DayRecordBean();
            recordBean = dao.findNewestRecord(userId);
            int[] num = new int[7];
            num = dao.getColData(userId);
            barChart(num);
            column.setVisibility(View.VISIBLE);
            addFirstRecord.setVisibility(View.INVISIBLE);
            activityIcon.setVisibility(View.VISIBLE);
            weatherCard.setVisibility(View.VISIBLE);
            recordDate.setVisibility(View.VISIBLE);
            moodCard.setVisibility(View.VISIBLE);
            recordTime.setVisibility(View.VISIBLE);


            activityTitle.setText(recordBean.activity);
            if(recordBean.activity!=null){
                switch (recordBean.activity){
                    case "Party":
                        activityIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_activities_party)));
                        break;
                    case "Travel":
                        activityIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_activities_travel)));
                        break;
                    case "Beach":
                        activityIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_activities_beach)));
                        break;
                    default :
                        break;
                }
            }

            if(recordBean.mood != null) {
                switch (recordBean.mood) {
                    case "sad":
                        moodIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_mood_sad)));
                        break;
                    case "happy":
                        moodIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_mood_happy)));
                        break;
                    case "angry":
                        moodIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_mood_angry)));
                        break;
                    case "sleepy":
                        moodIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_mood_sleepy)));
                        break;
                    default:
                        break;
                }
            }

            if(recordBean.weather!=null){
                switch (recordBean.weather){
                    case "sunny":
                        weatherIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_weather_sun)));
                        break;
                    case "overcast":
                        weatherIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_weather_suncloud)));
                        break;
                    case "cloudy":
                        weatherIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_weather_cloud)));
                        break;
                    case "sunshower":
                        weatherIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_weather_sunrain)));
                        break;
                    case "rain":
                        weatherIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_weather_rain)));
                        break;
                    case "thunder":
                        weatherIcon.setImageDrawable(getResources().getDrawable((R.drawable.record_weather_thunderrain)));;
                        break;

                    default :
                        break;
                }
            }


            char tmp_str[] = recordBean.recordDate.toCharArray();
            String month = null, str;
            str = String.valueOf(tmp_str[3]);
            str = str + tmp_str[4];

            switch (str){
                case "01":
                    month  = "Jan";
                    break;
                case "02":
                    month = "Feb";
                    break;
                case "03":
                    month = "Mar";
                    break;
                case "04":
                    month = "Apr";
                    break;
                case "05":
                    month = "May";
                    break;
                case "06":
                    month = "Jun";
                    break;
                case "07":
                    month = "Jul";
                    break;
                case "08":
                    month = "Aug";
                    break;
                case "09":
                    month = "Sept";
                    break;
                case "10":
                    month = "Oct";
                    break;
                case "11":
                    month = "Nov";
                    break;
                case "12":
                    month = "Dec";
                    break;
                default:
                    break;
            }

            recordDate.setText(month + ", " + tmp_str[0] + tmp_str[1] + ", " + tmp_str[6] + tmp_str[7] + tmp_str[8] + tmp_str[9]);
            recordTime.setText(String.valueOf(recordBean.learningTime) + " MIN");
            timeBar.setProgress(recordBean.learningTime, true);
        }
    }

}
