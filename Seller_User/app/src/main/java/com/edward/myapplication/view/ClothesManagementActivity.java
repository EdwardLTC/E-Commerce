package com.edward.myapplication.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.edward.myapplication.R;
import com.edward.myapplication.adapter.ViewPagerClothes;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.UnsupportedEncodingException;

public class ClothesManagementActivity extends AppCompatActivity implements View.OnClickListener {
    TabLayout tabLayout;
    ViewPager2 viewPagerClothes;
    ViewPagerClothes viewPagerClothesAdapter;
    ImageView ivAddClothes;
    CardView cvBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_management);
        initViews();

        String hash1 = MD5("123");
        String hash2 = MD5("123");
        Log.d("hash1", hash1);
        Log.d("hash2", hash2);
        Log.d("", "===========================");

        String hash3 = MD5("123");
        String hash4 = MD5("abc");
        Log.d("hash3", hash3);
        Log.d("hash4", hash4);

        viewPagerClothesAdapter =new ViewPagerClothes(this);
        viewPagerClothes.setAdapter(viewPagerClothesAdapter);


        new TabLayoutMediator(tabLayout, viewPagerClothes, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Clothes");
                        break;
                    case 1:
                        tab.setText("Categories");
                        break;
                    default:
                        tab.setText("Clothes");
                        break;
                }
            }
        }).attach();

    }

    private void initViews() {
        tabLayout = findViewById(R.id.tabLayout);
        viewPagerClothes = findViewById(R.id.viewPagerClothes);
        cvBack = findViewById(R.id.cvBackToHomeFromClothes);
        ivAddClothes = findViewById(R.id.ivAddClothes);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvBackToHomeFromClothes:
                startActivity(new Intent(ClothesManagementActivity.this, SellerDashboardActivity.class));
                break;
            case R.id.ivAddClothes:
                break;
        }
    }

    public String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes("UTF-8"));
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        } catch(UnsupportedEncodingException ex){
        }
        return null;
    }
}