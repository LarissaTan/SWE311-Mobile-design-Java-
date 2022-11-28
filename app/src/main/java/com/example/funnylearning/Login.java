package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.funnylearning.Database.UserDao;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    UserDao userDao = new UserDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //forgetPwd
        TextView forgetPassword, signUp;
        TextInputEditText login_account, login_password;

        forgetPassword = findViewById(R.id.forgetPwd);
        signUp = findViewById(R.id.signUp);
        login_account = findViewById(R.id.login_account);
        login_password = findViewById(R.id.login_password);

        Button login =  findViewById(R.id.btnLogin_lo);
        Button jump = (Button) findViewById(R.id.btnJump_lo);

        forgetPassword.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent it = new Intent(Login.this, ForgotPswEmail.class);
                startActivity(it);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Signup.class);
                startActivity(it);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Homepage.class);
                startActivity(it);
            }
        });

        jump.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Homepage.class);
                startActivity(it);
            }
        });
    }
}