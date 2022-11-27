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
        System.out.println("basic act is working");

        Intent intent = getIntent();

        Integer tagr = Integer.valueOf(intent.getStringExtra(ReadingFragment.EXTRA_NAMEr));
        setContentView(R.layout.activity_base);

        Reading_levelFragment read1  = new Reading_levelFragment();
        Reading_level_2_Fragment read2  = new Reading_level_2_Fragment();

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