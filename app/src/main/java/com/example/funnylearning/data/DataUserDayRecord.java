package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.DayRecordBean;
import com.example.funnylearning.Database.UserDayRecordDao;

public class DataUserDayRecord {
    public static void start(Context context){
        UserDayRecordDao recordDao = new UserDayRecordDao(context);
        recordDao.open();

        DayRecordBean recordBean = new DayRecordBean();
        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 20;
        recordBean.recordDate = "20-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 40;
        recordBean.recordDate = "21-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 30;
        recordBean.recordDate = "22-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 35;
        recordBean.recordDate = "23-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 25;
        recordBean.recordDate = "24-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 30;
        recordBean.recordDate = "25-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 40;
        recordBean.recordDate = "26-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 30;
        recordBean.recordDate = "27-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 45;
        recordBean.recordDate = "28-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 35;
        recordBean.recordDate = "29-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 40;
        recordBean.recordDate = "30-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 33;
        recordBean.recordDate = "31-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 37;
        recordBean.recordDate = "01-01-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 22;
        recordBean.recordDate = "02-01-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 47;
        recordBean.recordDate = "03-01-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 47;
        recordBean.recordDate = "04-01-2022";
        recordDao.insertDayRecord(recordBean);
    }
}
