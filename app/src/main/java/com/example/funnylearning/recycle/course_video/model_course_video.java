package com.example.funnylearning.recycle.course_video;

public class model_course_video {
    int userId;
    int courseId;
    String vidId;
    int drawable;
    String title;
    int rating;
    int duration;

    public model_course_video(int userId, int courseId, String vidId, int drawable, String title, int rating, int duration) {
        this.userId = userId;
        this.courseId = courseId;
        this.vidId = vidId;
        this.drawable = drawable;
        this.title = title;
        this.rating = rating;
        this.duration = duration;
    }
}
