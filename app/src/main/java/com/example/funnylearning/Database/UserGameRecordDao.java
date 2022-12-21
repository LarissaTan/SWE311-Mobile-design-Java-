package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.Game;
import com.example.funnylearning.Bean.model.UserGameRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import io.getstream.chat.android.client.models.User;

public class UserGameRecordDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    public UserGameRecordDao(Context context) {this.context = context;}

    //open db
    public void open() throws SQLiteException {
        dbHelper = new MyDatabase(context);
        //dbHelper.onCreate(db);
        try {
            db = dbHelper.getWritableDatabase();
        } catch (SQLiteException ex) {
            db = dbHelper.getReadableDatabase();
        }
    }

    //close db
    public void close() {
        if (db != null) {
            db.close();
            db = null;
        }
    }

    //insert score
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

    //get all game record
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

    //get record by type name
    @SuppressLint("Range")
    public UserGameRecord getRecordByTypeName(String typeName, int userId){
        open();
        UserGameRecord record = new UserGameRecord();
        Cursor cursor = db.rawQuery
                ("select * from tb_UserGameRecord where gameId in " +
                        "(select gameId from tb_Game where typeId in " +
                        "(select typeId from tb_CourseType where type = ? )) " +
                        "and userId = " + userId +
                        " order by date desc", new String[]{typeName});

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            if(cursor.moveToNext()) {
                Date date = new Date(cursor.getLong(cursor.getColumnIndex("date")));
                record.setDate(date);
                record.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("userId"))));
                record.setGameId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gameId"))));
                record.setScore(Integer.parseInt(cursor.getString(cursor.getColumnIndex("score"))));
            }
            return record;
        }
    }
}
