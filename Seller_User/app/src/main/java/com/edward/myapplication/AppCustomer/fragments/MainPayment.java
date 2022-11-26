package com.edward.myapplication.AppCustomer.fragments;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.edward.myapplication.R;

public class MainPayment extends AppCompatActivity {
    Button btnpayment, btnbacktohome;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_payment);
        btnpayment = findViewById(R.id.btnpayment);
        btnbacktohome = findViewById(R.id.btnbacktohome);
    }
}