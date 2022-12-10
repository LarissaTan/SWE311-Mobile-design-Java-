package com.example.funnylearning.Settings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.funnylearning.ChoosePhoto;
import com.example.funnylearning.R;
import com.example.funnylearning.Setting;

public class HelpCenterActivity extends AppCompatActivity {

    LinearLayout map, back, call;

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

        call  = findViewById(R.id.help_phone);

        call.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String number = "126702095"; //得到号码
                Intent intent = new Intent();//意图对象
                intent.setAction(Intent.ACTION_CALL);//执行打电话动作
                Uri data = Uri.parse("tel:" + number);//设置拨打的号码
                intent.setData(data);
                startActivity(intent); //激活acitivity组件
                Toast.makeText(HelpCenterActivity.this, "It is calling.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}