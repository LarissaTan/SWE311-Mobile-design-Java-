package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.funnylearning.Database.UserDao;
import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {

    UserDao userDao = new UserDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextInputEditText name, email, password, repassword;

        name = (TextInputEditText) findViewById(R.id.signUp_name);
        email = (TextInputEditText) findViewById(R.id.signUp_email);
        password = (TextInputEditText) findViewById(R.id.signUp_password);
        repassword = (TextInputEditText) findViewById(R.id.signUp_repassword);

        Button btnSignup;
        btnSignup = findViewById(R.id.btnSignup);
        Button btnJump;
        btnJump = findViewById(R.id.signUp_jump);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Signup.this, Gender.class);
                startActivity(it);
            }
        });

        btnJump.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Signup.this, Gender.class);
                startActivity(it);
            }
        });

        TextView login = findViewById(R.id.login_re);
        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Signup.this, Login.class);
                startActivity(it);
            }
        });
    }
}