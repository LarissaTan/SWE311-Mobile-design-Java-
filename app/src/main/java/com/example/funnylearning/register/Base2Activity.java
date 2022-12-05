package com.example.funnylearning.register;

import android.content.Intent;
import android.os.Bundle;

import com.example.funnylearning.navigation.MathFragment;


import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.funnylearning.R;

public class Base2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("basic act is working");

        Intent intent = getIntent();

        Integer tagm = Integer.valueOf(intent.getStringExtra("tagm"));
        setContentView(R.layout.activity_base2);

        MathFragment math0 = new MathFragment();
        Math_levelFragment math1  = new Math_levelFragment();
        Math_level_2_Fragment math2  = new Math_level_2_Fragment();

        if(tagm == 0)    replacementFragment(math0);
        if(tagm == 1)    replacementFragment(math1);
        if(tagm == 2)    replacementFragment(math2);

    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_base2,fragment);
        fragmentTransaction.commit();
    }

}