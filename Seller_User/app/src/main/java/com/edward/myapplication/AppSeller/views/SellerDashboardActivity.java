package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.edward.myapplication.R;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

public class SellerDashboardActivity extends AppCompatActivity implements View.OnClickListener {

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

        handleListener();
    }

    private void handleListener() {
        clClothes.setOnClickListener(this);
        clVouchers.setOnClickListener(this);
        ibLogoutSellerAccount.setOnClickListener(this);
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clClothes:
                startActivity(new Intent(this, ClothesManagementActivity.class));
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case R.id.clVouchers:
                startActivity(new Intent(this, VouchersManagementActivity.class));
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case R.id.ibLogoutSellerAccount:
                showDialogLout();
                break;
        }
    }

    private void showDialogLout() {
        PopupDialog.getInstance(this)
                .setStyle(Styles.IOS)
                .setHeading("Confirm logout")
                .setDescription("Are you sure you want to log out?")
                .setPositiveButtonText("OK")
                .setPositiveButtonTextColor(R.color.red_blur)
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onPositiveClicked(Dialog dialog) {
                        logout();
                        super.onPositiveClicked(dialog);
                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                    }
                });
    }

    private void logout() {
    }
}