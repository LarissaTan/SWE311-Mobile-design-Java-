package com.example.funnylearning.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.funnylearning.R;
import com.example.funnylearning.Setting;

public class HelpCenterActivity extends AppCompatActivity {

    LinearLayout map, back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);

        map = findViewById(R.id.help_address);
        back  = findViewById(R.id.backButton_helpCenter);

        map.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(HelpCenterActivity.this, MapsActivity.class);
                startActivity(it);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}