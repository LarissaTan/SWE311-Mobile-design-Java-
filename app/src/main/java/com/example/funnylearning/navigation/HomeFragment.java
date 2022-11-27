package com.example.funnylearning.navigation;

import static androidx.databinding.DataBindingUtil.setContentView;
import static com.github.mikephil.charting.utils.ColorTemplate.MATERIAL_COLORS;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.funnylearning.ChatActivity;
import com.example.funnylearning.EnterPage;
import com.example.funnylearning.Homepage;
import com.example.funnylearning.R;
import com.example.funnylearning.Temp_head;
import com.example.funnylearning.onBoarding.onBoarding;
import com.example.funnylearning.others.ColumnView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements OnChartValueSelectedListener {

    private LinearLayout column;

    // 此处插入数据
    private void barChart() {
        //第一个为空，它需要占一个位置
        String[] transverse = {"","Mon","Tue","Wed","Thu","Fri","Sat","Sun"};
        String[] vertical = {"", " ", " ", " ", " "};
        int[] data = {20 , 30, 40, 30, 20, 20, 18};
        List<Integer> color = new ArrayList<>();
        column.addView(new ColumnView(getContext(), transverse, vertical, color, data));
    }

    public static final String EXTRA_NAME = "tag";

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        //Bundle args = new Bundle();
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

        super.onCreate(savedInstanceState);
        column = (LinearLayout) view.findViewById(R.id.column);
        barChart();

        /******************************************************************************************************/
        CircularProgressBar circularProgressBar = view.findViewById(R.id.home_math_progressBar);

        circularProgressBar.setProgress(80f);
        circularProgressBar.setProgressMax(100f);

        // Set Width
        circularProgressBar.setProgressBarWidth(7f); // in DP
        circularProgressBar.setBackgroundProgressBarWidth(7f); // in DP

        // Other
        circularProgressBar.setRoundBorder(true);
        circularProgressBar.setStartAngle(180f);
        circularProgressBar.setProgressDirection(CircularProgressBar.ProgressDirection.TO_LEFT);

    /******************************************************************************************************/
        CircularProgressBar circularProgressBarEnglish = view.findViewById(R.id.home_english_progressBar);
        circularProgressBarEnglish.setProgress(76f);
        circularProgressBarEnglish.setProgressMax(100f);

        // Set Width
        circularProgressBarEnglish.setProgressBarWidth(7f); // in DP
        circularProgressBarEnglish.setBackgroundProgressBarWidth(7f); // in DP

        // Other
        circularProgressBarEnglish.setRoundBorder(true);
        circularProgressBarEnglish.setStartAngle(180f);
        circularProgressBarEnglish.setProgressDirection(CircularProgressBar.ProgressDirection.TO_LEFT);
        /******************************************************************************************************/

        Button test,ong,cheh;
        ImageView addJournal;
        addJournal = view.findViewById(R.id.home_add_journal);
        test = view.findViewById(R.id.just_for_test);
        ong =  view.findViewById(R.id.test_ong);
        cheh = view.findViewById(R.id.test_cheh);

        addJournal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tag =  "3";
                Intent it = new Intent(getContext(), Temp_head.class);
                it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
            }
        });

        cheh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                tag = "0";
                //Intent it = new Intent(getContext(), ChatActivity.class);
                Intent it = new Intent(getContext(), Temp_head.class);
                it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
            }
        });

        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.dialog_success);
                dialog.setTitle("Title...");
                dialog.getWindow().setDimAmount(0.5f);
                dialog.getWindow ().setBackgroundDrawable (new ColorDrawable(android.graphics.Color.TRANSPARENT));

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.dialog_txt);
                text.setText("You find 2 words !  Good job!");

                Button dialogButton = (Button) dialog.findViewById(R.id.dialog_btn_continue);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                /*
                // TODO Auto-generated method stub
                //tag = "1";
                Intent it = new Intent(getContext(), onBoarding.class);
                //it.putExtra(EXTRA_NAME,tag);
                startActivity(it);
                */
                /*
                btnSignIn.setOnClickListener {
                    val dialogBinding = layoutInflater.inflate(R.layout.dialog_login_alert,null)

                    val login_dialog = Dialog(this)
                    login_dialog.setContentView(dialogBinding)
                    login_dialog.setCancelable(true)
                    login_dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    login_dialog.show()

                    val btn_back = dialogBinding.findViewById<Button>(R.id.btn_login_dialog_back)
                            btn_back.setOnClickListener {
                        login_dialog.dismiss()
                    }
                }
                */
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

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}

        /*


        用这个方法没办法去掉坐标轴

        BarChart barChart = view.findViewById(R.id.home_chart);


        String hexColor = "F9D150";
        String hexColor2 = "FEF7E1";

        int[] colorArray = new int[]{ColorTemplate.rgb(hexColor2),ColorTemplate.rgb(hexColor)};

        ArrayList<BarEntry> data1 = new ArrayList<>();
        data1.add(new BarEntry(2014,new float[]{220,60}));
        data1.add(new BarEntry(2015,new float[]{220,60}));
        data1.add(new BarEntry(2016,new float[]{220,60}));
        data1.add(new BarEntry(2017,new float[]{220,60}));
        data1.add(new BarEntry(2018,new float[]{220,60}));
        data1.add(new BarEntry(2019,new float[]{220,60}));
        data1.add(new BarEntry(2020,new float[]{220,60}));

        ArrayList<BarEntry> data2 = new ArrayList<>();
        data2.add(new BarEntry(2014,60));
        data2.add(new BarEntry(2015,60));
        data2.add(new BarEntry(2016,61));
        data2.add(new BarEntry(2017,65));
        data2.add(new BarEntry(2018,62));
        data2.add(new BarEntry(2019,65));
        data2.add(new BarEntry(2020,79));



        BarDataSet barData2Set = new BarDataSet(data2,null);

        BarDataSet barData1Set = new BarDataSet(data1,null);
        barData1Set.setColor(ColorTemplate.rgb(hexColor2));
        barData1Set.setValueTextColor(Color.WHITE);
        barData1Set.setValueTextSize(16f);

        barData2Set.setColor(ColorTemplate.rgb(hexColor));
        barData2Set.setValueTextColor(Color.WHITE);
        barData2Set.setValueTextSize(16f);
        BarData barData = new BarData(barData1Set);

        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);

        Legend legend = barChart.getLegend();
        legend.setEnabled(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setEnabled(true);

        barChart.setFitBars(true);
        barChart.setData(barData);
        barChart.setVisibleXRangeMaximum(15);
        barChart.setDescription(null);
        barChart.animateX(0);


*/