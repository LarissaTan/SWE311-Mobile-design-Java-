package com.example.funnylearning.navigation;

import static com.github.mikephil.charting.utils.ColorTemplate.MATERIAL_COLORS;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.funnylearning.ChatActivity;
import com.example.funnylearning.EnterPage;
import com.example.funnylearning.Homepage;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.onBoarding.onBoarding;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    public static final String EXTRA_NAME = "tag";

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public String tag = "0";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        BarChart barChart = view.findViewById(R.id.home_chart);

        ArrayList<BarEntry> visitors = new ArrayList<>();
        visitors.add(new BarEntry(2014,420));
        visitors.add(new BarEntry(2015,920));
        visitors.add(new BarEntry(2016,321));
        visitors.add(new BarEntry(2017,675));
        visitors.add(new BarEntry(2018,892));
        visitors.add(new BarEntry(2019,345));
        visitors.add(new BarEntry(2020,239));

        BarDataSet barDataSet = new BarDataSet(visitors,"Visitors");
        //barDataSet.setColor(ColorTemplate.JOYFUL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.getDescription().setText("Example");
        barChart.animateX(2000);





        Button test,ong,cheh;
        test = view.findViewById(R.id.just_for_test);
        ong =  view.findViewById(R.id.test_ong);
        cheh = view.findViewById(R.id.test_cheh);


        cheh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                tag = "0";
                Intent it = new Intent(getContext(), ChatActivity.class);
                //it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                //tag = "1";
                Intent it = new Intent(getContext(), onBoarding.class);
                //it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
            }
        });

        ong.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                tag = "2";
                Intent it = new Intent(getContext(), Temp_head.class);
                it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
            }
        });

        return view;
    }
}