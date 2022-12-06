package com.example.funnylearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.funnylearning.Database.UserDataDao;

public class ChoosePhoto extends AppCompatActivity {

    LinearLayout choosePhoto[] = {null, null, null, null, null};
    ImageView chosenPhotoShow;
    int drawable[] = {0, 0, 0, 0, 0};
    int chosenPhoto = 0;
    UserDataDao userDataDao = new UserDataDao(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);
        Bundle extras = getIntent().getExtras();
        int userId =0;
        if(extras != null){
            userId = extras.getInt("userId");
        }else {
            Toast.makeText(this, "no id passed", Toast.LENGTH_SHORT).show();
        }
        int finalUserId = userId;

        chosenPhotoShow = findViewById(R.id.choose_photo_imgView1);

        choosePhoto[0] = findViewById(R.id.choose_photo_1);
        choosePhoto[1] = findViewById(R.id.choose_photo_2);
        choosePhoto[2] = findViewById(R.id.choose_photo_3);
        choosePhoto[3] = findViewById(R.id.choose_photo_4);
        choosePhoto[4] = findViewById(R.id.choose_photo_5);

        drawable[0] = R.drawable.user_avatar;
        drawable[1] = R.drawable.profile_photo_2;
        drawable[2] = R.drawable.profile_photo_3;
        drawable[3] = R.drawable.profile_photo_4;
        drawable[4] = R.drawable.profile_photo_5;

        setChoosePhoto(0);

        //register_finNext
        Button btn;

        btn = findViewById(R.id.register_finNext);

        choosePhoto[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosePhoto(0);
            }
        });

        choosePhoto[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosePhoto(1);
            }
        });

        choosePhoto[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosePhoto(2);
            }
        });

        choosePhoto[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosePhoto(3);
            }
        });

        choosePhoto[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setChoosePhoto(4);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Boolean checkPhoto = false;
                for(int i=0; i<5; i++){
                    if(chosenPhoto == drawable[i]){
                        checkPhoto = true;
                        break;
                    }
                }

                if(checkPhoto == true){
                    Boolean insert = userDataDao.updatePhoto(chosenPhoto, finalUserId);

                    if(insert == true){
                        Intent it = new Intent(ChoosePhoto.this, TimeSetting.class);
                        it.putExtra("userId",finalUserId);
                        startActivity(it);
                    }else {
                        Toast.makeText(ChoosePhoto.this, "Photo input fail", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChoosePhoto.this, "Please choose a photo!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void setChoosePhoto(int id){
        for(int i=0; i<5; i++){
            choosePhoto[i].setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        choosePhoto[id].setBackgroundColor(Color.parseColor("#C7C6C1"));
        chosenPhotoShow.setImageResource(drawable[id]);

        chosenPhoto = drawable[id];
    }
}