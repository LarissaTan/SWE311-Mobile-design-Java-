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

    //insert game data
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
            values.put("goal", game.getGoal());

            long result = db.insert("tb_Game",null,values);

            if (result == -1)
                return false;
            else
                return true;
        }

        return false;
    }

    //check duplicate game
    public Boolean checkGame(String name){
        open();
        Cursor cursor = db.rawQuery("select * from tb_Game where gameName = ? " , new String[] {name});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    //get all games
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
                game.setGoal(Integer.parseInt(cursor.getString(cursor.getColumnIndex("goal"))));
                gameList.add(game);
            }
            return gameList;
        }
    }

    //get game by type name
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
                game.setGoal(Integer.parseInt(cursor.getString(cursor.getColumnIndex("goal"))));
                gameList.add(game);
            }
            return gameList;
        }
    }

    //get type id
    @SuppressLint("Range")
    public int getTypeId (int gameId){
        open();
        Cursor cursor = db.rawQuery("select typeId from tb_Game where gameId = " + gameId, new String[] {});
        if(cursor.getCount() == 0)
            return -1;
        else {
            if(cursor.moveToNext()){
                return Integer.parseInt(cursor.getString(cursor.getColumnIndex("typeId")));
            }else {
                return -1;
            }
        }
    }

    //get goal
    @SuppressLint("Range")
    public int getGoal (int gameId){
        open();
        Cursor cursor = db.rawQuery("select goal from tb_Game where gameId = " + gameId, new String[] {});
        if(cursor.getCount() == 0)
            return -1;
        else {
            if(cursor.moveToNext()){
                return Integer.parseInt(cursor.getString(cursor.getColumnIndex("goal")));
            }else {
                return -1;
            }
        }
    }

}
