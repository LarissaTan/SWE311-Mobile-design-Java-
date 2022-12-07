package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.GameBean;
import com.example.funnylearning.Bean.model.CourseType;
import com.example.funnylearning.Bean.model.UserGameRecord;

import java.util.ArrayList;

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

        if(!checkRecord(gameRecord.getUserId(), gameRecord.getGameId()))
        {
            open();
            ContentValues values = new ContentValues();

            values.put("userId", gameRecord.getUserId());
            values.put("typeId", gameRecord.getGameId());
            values.put("score",gameRecord.getScore());

            long result = db.insert("tb_UserGameRecord",null,values);

            if (result == -1)
                return false;
            else
                return true;
        }else{
            return false;
        }

    }

    public Boolean checkRecord(int uId, int gId){
        open();
        Cursor cursor = db.rawQuery("select * from tb_UserGameRecord where userId = " + uId + " and gameId = " + gId, new String[] {});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
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
                gameRecord.setUserId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("userId"))));
                gameRecord.setGameId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gameId"))));
                gameRecord.setScore(Integer.parseInt(cursor.getString(cursor.getColumnIndex("score"))));
            }
            return scoreList;
        }
    }
}
