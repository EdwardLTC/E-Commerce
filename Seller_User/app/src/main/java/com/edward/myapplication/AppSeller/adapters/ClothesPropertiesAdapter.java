package com.edward.myapplication.AppSeller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.interfaces.OnItem;
import com.edward.myapplication.model.modelrespon.ClothesPropertiesRes;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetCategory;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClothesPropertiesAdapter extends RecyclerView.Adapter<ClothesPropertiesAdapter.ViewHolder> {

    List<ClothesPropertiesRes> ls;
    Context c;

    public ClothesPropertiesAdapter(List<ClothesPropertiesRes> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_clothes_properties_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothesPropertiesRes clothesProperties = ls.get(position);
        holder.tvPriceSellersClothesInfoItem.setText("Price: " + clothesProperties.getPrice());
        holder.tvQuantitySellersClothesInfoItem.setText("Quantity: " + clothesProperties.getQuantily());
        holder.tvSizeSellersClothesInfoItem.setText("Size: " +clothesProperties.getSize().trim());
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvPriceSellersClothesInfoItem, tvSizeSellersClothesInfoItem,
                tvQuantitySellersClothesInfoItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPriceSellersClothesInfoItem = itemView.findViewById(R.id.tvPriceSellersClothesInfoItem);
            tvSizeSellersClothesInfoItem = itemView.findViewById(R.id.tvSizeSellersClothesInfoItem);
            tvQuantitySellersClothesInfoItem = itemView.findViewById(R.id.tvQuantitySellersClothesInfoItem);

        }
    }

}
