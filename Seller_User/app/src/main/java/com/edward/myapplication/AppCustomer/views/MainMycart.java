package com.edward.myapplication.AppCustomer.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;

public class MainMycart extends AppCompatActivity {
    Button btnback, btncheckout;
    RecyclerView recyclerView;
    TextView tvprice;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mycart);
        anhxa();
    }

    private void anhxa() {
        btncheckout = findViewById(R.id.btncheckout);
        btnback = findViewById(R.id.btnback);
        tvprice = findViewById(R.id.tvprice);
        recyclerView = findViewById(R.id.recyclerView);
    }

}