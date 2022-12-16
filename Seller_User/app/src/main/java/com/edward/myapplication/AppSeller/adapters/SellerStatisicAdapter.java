package com.edward.myapplication.AppSeller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.R;
import com.edward.myapplication.model.modelrespon.ClothesStatisticalRes;

import java.util.List;

public class SellerStatisicAdapter extends RecyclerView.Adapter<SellerStatisicAdapter.ViewHolder> {
    Context c;
    List<ClothesStatisticalRes> ls;

    public SellerStatisicAdapter(Context c, List<ClothesStatisticalRes> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.layout_seller_statistic_item, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothesStatisticalRes clothesStatisticalRes = ls.get(position);
        Glide.with(c).load(clothesStatisticalRes.imgsUrl.get(0)).into(holder.ivSellerClothesStatistic);
        holder.tvSellerNameClothesStatistic.setText(clothesStatisticalRes.name);
        holder.tvSellerQuanityClothesStatistic.setText("Quantity: "+clothesStatisticalRes.buyTime);
        holder.tvSellerPriceClothesStatistic.setText("$" + clothesStatisticalRes.maxPrice);

        if (position == ls.size()-1) {
            holder.llLineStatistic.setBackgroundResource(R.color.white);
        }
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivSellerClothesStatistic;
        TextView tvSellerNameClothesStatistic,tvSellerQuanityClothesStatistic,
                tvSellerPriceClothesStatistic;
        LinearLayout llLineStatistic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivSellerClothesStatistic = itemView.findViewById(R.id.ivSellerClothesStatistic);
            tvSellerNameClothesStatistic = itemView.findViewById(R.id.tvSellerNameClothesStatistic);
            tvSellerQuanityClothesStatistic = itemView.findViewById(R.id.tvSellerQuanityClothesStatistic);
            tvSellerPriceClothesStatistic = itemView.findViewById(R.id.tvSellerPriceClothesStatistic);
            llLineStatistic = itemView.findViewById(R.id.llLineStatistic);
        }
    }
}
