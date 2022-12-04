package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDao;
import com.example.funnylearning.Database.UserDataDao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    UserDao userDao = new UserDao(this);
    UserDataDao userDataDao = new UserDataDao(this);

    TextInputLayout login_account, login_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //forgetPwd
        TextView forgetPassword, signUp;

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

                if (confirmInput()) {
                    String user_email = login_account.getEditText().getText().toString();
                    String user_password = login_password.getEditText().getText().toString();

                    Boolean checkUser = userDao.checkUserEmail(user_email);

                    if (checkUser == true) {
                        Boolean checkPassword = userDao.checkUserEmailPassword(user_email,user_password);

                        if(checkPassword == true){
                            Integer userId = userDataDao.getUserId(user_email);

                            if (userId != -1) {
                                Intent it = new Intent(Login.this, Homepage.class);
                                it.putExtra("userId", userId);
                                startActivity(it);
                            } else {
                                Toast.makeText(Login.this, "User data fail", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            login_password.setError("Wrong Password!");
                            Toast.makeText(Login.this, "Wrong Password!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        login_account.setError("User account not exists!");
                        Toast.makeText(Login.this, "User account not exists! Please sign up!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(Login.this, "Please enter valid input!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        jump.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent it = new Intent(Login.this, Homepage.class);
                startActivity(it);
            }
        });
    }

    private boolean confirmInput(){
        if(!validate_email() | !validate_password()){
            return false;
        } else
            return true;
    }

    private boolean validate_email()
    {
        String textEmail = login_account.getEditText().getText().toString().trim();

        if(textEmail.isEmpty()) {
            login_account.setError("This field cannot be empty!");
            return false;
        } else if(textEmail.length() < 3) {
            login_account.setError("Minimum 3 characters");
            return false;
        } else if(textEmail.length() > 50){
            login_account.setError("Maximum 50 characters");
            return false;
        } else if(!isEmailValid(textEmail)){
            login_account.setError("Please enter a valid email address");
            return false;
        } else {
            login_account.setError(null);
            return true;
        }
    }

    private boolean isEmailValid(CharSequence address){
        return Patterns.EMAIL_ADDRESS.matcher(address).matches();
    }

    private boolean validate_password()
    {
        String textPassword = login_password.getEditText().getText().toString().trim();

        if(textPassword.isEmpty()) {
            login_password.setError("This field cannot be empty!");
            return false;
        } else if(textPassword.length() < 3) {
            login_password.setError("Minimum 3 characters");
            return false;
        } else if(textPassword.length() > 20){
            login_password.setError("Maximum 20 characters");
            return false;
        } else {
            login_password.setError(null);
            return true;
        }
    }
}