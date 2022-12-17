package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDao;
import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.others.HumanVerifidation;
import com.example.funnylearning.others.other;
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

        code = findViewById(R.id.signUp_inputlayout_human_code);
        email = findViewById(R.id.signUp_inputlayout_email);
        image_code = findViewById(R.id.veri_code_img_human);
        refresh = findViewById(R.id.veri_code_refresh);

        HumanVerifidation humanVeri = new HumanVerifidation();
        Bitmap tmp = humanVeri.getImg();
        image_code.setImageBitmap(tmp);
        veriCode = humanVeri.getCode();

        other te = new other();
        System.out.println("the hash code is "  +  te.SHA("123"));

        Button btnSignup;
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (confirmInput()) {
                    String user_email = email.getEditText().getText().toString();

                    Boolean checkUser = userDao.checkUserEmail(user_email);

                    if(checkUser == false){
                        Intent it = new Intent(Signup.this, Signup_2.class);
                        it.putExtra("user_email", user_email);
                        startActivity(it);
                        finishAndRemoveTask();
                    }else {
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
                finish();
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
        String textCode = code.getEditText().getText().toString().trim();

        if(textCode.isEmpty()) {
            code.setError("This field cannot be empty!");
            return false;
        } else if(textCode.length() < 4){
            code.setError("Minimum 4 characters");
            return false;
        } else if(textCode.length() > 4){
            code.setError("Maximum 4 characters");
            return false;
        }else if(!textCode.equals(veriCode)){
            code.setError("Wrong Code!!!");
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
}