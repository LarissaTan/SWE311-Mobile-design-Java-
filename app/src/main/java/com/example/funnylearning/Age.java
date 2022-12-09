package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDataDao;
import com.shawnlin.numberpicker.NumberPicker;

public class Age extends AppCompatActivity {

    NumberPicker numberPicker;

    UserDataDao userDataDao = new UserDataDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_age);
        Bundle extras = getIntent().getExtras();
        int userId =0;
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }
        int finalUserId = userId;

        //age_btn1
        Button btn;

        btn = findViewById(R.id.age_btn1);

        numberPicker = findViewById(R.id.age_number_picker);

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                int age = numberPicker.getValue();
                if (age>=3 && age<=12){
                    Boolean insert = userDataDao.updateAge(age, finalUserId);

                    if(insert == true){
                        Intent it = new Intent(Age.this, Goal.class);
                        it.putExtra("userId",finalUserId);
                        startActivity(it);
                        finish();
                    }else {
                        Toast.makeText(Age.this, "update age fail", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(Age.this, "the age is not in range", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}