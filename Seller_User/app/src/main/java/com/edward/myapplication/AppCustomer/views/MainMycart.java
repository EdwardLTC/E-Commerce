package com.edward.myapplication.AppCustomer.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;

public class MainMycart extends AppCompatActivity {
    Button btncheckoutmycart, btnbackmycart;
    RecyclerView recyclerView;
    TextView tvpireprice2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mycart);
        btncheckoutmycart = findViewById(R.id.btncheckoutmycart);
        btnbackmycart = findViewById(R.id.btnbackmycart);
        tvpireprice2 = findViewById(R.id.tv_price2);
        recyclerView = findViewById(R.id.RecyclerViewMyCart);
    }
}