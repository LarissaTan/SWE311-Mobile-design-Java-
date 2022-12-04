package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDao;
import com.google.android.material.textfield.TextInputLayout;

public class ForgotPswEnter extends AppCompatActivity {

    UserDao userDao = new UserDao(this);
    TextInputLayout code, password, repassword;

    // temporary verification code
    String validateCode = "3212";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_psw_enter);
        Bundle extras = getIntent().getExtras();
        String user_email = null;
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

        String finalUser_email = user_email;
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                
                if(confirmInput())
                {
                    String user_password = password.getEditText().getText().toString();
                    String textCode = code.getEditText().getText().toString().trim(); 
                    
                    if(textCode == validateCode)
                    {
                        Boolean updatePassword = userDao.updatePassword(finalUser_email, user_password);

                        if(updatePassword == true)
                        {
                            Toast.makeText(getApplicationContext(),"You have successfully reset your password! Now, it`s time to login!", Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(ForgotPswEnter.this, ForgotPswSucceed.class);
                            startActivity(it);
                        }
                    }else
                    {
                        code.setError("Invalid Verification Code!");
                        Toast.makeText(ForgotPswEnter.this, "The verification code is invalid", Toast.LENGTH_SHORT).show();
                    }
                }else
                {
                    Toast.makeText(ForgotPswEnter.this, "Please enter valid input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean confirmInput(){
        if(!validate_code() | !validate_password() | !validate_repassword()){
            return false;
        } else
            return true;
    }

    private boolean validate_code()
    {
        String textCode = code.getEditText().getText().toString().trim();

        if(textCode.isEmpty()) {
            code.setError("This field cannot be empty!");
            return false;
        } else if(textCode.length() < 3) {
            code.setError("Minimum 3 characters");
            return false;
        } else if(textCode.length() > 5){
            code.setError("Maximum 5 characters");
            return false;
        } else {
            code.setError(null);
            return true;
        }
        
    }

    private boolean validate_password()
    {
        String textPassword = password.getEditText().getText().toString().trim();

        if(textPassword.isEmpty()) {
            password.setError("This field cannot be empty!");
            return false;
        } else if(textPassword.length() < 3) {
            password.setError("Minimum 3 characters");
            return false;
        } else if(textPassword.length() > 20){
            password.setError("Maximum 20 characters");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private boolean validate_repassword()
    {
        String textRepassword = repassword.getEditText().getText().toString().trim();
        String textPassword = password.getEditText().getText().toString().trim();

        if(textRepassword.isEmpty()) {
            repassword.setError("This field cannot be empty!");
            return false;
        } else if(textRepassword.length() < 3) {
            repassword.setError("Minimum 3 characters");
            return false;
        } else if(textRepassword.length() > 20){
            repassword.setError("Maximum 20 characters");
            return false;
        } else if(!textRepassword.equals(textPassword)){
            repassword.setError("The passwords are not same!");
            return false;
        } else {
            repassword.setError(null);
            return true;
        }
    }
}