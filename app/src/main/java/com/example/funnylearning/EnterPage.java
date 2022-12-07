package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
}