package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.model.CourseVideo;
import com.example.funnylearning.Database.CourseVideoDao;
import com.example.funnylearning.R;

import java.sql.Time;

public class DataCourseVideo {
    public void start(Context context){
        CourseVideoDao courseVideoDao = new CourseVideoDao(context);
        courseVideoDao.open();

        final int videoNumber = 9;

        CourseVideo[] courseVideo = new CourseVideo[videoNumber];

        int[] typeId = new int[videoNumber];
        int[] coursePicture = new int[videoNumber];
        int[] viewNumber = new int[videoNumber];
        int[] level = new int[videoNumber];
        String[] courseName = new String[videoNumber];
        String[] videoId = new String[videoNumber];
        Time[] duration = new Time[videoNumber];

        // video 1
        typeId[0] = 1;
        coursePicture[0] = R.drawable.math_addition_within_10;
        viewNumber[0] = 100;
        level[0] = 1;
        courseName[0] = "Learn Addition within 10";
        videoId[0] = "-ou9VvyJNOY";
        duration[0] = new Time(0,4,12);

        // video 2
        typeId[1] = 2;
        coursePicture[1] = R.drawable.math_addition_within_100;
        viewNumber[1] = 55;
        level[1] = 2;
        courseName[1] = "Learn Addition within 100";
        videoId[1] = "sLyx2iI_kgM";
        duration[1] = new Time(0,4,51);

        for(int i=0; i<videoNumber; i++){
            if(videoId[i]!=null){
                courseVideo[i].setTypeId(typeId[i]);
                courseVideo[i].setCoursePicture(coursePicture[i]);
                courseVideo[i].setViewNumber(viewNumber[i]);
                courseVideo[i].setLevel(level[i]);
                courseVideo[i].setCourseName(courseName[i]);
                courseVideo[i].setVideoId(videoId[i]);
                courseVideo[i].setDuration(duration[i]);
            }
        }

        for(int i=0; i<videoNumber; i++){
            if(courseVideo != null){
                courseVideoDao.insertCourseVideo(courseVideo[i]);
            }
        }
    }
}
