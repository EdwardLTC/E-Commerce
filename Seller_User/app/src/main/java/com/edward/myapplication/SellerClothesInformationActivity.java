package com.edward.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.edward.myapplication.adapter.ClothesImageAdapter;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.ClothesImage;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class SellerClothesInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBackToClothesActivity, ivMoreOptionsClothes;
    private TextView tvSellerNameClothesInfo, tvSellerPriceClothesInfo,
            tvSellerQuantityClothesInfo, tvSellerSizeClothesInfo,
            tvSellerDescriptionClothesInfo;
    private ViewPager2 viewPagerClothesImage;
    private CircleIndicator3 indicator;
    private ClothesImageAdapter clothesImageAdapter;
    private List<ClothesImage> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_clothes_information);
        ls = new ArrayList<>();
        initViews();
        loadViewPagerClothesImage();

        ivMoreOptionsClothes.setOnClickListener(this);
    }

    private void initViews() {
        viewPagerClothesImage = findViewById(R.id.viewPagerClothesImage);
        indicator = findViewById(R.id.indicator);
        ivBackToClothesActivity = findViewById(R.id.ivBackToClothesActivity);
        ivMoreOptionsClothes = findViewById(R.id.ivMoreOptionsClothes);
        tvSellerNameClothesInfo = findViewById(R.id.tvSellerNameClothesInfo);
        tvSellerPriceClothesInfo = findViewById(R.id.tvSellerPriceClothesInfo);
        tvSellerQuantityClothesInfo = findViewById(R.id.tvSellerQuantityClothesInfo);
        tvSellerSizeClothesInfo = findViewById(R.id.tvSellerSizeClothesInfo);
        tvSellerDescriptionClothesInfo = findViewById(R.id.tvSellerDescriptionClothesInfo);
    }

    private void loadViewPagerClothesImage() {
        ls.add(new ClothesImage("https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        ls.add(new ClothesImage("https://images.unsplash.com/photo-1618354691373-d851c5c3a990?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=415&q=80"));
        ls.add(new ClothesImage("https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80"));
        clothesImageAdapter = new ClothesImageAdapter(this, ls);
        viewPagerClothesImage.setAdapter(clothesImageAdapter);
        indicator.setViewPager(viewPagerClothesImage);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivBackToClothesActivity:
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