package com.edward.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edward.myapplication.adapter.VouchersAdapter;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.R;
import com.edward.myapplication.adapter.ProgressDialogCustom;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.modelrequest.VoucherReq;
import com.edward.myapplication.model.modelrespon.ResGetListVoucher;
import com.edward.myapplication.model.modelrespon.Respon;
import com.edward.myapplication.model.modelrespon.VoucherRes;
import com.edward.myapplication.view.SellerDashboardActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VouchersManagementActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rcvVouchersManagement;
    CardView cvBackToHomeFromVouchers;
    TextView tvRefreshVouchers, tvCantFindVouchers, tvTryAgainVouchers;
    EditText edtSearchVouchers;
    FloatingActionButton fabAddVouchers;
    Dialog dialog;
    VouchersAdapter vouchersAdapter;
    List<VoucherRes> ls;

    private int idSeller = 7;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vouchers_management);
        initViews();
        initRecyclerView();

        ls = new ArrayList<>();
        loadVouchersList();

        tvRefreshVouchers.setOnClickListener(this);
        cvBackToHomeFromVouchers.setOnClickListener(this);

        edtSearchVouchers.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String find = textView.getText().toString();
                    if (!find.isEmpty()) {
                        searchVouchers(find);
//                        searchSellers(find);
                    }
                    MyHelper.hideKeyboard(VouchersManagementActivity.this);
                    return true;
                }

                return false;
            }
        });

        fabAddVouchers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogCreateVouchers();
            }
        });
    }

    private void initViews() {
        rcvVouchersManagement = findViewById(R.id.rcvVouchersManagement);
        edtSearchVouchers = findViewById(R.id.edtSearchVouchers);
        cvBackToHomeFromVouchers = findViewById(R.id.cvBackToHomeFromVouchers);

        tvRefreshVouchers = findViewById(R.id.tvRefreshVouchers);
        tvRefreshVouchers.setPaintFlags(tvRefreshVouchers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvCantFindVouchers = findViewById(R.id.tvCantFindVouchers);
        tvTryAgainVouchers = findViewById(R.id.tvTryAgainVouchers);

        fabAddVouchers = findViewById(R.id.fabAddVouchers);
    }

    private void initRecyclerView() {
        rcvVouchersManagement.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvVouchersManagement.setLayoutManager(layoutManager);
    }

    private void loadVouchersList() {
        ServiceAPI.serviceApi.GetAllVoucherOf(idSeller)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListVoucher>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(VouchersManagementActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListVoucher resGetListVoucher) {
                        if (resGetListVoucher.get_Respon().getRespone_code() == 200) {
                            ls = resGetListVoucher.get_VoucherRes();
                            vouchersAdapter = new VouchersAdapter(VouchersManagementActivity.this, ls);
                            rcvVouchersManagement.setAdapter(vouchersAdapter);

                            if (ls.size() == 0) {
                                tvTryAgainVouchers.setVisibility(View.VISIBLE);
                                tvCantFindVouchers.setVisibility(View.VISIBLE);
                            } else {
                                tvTryAgainVouchers.setVisibility(View.INVISIBLE);
                                tvCantFindVouchers.setVisibility(View.INVISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>>>> ", "vouchers error");
                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvBackToHomeFromVouchers:
                startActivity(new Intent(VouchersManagementActivity.this, SellerDashboardActivity.class));
                break;
            case R.id.tvRefreshVouchers:
                loadVouchersList();
                edtSearchVouchers.setText("");
                break;
            case R.id.btCancelDialogAddVouchers:
            case R.id.btCancelDialogUpdateVouchers:
                dialog.dismiss();
                break;

        }
    }

//
    public void showDialogDeleteVouchers(VoucherRes voucherRes) {
        PopupDialog.getInstance(this)
                .setStyle(Styles.IOS)
                .setHeading("Delete")
                .setDescription("Are you sure you want to delete this Vouchers?"+
                        " You won't be able to see them again.")
                .setPositiveButtonText("Delete")
                .setPositiveButtonTextColor(R.color.red_blur)
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onPositiveClicked(Dialog dialog) {
                        deleteVouchers(voucherRes);
                        super.onPositiveClicked(dialog);
                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                    }
                });
    }


    public void deleteVouchers(VoucherRes voucherRes) {
        ServiceAPI.serviceApi.DeleteVoucher(voucherRes.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d(">>>>>> ", "subscribe");

                    }

                    @Override
                    public void onNext(Respon respon) {
                        Log.d(">>>>>> ", respon.getRespone_code()+"");
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(VouchersManagementActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " deleted")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadVouchersList();
                                        }
                                    });
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>> ", "error");

                    }

                    @Override
                    public void onComplete() {
                        Log.d(">>>>>> ", "complete");
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });

    }

    public void showDialogCreateVouchers() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_add_vouchers);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogFade2;

        Button btAddVouchers = dialog.findViewById(R.id.btAddVouchers);
        Button btCancelDialogAddVouchers = dialog.findViewById(R.id.btCancelDialogAddVouchers);
        EditText edtCreateVouchers = dialog.findViewById(R.id.edtCreateNameVouchers);
        EditText edtCreateDiscountVouchers = dialog.findViewById(R.id.edtCreateDiscountVouchers);

        btCancelDialogAddVouchers.setOnClickListener(this);
        btAddVouchers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtCreateVouchers.getText().toString();
                String discount = edtCreateDiscountVouchers.getText().toString();
                if (discount.length() != 0) {
                    addVouchers(discount);
                    dialog.dismiss();
                }
            }

        });

        Window window = dialog.getWindow();
        if (window == null)
            return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }


    private void addVouchers(String discount) {
        VoucherReq voucherReq = new VoucherReq(1,idSeller, Integer.parseInt(discount));
        ServiceAPI.serviceApi.CreateVoucher(voucherReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(VouchersManagementActivity.this, "Please wait");

                    }

                    @Override
                    public void onNext(Respon respon) {
                        Log.d(">>>>>>>>> ", respon.getRespone_code()+"");
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(VouchersManagementActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " added")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadVouchersList();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>>>> ", "add vouchers error");

                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();

                    }
                });
    }

    public void showDialogUpdateVouchers(VoucherRes voucherRes) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_update_vouchers);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogFade2;

        Button btUpdateVouchers = dialog.findViewById(R.id.btUpdateVouchers);
        Button btCancelDialogUpdateVouchers = dialog.findViewById(R.id.btCancelDialogUpdateVouchers);
        EditText edtUpdateVouchers = dialog.findViewById(R.id.edtUpdateNameVouchers);
        EditText edtUpdateDiscountVouchers = dialog.findViewById(R.id.edtUpdateDiscountVouchers);

        edtUpdateDiscountVouchers.setText(voucherRes.getRatio()+"");



        btCancelDialogUpdateVouchers.setOnClickListener(this);
        btUpdateVouchers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String discount = edtUpdateDiscountVouchers.getText().toString();
                if (discount.length() != 0) {
                    VoucherReq voucherReq = new VoucherReq(voucherRes.getId(), idSeller, Integer.parseInt(discount));
                    updateVouchers(voucherReq);
                    dialog.dismiss();
                }
            }

        });

        Window window = dialog.getWindow();
        if (window == null)
            return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    private void updateVouchers(VoucherReq voucherReq) {
        ServiceAPI.serviceApi.UpdateVoucher(voucherReq)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(VouchersManagementActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " updated")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadVouchersList();
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void searchVouchers(String find) {
//        List<VoucherRes> lsSearch = new ArrayList<>();
//        for (VoucherRes voucherRes : ls) {
//            if (vouchersRes.getName().toLowerCase().contains(find.toLowerCase())) {
//                lsSearch.add(voucherRes);
//            }
//        }
//        VouchersAdapter = new VouchersAdapter(this, lsSearch);
//        rcvVouchersManagement.setAdapter(VouchersAdapter);
//
//        if (lsSearch.size() == 0) {
//            tvTryAgainVouchers.setVisibility(View.VISIBLE);
//            tvCantFindVouchers.setVisibility(View.VISIBLE);
//        } else {
//            tvTryAgainVouchers.setVisibility(View.INVISIBLE);
//            tvCantFindVouchers.setVisibility(View.INVISIBLE);
//        }
    }

}