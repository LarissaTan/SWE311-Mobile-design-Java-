package com.example.funnylearning.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.funnylearning.R;
import com.example.funnylearning.navigation.HomeFragment;
import com.example.funnylearning.navigation.MathFragment;
import com.example.funnylearning.navigation.ReadingFragment;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        Intent intent = getIntent();

        Integer tag = Integer.valueOf(intent.getStringExtra(MathFragment.EXTRA_NAME));
        Integer tagr = Integer.valueOf(intent.getStringExtra(ReadingFragment.EXTRA_NAME));

        Math_levelFragment math1  = new Math_levelFragment();
        Math_level_2_Fragment math2  = new Math_level_2_Fragment();

        Reading_levelFragment read1  = new Reading_levelFragment();
        Reading_level_2_Fragment read2  = new Reading_level_2_Fragment();

        if(tag == 1)    replacementFragment(math1);
        if(tag == 2)    replacementFragment(math2);
        if(tagr == 1)   replacementFragment(read1);
        if(tagr == 2)   replacementFragment(read2);
    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_base,fragment);
        fragmentTransaction.commit();
    }
}

//frame_layout_base