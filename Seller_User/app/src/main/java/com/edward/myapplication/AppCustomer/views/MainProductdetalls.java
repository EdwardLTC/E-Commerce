package com.edward.myapplication.AppCustomer.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;

import com.edward.myapplication.R;
import com.mcdev.quantitizerlibrary.HorizontalQuantitizer;

public class MainProductdetalls extends AppCompatActivity {
    Button btnbackproduct, btnfavoriteproduct, btncheckoutproduct;
    HorizontalQuantitizer HqAddClothes;
    Button btnSizeS, btnSizeM, btnSizeL, btnSizeXL;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_productdetalls);
        btnbackproduct = findViewById(R.id.btnbackproduct);
        btnfavoriteproduct = findViewById(R.id.btnfavoriteproduct);
        btncheckoutproduct = findViewById(R.id.btncheckoutproduct);
        btnSizeS = findViewById(R.id.btnSizeS);
        btnSizeM = findViewById(R.id.btnSizeM);
        btnSizeL = findViewById(R.id.btnSizeL);
        btnSizeXL = findViewById(R.id.btnSizeXL);
    }
}