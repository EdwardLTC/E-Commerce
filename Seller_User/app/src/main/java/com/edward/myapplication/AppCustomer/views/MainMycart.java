package com.edward.myapplication.AppCustomer.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;
import com.edward.myapplication.model.modelrespon.ClothesRes;

import java.util.ArrayList;

public class MainMycart extends AppCompatActivity {
    Button btnback, btncheckout;
    RecyclerView recyclerView;
    TextView tvprice;
    ArrayList<ClothesRes> list;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mycart);
        anhxa();

//        list.add(new ClothesRes(1, 2, 3, "AA", "ao", "https://bizweb.dktcdn.net/thumb/medium/100/287/440/products/ao-croptop-om-eo-nu-wash-croptop-local-brand-dep-7.jpg?v=1641292915563", 5));
    }

    private void anhxa() {
        btncheckout = findViewById(R.id.btncheckout);
        btnback = findViewById(R.id.btnback);     tvprice = findViewById(R.id.tvprice);
        recyclerView = findViewById(R.id.recyclerView);
    }

}