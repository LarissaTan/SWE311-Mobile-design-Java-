package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;

import com.example.funnylearning.Database.UserDao;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPswEmail extends AppCompatActivity {

    UserDao userDao = new UserDao(this);
    TextInputLayout email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_psw_email);

        email = findViewById(R.id.forgot_psw_email_email);

        Button btn;

        btn = findViewById(R.id.reset_pwd_enter);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(validate_email()){
                    String user_email = email.getEditText().getText().toString();

                    Boolean checkUser = userDao.checkUserEmail(user_email);

                    if(checkUser == true){
                        Intent it = new Intent(ForgotPswEmail.this, ForgotPswEnter.class);
                        it.putExtra("email", user_email);
                        startActivity(it);
                    }else {
                        email.setError("This email haven't registered!");
                    }

                }
            }
        });
    }

    private boolean validate_email()
    {
        String textEmail = email.getEditText().getText().toString().trim();

        if(textEmail.isEmpty()) {
            email.setError("This field cannot be empty!");
            return false;
        } else if(textEmail.length() < 3) {
            email.setError("Minimum 3 characters");
            return false;
        } else if(textEmail.length() > 50){
            email.setError("Maximum 50 characters");
            return false;
        } else if(!isEmailValid(textEmail)){
            email.setError("Please enter a valid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private boolean isEmailValid(CharSequence address){
        return Patterns.EMAIL_ADDRESS.matcher(address).matches();
    }
}