package com.edward.myapplication.AppCustomer.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.edward.myapplication.AppCustomer.views.CustomerAllClothesActivity;
import com.edward.myapplication.AppCustomer.views.MainProductdetalls;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.ClothesPropertiesRes;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListProperties;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AllClothesAdapter extends RecyclerView.Adapter<AllClothesAdapter.ViewHolder> {

    List<ClothesRes> ls;
    Context c;
    private String price = "err";

    public AllClothesAdapter(List<ClothesRes> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_all_clothes_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothesRes clothesRes = ls.get(position);
        holder.tvNameAllClothesItem.setText(clothesRes.getName());
        holder.tvPriceAllClothesItem.setText("$" + clothesRes.getMaxPrice());
        Glide.with(c).load(clothesRes.getImgsUrl().get(0)).into(holder.ivAllClothesItem);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, MainProductdetalls.class);
                intent.putExtra("clothesRes", clothesRes);
                CustomerAllClothesActivity.CLOTHEsRES = clothesRes;
                c.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameAllClothesItem, tvPriceAllClothesItem;
        ImageView ivAllClothesItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameAllClothesItem = itemView.findViewById(R.id.tvNameAllClothesItem);
            tvPriceAllClothesItem = itemView.findViewById(R.id.tvPriceAllClothesItem);
            ivAllClothesItem = itemView.findViewById(R.id.ivAllClothesItem);

        }
    }
}

