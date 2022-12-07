package com.example.funnylearning.onBoarding;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Homepage;
import com.example.funnylearning.R;

public class onBoarding extends AppCompatActivity {
    /*init the parameters*/
    ViewPager viewPager;
    LinearLayout layout;
    Button skip;

    TextView[] dots;
    adapter_onboarding adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        Bundle extras = getIntent().getExtras();
        int userId =0;
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }
        int finalUserId = userId;

        skip = findViewById(R.id.skipButton);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(onBoarding.this, Homepage.class);
                i.putExtra("userId",finalUserId);
                startActivity(i);
                finish();
            }
        });
        viewPager = (ViewPager) findViewById(R.id.slideViewPager);
        layout = (LinearLayout) findViewById(R.id.indicator_layout);

        adapter = new adapter_onboarding(this);

        viewPager.setAdapter(adapter);

        setIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);


    }

    public void setIndicator(int position){

        dots = new TextView[3];
        layout.removeAllViews();

        for (int i = 0 ; i < dots.length ; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            layout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));

    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            setIndicator(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}