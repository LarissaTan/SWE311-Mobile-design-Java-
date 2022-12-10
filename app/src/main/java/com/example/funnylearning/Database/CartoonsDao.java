package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.DayRecordBean;
import com.example.funnylearning.Bean.model.Cartoons;

import java.io.UnsupportedEncodingException;
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
        values.put("image",cartoon.image);

        long tmp = db.insert("tb_Cartoon",null,values);

        ContentValues values1 = new ContentValues();
        values1.put("id",cartoon.id);
        values1.put("summary",cartoon.Summary);
        values1.put("key1",cartoon.Key1);
        values1.put("key2",cartoon.Key2);

        return db.insert("tb_CartoonData",null,values1);
    }

    public boolean isCartoonExist(String tmp){
        Cursor cursor = db.query("tb_Cartoon", null,"name=?", new String[]{tmp},null,null,null);
        return cursor.moveToNext();
    }

    //查找所有cartoon的信息
    @SuppressLint("Range")
    public ArrayList<Cartoons> getAllCartoons() {
        ArrayList<Cartoons> cartoons = new ArrayList<Cartoons>();
        Cursor cursor = db.query("tb_Cartoon", null, null, null, null, null,null);
        Cursor cursor1 = db.query("tb_CartoonData",null,null,null,null,null,null);


        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                cursor1.moveToNext();
                Cartoons c = new Cartoons();
                c.id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("id")));
                c.Name  = cursor.getString(cursor.getColumnIndex("name"));
                c.Level = Integer.valueOf(cursor.getString(cursor.getColumnIndex("level")));
                c.Url = cursor.getString(cursor.getColumnIndex("url"));
                c.Duration = cursor.getString(cursor.getColumnIndex("duration"));
                c.Summary = cursor1.getString(cursor1.getColumnIndex("summary"));
                c.Key1 = cursor1.getString(cursor1.getColumnIndex("key1"));
                c.Key2 = cursor1.getString(cursor1.getColumnIndex("key2"));
                c.image = cursor.getString(cursor.getColumnIndex("image"));
                cartoons.add(c);
            }
            return cartoons;
        }
    }

    @SuppressLint("Range")
    public ArrayList<Cartoons> getKeyCartoons(String keyword){
        ArrayList<Cartoons> cartoons = new ArrayList<Cartoons>();
        Cursor cursor = null;

        cursor = db.rawQuery("select * from tb_Cartoon where name like ? ", new String[]{"%"+keyword+"%"});


        Cursor cursor_tmp;
//        String sql2 = "select name from tb_CartoonData where name like '%"+keyword+"%'";//注意：这里有单引号
//        Cursor cursor1 = db.rawQuery(sql2,null);

        int resultCounts = cursor.getCount();  //记录总数

        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Cartoons c = new Cartoons();
                c.id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("id")));
                c.Name = cursor.getString(cursor.getColumnIndex("name"));
                c.Level = Integer.valueOf(cursor.getString(cursor.getColumnIndex("level")));
                c.Url = cursor.getString(cursor.getColumnIndex("url"));
                c.Duration = cursor.getString(cursor.getColumnIndex("duration"));
                c.image = cursor.getString(cursor.getColumnIndex("image"));

                cursor_tmp = db.query("tb_CartoonData", null, "id=" + c.id, new String[]{}, null, null, null);
                if(cursor_tmp.moveToFirst()) {
                    c.Summary = cursor_tmp.getString(cursor_tmp.getColumnIndex("summary"));
                    c.Key1 = cursor_tmp.getString(cursor_tmp.getColumnIndex("key1"));
                    c.Key2 = cursor_tmp.getString(cursor_tmp.getColumnIndex("key2"));
                }
                cartoons.add(c);
            }
            return cartoons;
        }
    }
}
