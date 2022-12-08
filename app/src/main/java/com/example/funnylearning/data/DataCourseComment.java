package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.model.CourseVideoComment;
import com.example.funnylearning.Database.CourseCommentDao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataCourseComment {
    public static void start(Context context){
        CourseCommentDao courseCommentDao = new CourseCommentDao(context);
        courseCommentDao.open();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int commentAmount = 2;

        int[] userId = new int[commentAmount];
        int[] courseId = new int[commentAmount];
        String[] comment = new String[commentAmount];
        String[] date = new String[commentAmount];

        userId[0] = 1;
        courseId[0] = 1;
        comment[0] = "This video is easy for my child to learn. She understand a lot. And it motivates her to continue learning.";
        date[0] = "2022-12-7 12:00:00";

        userId[1] = 2;
        courseId[1] = 1;
        comment[1] = "My boy can fully follow up the video. He likes the voice of the lecturer. Hope more lectures can be added.";
        date[1] = "2022-12-7 15:00:00";

        for(int i=0; i<commentAmount; i++){
            if(comment[i]!=null){
                CourseVideoComment courseVideoComment = new CourseVideoComment();
                Date commentDate = null;
                try {
                    commentDate = dateFormat.parse(date[i]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                courseVideoComment.setUserId(userId[i]);
                courseVideoComment.setCourseId(courseId[i]);
                courseVideoComment.setComment(comment[i]);
                courseVideoComment.setDate(commentDate);

                courseCommentDao.insertComment(courseVideoComment);
            }
        }

    }
}
