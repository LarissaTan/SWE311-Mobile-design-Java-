package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.funnylearning.Bean.FindWordsBean;

import java.util.ArrayList;

public class FindWordsDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    public FindWordsDao(Context context) {
        this.context = context;
    }

    public long insertFindWords(FindWordsBean temp){
        ContentValues values = new ContentValues();

        if(isWordsExist(temp.correct)){
            return -1;
        }

        values.put("id",temp.id);
        values.put("correct",temp.correct);
        values.put("wrong1",temp.wrong1);
        values.put("wrong2",temp.wrong2);

        long tmp = db.insert("tb_FindWords",null,values);

        return tmp;
    }

    public boolean isWordsExist(String tmp){
        Cursor cursor = db.query("tb_FindWords", null,"correct=?", new String[]{tmp},null,null,null);
        return cursor.moveToNext();
    }

    @SuppressLint("Range")
    public ArrayList<FindWordsBean> getAllWords() {
        ArrayList<FindWordsBean> words = new ArrayList<FindWordsBean>();
        Cursor cursor = db.query("tb_FindWords", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                FindWordsBean f = new FindWordsBean();
                f.id = Integer.valueOf(cursor.getString(cursor.getColumnIndex("id")));
                f.correct  = cursor.getString(cursor.getColumnIndex("correct"));
                f.wrong1 = cursor.getString(cursor.getColumnIndex("wrong1"));
                f.wrong2 = cursor.getString(cursor.getColumnIndex("wrong2"));
                words.add(f);
            }
            return words;
        }
    }
}
