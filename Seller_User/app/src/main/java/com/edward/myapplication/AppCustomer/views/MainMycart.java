package com.edward.myapplication.AppCustomer.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrequest.BillDetailReq;
import com.edward.myapplication.model.modelrequest.BillReq;
import com.edward.myapplication.model.modelrespon.ClothesRes;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainMycart extends AppCompatActivity {
    Button btnback, btncheckout;
    RecyclerView recyclerView;
    TextView tvprice;
    ArrayList<ClothesRes> list;
    private List<BillDetailReq> listBillDetailReq;
    private int idUser = 21;
    private int idSeller = 20;
    private int idVoucher = 3;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_mycart);
        listBillDetailReq = new ArrayList<>();
        anhxa();

//        list.add(new ClothesRes(1, 2, 3, "AA", "ao", "https://bizweb.dktcdn.net/thumb/medium/100/287/440/products/ao-croptop-om-eo-nu-wash-croptop-local-brand-dep-7.jpg?v=1641292915563", 5));
        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                listBillDetailReq.add(new BillDetailReq(5, ""))
//                ServiceAPI.serviceApi.CreateBill(new BillReq(idUser, idSeller, idVoucher, "In processing"))
//                        .subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(new Observer<BillReq>() {
//                            @Override
//                            public void onSubscribe(Disposable d) {
//
//                            }
//
//                            @Override
//                            public void onNext(BillReq billReq) {
//
//                            }
//
//                            @Override
//                            public void onError(Throwable e) {
//
//                            }
//
//                            @Override
//                            public void onComplete() {
//
//                            }
//                        });
            }
        });
    }

    private void anhxa() {
        btncheckout = findViewById(R.id.btncheckout);
        btnback = findViewById(R.id.btnback);     tvprice = findViewById(R.id.tvprice);
        recyclerView = findViewById(R.id.recyclerView);
    }

}