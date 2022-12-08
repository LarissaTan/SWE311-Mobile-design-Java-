package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class CourseLikeDao {
    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public CourseLikeDao(Context context) {
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

    public Boolean insertLike(int userId, int courseId){
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", userId);
        contentValues.put("courseId",courseId);
        long result = db.insert("tb_CourseLike",null,contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean deleteLike(int userId, int courseId){
        open();
        ContentValues contentValues = new ContentValues();
        long result = db.delete("tb_CourseLike", " userId = " + userId + " and courseId = " + courseId, null);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkLike(int userId, int courseId){
        open();
        Cursor cursor = db.rawQuery("select * from tb_CourseLike where userId = " + userId + " and courseId = " + courseId, null);
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    @SuppressLint("Range")
    public int getLikeNumber(int courseId){
        open();
        Cursor cursor = db.rawQuery("select count(userId) from tb_CourseLike where courseId = " + courseId, new String[]{});
        int likeNumber = 0;
        if(cursor.getCount()==0){
            return 0;
        }else{
            if(cursor.moveToNext()){
                likeNumber = cursor.getInt(cursor.getColumnIndex("count(userId)"));
            }else {
                return 0;
            }
        }
        return likeNumber;
    }
}
