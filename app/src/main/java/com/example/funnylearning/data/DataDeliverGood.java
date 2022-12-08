package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.DeliverGoodBean;
import com.example.funnylearning.Database.DeliverGoodDao;
import com.example.funnylearning.R;

public class DataDeliverGood {
    public static void start(Context context) {
        DeliverGoodDao goodsDao = new DeliverGoodDao(context);
        goodsDao.open();

        final int count = 1;

        int[] id = new int[count];
        String[] word = new String[count];
        int[] correctUrl = new int[count];
        int[] wrong1Url = new int[count];
        int[] wrong2Url = new int[count];
        int[] audio = new int[count];


        id[0] = 1;
        word[0] = "bee";
        correctUrl[0] = R.drawable.read_level_2;
        wrong1Url[0] = R.drawable.read_level_1;
        wrong2Url[0] = R.drawable.read_level_3;
        audio[0] = R.raw.bee;

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
