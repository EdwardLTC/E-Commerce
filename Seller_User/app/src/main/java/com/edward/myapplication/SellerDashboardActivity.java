package com.edward.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class SellerDashboardActivity extends AppCompatActivity {

    TextView tvMoreDetailsClothes, tvMoreDetailsVouchers, tvMoreDetailsBills,
            tvMoreDetailsStatistics, tvMoreDetailsSupports,
            tvNameSellerDashboard, tvEmailSellerDashboard;
    ImageButton ibLogoutSellerAccount;
    ImageView ivAvatarSeller;
    ConstraintLayout clClothes, clVouchers, clBills,
            clStatistics, clSupports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        initViews();
        underLineText();
    }

    private void initViews() {
        tvMoreDetailsClothes = findViewById(R.id.tvMoreDetailsClothes);
        tvMoreDetailsVouchers = findViewById(R.id.tvMoreDetailsVouchers);
        tvMoreDetailsBills = findViewById(R.id.tvMoreDetailsBills);
        tvMoreDetailsStatistics = findViewById(R.id.tvMoreDetailsStatistics);
        tvMoreDetailsSupports = findViewById(R.id.tvMoreDetailsSupports);
        tvNameSellerDashboard = findViewById(R.id.tvNameSellerDashboard);
        tvEmailSellerDashboard = findViewById(R.id.tvEmailSellerDashboard);
        ibLogoutSellerAccount = findViewById(R.id.ibLogoutSellerAccount);
        ivAvatarSeller = findViewById(R.id.ivAvatarSeller);
        clBills = findViewById(R.id.clBills);
        clClothes = findViewById(R.id.clClothes);
        clVouchers = findViewById(R.id.clVouchers);
        clStatistics = findViewById(R.id.clStatistics);
        clSupports = findViewById(R.id.clSupports);
    }

    private void underLineText() {
        tvMoreDetailsClothes.setPaintFlags(tvMoreDetailsClothes.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsVouchers.setPaintFlags(tvMoreDetailsVouchers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsBills.setPaintFlags(tvMoreDetailsBills.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsStatistics.setPaintFlags(tvMoreDetailsStatistics.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsSupports.setPaintFlags(tvMoreDetailsSupports.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }
}