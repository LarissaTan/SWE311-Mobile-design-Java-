package com.example.funnylearning.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class FindWordsDao {

    private Context context;
    private MyDatabase dbHelper;
    private SQLiteDatabase db;

    public FindWordsDao(Context context) {
        this.context = context;
    }
}
