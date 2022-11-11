package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.funnylearning.navigation.HomeFragment;
import com.example.funnylearning.navigation.ReadingFragment;
import com.example.funnylearning.navigation.SettingFragment;
import com.example.funnylearning.temp_head.Message;
import com.example.funnylearning.temp_head.RecordFragment;

public class Temp_head extends AppCompatActivity {

    private TextView head_bar_title;
    public String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("temp_head is working");
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();

        int tag = Integer.parseInt(intent.getStringExtra(HomeFragment.EXTRA_NAME));

        if(tag == 0){
            title = "Messages";
            setContentView(R.layout.activity_temp_head);
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);
            Message messageFragment = new Message();
            //SettingFragment settingFragment = new SettingFragment();
            replacementFragment(messageFragment);
        }

        if(tag == 1){
            title = "Messages";
            setContentView(R.layout.activity_temp_head);
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);
            Message messageFragment = new Message();
            //SettingFragment settingFragment = new SettingFragment();
            replacementFragment(messageFragment);
        }

        if(tag == 2){
            title = "Record your day";
            setContentView(R.layout.activity_temp_head);
            // setTest need to put behind the setContentView!!!!!
            head_bar_title = findViewById(R.id.head_bar_title);
            head_bar_title.setText(title);
            RecordFragment recordFragment = new RecordFragment();
            //SettingFragment settingFragment = new SettingFragment();
            replacementFragment(recordFragment);
        }

    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.temp_fragment,fragment);
        fragmentTransaction.commit();
    }
}