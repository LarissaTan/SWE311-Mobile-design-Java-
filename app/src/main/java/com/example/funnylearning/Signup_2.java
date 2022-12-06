package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class Signup_2 extends AppCompatActivity {

    TextInputLayout verificationCode, name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup2);
        Bundle extras = getIntent().getExtras();
        String email = null;
        if(extras != null){
            email = extras.getString("user_email");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }

        verificationCode = findViewById(R.id.signUp_inputlayout_emailcode);
        name = findViewById(R.id.signUp_inputlayout_name);

        Button btn;
        btn = findViewById(R.id.btnSignup_2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}