package com.edward.myapplication.Customer.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.edward.myapplication.R;

public class MainCheckout extends AppCompatActivity {
    Button btnbackcheckout, btncheckout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_checkout);
        btnbackcheckout = findViewById(R.id.btnbackcheckout);
        btncheckout = findViewById(R.id.btncheckout);
    }
}