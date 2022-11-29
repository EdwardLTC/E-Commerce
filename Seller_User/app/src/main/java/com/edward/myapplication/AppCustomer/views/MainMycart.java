package com.edward.myapplication.AppCustomer.views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppCustomer.adapters.MycartAdapter;
import com.edward.myapplication.AppSeller.views.VouchersManagementActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.dao.BillDao;
import com.edward.myapplication.model.BillDetail;
import com.edward.myapplication.model.NewBill;
import com.edward.myapplication.model.modelrequest.BillDetailReq;
import com.edward.myapplication.model.modelrequest.BillReq;
import com.edward.myapplication.model.modelrespon.ClothesRes;
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

public class MainMycart extends AppCompatActivity {
    Button btnback, btncheckout;
    RecyclerView rcvClothesInBill, rcvVouchersInBill ;
    TextView tvAllPriceClothes;
    ArrayList<ClothesRes> list;
    private List<BillDetailReq> listBillDetailReq;
    private List<NewBill> lsNewBill;
    private List<BillDetail> lsBillDetail;
    private List<VoucherRes> ls;

    private MycartAdapter mycartAdapter;
    private BillDao billDao;
    private int idUser = 21;
    private int idSeller = 20;
    private int idVoucher = 3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mycart);
        billDao = new BillDao(this);
        listBillDetailReq = new ArrayList<>();
        anhxa();
        initRecyclerView();

        lsBillDetail = billDao.getListBillDetail(idUser+"");
        mycartAdapter = new MycartAdapter(this, lsBillDetail);
        rcvClothesInBill.setAdapter(mycartAdapter);

        tvAllPriceClothes.setText("$" + getSumOfPriceClothes(lsBillDetail));

        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBillDetailReq = billDao.getListBillDetailReg(idUser+"");
                Toast.makeText(MainMycart.this, listBillDetailReq.size()+"", Toast.LENGTH_SHORT).show();
                ServiceAPI.serviceApi.CreateBill(new BillReq(idUser, idSeller, idVoucher, "In processing", listBillDetailReq))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<BillReq>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                ProgressDialogCustom.showProgressDialog(MainMycart.this, "Please wait");
                            }

                            @Override
                            public void onNext(BillReq billReq) {
                                PopupDialog.getInstance(MainMycart.this)
                                        .setStyle(Styles.SUCCESS)
                                        .setHeading("Well Done")
                                        .setHeading("You have successfully"+
                                                " payed")
                                        .setCancelable(false)
                                        .showDialog(new OnDialogButtonClickListener() {
                                            @Override
                                            public void onDismissClicked(Dialog dialog1) {
                                                super.onDismissClicked(dialog1);
                                            }
                                        });
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("Loi thanh toan:", "errr");
                                ProgressDialogCustom.dismissProgressDialog();
                            }

                            @Override
                            public void onComplete() {
                                ProgressDialogCustom.dismissProgressDialog();

                            }
                        });
            }
        });
    }

    private void anhxa() {
        btncheckout = findViewById(R.id.btncheckout);
        btnback = findViewById(R.id.btnback);
        tvAllPriceClothes = findViewById(R.id.tvAllPriceClothes);
        rcvClothesInBill = findViewById(R.id.rcvClothesInBill);
        rcvVouchersInBill = findViewById(R.id.rcvVoucherInBill);
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

}