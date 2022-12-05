package com.example.funnylearning.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.funnylearning.Bean.model.Cartoons;
import com.example.funnylearning.Bean.model.CourseType;

import java.util.ArrayList;

public class CourseTypeDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    //构造函数
    public CourseTypeDao(Context context) {
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

    public Boolean insertCourseType(CourseType courseType){
        open();
        ContentValues contentValues = new ContentValues();

        if(checkCourseName(courseType.getCourseName())){
            return false;
        }
        contentValues.put("typeName", courseType.getCourseName());
        contentValues.put("type",courseType.getType());
        long result = db.insert("tb_CourseType",null,contentValues);
        if (result == -1) return false;
        else
            return true;
    }

    public Boolean checkCourseName(String typeName){
        open();
        Cursor cursor = db.rawQuery("select typeName from tb_CourseType where typeName = ?", new String[] {typeName});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    @SuppressLint("Range")
    public String getCourseName(int typeId){
        open();
        Cursor cursor = db.rawQuery("select typeName from tb_CourseType where typeId = " + typeId, new String[] {});
        if(cursor.getCount()>0)
            return null;
        else {
            return cursor.getString(cursor.getColumnIndex("typeName"));
        }
    }

    @SuppressLint("Range")
    public ArrayList<CourseType> getAllCourse() {
        ArrayList<CourseType> courseList = new ArrayList<CourseType>();
        Cursor cursor = db.query("tb_CourseType", null, null, null, null, null,null);

        int resultCounts = cursor.getCount();  //记录总数
        if (resultCounts == 0 ) {
            return null;
        } else {
            while (cursor.moveToNext()) {
                CourseType course = new CourseType();
                course.setCourseId(cursor.getInt(cursor.getColumnIndex("courseId")));
                course.setCourseName(cursor.getString(cursor.getColumnIndex("courseName")));

                courseList.add(course);
            }
            return courseList;
        }
    }
}
