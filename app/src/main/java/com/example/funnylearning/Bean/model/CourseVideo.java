package com.example.funnylearning.Bean.model;

import java.sql.Time;

public class CourseVideo {
    int courseId, typeId, coursePicture, viewNumber, level;
    String courseName, videoId;
    Time duration;

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCoursePicture() {
        return coursePicture;
    }

    public void setCoursePicture(int coursePicture) {
        this.coursePicture = coursePicture;
    }

    public Time getDuration() {
        return duration;
    }

    public void setDuration(Time duration) {
        this.duration = duration;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if(level>0 && level<4){
            this.level = level;
        }else {
            System.out.println("Level out of limit");
        }
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public int getViewNumber() {
        return viewNumber;
    }

    public void setViewNumber(int viewNumber) {
        this.viewNumber = viewNumber;
    }
}
