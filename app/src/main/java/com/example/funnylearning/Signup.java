package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDao;
import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.others.HumanVerifidation;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
//veri_code_img_human
public class Signup extends AppCompatActivity {

    UserDao userDao = new UserDao(this);
    UserDataDao userDataDao = new UserDataDao(this);

    TextInputLayout email, code;
    ImageView image_code, refresh;

    String veriCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        code = findViewById(R.id.signUp_inputlayout_name);
        email = findViewById(R.id.signUp_inputlayout_name);
        image_code = findViewById(R.id.veri_code_img_human);
        refresh = findViewById(R.id.veri_code_refresh);

        HumanVerifidation humanVeri = new HumanVerifidation();
        Bitmap tmp = humanVeri.getImg();
        image_code.setImageBitmap(tmp);
        veriCode = humanVeri.getCode();

        Button btnSignup;
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (confirmInput()) {
                    String veri_code = code.getEditText().getText().toString();
                    String user_email = email.getEditText().getText().toString();

                    Boolean checkUser = userDao.checkUserEmail(user_email);

//                    if (checkUser == false) {
//                        Boolean insert = userDao.insertEmailPassword(user_email, user_password);
//                        if (insert == true) {
//                            Toast.makeText(Signup.this, "Registered successfully", Toast.LENGTH_SHORT).show();
//
//                            Boolean insertName = userDataDao.insertEmailName(user_email, user_name);
//                            Integer userId = userDataDao.getUserId(user_email);
//
//                            if (insertName == true && userId != -1) {
//                                Intent it = new Intent(Signup.this, Gender.class);
//                                it.putExtra("userId", userId);
//                                startActivity(it);
//                            } else {
//                                Toast.makeText(Signup.this, "User data create fail", Toast.LENGTH_SHORT).show();
//                            }
//                        } else {
//                            Toast.makeText(Signup.this, "Registration failed", Toast.LENGTH_SHORT).show();
//                        }
//                    } else {
//                        email.setError("This email already registered!");
//                        Toast.makeText(Signup.this, "User already exists! Please log in", Toast.LENGTH_SHORT).show();
//                    }
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

        refresh.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HumanVerifidation temp = new HumanVerifidation();
                Bitmap tmp_img = temp.getImg();
                image_code.setImageBitmap(tmp_img);
                veriCode = temp.getCode();
                System.out.println("code is " + veriCode);
            }
        });


    }

    private boolean confirmInput(){
        if(!validate_code() | !validate_email() ){
            return false;
        } else
            return true;
    }

    private boolean validate_code()
    {
        String textName = code.getEditText().getText().toString().trim();

        if(textName.isEmpty()) {
            code.setError("This field cannot be empty!");
            return false;
        } else if(textName.length() < 3) {
            code.setError("Minimum 3 characters");
            return false;
        } else if(textName.length() > 20){
            code.setError("Maximum 20 characters");
            return false;
        } else {
            code.setError(null);
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

//    private boolean validate_password()
//    {
//        String textPassword = password.getEditText().getText().toString().trim();
//
//        if(textPassword.isEmpty()) {
//            password.setError("This field cannot be empty!");
//            return false;
//        } else if(textPassword.length() < 3) {
//            password.setError("Minimum 3 characters");
//            return false;
//        } else if(textPassword.length() > 20){
//            password.setError("Maximum 20 characters");
//            return false;
//        } else {
//            password.setError(null);
//            return true;
//        }
//    }
//
//    private boolean validate_repassword()
//    {
//        String textRepassword = repassword.getEditText().getText().toString().trim();
//        String textPassword = password.getEditText().getText().toString().trim();
//
//        if(textRepassword.isEmpty()) {
//            repassword.setError("This field cannot be empty!");
//            return false;
//        } else if(textRepassword.length() < 3) {
//            repassword.setError("Minimum 3 characters");
//            return false;
//        } else if(textRepassword.length() > 20){
//            repassword.setError("Maximum 20 characters");
//            return false;
//        } else if(!textRepassword.equals(textPassword)){
//            repassword.setError("The passwords are not same!");
//            return false;
//        } else {
//            repassword.setError(null);
//            return true;
//        }
//    }

}