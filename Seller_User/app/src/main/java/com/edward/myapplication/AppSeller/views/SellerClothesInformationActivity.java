package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.edward.myapplication.AppSeller.adapters.ClothesPropertiesAdapter;
import com.edward.myapplication.R;
import com.edward.myapplication.AppSeller.adapters.ClothesImageAdapter;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.ClothesImage;
import com.edward.myapplication.model.modelrespon.ClothesPropertiesRes;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetCategory;
import com.edward.myapplication.model.modelrespon.ResGetListProperties;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import me.relex.circleindicator.CircleIndicator3;

public class SellerClothesInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBackToClothesActivity, ivMoreOptionsClothes;
    private TextView tvSellerNameClothesInfo, tvSellerPriceClothesInfo,
            tvSellerDescriptionClothesInfo;
    private ViewPager2 viewPagerClothesImage;
    private CircleIndicator3 indicator;
    private ClothesImageAdapter clothesImageAdapter;
    private List<ClothesImage> ls;
    private RecyclerView rcvSellerClothesPropertiesInfo;
    private List<ClothesPropertiesRes> lsClothesProperties;
    private ClothesPropertiesAdapter clothesPropertiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_clothes_information);
        initViews();

        ls = new ArrayList<>();
        lsClothesProperties = new ArrayList<>();
        initRecyclerViewClothesProperties();

        ClothesRes clothesRes = (ClothesRes) getIntent().getSerializableExtra("clothes");
        fillValue(clothesRes);
//        loadViewPagerClothesImage();

        ivMoreOptionsClothes.setOnClickListener(this);
        ivBackToClothesActivity.setOnClickListener(this);
    }

    private void initViews() {
        viewPagerClothesImage = findViewById(R.id.viewPagerClothesImage);
        indicator = findViewById(R.id.indicator);
        ivBackToClothesActivity = findViewById(R.id.ivBackToClothesActivity);
        ivMoreOptionsClothes = findViewById(R.id.ivMoreOptionsClothes);
        tvSellerNameClothesInfo = findViewById(R.id.tvSellerNameClothesInfo);
        tvSellerPriceClothesInfo = findViewById(R.id.tvSellerPriceClothesInfo);
        tvSellerDescriptionClothesInfo = findViewById(R.id.tvSellerDescriptionClothesInfo);
        rcvSellerClothesPropertiesInfo = findViewById(R.id.rcvSellerClothesPropertiesInfo);
    }

    private void initRecyclerViewClothesProperties() {
        rcvSellerClothesPropertiesInfo.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvSellerClothesPropertiesInfo.setLayoutManager(layoutManager);
    }


//    private void loadViewPagerClothesImage() {
//        ls.add(new ClothesImage("https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
//        ls.add(new ClothesImage("https://images.unsplash.com/photo-1618354691373-d851c5c3a990?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=415&q=80"));
//        ls.add(new ClothesImage("https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
//        clothesImageAdapter = new ClothesImageAdapter(this, ls);
//        viewPagerClothesImage.setAdapter(clothesImageAdapter);
//        indicator.setViewPager(viewPagerClothesImage);
//
//    }

    private void fillValue(ClothesRes clothesRes) {
        tvSellerNameClothesInfo.setText(clothesRes.getName());
        tvSellerDescriptionClothesInfo.setText(clothesRes.getDes());

        List<String> lsUrl = clothesRes.getImgsUrl();

        for (String link : lsUrl) {
            ls.add(new ClothesImage(link));
        }
        clothesImageAdapter = new ClothesImageAdapter(this, ls);
        viewPagerClothesImage.setAdapter((clothesImageAdapter));
        indicator.setViewPager(viewPagerClothesImage);

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
                            lsClothesProperties = resGetListProperties.get_ClothesPropertiesRes();
                            clothesPropertiesAdapter = new ClothesPropertiesAdapter(lsClothesProperties, SellerClothesInformationActivity.this);
                            rcvSellerClothesPropertiesInfo.setAdapter(clothesPropertiesAdapter);

                            String price = "";
                            if (ls.size() == 0) {
                                price = "0.0";
                            } else
                                price = "$" + resGetListProperties.get_ClothesPropertiesRes().get(0).getPrice();
                            tvSellerPriceClothesInfo.setText(price);
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

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBackToClothesActivity:
                startActivity(new Intent(this, ClothesManagementActivity.class));
                finish();
                break;
            case R.id.ivMoreOptionsClothes:
                showPopupDialog();
                break;
        }
    }

    private void showPopupDialog() {
        // Initializing the popup menu and giving the reference as current context
        PopupMenu popupMenu = new PopupMenu(this, ivMoreOptionsClothes);

        // Inflating popup menu from popup_menu.xml file
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_clothes_info, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuUpdateCloth:
                        updateCloth();
                        break;
                    case R.id.menuDeleteCloth:
                        deleteCloth();
                        break;
                }
                return true;
            }
        });
        // Showing the popup menu
        popupMenu.show();
    }

    private void updateCloth() {
    }

    private void deleteCloth() {
    }
}