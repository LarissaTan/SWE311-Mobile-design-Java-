package com.example.funnylearning.data;

import android.content.Context;

import com.example.funnylearning.Bean.FindWordsBean;
import com.example.funnylearning.Database.FindWordsDao;

public class DataFindWords {
    public static void start(Context context){
        /***
         * 插入words 数据
         */
        FindWordsDao wordsDao=new FindWordsDao(context);
        wordsDao.open();
        FindWordsBean f = new FindWordsBean();

        f.id = 1;
        f.correct = "Teacher";
        f.wrong1 = "Taecher";
        f.wrong2 = "Teacer";

        wordsDao.insertFindWords(f);

        f.id = 2;
        f.correct = "Correct";
        f.wrong1 = "Corect";
        f.wrong2 = "Corract";

        wordsDao.insertFindWords(f);

        f.id = 3;
        f.correct = "True";
        f.wrong1 = "Trua";
        f.wrong2 = "Tuer";

        wordsDao.insertFindWords(f);

        f.id = 4;
        f.correct = "Mobile";
        f.wrong1 = "Moblie";
        f.wrong2 = "Moilbe";

        wordsDao.insertFindWords(f);

        f.id = 5;
        f.correct = "Successful";
        f.wrong1 = "Sucessful";
        f.wrong2 = "Succesful";

        wordsDao.insertFindWords(f);

        f.id = 6;
        f.correct = "Minute";
        f.wrong1 = "Mintue";
        f.wrong2 = "Minue";

        wordsDao.insertFindWords(f);

        f.id = 7;
        f.correct = "Delicious";
        f.wrong1 = "Delcious";
        f.wrong2 = "Decious";

        wordsDao.insertFindWords(f);

        f.id = 8;
        f.correct = "Contribution";
        f.wrong1 = "Contribation";
        f.wrong2 = "Contrabution";

        wordsDao.insertFindWords(f);

        f.id = 9;
        f.correct = "Bulletproof";
        f.wrong1 = "Buletproof";
        f.wrong2 = "Bulletprof";

        wordsDao.insertFindWords(f);

        f.id = 10;
        f.correct = "Computer";
        f.wrong1 = "Komputer";
        f.wrong2 = "comprute";

        wordsDao.insertFindWords(f);
    }
}
