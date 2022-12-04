package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDao;
import com.example.funnylearning.Database.UserDataDao;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Signup extends AppCompatActivity {

    UserDao userDao = new UserDao(this);
    UserDataDao userDataDao = new UserDataDao(this);

    TextInputLayout name, email, password, repassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name = findViewById(R.id.signUp_inputlayout_name);
        email = findViewById(R.id.signUp_inputlayout_email);
        password = findViewById(R.id.signUp_inputlayout_password);
        repassword = findViewById(R.id.signUp_inputlayout_repassword);

        Button btnSignup;
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (confirmInput()) {
                    String user_name = name.getEditText().getText().toString();
                    String user_email = email.getEditText().getText().toString();
                    String user_password = password.getEditText().getText().toString();

                    Boolean checkUser = userDao.checkUserEmail(user_email);

                    if (checkUser == false) {
                        Boolean insert = userDao.insertEmailPassword(user_email, user_password);
                        if (insert == true) {
                            Toast.makeText(Signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                            Boolean insertName = userDataDao.insertEmailName(user_email, user_name);
                            Integer userId = userDataDao.getUserId(user_email);

                            if (insertName == true && userId != -1) {
                                Intent it = new Intent(Signup.this, Gender.class);
                                it.putExtra("userId", userId);
                                startActivity(it);
                            } else {
                                Toast.makeText(Signup.this, "User data create fail", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        email.setError("This email already registered!");
                        Toast.makeText(Signup.this, "User already exists! Please log in", Toast.LENGTH_SHORT).show();
                    }
                }
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

    private boolean confirmInput(){
        if(!validate_name() | !validate_email() | !validate_password() | !validate_repassword()){
            return false;
        } else
            return true;
    }

    private boolean validate_name()
    {
        String textName = name.getEditText().getText().toString().trim();

        if(textName.isEmpty()) {
            name.setError("This field cannot be empty!");
            return false;
        } else if(textName.length() < 3) {
            name.setError("Minimum 3 characters");
            return false;
        } else if(textName.length() > 20){
            name.setError("Maximum 20 characters");
            return false;
        } else {
            name.setError(null);
            return true;
        }
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