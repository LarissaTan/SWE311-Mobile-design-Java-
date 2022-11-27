package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ForgotPswEnter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_psw_enter);

        Button btn;

        btn = findViewById(R.id.forget_pwd_set);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(),"You have successfully reset your password! Now, it`s time to login!", Toast.LENGTH_SHORT).show();
                Intent it = new Intent(ForgotPswEnter.this, Login.class);
                startActivity(it);
            }
        });
    }
}