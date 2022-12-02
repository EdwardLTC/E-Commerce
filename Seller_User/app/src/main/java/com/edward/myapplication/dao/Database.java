package com.edward.myapplication.dao;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "DB_Bill", null, 6);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE BILL (" +
                "idCustomer integer, " +
                "nameClothes text, " +
                "price text," +
                "imgUrl text," +
                "idClothes integer," +
                "size text," +
                "quantity integer)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i != i1) {
            String sql = "DROP TABLE IF EXISTS BILL";
            sqLiteDatabase.execSQL(sql);
            onCreate(sqLiteDatabase);
        }
    }
}