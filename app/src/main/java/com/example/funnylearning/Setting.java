package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.funnylearning.Settings.FAQ;
import com.example.funnylearning.Settings.MapsActivity;

public class Setting extends AppCompatActivity {

    ImageView faq,help,resetPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        faq = findViewById(R.id.setting_faq);
        help = findViewById(R.id.setting_help);
        resetPwd = findViewById(R.id.setting_reset);

        faq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Setting.this, FAQ.class);
                startActivity(it);
                //finishAndRemoveTask();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Setting.this, MapsActivity.class);
                startActivity(it);
                //finishAndRemoveTask();
            }
        });

    }
}