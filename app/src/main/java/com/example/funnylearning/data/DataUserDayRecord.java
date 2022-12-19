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
        recordBean.recordDate = "03-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 40;
        recordBean.recordDate = "04-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 30;
        recordBean.recordDate = "05-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 35;
        recordBean.recordDate = "06-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 25;
        recordBean.recordDate = "07-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 30;
        recordBean.recordDate = "08-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 40;
        recordBean.recordDate = "10-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 30;
        recordBean.recordDate = "11-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 45;
        recordBean.recordDate = "12-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 35;
        recordBean.recordDate = "13-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 40;
        recordBean.recordDate = "14-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 33;
        recordBean.recordDate = "15-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 37;
        recordBean.recordDate = "16-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 22;
        recordBean.recordDate = "17-12-2022";
        recordDao.insertDayRecord(recordBean);

        recordBean.weather = "rain";
        recordBean.mood = "happy";
        recordBean.activity = "Party";
        recordBean.userid = 1;
        recordBean.learningTime = 47;
        recordBean.recordDate = "18-12-2022";
        recordDao.insertDayRecord(recordBean);
    }
}
