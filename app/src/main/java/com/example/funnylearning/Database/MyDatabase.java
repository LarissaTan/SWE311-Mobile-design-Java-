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
    public static final String CREATE_USERDATA = "create table if not exists tb_UserData(userId integer primary key AUTOINCREMENT,name varchar(20),email varchar(50), age int, gender bool, learningGoal time, location text, profilePicture int, foreign key (email) references tb_User(email));";

    /* Course and game information table
    course type has a id -> type id include all the course in the system
    For example: math, reading (english), [future: science, moral, and other]
    table related to course will have a type id
    */
    public static final String CREATE_COURSETYPE = "create table if not exists tb_CourseType(typeId integer primary key autoincrement, typeName varchar(20), type varchar(20));";
    // course table: store the video link for the child to learn
    public static final String CREATE_COURSE = "create table if not exists tb_Course(courseId integer primary key autoincrement, courseName varchar(50), typeId integer, videoId varchar(20), duration time, coursePicture int, viewNumber int, level int, foreign key(typeId) references tb_CourseType(typeId));";
    // course comment: store the comment for the video
    public static final String CREATE_COURSECOMMENT = "create table if not exists tb_CourseComment(courseId integer, userId integer, comment text, time datetime, foreign key(courseID) references tb_Course(courseId), foreign key(userId) references tb_UserData(userId));";
    // course like: store the like for each video
    public static final String CREATE_COURSELIKE = "create table if not exists tb_CourseLike(courseId integer, userId integer, foreign key(courseId) references tb_Course(courseId), foreign key(userId) references tb_UserData(userId));";
    // game: to evaluate the child master the course or not
    public static final String CREATE_GAME = "create table if not exists tb_Game(gameId integer primary key autoincrement, gameName varchar(50), typeId integer, level int, gamePicture int, link varchar(100),  level int, foreign key(typeId) references tb_CourseType(typeId));";
    // user game record: to store the record of the game for each user
    public static final String CREATE_USERGAMERECORD = "create table if not exists tb_UserGameRecord(date date, userId integer, typeId integer, score int, foreign key(userId) references tb_UserData(userId), foreign key(typeId) references tb_CourseType(typeId));";
    // user goal: record the goal set by the user
    public static final String CREATE_USERGOALLEVEL = "create table if not exists tb_UserGoalLevel(userId integer, typeId integer, achievement bool default '0', foreign key(userId) references tb_UserData(userId), foreign key(typeId) references tb_CourseType(typeId));";
    // user other data
    public static final String CREATE_USERDAYRECORD = "create table if not exists tb_UserDayRecord(userId integer, mood varchar(10), activity varchar(10), weather varchar(10), learningTime time, recordDate date, foreign key(userId) references tb_UserData(userId));";
    // user learning data
    public static final String CREATE_USERLEARNINGRECORD = "create table if not exists tb_UserLearningData(userId integer, learningTime time, learningDate date, foreign key(userId) references tb_UserData(userId));";

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

        db.execSQL(CREATE_COURSETYPE);
        //db.execSQL(CREATE_COURSE);
        //db.execSQL(CREATE_COURSECOMMENT);
        //db.execSQL(CREATE_COURSELIKE);
        //db.execSQL(CREATE_GAME);
        //db.execSQL(CREATE_USERGAMERECORD);
        db.execSQL(CREATE_USERGOALLEVEL);
        //db.execSQL(CREATE_USERDAYRECORD);
        db.execSQL(CREATE_USERLEARNINGRECORD);

        db.execSQL(CREATE_CARTOONDATA);
        db.execSQL(CREATE_CARTOONDETAILS);
        db.execSQL(CREATE_FINDWORDS);
        db.execSQL(CREATE_DELIVERGOODS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

}
