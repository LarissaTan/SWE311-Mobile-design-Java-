package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.funnylearning.navigation.ReadingFragment;
import com.example.funnylearning.navigation.SettingFragment;
import com.example.funnylearning.temp_head.Message;

public class Temp_head extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("temp_head is working");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_head);

        Message messageFragment = new Message();
        SettingFragment settingFragment = new SettingFragment();
        replacementFragment(messageFragment);
    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.temp_fragment,fragment);
        fragmentTransaction.commit();
    }
}