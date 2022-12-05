package com.edward.myapplication.AppCustomer.views;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppCustomer.adapters.CustomerAllClothesInBillAdapter;
import com.edward.myapplication.AppSeller.adapters.SellerAllClothesInBillAdapter;
import com.edward.myapplication.AppSeller.views.BillsManagementActivity;
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

public class CustomerBillDetailsActivity extends AppCompatActivity {

    ImageView ivBackFromCustomerBillDetailToBillManagement;
    TextView tvNameCustomerBill, tvAddressCustomerBill, tvTotalCustomerBill;
    RecyclerView rcvCustomerAllClothesInBill;
    List<ClothesRes> ls;
    CustomerAllClothesInBillAdapter customerAllClothesInBillAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_bill_details);
        initViews();
        initRecyclerView();
        loadListClothesInBill();
        fillValueCustomer();
        fillTotalPriceInClothes();

        ivBackFromCustomerBillDetailToBillManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomerBillDetailsActivity.super.onBackPressed();
            }
        });

    }


    private void initViews() {
        ivBackFromCustomerBillDetailToBillManagement = findViewById(R.id.ivBackFromCustomerBillDetailToBillManagement);
        tvNameCustomerBill = findViewById(R.id.tvNameCustomerBill);
        tvAddressCustomerBill = findViewById(R.id.tvAddressCustomerBill);
        tvTotalCustomerBill = findViewById(R.id.tvTotalCustomerBill);
        rcvCustomerAllClothesInBill = findViewById(R.id.rcvCustomerAllClothesInBill);
    }

    private void initRecyclerView() {
        rcvCustomerAllClothesInBill.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvCustomerAllClothesInBill.setLayoutManager(layoutManager);
    }

    private void loadListClothesInBill() {
        ServiceAPI.serviceApi.GetClothesFromFromBill(BillsManagementActivity.BILL.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListClothes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(CustomerBillDetailsActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListClothes resGetListClothes) {
                        if (resGetListClothes.get_Respon().getRespone_code() == 200) {
                            ls = resGetListClothes.get_ClothesRes();
                            customerAllClothesInBillAdapter = new CustomerAllClothesInBillAdapter(ls, CustomerBillDetailsActivity.this);
                            rcvCustomerAllClothesInBill.setAdapter(customerAllClothesInBillAdapter);
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
                            tvAddressCustomerBill.setText(resGetPerson.get_PersonRes().getAddress());
                            tvNameCustomerBill.setText(resGetPerson.get_PersonRes().getName());
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

                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onNext(ResGetBillPayment resGetBillPayment) {
                        if (resGetBillPayment._Respon.getRespone_code() == 200) {
                            tvTotalCustomerBill.setText("$" + resGetBillPayment._Payment);
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