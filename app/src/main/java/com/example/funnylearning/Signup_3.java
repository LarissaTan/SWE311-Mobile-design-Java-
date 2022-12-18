package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDao;
import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.others.Encrypt;
import com.google.android.material.textfield.TextInputLayout;

public class Signup_3 extends AppCompatActivity {

    TextInputLayout password, repassword;

    UserDao userDao = new UserDao(this);
    UserDataDao userDataDao = new UserDataDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup3);

        Bundle extras = getIntent().getExtras();
        String email = null;
        String name = null;
        if(extras != null){
            email = extras.getString("user_email");
            name = extras.getString("name");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }

        password = findViewById(R.id.signUp_inputlayout_password);
        repassword = findViewById(R.id.signUp_inputlayout_repassword);

        Button btn;
        btn = findViewById(R.id.btnSignup_3);

        String finalEmail = email;
        String finalName = name;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(confirmInput()){
                    String textPassword = Encrypt.SHA(password.getEditText().getText().toString());

                    Boolean insert = userDao.insertEmailPassword(finalEmail, textPassword);
                    if (insert == true) {
                        Toast.makeText(Signup_3.this, "Registered successfully", Toast.LENGTH_SHORT).show();

                        Boolean insertName = userDataDao.insertEmailName(finalEmail, finalName);
                        Integer userId = userDataDao.getUserId(finalEmail);

                        if (insertName == true && userId != -1) {
                            Intent it = new Intent(Signup_3.this, Gender.class);
                            it.putExtra("userId", userId);
                            startActivity(it);
                            finish();
                        } else {
                            Toast.makeText(Signup_3.this, "User data create fail", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Signup_3.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Signup_3.this, "Please enter valid input!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean confirmInput(){
        if(!validate_password() | !validate_repassword() ){
            return false;
        } else
            return true;
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