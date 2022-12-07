package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.FindWordsBean;
import com.example.funnylearning.Bean.GameBean;

import java.util.ArrayList;

public class GameDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    public GameDao(Context context) {this.context=context;}

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

    public long insertGame(GameBean temp){
        ContentValues values = new ContentValues();

        if(isExist(temp.name)){
            return -1;
        }

        values.put("name",temp.name);
        values.put("typeId",temp.typeId);
        values.put("rating",temp.rating);
        values.put("image",temp.image);
        values.put("link",temp.link);

        long tmp = db.insert("tb_Game",null,values);
        return tmp;
    }

    public Boolean isExist(String tmp){
        Cursor cursor = db.query("tb_Game", null,"name=?", new String[]{tmp},null,null,null);
        return cursor.moveToNext();
    }

    @SuppressLint("Range")
    public ArrayList<GameBean> getAllGames() {
        ArrayList<GameBean> games = new ArrayList<GameBean>();
        Cursor cursor = db.query("tb_Game", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                GameBean g = new GameBean();
                g.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("id")));
                g.name = cursor.getString(cursor.getColumnIndex("name"));
                g.typeId = Integer.parseInt(cursor.getString(cursor.getColumnIndex("typeId")));
                g.rating = Integer.parseInt(cursor.getString(cursor.getColumnIndex("rating")));
                g.image = Integer.parseInt(cursor.getString(cursor.getColumnIndex("image")));
                g.link = cursor.getString(cursor.getColumnIndex("link"));

                games.add(g);
            }
            return games;
        }
    }
}
