package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.funnylearning.temp_head.CartoonsFragment;
import com.example.funnylearning.temp_head.Message;
import com.example.funnylearning.temp_head.RecordFragment;
import com.example.funnylearning.temp_head.VideoFragment;

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

            VideoFragment videoFragment = new VideoFragment();
            replacementFragment(videoFragment);
        }

        if(tag == 3){
            title = "Record your day";
            // setTest need to put behind the setContentView!!!!!
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);
            RecordFragment recordFragment = new RecordFragment();
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

    }

    private void replacementFragment(Fragment fragment){
        fragment.setArguments(dataToPass);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.temp_fragment,fragment);
        fragmentTransaction.commit();
    }
}