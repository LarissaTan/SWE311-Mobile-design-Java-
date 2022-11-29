package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

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

    public Boolean insertGender(Boolean gender, int userId){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("gender", gender);
        long result = db.update("tb_UserData", contentValues, "userId =" + userId, new String[]{});
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean insertAge(int age, int userId){
        open();
        Cursor cursor = db.rawQuery("update tb_UserData set age = " + age +" where userId = " + userId + ";", new String[]{});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
