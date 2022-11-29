package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.AppSeller.adapters.ClothesAdapter;
import com.edward.myapplication.AppSeller.adapters.VouchersAdapter;
import com.edward.myapplication.AppSeller.fragments.ClothesFragment;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.PersonRes;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;
import com.edward.myapplication.model.modelrespon.ResGetListVoucher;
import com.edward.myapplication.model.modelrespon.ResGetPerson;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SellerDashboardActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvMoreDetailsClothes, tvMoreDetailsVouchers, tvMoreDetailsBills,
            tvMoreDetailsStatistics, tvMoreDetailsSupports,
            tvNameSellerDashboard, tvEmailSellerDashboard,
            tvQuantityClothes, tvQuantityVouchers;
    ImageButton ibLogoutSellerAccount;
    CircleImageView ivAvatarSeller;
    ConstraintLayout clClothes, clVouchers, clBills,
            clStatistics, clSupports;

    private int idSeller = LoginActivity.PERSONRES.getId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_dashboard);
        initViews();
        underLineText();
        fillValueSeller();
        handleListener();


        // đếm số lượng vouchers
        ServiceAPI.serviceApi.GetAllVoucherOf(idSeller)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListVoucher>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResGetListVoucher resGetListVoucher) {
                        if (resGetListVoucher.get_Respon().getRespone_code() == 200) {
                            String sizeVouchers = resGetListVoucher.get_VoucherRes().size() + "";
                            tvQuantityVouchers.setText(sizeVouchers);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>>>> ", "vouchers error");
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        // đếm số lượng sản phẩm
        ServiceAPI.serviceApi.getAllClothesFromSeller(idSeller)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListClothes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResGetListClothes resGetListClothes) {
                        if (resGetListClothes.get_Respon().getRespone_code() == 200) {
                            String sizeClothes = resGetListClothes.get_ClothesRes().size() + "";
                            tvQuantityClothes.setText(sizeClothes);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });
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
        tvQuantityClothes = findViewById(R.id.tvQuantityClothes);
        tvQuantityVouchers = findViewById(R.id.tvQuantityVouchers);
    }

    private void underLineText() {
        tvMoreDetailsClothes.setPaintFlags(tvMoreDetailsClothes.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsVouchers.setPaintFlags(tvMoreDetailsVouchers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsBills.setPaintFlags(tvMoreDetailsBills.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsStatistics.setPaintFlags(tvMoreDetailsStatistics.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        tvMoreDetailsSupports.setPaintFlags(tvMoreDetailsSupports.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void fillValueSeller() {
        tvNameSellerDashboard.setText(LoginActivity.PERSONRES.getName());
        tvEmailSellerDashboard.setText(LoginActivity.PERSONRES.getMail());
        Glide.with(SellerDashboardActivity.this).load(LoginActivity.PERSONRES.getImgUrl()).into(ivAvatarSeller);
//        ServiceAPI.serviceApi.GetPersonWhere(idSeller)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResGetPerson>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        ProgressDialogCustom.showProgressDialog(SellerDashboardActivity.this, "Please wait");
//                    }
//
//                    @Override
//                    public void onNext(ResGetPerson resGetPerson) {
//                        Log.d(">>>>>>>>>>",resGetPerson.get_Respon().respone_code+"" );
//                        if (resGetPerson.get_Respon().respone_code== 200) {
//                            tvNameSellerDashboard.setText(resGetPerson.get_PersonRes().getName());
//                            tvEmailSellerDashboard.setText(resGetPerson.get_PersonRes().getMail());
//                            Glide.with(SellerDashboardActivity.this).load(resGetPerson.get_PersonRes().getImgUrl()).into(ivAvatarSeller);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(">>>>>>>>>>","err" );
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        ProgressDialogCustom.dismissProgressDialog();
//
//                    }
//                });
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
        startActivity(new Intent(this, LoginActivity.class));
    }
}