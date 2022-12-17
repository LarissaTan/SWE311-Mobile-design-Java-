package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Database.UserDataDao;
import com.example.funnylearning.R;

import java.sql.Time;

public class DataUserData {
    public static void start(Context context){
        UserDataDao userDataDao = new UserDataDao(context);
        userDataDao.open();

        int userAmount = 6;

        String[] email = new String[userAmount];
        String[] name = new String[userAmount];
        Boolean[] gender = new Boolean[userAmount];
        int[] age = new int[userAmount];
        int[] photo = new int[userAmount];
        Time[] time = new Time[userAmount];

        // account 1
        email[0] = "swe2009514@xmu.edu.my";
        name[0] = "Tan Qianqian";
        gender[0] = false;
        age[0] = 12;
        photo[0] = R.drawable.user_avatar;
        time[0] = new Time(0,60,0);

        // account 2
        email[1] = "test@gmail.com";
        name[1] = "Testing man";
        gender[1] = true;
        age[1] = 3;
        photo[1] = R.drawable.profile_photo_2;
        time[1] = new Time(0,5,0);

        // account 3
        email[2] = "shuze@gmail.com";
        name[2] = "Cheh Shu Ze";
        gender[2] = true;
        age[2] = 6;
        photo[2] = R.drawable.profile_photo_3;
        time[2] = new Time(0,45,0);

        // account 4
        email[3] = "congkin@gmail.com";
        name[3] = "Ong Cong Kin";
        gender[3] = true;
        age[3] = 4;
        photo[3] = R.drawable.profile_photo_4;
        time[3] = new Time(0,25,0);

        // account 5
        email[4] = "jessey@gmail.com";
        name[4] = "Jessey";
        gender[4] = false;
        age[4] = 10;
        photo[4] = R.drawable.profile_photo_5;
        time[4] = new Time(0,55,0);

        email[4] = "yutong@gmail.com";
        name[4] = "Liu Yutong";
        gender[4] = false;
        age[4] = 8;
        photo[4] = R.drawable.profile_photo_3;
        time[4] = new Time(0,45,0);

        for(int i=0; i<userAmount; i++){
            if(email[i]!=null){
                if(userDataDao.insertEmailName(email[i], name[i])){
                    userDataDao.updateGender(gender[i], i+1);
                    userDataDao.updateAge(age[i], i+1);
                    userDataDao.updatePhoto(photo[i], i+1);
                    userDataDao.updateLearningGoal(time[i], i+1);
                }
            }
        }
    }
}
