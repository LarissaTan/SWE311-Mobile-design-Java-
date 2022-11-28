package com.example.funnylearning.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class UserDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public UserDao(Context context) {
        this.context = context;
    }

    //打开数据库
    public void open() throws SQLiteException {
        dbHelper = new MyDatabase(context);
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

    public Boolean insertData(String userId, String password){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userid", userId);
        contentValues.put("password", password);
        long result = db.insert("tb_User",null,contentValues);
        close();
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkUserId(String userId){
        open();
        Cursor cursor = db.rawQuery("select * from user where userid = ?", new String[] {userId});
        close();
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkUserNamePassword(String userId, String password){
        open();
        Cursor cursor = db.rawQuery("select * from user where userid = ? and password = ?", new String[] {userId, password});
        close();
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
