package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.UserGameRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class UserGameRecordDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    public UserGameRecordDao(Context context) {this.context = context;}

    public void open() throws SQLiteException {
        dbHelper = new MyDatabase(context);
        //dbHelper.onCreate(db);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    public Boolean insertScore(UserGameRecord gameRecord){

            open();
            ContentValues values = new ContentValues();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            values.put("date", dateFormat.format(gameRecord.getDate()));
            values.put("userId", gameRecord.getUserId());
            values.put("gameId", gameRecord.getGameId());
            values.put("score",gameRecord.getScore());

            long result = db.insert("tb_UserGameRecord",null,values);

            if (result == -1)
                return false;
            else
                return true;
    }

    @SuppressLint("Range")
    public ArrayList<UserGameRecord> getAllScore(){
        open();
        ArrayList<UserGameRecord> scoreList = new ArrayList<UserGameRecord>();
        Cursor cursor = db.query("tb_UserGameRecord", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                UserGameRecord gameRecord = new UserGameRecord();
                Date date = new Date(cursor.getLong(cursor.getColumnIndex("date")));
                gameRecord.setDate(date);
                gameRecord.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("userId"))));
                gameRecord.setGameId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gameId"))));
                gameRecord.setScore(Integer.parseInt(cursor.getString(cursor.getColumnIndex("score"))));
            }
            return scoreList;
        }
    }
}
