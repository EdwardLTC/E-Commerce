package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edward.myapplication.R;
import com.edward.myapplication.AppSeller.adapters.ClothesAdapter;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.interfaces.OnItem;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClothesListOfCategoryActivity extends AppCompatActivity implements View.OnClickListener, OnItem {

    private int idCategory;
    private int idSeller = 7;

    private List<ClothesRes> ls;
    private ClothesAdapter clothesAdapter;
    private ImageView ivBackToClothesActivityFromClothesOfCategory;
    private TextView tvRefreshClothesOfCategory, tvCantFindClothesOfCategory, tvTryAgainClothesOfCategory;
    private EditText edtSearchClothesOfCategory;
    private RecyclerView rcvClothesOfCategory;
    public OnItem onItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_list_of_category);
        ls = new ArrayList<>();
        initViews();
        initRecyclerView();
        loadListClothesOfCategory();


        idCategory =  getIntent().getIntExtra("idCategory", -1);
    }

    private void initViews() {
        ivBackToClothesActivityFromClothesOfCategory = findViewById(R.id.ivBackToClothesActivityFromClothesOfCategory);
        tvRefreshClothesOfCategory = findViewById(R.id.tvRefreshClothesOfCategory);
        tvCantFindClothesOfCategory = findViewById(R.id.tvCantFindClothesOfCategory);
        tvTryAgainClothesOfCategory = findViewById(R.id.tvTryAgainClothesOfCategory);
        edtSearchClothesOfCategory = findViewById(R.id.edtSearchClothesOfCategory);
        rcvClothesOfCategory = findViewById(R.id.rcvClothesOfCategory);
    }

    private void initRecyclerView() {
        rcvClothesOfCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvClothesOfCategory.setLayoutManager(layoutManager);
    }

    private void loadListClothesOfCategory() {
        ServiceAPI.serviceApi.getAllClothesFromSellerAndCategory(idSeller, idCategory)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListClothes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(ClothesListOfCategoryActivity.this, "Please wait");

                    }

                    @Override
                    public void onNext(ResGetListClothes resGetListClothes) {
                        if (resGetListClothes.get_Respon().getRespone_code() == 200) {
                            ls = resGetListClothes.get_ClothesRes();
                            clothesAdapter = new ClothesAdapter(ls, ClothesListOfCategoryActivity.this, ClothesListOfCategoryActivity.this);
                            rcvClothesOfCategory.setAdapter(clothesAdapter);

                            if (ls.size() == 0) {
                                tvCantFindClothesOfCategory.setVisibility(View.VISIBLE);
                                tvCantFindClothesOfCategory.setVisibility(View.VISIBLE);
                            } else {
                                tvCantFindClothesOfCategory.setVisibility(View.INVISIBLE);
                                tvCantFindClothesOfCategory.setVisibility(View.INVISIBLE);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBackToClothesActivityFromClothesOfCategory:
                startActivity(new Intent(this, ClothesManagementActivity.class));
                break;
            case R.id.tvRefreshClothesOfCategory:
                loadListClothesOfCategory();
                edtSearchClothesOfCategory.setText("");
                break;
        }

    }

    @Override
    public void fillData(ImageView ivClothes, TextView tvNameClothes, TextView tvTypeClothes, TextView tvQuantity, ClothesRes clothesRes, int position) {
        tvNameClothes.setText(clothesRes.getName());
    }
}