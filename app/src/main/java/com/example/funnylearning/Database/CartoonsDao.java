package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.Cartoons;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartoonsDao {


    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public CartoonsDao(Context context) {
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

    public long insertCartoon(Cartoons cartoon){
        // 创建ContentValues对象
        ContentValues values = new ContentValues();

        if(isCartoonExist(cartoon.Name)){
            //用户已位于数据库中
            return -1;
        }

        values.put("id",cartoon.id);
        values.put("name",cartoon.Name);
        values.put("level",cartoon.Level);
        values.put("url",cartoon.Url);
        values.put("duration",cartoon.Duration);
        values.put("summary",cartoon.Summary);
        values.put("key1",cartoon.Key1);
        values.put("key2",cartoon.Key2);

        return db.insert("users",null,values);
    }

    public boolean isCartoonExist(String username){
        Cursor cursor = db.query("tb_Cartoon", null,"name=?", new String[]{username},null,null,null);
        return cursor.moveToNext();
    }

    //查找所有cartoon的信息
    @SuppressLint("Range")
    public ArrayList<Map<String, Object>> getAllCartoons() {
        ArrayList<Map<String, Object>> listCartoon = new ArrayList<Map<String, Object>>();
        Cursor cursor = db.query("tb_Cartoon", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", cursor.getString(cursor.getColumnIndex("id")));
                map.put("name", cursor.getString(cursor.getColumnIndex("name")));
                map.put("level", cursor.getString(cursor.getColumnIndex("level")));
                map.put("url", cursor.getString(cursor.getColumnIndex("url")));
                map.put("duration", cursor.getString(cursor.getColumnIndex("duration")));
                map.put("summary", cursor.getString(cursor.getColumnIndex("summary")));
                map.put("key1", cursor.getString(cursor.getColumnIndex("key1")));
                map.put("key2", cursor.getString(cursor.getColumnIndex("key2")));
                listCartoon.add(map);
            }
            return listCartoon;
        }
    }
}
