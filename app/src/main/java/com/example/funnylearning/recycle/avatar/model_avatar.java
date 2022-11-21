package com.example.funnylearning.recycle.avatar;

public class model_avatar {
    String name = new String();
    String messageTop = new String();
    String time = new String();
    String alert_num = new String();

    public model_avatar(String nameTmp, String messageTmp, String timeTmp, String num) {
        name = nameTmp;
        messageTop = messageTmp;
        time = timeTmp;
        alert_num = num;
    }
}
