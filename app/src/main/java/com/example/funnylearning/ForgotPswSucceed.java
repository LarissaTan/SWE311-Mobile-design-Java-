package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForgotPswSucceed extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_psw_succeed);

        Button btn_succeed;
        btn_succeed = findViewById(R.id.forget_pwd_success_button);

        btn_succeed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(ForgotPswSucceed.this, Login.class);
                startActivity(it);
            }
        });
    }
}