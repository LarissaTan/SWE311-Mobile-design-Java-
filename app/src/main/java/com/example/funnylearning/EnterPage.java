package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.funnylearning.Database.CartoonsDao;

public class EnterPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_page);

        Button enter_page_btnLogin, enter_page_btnSignup;
        Button btn_jump;

        enter_page_btnLogin = findViewById(R.id.enter_page_btnLogin);
        enter_page_btnSignup = findViewById(R.id.enter_page_btnSignup);
        btn_jump = findViewById(R.id.btnJump_lo);

        if(permissions()){
            ask();
        }

        enter_page_btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(EnterPage.this, Signup.class);
                startActivity(it);
            }
        });

        enter_page_btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(EnterPage.this, Login.class);
                startActivity(it);
            }
        });

        btn_jump.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(EnterPage.this, Homepage.class);
                it.putExtra("userId", 1);
                startActivity(it);
            }
        });

    }

    protected boolean permissions(){
        return (Build.VERSION.SDK_INT  > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    protected void ask() {
        String[] permi = {
                "android.permission.CALL_PHONE"
        };
        int requestCode = 200;
        //代码动态配置的方式，向系统申请权限
        requestPermissions(permi, requestCode);
    }
}