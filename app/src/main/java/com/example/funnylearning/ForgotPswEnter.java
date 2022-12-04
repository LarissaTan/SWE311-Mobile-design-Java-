package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPswEnter extends AppCompatActivity {

    TextInputLayout code, password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_psw_enter);
        Bundle extras = getIntent().getExtras();
        String user_email;
        if(extras != null){
            user_email = extras.getString("email");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }

        code = findViewById(R.id.forgot_psw_enter_Code);
        password = findViewById(R.id.forgot_psw_enter_new_password);
        repassword = findViewById(R.id.forgot_psw_enter_repeat_password);

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