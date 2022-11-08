package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Enter_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_page);

        Button enter_page_btnLogin, enter_page_btnSignup;

        enter_page_btnLogin = findViewById(R.id.enter_page_btnLogin);
        enter_page_btnSignup = findViewById(R.id.enter_page_btnSignup);

        enter_page_btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(Enter_page.this, Signup.class);
                startActivity(it);
            }
        });

        enter_page_btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(Enter_page.this, Homepage.class);
                startActivity(it);
            }
        });


    }
}