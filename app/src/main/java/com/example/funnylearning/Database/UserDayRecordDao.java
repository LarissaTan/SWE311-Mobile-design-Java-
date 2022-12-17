package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.DayRecordBean;
import com.example.funnylearning.Bean.model.Cartoons;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

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

    public boolean isDayRecordExist(int id) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String tmp = date.format(formatter);
        Cursor cursor = db.query("tb_UserDayRecord", null, "recordDate=? and userId=" + id, new String[]{tmp}, null, null, null);
        System.out.println("cursor is " + cursor.getCount());
        if(cursor.getCount() == 0)
            return false;
        else
            return true;
    }

    public boolean isNoRecord(int id){
        Cursor cursor = db.query("tb_UserDayRecord", null, "userId=" + id, new String[]{}, null, null, null);
        if(cursor.getCount() == 0)
            return true;
        else
            return false;
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
        //System.out.println("id  = " + id + ",  date = " + date);
        db.execSQL("delete from tb_UserDayRecord where recordDate=? and userId=?", new Object[]{date, id});
    }

    int[] time = new int[7];

    @SuppressLint("Range")
    public int[] getColData(int id){
        open();
        Cursor cursor = db.query("tb_UserDayRecord", null, "userId=" + id, new String[]{}, null, null, null);
        int num = 0, total;
        total = cursor.getCount();

        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String tmp = date.format(formatter);
        int tmp1 = tmp.charAt(0) - 48;
        int tmp2 = tmp.charAt(1)  - 48;
        int today = tmp1*10 + tmp2;

        while (cursor.moveToNext()) {
            int t;
            // 去掉前面的数据，留下最后7条
            while(total > 7){
                cursor.moveToNext();
                total--;
            }

            String str = cursor.getString(cursor.getColumnIndex("recordDate"));
            int first = str.charAt(0) - 48;
            int second = str.charAt(1)  - 48;
            int day = first*10 + second;

            //找出近7天的数据
            if(today - day < 8 || (today < 7 && 24 -today < day) || today == day){
                System.out.println("str" + str);
                int week = getWeek(str);
                t = Integer.valueOf(cursor.getString(cursor.getColumnIndex("learningTime")));
                System.out.println("str" + str + ", week" + week + ", t" + t);
                time[week] = t;
            }
        }
        return time;



    }

    public static int getWeek(String time) {
        int Week = -1;
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(format.parse(time));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int wek=c.get(Calendar.DAY_OF_WEEK);
        System.out.println("week is "+wek);

        if (wek == 1) {
            Week = 6;
        }
        if (wek == 2) {
            Week = 0;
        }
        if (wek == 3) {
            Week = 1;
        }
        if (wek == 4) {
            Week = 2;
        }
        if (wek == 5) {
            Week = 3;
        }
        if (wek == 6) {
            Week = 4;
        }
        if (wek == 7) {
            Week = 5;
        }
        return Week;
    }
}
