package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.UserData;

import java.sql.Time;
import java.text.SimpleDateFormat;

public class UserDataDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public UserDataDao(Context context) {
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

    public Boolean insertEmailName (String email, String Name){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", Name);
        long result = db.insert("tb_UserData",null,contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    @SuppressLint("Range")
    public Integer getUserId(String email)
    {
        open();
        Cursor cursor = db.rawQuery("select userId from tb_UserData where email = ? ;", new String[]{email});
        Integer id = 0;
        if (cursor.getCount() == 0 ) {
            return -1;
        } else {
            while (cursor.moveToNext()) {
                id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("userId")));
            }
            return id;
        }
    }

    public Boolean updateGender(Boolean gender, int userId){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gender", gender);
        long result = db.update("tb_UserData", contentValues, "userId =" + userId, new String[]{});
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean updateAge(int age, int userId){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("age", age);
        long result = db.update("tb_UserData", contentValues, "userId =" + userId, new String[]{});
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean updatePhoto(int photoId, int userId){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("profilePicture", photoId);
        long result = db.update("tb_UserData", contentValues, "userId =" + userId, new String[]{});
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean updateLearningGoal(Time time, int userId){
        open();
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        contentValues.put("learningGoal", dateFormat.format(time));
        long result = db.update("tb_UserData", contentValues, "userId =" + userId, new String[]{});
        if (result == -1) return false;
        else
            return true;
    }

    @SuppressLint("Range")
    public UserData getUserData(int userId){
        UserData userData = new UserData();
        open();

        Cursor cursor = db.rawQuery("select * from tb_UserData where userId = " + userId, new String[]{});
        if(cursor.getCount() == 0)
            return null;
        else {
            if (cursor.moveToNext()) {
                userData.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                userData.setName(cursor.getString(cursor.getColumnIndex("name")));
                userData.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                userData.setAge(cursor.getInt(cursor.getColumnIndex("age")));
                userData.setGender(cursor.getInt(cursor.getColumnIndex("gender"))>0);
                Time time = new Time(cursor.getLong(cursor.getColumnIndex("learningGoal")));
                userData.setLearningGoal(time);
                userData.setLocation(cursor.getString(cursor.getColumnIndex("location")));
                userData.setProfilePicture(cursor.getInt(cursor.getColumnIndex("profilePicture")));

                return userData;
            } else {
                return null;
            }
        }
    }
}
