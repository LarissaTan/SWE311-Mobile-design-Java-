package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.UserLevel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class UserGoalLevelDao {
    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public UserGoalLevelDao(Context context) {
        this.context = context;
    }

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

    public Boolean checkGoalLevel(int userId, int typeId){
        open();
        Cursor cursor = db.rawQuery("select * from tb_UserGoalLevel where userId = " + userId + " and typeId = " + typeId, new String[] {});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean insertLevel(UserLevel levelList){

        if(!checkGoalLevel(levelList.getUserId(), levelList.getTypeId()))
        {
            open();
            ContentValues contentValues = new ContentValues();

            contentValues.put("userId", levelList.getUserId());
            contentValues.put("typeId", levelList.getTypeId());
            contentValues.put("achievement", true);
            long result = db.insert("tb_UserGoalLevel",null,contentValues);
            if (result == -1) return false;
            else
                return true;
        }else{
            return false;
        }
    }

    public Boolean insertGoal(UserLevel goalList){
        if(!checkGoalLevel(goalList.getUserId(), goalList.getTypeId()))
        {
            open();
            ContentValues contentValues = new ContentValues();

            contentValues.put("userId", goalList.getUserId());
            contentValues.put("typeId", goalList.getTypeId());
            contentValues.put("achievement", false);
            long result = db.insert("tb_UserGoalLevel",null,contentValues);
            if (result == -1) return false;
            else
                return true;
        }else{
            return false;
        }
    }

    @SuppressLint("Range")
    public Boolean updateLevel (int userId, int gameId){
        open();
        Cursor cursor = db.rawQuery("select typeId from tb_Game where gameId = " + gameId,null);
        int typeId = 0;

        if(cursor.getCount()==0)
            return false;
        else
        {
            if(cursor.moveToNext())
            {
                typeId = cursor.getInt(cursor.getColumnIndex("typeId"));
            }else {
                return false;
            }

            if(!checkGoalLevel(userId, typeId)){
                UserLevel level = new UserLevel();
                level.setUserId(userId);
                level.setTypeId(typeId);
                insertLevel(level);
                return true;
            }
            else
            {
                ContentValues contentValues = new ContentValues();
                contentValues.put("achievement", true);
                long result = db.update
                        ("tb_UserGoalLevel",contentValues,
                                "userId = " + userId + " and typeId = " + typeId, null);

                return result != -1;
            }
        }
    }
}
