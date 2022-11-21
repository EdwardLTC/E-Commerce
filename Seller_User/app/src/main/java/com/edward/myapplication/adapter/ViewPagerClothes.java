package com.edward.myapplication.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.edward.myapplication.fragment.CategoriesFragment;
import com.edward.myapplication.fragment.ClothesFragment;


public class ViewPagerClothes extends FragmentStateAdapter {
    public ViewPagerClothes(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ClothesFragment();
            case 1:
                return new CategoriesFragment();
            default:
                return new ClothesFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}