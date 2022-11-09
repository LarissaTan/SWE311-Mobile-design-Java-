package com.example.funnylearning.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDatabase extends SQLiteOpenHelper {

    //定义数据库名和版本号
    public static final String name = "funny_learning.db";
    public static final int DB_VERSION = 1;

    public static final String CREATE_USERDATA = "create table tb_User(id int primary key,name varchar(20),email varchar(20),pwd varchar(20),age int,gender bool)";
    //构造函数
    public MyDatabase(Context context) {
        super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USERDATA);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
