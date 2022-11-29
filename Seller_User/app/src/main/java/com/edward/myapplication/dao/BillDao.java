package com.edward.myapplication.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.edward.myapplication.model.BillDetail;
import com.edward.myapplication.model.NewBill;
import com.edward.myapplication.model.modelrequest.BillDetailReq;

import java.util.ArrayList;
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
    @SuppressLint("Range")
    public List<BillDetailReq> getListBillDetailReg(String ...args) {
        sqLiteDatabase = database.getReadableDatabase();
        List<BillDetailReq> ls = new ArrayList<>();
        String sql = "SELECT idclothes, size, quantity FROM BILL WHERE idCustomer = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    int idClothes = cursor.getInt(cursor.getColumnIndex("idClothes"));
                    String size = cursor.getString(cursor.getColumnIndex("size"));
                    int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                    ls.add(new BillDetailReq(idClothes, size, quantity));
                } while (cursor.moveToNext());
            }
        }
        return ls;
    }
    @SuppressLint("Range")
    public List<BillDetail> getListBillDetail(String ...args) {
        sqLiteDatabase = database.getReadableDatabase();
        List<BillDetail> ls = new ArrayList<>();
        String sql = "SELECT nameClothes, imgUrl, size, quantity, price FROM BILL WHERE idCustomer = ?";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, args);
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndex("nameClothes"));
                    String price = cursor.getString(cursor.getColumnIndex("price"));
                    String imgUrl = cursor.getString(cursor.getColumnIndex("imgUrl"));
                    String size = cursor.getString(cursor.getColumnIndex("size"));
                    int quantity = cursor.getInt(cursor.getColumnIndex("quantity"));
                    ls.add(new BillDetail(name, imgUrl, size, quantity, price));
                } while (cursor.moveToNext());
            }
        }
        return ls;
    }

    public void add(NewBill newBill) {
        sqLiteDatabase = database.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("idCustomer", newBill.getIdCustomer());
        values.put("nameClothes", newBill.getNameClothes());
        values.put("price", newBill.getPrice());
        values.put("imgUrl", newBill.getImgUrl());
        values.put("idClothes", newBill.getIdClothes());
        values.put("size", newBill.getSize());
        values.put("quantity", newBill.getQuantity());

        sqLiteDatabase.insert("BILL", null, values);
    }

//    public void delete()

}
