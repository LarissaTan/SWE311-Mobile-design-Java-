package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.CourseVideoComment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CourseCommentDao {
    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public CourseCommentDao(Context context) {
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

    public Boolean insertComment(CourseVideoComment courseVideoComment){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ContentValues contentValues = new ContentValues();
        contentValues.put("userId", courseVideoComment.getUserId());
        contentValues.put("courseId", courseVideoComment.getCourseId());
        contentValues.put("comment", courseVideoComment.getComment());
        contentValues.put("time", dateFormat.format(courseVideoComment.getDate()));
        long result = db.insert("tb_CourseComment",null,contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    @SuppressLint("Range")
    public ArrayList<CourseVideoComment> getAllVideoComment(int courseId) {
        ArrayList<CourseVideoComment> comments = new ArrayList<CourseVideoComment>();
        Cursor cursor = db.rawQuery("select * from tb_CourseComment where courseId = " + courseId, new String[]{});

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                CourseVideoComment courseVideoComment = new CourseVideoComment();
                courseVideoComment.setUserId(cursor.getInt(cursor.getColumnIndex("userId")));
                courseVideoComment.setCourseId(cursor.getInt(cursor.getColumnIndex("courseId")));
                courseVideoComment.setComment(cursor.getString(cursor.getColumnIndex("comment")));
                Date date = new Date(cursor.getLong(cursor.getColumnIndex("time")));
                courseVideoComment.setDate(date);

                comments.add(courseVideoComment);
            }
            return comments;
        }
    }
}
