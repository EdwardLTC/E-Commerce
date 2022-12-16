package com.edward.myapplication.AppSeller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.R;
import com.edward.myapplication.model.modelrespon.ClothesRes;

import java.util.List;

public class SellerAllClothesInBillAdapter extends RecyclerView.Adapter<SellerAllClothesInBillAdapter.ViewHolder> {

    List<ClothesRes> ls;
    Context c;

    public SellerAllClothesInBillAdapter(List<ClothesRes> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.layout_seller_all_clothes_in_bill, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothesRes clothesRes = ls.get(position);
        holder.tvPrice.setText("$"+clothesRes.getMaxPrice());
        holder.tvName.setText(clothesRes.getName());
        holder.tvQuantity.setText("Quantity: " + clothesRes.getQuantily());
        holder.tvSize.setText("Size: " + clothesRes.size);
        Glide.with(c).load(clothesRes.getImgsUrl().get(0)).into(holder.ivClothes);
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivClothes;
        TextView tvPrice, tvName, tvSize, tvQuantity;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tvPriceClothesInBill);
            ivClothes = itemView.findViewById(R.id.ivClothesInBill);
            tvName = itemView.findViewById(R.id.tvNameClothesInBill);
            tvSize = itemView.findViewById(R.id.tvSizeClothesInBill);
            tvQuantity = itemView.findViewById(R.id.tvQuantityClothesInBill);
        }
    }
}
