package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.DeliverGoodBean;
import com.example.funnylearning.Database.DeliverGoodDao;
import com.example.funnylearning.R;

public class DataDeliverGood {
    public static void start(Context context) {
        DeliverGoodDao goodsDao = new DeliverGoodDao(context);
        goodsDao.open();

        final int count = 11;

        int[] id = new int[count];
        String[] word = new String[count];
        int[] correctUrl = new int[count];
        int[] wrong1Url = new int[count];
        int[] wrong2Url = new int[count];
        int[] audio = new int[count];

        id[0] = 1;
        word[0] = "bee";
        correctUrl[0] = R.drawable.read_level_bee;
        wrong1Url[0] = R.drawable.read_level_truck;
        wrong2Url[0] = R.drawable.read_level_cow;
        audio[0] = R.raw.bee;

        id[1] = 2;
        word[1] = "clouds";
        correctUrl[1] = R.drawable.read_level_clouds;
        wrong1Url[1] = R.drawable.read_level_monkey;
        wrong2Url[1] = R.drawable.read_level_sleep;
        audio[1] = R.raw.clouds;

        id[2] = 3;
        word[2] = "cow";
        correctUrl[2] = R.drawable.read_level_cow;
        wrong1Url[2] = R.drawable.read_level_jelly;
        wrong2Url[2] = R.drawable.read_level_owl;
        audio[2] = R.raw.cow;

        id[3] = 4;
        word[3] = "fox";
        correctUrl[3] = R.drawable.read_level_fox;
        wrong1Url[3] = R.drawable.read_level_yak;
        wrong2Url[3] = R.drawable.read_level_clouds;
        audio[3] = R.raw.fox;

        id[4] = 5;
        word[4] = "jelly";
        correctUrl[4] = R.drawable.read_level_jelly;
        wrong1Url[4] = R.drawable.read_level_pencil;
        wrong2Url[4] = R.drawable.read_level_bee;
        audio[4] = R.raw.jelly;

        id[5] = 6;
        word[5] = "monkey";
        correctUrl[5] = R.drawable.read_level_monkey;
        wrong1Url[5] = R.drawable.read_level_owl;
        wrong2Url[5] = R.drawable.read_level_jelly;
        audio[5] = R.raw.monkey;

        id[6] = 7;
        word[6] = "owl";
        correctUrl[6] = R.drawable.read_level_owl;
        wrong1Url[6] = R.drawable.read_level_fox;
        wrong2Url[6] = R.drawable.read_level_pencil;
        audio[6] = R.raw.owl;

        id[7] = 8;
        word[7] = "pencil";
        correctUrl[7] = R.drawable.read_level_pencil;
        wrong1Url[7] = R.drawable.read_level_sleep;
        wrong2Url[7] = R.drawable.read_level_monkey;
        audio[7] = R.raw.pencil;

        id[8] = 9;
        word[8] = "sleep";
        correctUrl[8] = R.drawable.read_level_sleep;
        wrong1Url[8] = R.drawable.read_level_cow;
        wrong2Url[8] = R.drawable.read_level_truck;
        audio[8] = R.raw.sleep;

        id[9] = 10;
        word[9] = "truck";
        correctUrl[9] = R.drawable.read_level_truck;
        wrong1Url[9] = R.drawable.read_level_clouds;
        wrong2Url[9] = R.drawable.read_level_yak;
        audio[9] = R.raw.truck;

        id[10] = 11;
        word[10] = "yak";
        correctUrl[10] = R.drawable.read_level_yak;
        wrong1Url[10] = R.drawable.read_level_bee;
        wrong2Url[10] = R.drawable.read_level_fox;
        audio[10] = R.raw.yak;

        for(int i = 0; i < count; i++)
        {
            DeliverGoodBean deliverGoodBean = new DeliverGoodBean();
            deliverGoodBean.id = id[i];
            deliverGoodBean.word = word[i];
            deliverGoodBean.correctUrl = correctUrl[i];
            deliverGoodBean.wrong1Url = wrong1Url[i];
            deliverGoodBean.wrong2Url = wrong2Url[i];
            deliverGoodBean.audio = audio[i];
            goodsDao.insertDeliverGood(deliverGoodBean);
        }

    }
}
