package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.funnylearning.others.emailCode;
import com.google.android.material.textfield.TextInputLayout;

import javax.mail.MessagingException;

public class Signup_2 extends AppCompatActivity {

    TextInputLayout verificationCode, name;
    int validateCode=(int) (Math.random()*9000+1000);//随机生成一个四位整数

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

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            emailCode.sendEmail(email, validateCode);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        verificationCode = findViewById(R.id.signUp_inputlayout_emailcode);
        name = findViewById(R.id.signUp_inputlayout_name);

        Button btn;
        btn = findViewById(R.id.btnSignup_2);

        String finalEmail = email;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (confirmInput()){
                    String textName = name.getEditText().getText().toString();
                    Integer textCode = Integer.parseInt(verificationCode.getEditText().getText().toString());
                    if(textCode.equals(validateCode)){
                        Intent it = new Intent(Signup_2.this, Signup_3.class);
                        it.putExtra("user_email", finalEmail);
                        it.putExtra("name", textName);
                        startActivity(it);
                        finish();
                    }else {
                        verificationCode.setError("Invalid verification code!");
                        Toast.makeText(Signup_2.this, "Please enter the correct verification code!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean confirmInput(){
        if(!validate_code() | !validate_name() ){
            return false;
        } else
            return true;
    }

    private boolean validate_code()
    {
        String textCode = verificationCode.getEditText().getText().toString().trim();

        if(textCode.isEmpty()) {
            verificationCode.setError("This field cannot be empty!");
            return false;
        } else if(textCode.length()<4){
            verificationCode.setError("Minimum 4 character");
            return false;
        } else if(textCode.length()>4){
            verificationCode.setError("Maximum 4 character");
            return false;
        } else{
            verificationCode.setError(null);
            return true;
        }
    }
    private boolean validate_name()
    {
        String textPassword = name.getEditText().getText().toString().trim();

        if(textPassword.isEmpty()) {
            name.setError("This field cannot be empty!");
            return false;
        } else if(textPassword.length() < 3) {
            name.setError("Minimum 3 characters");
            return false;
        } else if(textPassword.length() > 20){
            name.setError("Maximum 20 characters");
            return false;
        } else {
            name.setError(null);
            return true;
        }
    }

}