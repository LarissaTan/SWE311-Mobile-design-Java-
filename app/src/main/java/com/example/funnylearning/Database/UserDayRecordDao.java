package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.DayRecordBean;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDayRecordDao {
    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //打开数据库
    public void open() throws SQLiteException {
        dbHelper = new MyDatabase(context);
        //dbHelper.onCreate(db);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //关闭数据库
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    //构造函数
    public UserDayRecordDao(Context context) {
        this.context = context;
    }
    // userId integer, mood varchar(10), activity varchar(10), weather varchar(10), learningTime time, recordDate date
    public long insertDayRecord(DayRecordBean tmp){
        ContentValues values = new ContentValues();

//        if(isDayRecordExist(tmp.recordDate, tmp.userid)){
//            return -1;
//        }

        values.put("activity", tmp.activity);
        values.put("learningTime", tmp.learningTime);
        values.put("mood", tmp.mood);
        values.put("userId", tmp.userid);
        values.put("weather", tmp.weather);
        values.put("recordDate", String.valueOf(tmp.recordDate));

        return db.insert("tb_UserDayRecord",null,values);
    }

    @SuppressLint("Range")
    public void changeActivity(String activity, int id){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String tmp = date.format(formatter);
        db.execSQL("update tb_UserDayRecord set activity=? where recordDate=? and userId=?", new Object[]{activity, tmp, id});
    }

    @SuppressLint("Range")
    public void changeWeather(String weather, int id){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String tmp = date.format(formatter);
        db.execSQL("update tb_UserDayRecord set weather=? where recordDate=? and userId=?", new Object[]{weather, tmp, id});
    }

    @SuppressLint("Range")
    public void changeMood(String mood, int id){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String tmp = date.format(formatter);
        db.execSQL("update tb_UserDayRecord set mood=? where recordDate=? and userId=?", new Object[]{mood, tmp, id});
    }

    @SuppressLint("Range")
    public void changeTime(int time, int id){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String tmp = date.format(formatter);
        db.execSQL("update tb_UserDayRecord set learningTime=? where recordDate=? and userId=?", new Object[]{time, tmp, id});
    }

    private boolean isDayRecordExist(String tmp, int id) {
//        Cursor cursor_tmp = db.query("tb_UserDayRecord", null, "userId=" + id, new String[]{},null, null,null);
//        Cursor cursor = null;
//        if(cursor_tmp != null) {
//            cursor = db.query("tb_UserDayRecord", null, "recordDate=?", new String[]{tmp}, null, null, null);
//        }else{
//            return true;
//        }
        Cursor cursor = db.query("tb_UserDayRecord", null, "userId=" + id, new String[]{},null, null,null);
        return true;
    }

    @SuppressLint("Range")
    public DayRecordBean findNewestRecord(int id){
        DayRecordBean temp = new DayRecordBean();

        Cursor cursor = db.query("tb_UserDayRecord", null, "userId=" + id, new String[]{},null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            cursor.moveToLast();

            temp.recordDate = cursor.getString(cursor.getColumnIndex("recordDate"));
            temp.activity = cursor.getString(cursor.getColumnIndex("activity"));
            temp.learningTime = Integer.parseInt(cursor.getString(cursor.getColumnIndex("learningTime")));
            temp.mood = cursor.getString(cursor.getColumnIndex("mood"));
            temp.weather = cursor.getString(cursor.getColumnIndex("weather"));
        }
        return temp;
    }

    public void deleteRecord(int id, String date){
        open();
        ContentValues contentValues = new ContentValues();
        db.delete("tb_UserDayRecord", " userId = " + id + " and recordDate = " + date, null);
    }
}
