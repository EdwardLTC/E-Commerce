package com.edward.adminapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edward.adminapp.CategoriesManagementActivity;
import com.edward.adminapp.R;
import com.edward.adminapp.StatisticsActivity;
import com.google.android.material.bottomsheet.BottomSheetDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvMoreDetailsSellers, tvMoreDetailsCustomers, tvMoreDetailsCategories,
            tvMoreDetailsStatistics, tvMoreDetailsNotifications;

    private TextView tvQuantitySellers, tvQuantityCustomers, tvQuantityCategories;

    private ConstraintLayout clSellers, clCustomers, clCategories,
            clStatistics, clNotifications;

    private ImageButton ibLogout;
    private TextView tvAdminUsername;
    private ImageView ivAdminAvatar;

    private BottomSheetDialog bottomSheetDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        setUnderlineForText();

        clSellers.setOnClickListener(this);
        clCustomers.setOnClickListener(this);
        clCategories.setOnClickListener(this);
        clStatistics.setOnClickListener(this);
        clNotifications.setOnClickListener(this);
        ibLogout.setOnClickListener(this);
    }

    // ánh xạ view
    private void initViews() {
        tvMoreDetailsSellers = findViewById(R.id.tvMoreDetailsSellers);
        tvMoreDetailsCustomers = findViewById(R.id.tvMoreDetailsCustomers);
        tvMoreDetailsCategories = findViewById(R.id.tvMoreDetailsCategories);
        tvMoreDetailsStatistics = findViewById(R.id.tvMoreDetailsStatistics);
        tvMoreDetailsNotifications = findViewById(R.id.tvMoreDetailsNotifications);

        // số lượng quản lý
        tvQuantitySellers = findViewById(R.id.tvQuantitySellers);
        tvQuantityCustomers = findViewById(R.id.tvQuantityCustomers);
        tvQuantityCategories = findViewById(R.id.tvQuantityCategories);

        // layout chuyển qua activity khác
        clSellers = findViewById(R.id.clSellers);
        clCustomers = findViewById(R.id.clCustomers);
        clCategories = findViewById(R.id.clCategories);
        clStatistics = findViewById(R.id.clStatistics);
        clNotifications = findViewById(R.id.clNotifications);

        ibLogout = findViewById(R.id.ibLogout);
        tvAdminUsername = findViewById(R.id.tvAdminUsername);
        ivAdminAvatar = findViewById(R.id.ivAdminAvatar);
    }

    // chèn gạch chân cho chữ more details
    private void setUnderlineForText() {
        tvMoreDetailsSellers.setPaintFlags(tvMoreDetailsSellers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsCustomers.setPaintFlags(tvMoreDetailsCustomers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsCategories.setPaintFlags(tvMoreDetailsCategories.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsStatistics.setPaintFlags(tvMoreDetailsStatistics.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsNotifications.setPaintFlags(tvMoreDetailsNotifications.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        Intent intent = null;
        switch (id) {
            case R.id.clSellers:
                intent = new Intent(this, SellersManagementActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case R.id.clCustomers:
                intent = new Intent(this, CustomersManagementActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case R.id.clCategories:
                intent = new Intent(this, CategoriesManagementActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case R.id.clStatistics:
                intent = new Intent(this, StatisticsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case R.id.clNotifications:
                intent = new Intent(this, NotificationsManagementActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_enter_splash, R.anim.anim_exit_splash);
                break;
            case R.id.ibLogout:
                showBottomSheetDialog();
                break;
            case R.id.btCancel:
                bottomSheetDialog.hide();
                break;
            case R.id.btLogout:
                bottomSheetDialog.hide();
                logout();
                break;
        }
    }

    private void showBottomSheetDialog() {

        bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(R.layout.dialog_logout);

        Button btLogout = bottomSheetDialog.findViewById(R.id.btLogout);
        Button btCancel = bottomSheetDialog.findViewById(R.id.btCancel);

        btLogout.setOnClickListener(this);
        btCancel.setOnClickListener(this);

        bottomSheetDialog.show();
    }

    private void logout() {
    }
}