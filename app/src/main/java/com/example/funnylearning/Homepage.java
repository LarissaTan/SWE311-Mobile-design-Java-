package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.funnylearning.navigation.ExerciseFragment;
import com.example.funnylearning.navigation.GoalFragment;
import com.example.funnylearning.navigation.MathFragment;
import com.example.funnylearning.navigation.ReadingFragment;
import com.example.funnylearning.navigation.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        ExerciseFragment exerciseFragment = new ExerciseFragment();
        SettingFragment settingFragment = new SettingFragment();
        GoalFragment goalFragment = new GoalFragment();
        MathFragment mathFragment = new MathFragment();
        ReadingFragment readingFragment = new ReadingFragment();

        bottomNavigationView  = findViewById(R.id.bottomNavigationView);
        replacementFragment(goalFragment);
        //bottomNavigationView.setSelectedItemId(R.id.nav_goal);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.nav_goal:
                    replacementFragment(goalFragment);
                    break;
                case R.id.nav_exe:
                    replacementFragment(exerciseFragment);
                    break;
                case R.id.nav_math:
                    replacementFragment(mathFragment);
                    break;
                case R.id.nav_read:
                    replacementFragment(readingFragment);
                    break;
                case R.id.nav_settings:
                    replacementFragment(settingFragment);
                    break;
            }
            return true;
        });

    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_homepage,fragment);
        fragmentTransaction.commit();
    }

}