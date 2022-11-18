package com.edward.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.edward.myapplication.adapter.ViewPagerClothes;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ClothesManagementActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager2 viewPagerClothes;
    ViewPagerClothes viewPagerClothesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_management);
        initViews();

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
    }
}