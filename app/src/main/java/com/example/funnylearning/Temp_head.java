package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.temp_head.CartoonsFragment;
import com.example.funnylearning.temp_head.Message;
import com.example.funnylearning.temp_head.RecordFragment;
import com.example.funnylearning.temp_head.VideoFragment;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Temp_head extends AppCompatActivity {

    private TextView head_bar_title;
    public String title;
    Bundle dataToPass = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        System.out.println("temp_head is working");
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        Integer tag = Integer.valueOf(intent.getStringExtra("tag"));
        System.out.println("tag is = " + tag);
        setContentView(R.layout.activity_temp_head);

        if(tag == 0){
            //title = "Messages";
            title = "Math Level";
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);
            //Message messageFragment = new Message();
            //SettingFragment settingFragment = new SettingFragment();
            //replacementFragment(messageFragment);
            //Math_levelFragment math_levelFragment = new Math_levelFragment();
            //Reading_levelFragment reading_levelFragment = new Reading_levelFragment();
            //Math_level_2_Fragment math_level_2_fragment = new Math_level_2_Fragment();
            //replacementFragment(math_levelFragment);
        }

        if(tag == 1){
            title = "Messages";
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);
            Message messageFragment = new Message();
            //SettingFragment settingFragment = new SettingFragment();
            replacementFragment(messageFragment);
        }

        if(tag == 2){
            title = "Video";
            // setTest need to put behind the setContentView!!!!!
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);

            dataToPass = getIntent().getExtras();

            int i = dataToPass.getInt("userId");
            System.out.println("temp_head: userId = " + i + " (video");

            VideoFragment videoFragment = new VideoFragment();
            videoFragment.setArguments(dataToPass);
            replacementFragment(videoFragment);
        }

        if(tag == 3){
            title = "Record your day";
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);
            // setTest need to put behind the setContentView!!!!!
            dataToPass = getIntent().getExtras();

            int i = dataToPass.getInt("userId");
            System.out.println("temp_head: userId = " + i + " (record");

            RecordFragment recordFragment = new RecordFragment();
            recordFragment.setArguments(dataToPass);
            replacementFragment(recordFragment);
        }

        if(tag == 4){
            title = "Cartoon";
            // setTest need to put behind the setContentView!!!!!
            String itemNum = intent.getStringExtra("itemNum");
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);

            CartoonsFragment cartoonsFragment = new CartoonsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("NoItem",itemNum);
            cartoonsFragment.setArguments(bundle);
            replacementFragment(cartoonsFragment);
        }

        //temp_return_btn
        ImageView btn = findViewById(R.id.temp_return_btn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Temp_head.this.finish();
            }
        });

    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.temp_fragment,fragment);
        fragmentTransaction.commit();
    }
}