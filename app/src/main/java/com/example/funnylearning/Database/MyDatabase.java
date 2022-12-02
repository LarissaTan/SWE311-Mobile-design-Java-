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
    public static final String CREATE_USER = "create table if not exists tb_User(email varchar(50) primary key, password varchar(20));";

    // store user information
    public static final String CREATE_USERDATA = "create table if not exists tb_UserData(userId integer primary key AUTOINCREMENT,name varchar(20),email varchar(50), age int, gender bool, foreign key (email) references tb_User(email));";

    public static final String CREATE_CARTOONDATA = "create table if not exists tb_Cartoon(id int primary key,name varchar(20),level int,url varchar(40),duration varchar(10), image varchar(100))";
    public static final String CREATE_CARTOONDETAILS = "create table if not exists tb_CartoonData(id int primary key,summary varchar(200),key1 varchar(200),key2 varchar(200))";
    public static final String CREATE_FINDWORDS = "create table if not exists tb_FindWords(id int primary key, correct varchar(10), wrong1  varchar(10), wrong2 varchar(10))";
    public static final String CREATE_DELIVERGOODS =  "create table if not exists tb_DeliverGoods(id int primary key, word varchar(10), corrGood varchar(200), wrongGood1 varchar(200), wrongGood2 varchar(200),audio varchar(100))";

    // drop table
    public static final String DROP_USER = "drop Table if exists tb_User;";
    public static final String DROP_USERDATA = "drop Table if exists tb_UserData;";
    public static final String DROP_CARTOON = "drop Table if exists tb_Cartoon;";
    public static final String DROP_CARTOONDATA = "drop Table if exists tb_CartoonData;";



    //构造函数
    public MyDatabase(Context context) {
        super(context, name, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //testing
        db.execSQL(DROP_USERDATA);
        db.execSQL(DROP_USER);
        db.execSQL(DROP_CARTOON);
        db.execSQL(DROP_CARTOONDATA);

        db.execSQL(CREATE_USER);
        db.execSQL(CREATE_USERDATA);
        db.execSQL(CREATE_CARTOONDATA);
        db.execSQL(CREATE_CARTOONDETAILS);
        db.execSQL(CREATE_FINDWORDS);
        db.execSQL(CREATE_DELIVERGOODS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
