package com.edward.myapplication.AppCustomer.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.edward.myapplication.AppCustomer.adapters.AllClothesAdapter;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerAllClothesActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rcvAllClothes;
    AllClothesAdapter allClothesAdapter;
    List<ClothesRes> ls;

    ImageView ivBackToMainActivityFromAllClothes;
    EditText edtSearchAllClothes;
    TextView tvRefreshAllClothes, tvCantFindAllClothes, tvTryAgainAllClothes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_all_clothes);
        ls = new ArrayList<>();
        initViews();
        initRecyclerView();
        loadAllClothesList();

        ivBackToMainActivityFromAllClothes.setOnClickListener(this);
        tvRefreshAllClothes.setOnClickListener(this);
    }

    private void initViews() {
        rcvAllClothes = findViewById(R.id.rcvAllClothes);
        edtSearchAllClothes = findViewById(R.id.edtSearchAllClothes);
        ivBackToMainActivityFromAllClothes = findViewById(R.id.ivBackToMainActivityFromAllClothes);

        tvRefreshAllClothes = findViewById(R.id.tvRefreshAllClothes);
        tvRefreshAllClothes.setPaintFlags(tvRefreshAllClothes.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvCantFindAllClothes = findViewById(R.id.tvCantFindAllClothes);
        tvTryAgainAllClothes = findViewById(R.id.tvTryAgainAllClothes);

    }

    private void initRecyclerView() {
        rcvAllClothes.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        rcvAllClothes.setLayoutManager(layoutManager);
    }

    private void loadAllClothesList() {
        ServiceAPI.serviceApi.GetAllClothes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListClothes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(CustomerAllClothesActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListClothes resGetListClothes) {
                        if (resGetListClothes.get_Respon().getRespone_code() == 200) {
                            ls = resGetListClothes.get_ClothesRes();

                            allClothesAdapter = new AllClothesAdapter(ls, CustomerAllClothesActivity.this);
                            rcvAllClothes.setAdapter(allClothesAdapter);


                            if (ls.size() == 0) {
                                tvCantFindAllClothes.setVisibility(View.VISIBLE);
                                tvCantFindAllClothes.setVisibility(View.VISIBLE);
                            } else {
                                tvCantFindAllClothes.setVisibility(View.INVISIBLE);
                                tvCantFindAllClothes.setVisibility(View.INVISIBLE);
                            }
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBackToMainActivityFromAllClothes:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.tvRefreshAllClothes:
                break;
        }
    }
}