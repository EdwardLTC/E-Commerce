package com.edward.myapplication.AppCustomer.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.edward.myapplication.AppSeller.adapters.ClothesImageAdapter;
import com.edward.myapplication.AppSeller.adapters.ClothesPropertiesAdapter;
import com.edward.myapplication.AppSeller.views.SellerClothesInformationActivity;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.ClothesImage;
import com.edward.myapplication.model.modelrequest.FavoriteReq;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListProperties;
import com.edward.myapplication.model.modelrespon.Respon;
import com.mcdev.quantitizerlibrary.AnimationStyle;
import com.mcdev.quantitizerlibrary.HorizontalQuantitizer;
import com.mcdev.quantitizerlibrary.QuantitizerListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator3;

public class MainProductdetalls extends AppCompatActivity implements View.OnClickListener {
    Button btnbackproduct, btnfavoriteproduct, btncheckoutproduct;
    HorizontalQuantitizer hqAddClothesCustomer;
    Button btnSizeS, btnSizeM, btnSizeL, btnSizeXL;
    ViewPager2 viewPagerClothesImageCustomer;
    TextView tvNameClotheInfoCustomer, tvPriceClotheInfoCustomer, tvDesClotheInfoCustomer;
    CircleIndicator3 indicatorCustomer;
    private ClothesRes clothesRes;
    private List<ClothesImage> ls;
    private ClothesImageAdapter clothesImageAdapter;
    private int quantity = 0;

    private int idUser = 21;

    private int checkFavorite = 0;

    private int sizeCheck = 0;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_productdetalls);
        initViews();
        ls = new ArrayList<>();
        loadHorizontalQuantitizer();

        clothesRes = (ClothesRes) getIntent().getSerializableExtra("clothesRes");
        fillValue(clothesRes);

        btnbackproduct.setOnClickListener(this);
        btnfavoriteproduct.setOnClickListener(this);
        btncheckoutproduct .setOnClickListener(this);
        btnSizeS.setOnClickListener(this);
        btnSizeM.setOnClickListener(this);
        btnSizeL.setOnClickListener(this);
        btnSizeXL.setOnClickListener(this);
    }

    private void initViews() {
        btnbackproduct = findViewById(R.id.btnbackproduct);
        btnfavoriteproduct = findViewById(R.id.btnfavoriteproduct);
        btncheckoutproduct = findViewById(R.id.btncheckoutproduct);
        btnSizeS = findViewById(R.id.btnSizeSCustomer);
        btnSizeM = findViewById(R.id.btnSizeMCustomer);
        btnSizeL = findViewById(R.id.btnSizeLCustomer);
        btnSizeXL = findViewById(R.id.btnSizeXLCustomer);
        viewPagerClothesImageCustomer = findViewById(R.id.viewPagerClothesImageCustomer);
        hqAddClothesCustomer = findViewById(R.id.hqAddClothesCustomer);
        tvNameClotheInfoCustomer = findViewById(R.id.tvNameClotheInfoCustomer);
        tvPriceClotheInfoCustomer = findViewById(R.id.tvPriceClotheInfoCustomer);
        tvDesClotheInfoCustomer = findViewById(R.id.tvDesClotheInfoCustomer);
        indicatorCustomer = findViewById(R.id.indicatorCustomer);
    }

    private void fillValue(ClothesRes clothesRes) {
        tvDesClotheInfoCustomer.setText(clothesRes.getDes());
        tvNameClotheInfoCustomer.setText(clothesRes.getName());

        List<String> lsUrl = clothesRes.getImgsUrl();

        for (String link : lsUrl) {
            ls.add(new ClothesImage(link));
        }
        clothesImageAdapter = new ClothesImageAdapter(this, ls);
        viewPagerClothesImageCustomer.setAdapter((clothesImageAdapter));
        indicatorCustomer.setViewPager(viewPagerClothesImageCustomer);

        ServiceAPI.serviceApi.GetAllClothesProperties(clothesRes.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListProperties>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResGetListProperties resGetListProperties) {
                        Log.d(">>>>>>>>>>>.. ", resGetListProperties.get_Respon().getRespone_code()+"");
                        if (resGetListProperties.get_Respon().getRespone_code() == 200) {

                            String price = "";
                            if (ls.size() == 0) {
                                price = "0.0";
                            } else
                                price = "$" + resGetListProperties.get_ClothesPropertiesRes().get(0).getPrice();
                            tvPriceClotheInfoCustomer.setText(price);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>>>>>.. ", "errr");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void loadHorizontalQuantitizer() {
        hqAddClothesCustomer.setTextAnimationStyle(AnimationStyle.FALL_IN);
        hqAddClothesCustomer.setQuantitizerListener(new QuantitizerListener() {
            @Override
            public void onIncrease() {

            }

            @Override
            public void onDecrease() {
            }

            @Override
            public void onValueChanged(int i) {
                quantity = i;
            }
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnbackproduct:
                startActivity(new Intent(this, CustomerAllClothesActivity.class));
                break;
            case R.id.btnSizeSCustomer:
                sizeCheck = 1;
                MyHelper.checkButtonSize(btnSizeS, btnSizeM, btnSizeL, btnSizeXL);
                break;
            case R.id.btnSizeMCustomer:
                MyHelper.checkButtonSize(btnSizeM, btnSizeS, btnSizeL, btnSizeXL);
                sizeCheck = 2;
                break;
            case R.id.btnSizeLCustomer:
                MyHelper.checkButtonSize(btnSizeL, btnSizeM, btnSizeS, btnSizeXL);
                sizeCheck = 3;
                break;
            case R.id.btnSizeXLCustomer:
                MyHelper.checkButtonSize(btnSizeXL, btnSizeM, btnSizeL, btnSizeS);
                sizeCheck = 4;
                break;
            case R.id.btnfavoriteproduct:
                if (checkFavorite == 0) {
                    checkFavorite = 1;
                    addFavorite();
                } else {
                    checkFavorite = 0;
                    deleteFavorite();
                }
                break;
        }
    }

    private void addFavorite() {
        ServiceAPI.serviceApi.AddToFavorite(new FavoriteReq(clothesRes.getId(), idUser))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respon respon) {
                        btnfavoriteproduct.setBackgroundResource(R.drawable.ic_favorite_selected);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    private void deleteFavorite() {
        ServiceAPI.serviceApi.RemoveFromFavorite(new FavoriteReq(clothesRes.getId(), idUser))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respon respon) {
                        btnfavoriteproduct.setBackgroundResource(R.drawable.ic_favorite);
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