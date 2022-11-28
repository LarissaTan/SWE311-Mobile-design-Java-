package com.example.funnylearning.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.navigation.PopUpToBuilder;

public class MyDatabase extends SQLiteOpenHelper {

    //定义数据库名和版本号
    public static final String name = "funny_learning.db";
    public static final int DB_VERSION = 1;

    // for login signup purpose
    // only store login information
    public static final String CREATE_USER = "create table tb_User(email varchar(50) primary key, password varchar(20))";

    // store user information
    public static final String CREATE_USERDATA = "create table tb_UserData(userId int primary key AUTOINCREMENT,name varchar(20),email varchar(50), age int, gender bool, foreign key (email) references tb_User(email))";

    public static final String CREATE_CARTOONDATA = "create table tb_Cartoon(id int primary key,name varchar(20),level int,url varchar(40),duration varchar(10),summary varchar(200),key1 varchar(200),key2 varchar(200))";

    // drop table
    public static final String DROP_USER = "drop Table if exists tb_User";
    public static final String DROP_USERDATA = "drop Table if exists tb_UserData";

    //构造函数
    public MyDatabase(Context context) {
        super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_USERDATA);
        db.execSQL(CREATE_CARTOONDATA);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_USER);
        db.execSQL(DROP_USERDATA);
    }

}
