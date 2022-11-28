package com.edward.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

public class BillDao {

    Context context;
    SQLiteDatabase sqLiteDatabase;
    Database database;

    public BillDao(Context context) {
        this.context = context;
        database = new Database(context);
        sqLiteDatabase = database.getReadableDatabase();
    }
//
//    public List<>

}
