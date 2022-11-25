package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Paint;
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
import com.edward.myapplication.model.modelrespon.Respon;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClothesListOfCategoryActivity extends AppCompatActivity implements View.OnClickListener, OnItem{

    private int idCategory;
    private int idSeller = 7;

    private List<ClothesRes> ls;
    private ClothesAdapter clothesAdapter_1;
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


        ivBackToClothesActivityFromClothesOfCategory.setOnClickListener(this);
        tvRefreshClothesOfCategory.setOnClickListener(this);

        idCategory =  getIntent().getIntExtra("idCategory", -1);
    }

    private void initViews() {
        ivBackToClothesActivityFromClothesOfCategory = findViewById(R.id.ivBackToClothesActivityFromClothesOfCategory);
        tvRefreshClothesOfCategory = findViewById(R.id.tvRefreshClothesOfCategory);
        tvRefreshClothesOfCategory.setPaintFlags(tvRefreshClothesOfCategory.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
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
                            clothesAdapter_1 = new ClothesAdapter(ls, ClothesListOfCategoryActivity.this, ClothesListOfCategoryActivity.this);
                            rcvClothesOfCategory.setAdapter(clothesAdapter_1);

                            if (ls.size() == 0) {
                                tvCantFindClothesOfCategory.setVisibility(View.VISIBLE);
                                tvCantFindClothesOfCategory.setText("You have no products");
                                tvTryAgainClothesOfCategory.setVisibility(View.VISIBLE);
                                tvTryAgainClothesOfCategory.setText("Let's create a new once");

                            } else {
                                tvCantFindClothesOfCategory.setVisibility(View.INVISIBLE);
                                tvCantFindClothesOfCategory.setText("Can't not find any result");
                                tvTryAgainClothesOfCategory.setVisibility(View.INVISIBLE);
                                tvTryAgainClothesOfCategory.setText("Please try again");

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


    // ko xài
    @Override
    public void fillData(ImageView ivClothes, TextView tvNameClothes, TextView tvTypeClothes, TextView tvQuantity, ClothesRes clothesRes, int position) {

    }

    @Override
    public void showDialogDeleteClothes(ClothesRes clothesRes) {
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
                        deleteClothes(clothesRes);
                        super.onPositiveClicked(dialog);
                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                    }
                });
    }

    public void deleteClothes(ClothesRes clothesRes) {
        ServiceAPI.serviceApi.DeleteClothes(clothesRes.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(ClothesListOfCategoryActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " deleted")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadListClothesOfCategory();
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
}