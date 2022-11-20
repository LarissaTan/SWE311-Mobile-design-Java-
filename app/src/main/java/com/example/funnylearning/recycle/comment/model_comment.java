package com.example.funnylearning.recycle.comment;

public class model_comment {

    int profile;
    String name = new String();
    String duration = new String();
    String comment = new String();

    public model_comment(int profileTmp, String nameTmp, String durationTmp, String commentTmp)
    {
        name = nameTmp;
        profile = profileTmp;
        duration = durationTmp;
        comment = commentTmp;
    }
}
