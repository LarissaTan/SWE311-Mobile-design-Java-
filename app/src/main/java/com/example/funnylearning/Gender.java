package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDataDao;

public class Gender extends AppCompatActivity {

    RadioGroup gender;
    RadioButton male, female;
    UserDataDao userDataDao = new UserDataDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);
        Bundle extras = getIntent().getExtras();
        int userId =0;
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }
        //gender_btn1
        Button gender_btn;

        gender_btn = findViewById(R.id.gender_btn1);

        gender = findViewById(R.id.gender_select);

        int finalUserId = userId;

        gender_btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int selectedId = gender.getCheckedRadioButtonId();
                if(selectedId == -1){
                    // no radio button are checked
                    Toast.makeText(Gender.this, "Please select a gender", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean genderMale = null;
                    // one of the radio button is checked
                    if (selectedId != R.id.gender_male && selectedId != R.id.gender_female) {
                        Toast.makeText(Gender.this, "Gender Selection Error", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (selectedId == R.id.gender_male){
                            genderMale = true;
                        }else if (selectedId == R.id.gender_female){
                            genderMale = false;
                        }else {
                            Toast.makeText(Gender.this, "Gender id wrong", Toast.LENGTH_SHORT).show();
                        }

                        Boolean insert = userDataDao.updateGender(genderMale, finalUserId);

                        if(insert == true){
                            Toast.makeText(Gender.this, "Gender Selected Successfully", Toast.LENGTH_SHORT).show();
                            Intent it = new Intent(Gender.this, MathLevel.class);
                            it.putExtra("userId",finalUserId);
                            startActivity(it);
                            finish();
                        }
                        else{
                            Toast.makeText(Gender.this, "Gender Selected fail", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}