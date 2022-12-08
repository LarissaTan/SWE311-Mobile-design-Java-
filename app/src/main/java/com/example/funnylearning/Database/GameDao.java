package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.Game;
import com.example.funnylearning.Bean.model.UserGameRecord;

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

    public boolean insertGame(Game game){

        if(!checkGame(game.getName()))
        {
            open();
            ContentValues values = new ContentValues();

            values.put("gameName",game.getName());
            values.put("typeId",game.getTypeId());
            values.put("level",game.getRating());
            values.put("gamePicture",game.getImage());
            values.put("link", game.getLink());

            long result = db.insert("tb_Game",null,values);

            if (result == -1)
                return false;
            else
                return true;
        }

        return false;
    }

    public Boolean checkGame(String name){
        open();
        Cursor cursor = db.rawQuery("select * from tb_Game where gameName = ? " , new String[] {name});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    @SuppressLint("Range")
    public ArrayList<Game> getAllGame(){
        open();
        ArrayList<Game> gameList = new ArrayList<Game>();
        Cursor cursor = db.query("tb_Game", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Game game = new Game();
                game.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gameId"))));
                game.setName(cursor.getString(cursor.getColumnIndex("gameName")));
                game.setTypeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("typeId"))));
                game.setRating(Integer.parseInt(cursor.getString(cursor.getColumnIndex("level"))));
                game.setImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gamePicture"))));
                game.setLink(cursor.getString(cursor.getColumnIndex("link")));
                gameList.add(game);
            }
            return gameList;
        }
    }

    @SuppressLint("Range")
    public ArrayList<Game> getGameByTypeName(String typeName){
        open();
        ArrayList<Game> gameList = new ArrayList<Game>();
        Cursor cursor = db.rawQuery("select * from tb_Game where typeId in (select typeId from tb_CourseType where type = ? )", new String[]{typeName});

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                Game game = new Game();
                game.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gameId"))));
                game.setName(cursor.getString(cursor.getColumnIndex("gameName")));
                game.setTypeId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("typeId"))));
                game.setRating(Integer.parseInt(cursor.getString(cursor.getColumnIndex("level"))));
                game.setImage(Integer.parseInt(cursor.getString(cursor.getColumnIndex("gamePicture"))));
                game.setLink(cursor.getString(cursor.getColumnIndex("link")));
                gameList.add(game);
            }
            return gameList;
        }
    }

}
