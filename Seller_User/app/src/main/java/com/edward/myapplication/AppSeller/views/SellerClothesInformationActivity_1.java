package com.edward.myapplication.AppSeller.views;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.edward.myapplication.AppSeller.adapters.ClothesImageAdapter;
import com.edward.myapplication.R;
import com.edward.myapplication.model.ClothesImage;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class SellerClothesInformationActivity_1 extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBackToClothesActivity_1, ivMoreOptionsClothes_1;
    private TextView tvSellerNameClothesInfo_1, tvSellerPriceClothesInfo_1,
            tvSellerQuantityClothesInfo_1, tvSellerSizeClothesInfo_1,
            tvSellerDescriptionClothesInfo_1;
    private ViewPager2 viewPagerClothesImage_1;
    private CircleIndicator3 indicator_1;
    private ClothesImageAdapter clothesImageAdapter;
    private List<ClothesImage> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_clothes_information);
        ls = new ArrayList<>();
        initViews();
        loadViewPagerClothesImage();

        ivMoreOptionsClothes_1.setOnClickListener(this);
    }

    private void initViews() {
        viewPagerClothesImage_1 = findViewById(R.id.viewPagerClothesImage_1);
        indicator_1 = findViewById(R.id.indicator_1);
        ivBackToClothesActivity_1 = findViewById(R.id.ivBackToClothesActivity_1);
        ivMoreOptionsClothes_1 = findViewById(R.id.ivMoreOptionsClothes_1);
        tvSellerNameClothesInfo_1 = findViewById(R.id.tvSellerNameClothesInfo_1);
        tvSellerPriceClothesInfo_1 = findViewById(R.id.tvSellerPriceClothesInfo_1);
        tvSellerQuantityClothesInfo_1 = findViewById(R.id.tvSellerQuantityClothesInfo_1);
        tvSellerSizeClothesInfo_1 = findViewById(R.id.tvSellerSizeClothesInfo_1);
        tvSellerDescriptionClothesInfo_1 = findViewById(R.id.tvSellerDescriptionClothesInfo_1);
    }

    private void loadViewPagerClothesImage() {
        ls.add(new ClothesImage("https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        ls.add(new ClothesImage("https://images.unsplash.com/photo-1618354691373-d851c5c3a990?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=415&q=80"));
        ls.add(new ClothesImage("https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        clothesImageAdapter = new ClothesImageAdapter(this, ls);
        viewPagerClothesImage_1.setAdapter(clothesImageAdapter);
        indicator_1.setViewPager(viewPagerClothesImage_1);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBackToClothesActivity_1:
                break;
            case R.id.ivMoreOptionsClothes_1:
                showPopupDialog();
                break;
        }
    }

    private void showPopupDialog() {
        // Initializing the popup menu and giving the reference as current context
        PopupMenu popupMenu = new PopupMenu(this, ivMoreOptionsClothes_1);

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