package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Bean.model.CourseVideo;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
        long result = db.insert("tb_Course",null,contentValues);
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

    @SuppressLint("Range")
    public ArrayList<CourseVideo> getAllCourseVideo() {
        ArrayList<CourseVideo> videoList = new ArrayList<CourseVideo>();
        Cursor cursor = db.query("tb_Course", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                CourseVideo courseVideo = new CourseVideo();
                courseVideo.setCourseId(cursor.getInt(cursor.getColumnIndex("courseId")));
                courseVideo.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                courseVideo.setTypeId(cursor.getInt(cursor.getColumnIndex("typeId")));
                courseVideo.setVideoId(cursor.getString(cursor.getColumnIndex("videoId")));
                Time time = new Time(cursor.getLong(cursor.getColumnIndex("duration")));
                courseVideo.setDuration(time);
                courseVideo.setCoursePicture(cursor.getInt(cursor.getColumnIndex("coursePicture")));
                courseVideo.setViewNumber(cursor.getInt(cursor.getColumnIndex("viewNumber")));
                courseVideo.setLevel(cursor.getInt(cursor.getColumnIndex("level")));
                videoList.add(courseVideo);
            }
            return videoList;
        }
    }

    @SuppressLint("Range")
    public ArrayList<CourseVideo> getAllCourseVideoByTypeName(String typeName) {
        ArrayList<CourseVideo> videoList = new ArrayList<CourseVideo>();
        Cursor cursor = db.rawQuery("select * from tb_Course where typeId in (select typeId from tb_CourseType where type = ? )", new String[]{typeName});

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                CourseVideo courseVideo = new CourseVideo();
                courseVideo.setCourseId(cursor.getInt(cursor.getColumnIndex("courseId")));
                courseVideo.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));
                courseVideo.setTypeId(cursor.getInt(cursor.getColumnIndex("typeId")));
                courseVideo.setVideoId(cursor.getString(cursor.getColumnIndex("videoId")));
                Time time = new Time(cursor.getLong(cursor.getColumnIndex("duration")));
                courseVideo.setDuration(time);
                courseVideo.setCoursePicture(cursor.getInt(cursor.getColumnIndex("coursePicture")));
                courseVideo.setViewNumber(cursor.getInt(cursor.getColumnIndex("viewNumber")));
                courseVideo.setLevel(cursor.getInt(cursor.getColumnIndex("level")));
                videoList.add(courseVideo);
            }
            return videoList;
        }
    }

    @SuppressLint("Range")
    public Boolean updateViewNumber(int courseId){
        open();
        Cursor cursor = db.rawQuery("select viewNumber from tb_Course where courseId = " + courseId, new String[]{});
        int viewNumber = cursor.getInt(cursor.getColumnIndex("viewNumber"));
        viewNumber++;

        ContentValues contentValues = new ContentValues();
        contentValues.put("viewNumber", viewNumber);
        long result = db.update("tb_Course", contentValues, "courseId =" + courseId, new String[]{});
        if (result == -1) return false;
        else
            return true;
    }
}
