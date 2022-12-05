package com.edward.myapplication.AppCustomer.adapters;

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

public class CustomerAllClothesInBillAdapter extends RecyclerView.Adapter<CustomerAllClothesInBillAdapter.ViewHolder> {

    List<ClothesRes> ls;
    Context c;

    public CustomerAllClothesInBillAdapter(List<ClothesRes> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(c).inflate(R.layout.layout_customer_all_clothes_in_bill, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothesRes clothesRes = ls.get(position);
        holder.tvPriceCustomer.setText("$"+clothesRes.getMaxPrice());
        holder.tvNameCustomer.setText(clothesRes.getName());
        holder.tvQuantityCustomer.setText("Quantity: " + clothesRes.getQuantily());
        Glide.with(c).load(clothesRes.getImgsUrl().get(0)).into(holder.ivClothesCustomer);
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivClothesCustomer;
        TextView tvPriceCustomer, tvNameCustomer, tvSizeCustomer, tvQuantityCustomer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPriceCustomer = itemView.findViewById(R.id.tvPriceClothesInBillCustomer);
            ivClothesCustomer = itemView.findViewById(R.id.ivClothesInBillCustomer);
            tvNameCustomer = itemView.findViewById(R.id.tvNameClothesInBillCustomer);
//            tvSizeCustomer = itemView.findViewById(R.id.tvSizeClothesInBillCustomer);
            tvQuantityCustomer = itemView.findViewById(R.id.tvQuantityClothesInBillCustomer);
        }
    }
}
