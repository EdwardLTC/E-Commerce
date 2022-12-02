package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.edward.myapplication.AppSeller.adapters.SellerAllClothesInBillAdapter;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetBillPayment;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;
import com.edward.myapplication.model.modelrespon.ResGetPerson;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SellerBillDetailsActivity extends AppCompatActivity {

    ImageView ivBackFromSellerBillDetailToBillManagement;
    TextView tvNameSellerBill, tvAddressSellerBill, tvTotalSellerBill;
    RecyclerView rcvSellerAllClothesInBill;
    List<ClothesRes> ls;
    SellerAllClothesInBillAdapter sellerAllClothesInBillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_bill_details);
        initViews();
        initRecyclerView();
        loadListClothesInBill();
        fillValueCustomer();
        fillTotalPriceInClothes();

    }


    private void initViews() {
        ivBackFromSellerBillDetailToBillManagement = findViewById(R.id.ivBackFromSellerBillDetailToBillManagement);
        tvNameSellerBill = findViewById(R.id.tvNameSellerBill);
        tvAddressSellerBill = findViewById(R.id.tvAddressSellerBill);
        tvTotalSellerBill = findViewById(R.id.tvTotalSellerBill);
        rcvSellerAllClothesInBill = findViewById(R.id.rcvSellerAllClothesInBill);
    }

    private void initRecyclerView() {
        rcvSellerAllClothesInBill.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvSellerAllClothesInBill.setLayoutManager(layoutManager);
    }

    private void loadListClothesInBill() {
        ServiceAPI.serviceApi.GetClothesFromFromBill(BillsManagementActivity.BILL.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListClothes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(SellerBillDetailsActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListClothes resGetListClothes) {
                        if (resGetListClothes.get_Respon().getRespone_code() == 200) {
                            ls = resGetListClothes.get_ClothesRes();
                            sellerAllClothesInBillAdapter = new SellerAllClothesInBillAdapter(ls, SellerBillDetailsActivity.this);
                            rcvSellerAllClothesInBill.setAdapter(sellerAllClothesInBillAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("err: ", "err");
                        ProgressDialogCustom.dismissProgressDialog();

                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();

                    }
                });
    }

    private void fillValueCustomer() {
        ServiceAPI.serviceApi.GetPersonWhere(BillsManagementActivity.BILL.getIduser())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetPerson>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResGetPerson resGetPerson) {
                        if (resGetPerson.get_Respon().getRespone_code() == 200) {
                            tvAddressSellerBill.setText(resGetPerson.get_PersonRes().getAddress());
                            tvNameSellerBill.setText(resGetPerson.get_PersonRes().getName());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("err: ", "err");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void fillTotalPriceInClothes() {
        ServiceAPI.serviceApi.GetBillPayment(BillsManagementActivity.BILL.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetBillPayment>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResGetBillPayment resGetBillPayment) {
                        if (resGetBillPayment._Respon.getRespone_code() == 200) {
                            tvTotalSellerBill.setText(resGetBillPayment._Payment);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("err: ", "err");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}