package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.funnylearning.Bean.DayRecordBean;

public class UserDayRecord {
    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public UserDayRecord(Context context) {
        this.context = context;
    }
    // userId integer, mood varchar(10), activity varchar(10), weather varchar(10), learningTime time, recordDate date
    public long insertDayRecord(DayRecordBean tmp){

        ContentValues values = new ContentValues();

        if(isDayRecordExist(tmp.recordDate)){
            return -1;
        }

        values.put("activity", tmp.activity);
        values.put("learningTime", tmp.learningTime);
        values.put("mood", tmp.mood);
        values.put("userId", tmp.userid);
        values.put("weather", tmp.weather);
        values.put("recordDate", String.valueOf(tmp.recordDate));

        return db.insert("tb_UserDayRecord",null,values);
    }

    private boolean isDayRecordExist(String tmp) {
        Cursor cursor = db.query("tb_UserDayRecord", null,"recordDate=?", new String[]{String.valueOf(tmp)},null,null,null);
        return cursor.moveToNext();
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
            temp.learningTime = cursor.getString(cursor.getColumnIndex("learningTime"));
            temp.mood = cursor.getString(cursor.getColumnIndex("mood"));
            temp.weather = cursor.getString(cursor.getColumnIndex("weather"));
        }
        return temp;
    }
}
