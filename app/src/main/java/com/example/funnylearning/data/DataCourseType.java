package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.model.CourseType;
import com.example.funnylearning.Database.CourseTypeDao;

import java.util.ArrayList;

public class DataCourseType {
    public static void start(Context context){
        CourseTypeDao courseTypeDao=new CourseTypeDao(context);
        courseTypeDao.open();

        int courseAmount = 11;

        String[] courseName = new String[courseAmount];
        String[] courseType = new String[courseAmount];

        // course 1
        courseName[0] = "Addition within Ten";
        courseType[0] = "Math";

        // course 2
        courseName[1] =  "Addition within One Hundred";
        courseType[1] = "Math";

        // course 3
        courseName[2] =  "Subtraction within Ten";
        courseType[2] = "Math";

        // course 4
        courseName[3] =  "Subtraction within One Hundred";
        courseType[3] = "Math";

        // course 5
        courseName[4] = "Multiplication within ten";
        courseType[4] = "Math";

        // course 6
        courseName[5] = "Division within ten";
        courseType[5] = "Math";

        // course 7
        courseName[6] = "Learn the Basic Words";
        courseType[6] = "Reading";

        // course 8
        courseName[7] = "Learn the Basic Sentences";
        courseType[7] = "Reading";

        // course 9
        courseName[8] = "Learn the Basic Grammar";
        courseType[8] = "Reading";

        // course 10
        courseName[9] = "Learn the Basic Math";
        courseType[9] = "Math";

        // course 11
        courseName[10] = "Addition within Twenty";
        courseType[10] = "Math";

        for(int i=0; i<courseAmount; i++){
            if(courseName[i]!=null) {
                CourseType course = new CourseType();
                course.setTypeName(courseName[i]);
                course.setType(courseType[i]);
                courseTypeDao.insertCourseType(course);
            }
        }
    }
}
