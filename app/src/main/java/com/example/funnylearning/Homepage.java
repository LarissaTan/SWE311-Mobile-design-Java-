package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.widget.Toast;

import com.example.funnylearning.Bean.model.UserData;
import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.navigation.ExerciseFragment;
import com.example.funnylearning.navigation.HomeFragment;
import com.example.funnylearning.navigation.MathFragment;
import com.example.funnylearning.navigation.ReadingFragment;
import com.example.funnylearning.navigation.SettingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Homepage extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    Bundle userData = new Bundle();
    UserDataDao userDataDao = new UserDataDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        Bundle extras = getIntent().getExtras();
        int userId =0;
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }
        int finalUserId = userId;

        UserData user = null;
        user = userDataDao.getUserData(finalUserId);

        System.out.println("User information from database");
        System.out.println("id: " + user.getUserId());
        System.out.println("name: "+user.getName());

        userData.putInt("userId", user.getUserId());
        userData.putString("name", user.getName());
        userData.putString("email", user.getEmail());
        userData.putInt("age", user.getAge());
        userData.putBoolean("gender", user.getGender());
        userData.putString("learningGoal", user.getLearningGoal().toString());
        userData.putString("location", user.getLocation());
        userData.putInt("profilePicture", user.getProfilePicture());

        ExerciseFragment exerciseFragment = new ExerciseFragment();
        SettingFragment settingFragment = new SettingFragment();
        HomeFragment homeFragment = new HomeFragment();
        MathFragment mathFragment = new MathFragment();
        ReadingFragment readingFragment = new ReadingFragment();

        bottomNavigationView  = findViewById(R.id.bottomNavigationView);
        replacementFragment(homeFragment);
        //bottomNavigationView.setSelectedItemId(R.id.nav_goal);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {

            switch (item.getItemId()) {
                case R.id.nav_home:
                    replacementFragment(homeFragment);
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
        fragment.setArguments(userData);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_homepage,fragment);
        fragmentTransaction.commit();
    }

}