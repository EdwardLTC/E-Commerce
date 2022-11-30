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

import com.edward.myapplication.AppSeller.adapters.ClothesImageAdapter;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.dao.BillDao;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.ClothesImage;
import com.edward.myapplication.model.NewBill;
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

public class ProductsDetailActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnbackproduct2, btnfavoriteproduct2, btncheckoutproduct2;
    HorizontalQuantitizer hqAddClothesCustomer2;
    Button btnSizeS2, btnSizeM2, btnSizeL2, btnSizeXL2;
    ViewPager2 viewPagerClothesImageCustomer2;
    TextView tvNameClotheInfoCustomer2, tvPriceClotheInfoCustomer2, tvDesClotheInfoCustomer2;
    CircleIndicator3 indicatorCustomer2;
    private ClothesRes clothesRes;
    private List<ClothesImage> ls;
    private ClothesImageAdapter clothesImageAdapter;
    private int quantity = 0;

    private int idUser = 21;

    private int checkFavorite = 0;

    private int sizeCheck = 0;

    private BillDao billDao;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        initViews();
        billDao = new BillDao(this);
        ls = new ArrayList<>();
        loadHorizontalQuantitizer();

        clothesRes = (ClothesRes) getIntent().getSerializableExtra("clothesRes");
        fillValue(clothesRes);

        btnbackproduct2.setOnClickListener(this);
        btnfavoriteproduct2.setOnClickListener(this);
        btncheckoutproduct2.setOnClickListener(this);
        btnSizeS2.setOnClickListener(this);
        btnSizeM2.setOnClickListener(this);
        btnSizeL2.setOnClickListener(this);
        btnSizeXL2.setOnClickListener(this);
    }

    private void initViews() {
        btnbackproduct2 = findViewById(R.id.btnbackproduct2);
        btnfavoriteproduct2 = findViewById(R.id.btnfavoriteproduct2);
        btncheckoutproduct2 = findViewById(R.id.btncheckoutproduct2);
        btnSizeS2 = findViewById(R.id.btnSizeSCustomer2);
        btnSizeM2 = findViewById(R.id.btnSizeMCustomer2);
        btnSizeL2 = findViewById(R.id.btnSizeLCustomer2);
        btnSizeXL2 = findViewById(R.id.btnSizeXLCustomer2);
        viewPagerClothesImageCustomer2 = findViewById(R.id.viewPagerClothesImageCustomer2);
        hqAddClothesCustomer2 = findViewById(R.id.hqAddClothesCustomer2);
        tvNameClotheInfoCustomer2 = findViewById(R.id.tvNameClotheInfoCustomer2);
        tvPriceClotheInfoCustomer2 = findViewById(R.id.tvPriceClotheInfoCustomer2);
        tvDesClotheInfoCustomer2 = findViewById(R.id.tvDesClotheInfoCustomer2);
        indicatorCustomer2 = findViewById(R.id.indicatorCustomer2);
    }

    private void fillValue(ClothesRes clothesRes) {
        tvDesClotheInfoCustomer2.setText(clothesRes.getDes());
        tvNameClotheInfoCustomer2.setText(clothesRes.getName());

        List<String> lsUrl = clothesRes.getImgsUrl();

        for (String link : lsUrl) {
            ls.add(new ClothesImage(link));
        }
        clothesImageAdapter = new ClothesImageAdapter(this, ls);
        viewPagerClothesImageCustomer2.setAdapter((clothesImageAdapter));
        indicatorCustomer2.setViewPager(viewPagerClothesImageCustomer2);

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
                            tvPriceClotheInfoCustomer2.setText(price);
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
        hqAddClothesCustomer2.setTextAnimationStyle(AnimationStyle.FALL_IN);
        hqAddClothesCustomer2.setQuantitizerListener(new QuantitizerListener() {
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
            case R.id.btnbackproduct2:
                startActivity(new Intent(this, CustomerAllClothesActivity.class));
                break;
            case R.id.btnSizeSCustomer2:
                sizeCheck = 1;
                MyHelper.checkButtonSize(btnSizeS2, btnSizeM2, btnSizeL2, btnSizeXL2);
                break;
            case R.id.btnSizeMCustomer2:
                MyHelper.checkButtonSize(btnSizeM2, btnSizeS2, btnSizeL2, btnSizeXL2);
                sizeCheck = 2;
                break;
            case R.id.btnSizeLCustomer2:
                MyHelper.checkButtonSize(btnSizeL2, btnSizeM2, btnSizeS2, btnSizeXL2);
                sizeCheck = 3;
                break;
            case R.id.btnSizeXLCustomer2:
                MyHelper.checkButtonSize(btnSizeXL2, btnSizeM2, btnSizeL2, btnSizeS2);
                sizeCheck = 4;
                break;
            case R.id.btnfavoriteproduct2:
                if (checkFavorite == 0) {
                    checkFavorite = 1;
                    addFavorite();
                } else {
                    checkFavorite = 0;
                    deleteFavorite();
                }
                break;
            case R.id.btncheckoutproduct2:
                addToCart();
                break;
        }
    }

    private void addToCart() {
        billDao.add(new NewBill(idUser, clothesRes.getId(),
                clothesRes.getName(), clothesRes.getImgsUrl().get(0),
                MyHelper.getSizeClothes(sizeCheck),
                quantity, clothesRes.getMaxPrice()));
        startActivity(new Intent(this, MainMycart.class));
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
                        btnfavoriteproduct2.setBackgroundResource(R.drawable.ic_favorite_selected);
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
                        btnfavoriteproduct2.setBackgroundResource(R.drawable.ic_favorite);
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