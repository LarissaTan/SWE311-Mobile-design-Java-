package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.funnylearning.Settings.FAQ;
import com.example.funnylearning.Settings.HelpCenterActivity;
import com.example.funnylearning.Settings.MapsActivity;

public class Setting extends AppCompatActivity {

    ImageView faq,help,resetPwd,reminder;
    Switch notification;
    LinearLayout btn_logout, btn_return;

    private int userId = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Bundle extras = getIntent().getExtras();
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }

        faq = findViewById(R.id.setting_faq);
        help = findViewById(R.id.setting_help);
        resetPwd = findViewById(R.id.setting_reset);
        notification = findViewById(R.id.setting_notification);
        reminder = findViewById(R.id.setting_reminders);
        btn_logout = findViewById(R.id.setting_logout);
        btn_return = findViewById(R.id.backButton_setting);

        faq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Setting.this, FAQ.class);
                startActivity(it);
                //finishAndRemoveTask();
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Setting.this, HelpCenterActivity.class);
                startActivity(it);
                //finishAndRemoveTask();
            }
        });

        resetPwd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Setting.this, ForgotPswEmail.class);
                startActivity(it);
                //finishAndRemoveTask();
            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Setting.this, "We will develop it later. Sorry for that (╯_╰)", Toast.LENGTH_SHORT).show();
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(Setting.this, "We will develop it later. Sorry for that (╯_╰)", Toast.LENGTH_SHORT).show();
            }
        });

        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1, null);
                finish();
            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Setting.this, Homepage.class);
                it.putExtra("userId", userId);
                it.putExtra("nav_jump", 4);
                startActivity(it);
            }
        });

    }
}