package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.DeliverGoodBean;

import java.util.ArrayList;

public class DeliverGoodDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    public DeliverGoodDao(Context context) {
        this.context = context;
    }

    public long insertDeliverGood(DeliverGoodBean temp){
        ContentValues values = new ContentValues();

        if(isGoodExist(temp.word)){
            return -1;
        }

        values.put("id",temp.id);
        values.put("word",temp.word);
        values.put("corrGood",temp.correctUrl);
        values.put("wrongGood1",temp.wrong1Url);
        values.put("wrongGood2",temp.wrong2Url);
        values.put("audio",temp.audio);

        long tmp = db.insert("tb_DeliverGoods",null,values);

        return tmp;
    }

    public boolean isGoodExist(String word){
        Cursor cursor = db.query("tb_DeliverGoods", null,"word=?", new String[]{word},null,null,null);
        return cursor.moveToNext();
    }

    @SuppressLint("Range")
    public ArrayList<DeliverGoodBean> getAllGoods() {
        ArrayList<DeliverGoodBean> goods = new ArrayList<DeliverGoodBean>();
        Cursor cursor = db.query("tb_DeliverGoods", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                DeliverGoodBean g = new DeliverGoodBean();
                g.id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("id")));
                g.word  = cursor.getString(cursor.getColumnIndex("word"));
                g.correctUrl = cursor.getString(cursor.getColumnIndex("corrGood"));
                g.wrong1Url = cursor.getString(cursor.getColumnIndex("wrongGood1"));
                g.wrong2Url = cursor.getString(cursor.getColumnIndex("wrongGood2"));
                g.audio = cursor.getString(cursor.getColumnIndex("audio"));
                goods.add(g);
            }
            return goods;
        }
    }

//    public void open() throws SQLiteException {
//        dbHelper = new MyDatabase(context);
//        try {
//            db = dbHelper.getWritableDatabase();
//        } catch (SQLiteException ex) {
//            db = dbHelper.getReadableDatabase();
//        }
//    }
}
