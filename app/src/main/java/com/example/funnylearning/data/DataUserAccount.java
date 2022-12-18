package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Database.UserDao;
import com.example.funnylearning.others.Encrypt;

public class DataUserAccount {
    public static void start(Context context){
        UserDao userDao = new UserDao(context);
        userDao.open();

        int accountNumber = 6;

        String[] email = new String[accountNumber];
        String[] password = new String[accountNumber];

        // account 1: qian qian
        email[0] = "swe2009514@xmu.edu.my";
        password[0] = "1234";
        // account 2: unknown
        email[1] = "test@gmail.com";
        password[1] = "1234";
        // account 3: shuze
        email[2] = "shuze@gmail.com";
        password[2] = "1234";
        // account 4: congkin
        email[3] = "congkin@gmail.com";
        password[3] = "1234";
        // account 5: jessey
        email[4] = "jessey@gmail.com";
        password[4] = "1234";
        // account 6: yutong
        email[5] = "yutong@gmail.com";
        password[5] = "1234";

        for(int i=0; i<accountNumber; i++){
            if(email[i] != null){
                userDao.insertEmailPassword(email[i], Encrypt.SHA(password[i]));
            }
        }
    }
}
