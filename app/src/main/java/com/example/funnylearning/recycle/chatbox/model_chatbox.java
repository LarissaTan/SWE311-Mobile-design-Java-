package com.example.funnylearning.recycle.chatbox;

public class model_chatbox {
    String name = new String();
    String messageTop = new String();
    String time = new String();
    String alert_num = new String();

    public model_chatbox(String nameTmp, String messageTmp, String timeTmp, String num) {
        name = nameTmp;
        messageTop = messageTmp;
        time = timeTmp;
        alert_num = num;
    }
}

/*
* linear layout
* */