package com.edward.myapplication.Customer.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.edward.myapplication.R;

public class MainProductdetalls extends AppCompatActivity {
    Button btnbackproduct, btnfavoriteproduct, btncheckoutproduct;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_productdetalls);
        btnbackproduct = findViewById(R.id.btnbackproduct);
        btnfavoriteproduct = findViewById(R.id.btnfavoriteproduct);
        btncheckoutproduct = findViewById(R.id.btncheckoutproduct);
    }
}