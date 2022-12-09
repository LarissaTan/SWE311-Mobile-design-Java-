package com.example.funnylearning.temp_head;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.funnylearning.Bean.DayRecordBean;
import com.example.funnylearning.Database.UserDayRecordDao;
import com.example.funnylearning.R;
import com.example.funnylearning.recycle.activities.adapter_activities;
import com.example.funnylearning.recycle.activities.model_activities;
import com.example.funnylearning.recycle.mood.adapter_mood;
import com.example.funnylearning.recycle.mood.model_mood;
import com.example.funnylearning.recycle.weather.adapter_weather;
import com.example.funnylearning.recycle.weather.model_weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class RecordFragment extends Fragment {

    private RecyclerView mdList;
    private final ArrayList<model_mood> moodList = new ArrayList<model_mood>();
    private RecyclerView actList;
    private final ArrayList<model_activities> activitiesList = new ArrayList<model_activities>();
    private RecyclerView weaList;
    private final ArrayList<model_weather> weatherList = new ArrayList<model_weather>();

    public RecordFragment() {
        // Required empty public constructor
    }

    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_record, container, false);

        UserDayRecordDao dao = new UserDayRecordDao(view.getContext());

        dao.open();

        DayRecordBean bean = new DayRecordBean();
        bean.userid = 1;
        bean.activity = null;
        bean.mood = null;
        bean.weather = null;
        bean.learningTime = -1;

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String tmp = date.format(formatter);
        bean.recordDate = tmp;

        long temp = dao.insertDayRecord(bean);

        mdList = (RecyclerView) view.findViewById(R.id.recyclerview_record_mood);
        actList = (RecyclerView) view.findViewById(R.id.recyclerview_record_activities);
        weaList = (RecyclerView) view.findViewById(R.id.recyclerview_record_weather);

        moodList.clear();
        //System.out.println("message is working");
        moodList.add(new model_mood("Sad",R.drawable.record_mood_sad,"#DAE8FF"));
        moodList.add(new model_mood("Happy", R.drawable.record_mood_happy,"#FFFFFF"));
        moodList.add(new model_mood("Angry", R.drawable.record_mood_angry,"#FFF0CF"));
        moodList.add(new model_mood("Sleepy", R.drawable.record_mood_sleepy,"#E9E4FF"));

        //System.out.println("message is working");
        mdList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter_mood customAdapterMood = new adapter_mood(moodList);
        //System.out.println("layout manager is working");
        mdList.setAdapter(customAdapterMood);

        activitiesList.clear();
        //System.out.println("message is working");
        activitiesList.add(new model_activities("Party", R.drawable.record_activities_party));
        activitiesList.add(new model_activities("Travel", R.drawable.record_activities_travel));
        activitiesList.add(new model_activities("Beach", R.drawable.record_activities_beach));

        //System.out.println("message is working");
        actList.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        adapter_activities customAdapterActivities = new adapter_activities(activitiesList);
        //System.out.println("layout manager is working");
        actList.setAdapter(customAdapterActivities);

        weatherList.clear();
        //System.out.println("message is working");
        weatherList.add(new model_weather(R.drawable.record_weather_sun));
        weatherList.add(new model_weather(R.drawable.record_weather_suncloud));
        weatherList.add(new model_weather(R.drawable.record_weather_cloud));
        weatherList.add(new model_weather(R.drawable.record_weather_sunrain));
        weatherList.add(new model_weather(R.drawable.record_weather_rain));
        weatherList.add(new model_weather(R.drawable.record_weather_thunderrain));

        //System.out.println("message is working");
        weaList.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));
        adapter_weather customAdapterWeather = new adapter_weather(weatherList);
        //System.out.println("layout manager is working");
        weaList.setAdapter(customAdapterWeather);

        return view;
    }
}