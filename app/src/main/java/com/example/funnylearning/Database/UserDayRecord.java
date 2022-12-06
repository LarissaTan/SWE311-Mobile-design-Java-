package com.example.funnylearning.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.funnylearning.Bean.DayRecordBean;

import java.util.Date;

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


        return 0;
    }

    private boolean isDayRecordExist(Date tmp) {
        Cursor cursor = db.query("tb_UserDayRecord", null,"recordDate=?", new String[]{String.valueOf(tmp)},null,null,null);
        return cursor.moveToNext();
    }
}
