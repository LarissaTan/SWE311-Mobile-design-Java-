package com.example.funnylearning.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DeliverGoodDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    public DeliverGoodDao(Context context) {
        this.context = context;
    }

    public long insertDeliverGood(){

        return 0;

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
