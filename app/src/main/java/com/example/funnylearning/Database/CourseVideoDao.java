package com.example.funnylearning.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.CourseVideo;

import java.text.SimpleDateFormat;

public class CourseVideoDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public CourseVideoDao(Context context) {
        this.context = context;
    }

    //打开数据库
    public void open() throws SQLiteException {
        dbHelper = new MyDatabase(context);
        //dbHelper.onCreate(db);
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

    public Boolean insertCourseVideo(CourseVideo courseVideo){
        open();
        ContentValues contentValues = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

        if(checkCourseVideoId(courseVideo.getVideoId())){
            return false;
        }
        contentValues.put("courseName", courseVideo.getCourseName());
        contentValues.put("typeId",courseVideo.getTypeId());
        contentValues.put("videoId",courseVideo.getVideoId());
        contentValues.put("duration",dateFormat.format(courseVideo.getDuration()));
        contentValues.put("coursePicture",courseVideo.getCoursePicture());
        contentValues.put("viewNumber",courseVideo.getViewNumber());
        contentValues.put("level",courseVideo.getLevel());
        long result = db.insert("tb_CourseType",null,contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkCourseVideoId(String videoId){
        open();
        Cursor cursor = db.rawQuery("select videoId from tb_Course where videoId = ?", new String[] {videoId});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
