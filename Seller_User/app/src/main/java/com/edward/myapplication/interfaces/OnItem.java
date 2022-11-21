package com.edward.myapplication.interfaces;

import android.widget.ImageView;
import android.widget.TextView;

import com.edward.myapplication.adapter.ClothesAdapter;
import com.edward.myapplication.model.modelrespon.ClothesRes;

public interface OnItem {

    public void fillData(ImageView ivClothes,
                         TextView tvNameClothes,
                         TextView tvTypeClothes,
                         TextView tvQuantity,
                         ClothesRes clothesRes, int position);
}
