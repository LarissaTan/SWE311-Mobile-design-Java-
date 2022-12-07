package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.model.CourseVideo;
import com.example.funnylearning.Database.CourseVideoDao;
import com.example.funnylearning.R;

import java.sql.Time;

public class DataCourseVideo {
    public static void start(Context context){
        CourseVideoDao courseVideoDao = new CourseVideoDao(context);
        courseVideoDao.open();

        final int videoNumber = 10;

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

        // video 3
        typeId[2] = 3;
        coursePicture[2] = R.drawable.math_subtraction_within_10;
        viewNumber[2] = 90;
        level[2] = 1;
        courseName[2] = "Learn Subtraction within 10";
        videoId[2] = "rqiu_xcvSk4";
        duration[2] = new Time(0,4,2);

        // video 4
        typeId[3] = 4;
        coursePicture[3] = R.drawable.math_subtraction_within_100;
        viewNumber[3] = 40;
        level[3] = 2;
        courseName[3] = "Learn Subtraction within 100";
        videoId[3] = "cQFAZxarm3Y";
        duration[3] = new Time(0,8,36);

        // video 5
        typeId[4] = 5;
        coursePicture[4] = R.drawable.math_multiplication_within_10;
        viewNumber[4] = 60;
        level[4] = 2;
        courseName[4] = "Learn Multiplication within 100";
        videoId[4] = "eW2dRLyoyds";
        duration[4] = new Time(0,9,51);

        // video 6
        typeId[5] = 6;
        coursePicture[5] = R.drawable.math_division_within_10;
        viewNumber[5] = 40;
        level[5] = 2;
        courseName[5] = "Learn Division within 100";
        videoId[5] = "rGMecZ_aERo";
        duration[5] = new Time(0,8,24);

        // video 7
        typeId[6] = 7;
        coursePicture[6] = R.drawable.reading_learn_basic_word;
        viewNumber[6] = 100;
        level[6] = 1;
        courseName[6] = "Learn the basic word";
        videoId[6] = "U3CJv7mt-dw";
        duration[6] = new Time(0,11,24);

        // video 8
        typeId[7] = 7;
        coursePicture[7] = R.drawable.reading_learn_jobs_and_occupation;
        viewNumber[7] = 90;
        level[7] = 2;
        courseName[7] = "Learn the jobs and occupation";
        videoId[7] = "-jwzmuFDpkY";
        duration[7] = new Time(0,6,13);

        // video 9
        typeId[8] = 8;
        coursePicture[8] = R.drawable.reading_learn_basic_sentences;
        viewNumber[8] = 80;
        level[8] = 2;
        courseName[8] = "Learn the basic sentences";
        videoId[8] = "fE6Y7n-YUuU";
        duration[8] = new Time(0,10,42);

        // video 10
        typeId[9] = 9;
        coursePicture[9] = R.drawable.reading_learn_basic_grammar_1;
        viewNumber[9] = 105;
        level[9] = 1;
        courseName[9] = "Learn the basic grammar(1)";
        videoId[9] = "e7ODZR7e1W0";
        duration[9] = new Time(0,6,9);


        for(int i=0; i<videoNumber; i++){
            if(videoId[i]!= null){
                CourseVideo courseVideo = new CourseVideo();
                courseVideo.setTypeId(typeId[i]);
                courseVideo.setCoursePicture(coursePicture[i]);
                courseVideo.setViewNumber(viewNumber[i]);
                courseVideo.setLevel(level[i]);
                courseVideo.setCourseName(courseName[i]);
                courseVideo.setVideoId(videoId[i]);
                courseVideo.setDuration(duration[i]);
                courseVideoDao.insertCourseVideo(courseVideo);
            }
        }
    }
}
