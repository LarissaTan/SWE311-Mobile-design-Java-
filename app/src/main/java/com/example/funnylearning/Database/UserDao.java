package com.example.funnylearning.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UserDao {
    private SQLiteDatabase sqLiteDatabase;

    public UserDao(Context context){
        MyDatabase mySQLiteHelper = new MyDatabase(context);
        sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
    }
}
