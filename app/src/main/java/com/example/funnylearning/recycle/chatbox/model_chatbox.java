package com.example.funnylearning.recycle.chatbox;

public class model_chatbox {
    String name = new String();
    String messageTop = new String();
    String time = new String();
    Integer alert_num = new Integer(0);

    public model_chatbox(String nameTmp, String messageTmp, String timeTmp, int numTmp) {
        name = nameTmp;
        messageTop = messageTmp;
        time = timeTmp;
        alert_num = numTmp;
    }
}

/*
* linear layout
* */