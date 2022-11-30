package com.edward.myapplication.AppCustomer.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppCustomer.adapters.MycartAdapter;
import com.edward.myapplication.AppCustomer.adapters.VouchersInMyCartAdapter;
import com.edward.myapplication.AppSeller.views.VouchersManagementActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.dao.BillDao;
import com.edward.myapplication.interfaces.ChooseVoucher;
import com.edward.myapplication.model.BillDetail;
import com.edward.myapplication.model.NewBill;
import com.edward.myapplication.model.modelrequest.BillDetailReq;
import com.edward.myapplication.model.modelrequest.BillReq;
import com.edward.myapplication.model.modelrespon.BillRes;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResBill;
import com.edward.myapplication.model.modelrespon.ResGetListVoucher;
import com.edward.myapplication.model.modelrespon.VoucherRes;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainMycart extends AppCompatActivity implements ChooseVoucher, View.OnClickListener {
    Button btnback, btncheckout;
    RecyclerView rcvClothesInBill, rcvVouchersInBill ;
    TextView tvAllPriceClothes;
    private CardView cvPayMomo;
    private LinearLayout llPayByDelivery;
    private RadioButton rdoIsMomoChecked, rdoIsDeliveryChecked;
    private ArrayList<ClothesRes> list;
    private List<BillDetailReq> listBillDetailReq;
    private List<NewBill> lsNewBill;
    private List<BillDetail> lsBillDetail;
    private List<VoucherRes> ls;

    private MycartAdapter mycartAdapter;
    private VouchersInMyCartAdapter vouchersInMyCartAdapter;
    private BillDao billDao;
    private int idUser = 21;
    private int idSeller = 20;
    private int idVoucher = -1;
    private int ratio = 0;
    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mycart);
        ls = new ArrayList<>();
        billDao = new BillDao(this);
        listBillDetailReq = new ArrayList<>();
        anhxa();
        initRecyclerView();


        loadListVouchers();

        llPayByDelivery.setOnClickListener(this);
        cvPayMomo.setOnClickListener(this);

        lsBillDetail = billDao.getListBillDetail(idUser+"");
        mycartAdapter = new MycartAdapter(this, lsBillDetail);
        rcvClothesInBill.setAdapter(mycartAdapter);

        tvAllPriceClothes.setText("$" + getSumOfPriceClothes(lsBillDetail));

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBillDetailReq = billDao.getListBillDetailReg(idUser+"");
                if (rdoIsDeliveryChecked.isChecked()) {
                    ServiceAPI.serviceApi.CreateBill(new BillReq(idUser, idSeller, idVoucher, "In processing", listBillDetailReq))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<ResBill>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(ResBill resBill) {
                                    Log.d(">>>>>>>: ", resBill.get_Respon().getRespone_code()+"");
                                    if (resBill.get_Respon().getRespone_code() == 200) {
                                        PopupDialog.getInstance(MainMycart.this)
                                                .setStyle(Styles.SUCCESS)
                                                .setHeading("Well Done")
                                                .setHeading("You have successfully"+ " payed")
                                                .setCancelable(false)
                                                .setDismissButtonText("Back to home")
                                                .showDialog(new OnDialogButtonClickListener() {
                                                    @Override
                                                    public void onDismissClicked(Dialog dialog1) {
                                                        super.onDismissClicked(dialog1);
                                                        startActivity(new Intent(MainMycart.this, MainActivity.class));

                                                        billDao.delete(idUser);

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
                } else if (rdoIsMomoChecked.isChecked()) {

                }

            }
        });
    }

    private void anhxa() {
        btncheckout = findViewById(R.id.btncheckout);
        btnback = findViewById(R.id.btnback);
        tvAllPriceClothes = findViewById(R.id.tvAllPriceClothes);
        rcvClothesInBill = findViewById(R.id.rcvClothesInBill);
        rcvVouchersInBill = findViewById(R.id.rcvVoucherInBill);
        llPayByDelivery = findViewById(R.id.llPayByDelivery);
        rdoIsMomoChecked = findViewById(R.id.rdoIsMomoChecked);
        cvPayMomo = findViewById(R.id.cvPayMomo);
        rdoIsDeliveryChecked = findViewById(R.id.rdoIsDeliveryChecked);
    }

    private void initRecyclerView() {
        rcvClothesInBill.setHasFixedSize(true);
        rcvClothesInBill.setLayoutManager(new LinearLayoutManager(this));

        rcvVouchersInBill.setHasFixedSize(true);
        rcvVouchersInBill.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
    }

    private String getSumOfPriceClothes(List<BillDetail> ls) {
        double sum = 0;
        for (BillDetail billDetail: ls) {
            sum += Double.parseDouble(billDetail.getPrice()) * billDetail.getQuantity();
        }
        return String.valueOf(sum);
    }

    private void loadListVouchers() {
        ServiceAPI.serviceApi.GetAllVoucherOf(idSeller)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListVoucher>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(MainMycart.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListVoucher resGetListVoucher) {
                        if (resGetListVoucher.get_Respon().getRespone_code() == 200) {
                            ls = resGetListVoucher.get_VoucherRes();
                            vouchersInMyCartAdapter = new VouchersInMyCartAdapter(MainMycart.this, ls, MainMycart.this);
                            rcvVouchersInBill.setAdapter(vouchersInMyCartAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ProgressDialogCustom.dismissProgressDialog();
                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void chooseVoucher(int idVoucherRes, int radioRes) {
        idVoucher = idVoucherRes;
        ratio = radioRes;
        double lastPrice = Double.parseDouble(getSumOfPriceClothes(lsBillDetail)) * (100-ratio) / 100;
        tvAllPriceClothes.setText("$" + String.valueOf(lastPrice));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvPayMomo:
                rdoIsMomoChecked.setChecked(true);
                rdoIsDeliveryChecked.setChecked(false);
                break;
            case R.id.llPayByDelivery:
                rdoIsMomoChecked.setChecked(false);
                rdoIsDeliveryChecked.setChecked(true);
                break;
        }
    }
}