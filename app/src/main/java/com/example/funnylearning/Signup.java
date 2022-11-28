package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDao;
import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity {

    UserDao userDao = new UserDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        TextInputEditText name, email, password, repassword;

        name = findViewById(R.id.signUp_name);
        email = findViewById(R.id.signUp_email);
        password = findViewById(R.id.signUp_password);
        repassword = findViewById(R.id.signUp_repassword);

        Button btnSignup;
        btnSignup = findViewById(R.id.btnSignup);
        Button btnJump;
        btnJump = findViewById(R.id.signUp_jump);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String user_name = name.getText().toString();
                String user_email = email.getText().toString();
                String user_password = password.getText().toString();
                String user_repassword = repassword.getText().toString();

                if(user_name.equals("")||user_email.equals("")||user_password.equals("")||user_repassword.equals(""))
                {
                    Toast.makeText(Signup.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(user_password.equals(user_repassword)){
                        Boolean checkUser = userDao.checkUserEmail(user_email);
                        if(checkUser==false)
                        {
                            Boolean insert = userDao.insertEmailPassword(user_email, user_password);
                            if(insert == true) {
                                Toast.makeText(Signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                                Intent it = new Intent(Signup.this, Gender.class);
                                startActivity(it);
                            }
                            else {
                                Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(Signup.this, "User already exists! Please log in", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(Signup.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }

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