package com.edward.myapplication.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.R;
import com.edward.myapplication.interfaces.OnItem;
import com.edward.myapplication.model.Clothes;
import com.edward.myapplication.model.modelrespon.ClothesRes;

import java.util.List;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    String linkUrlTest = "https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80";

    List<ClothesRes> ls;
    Context c;
    OnItem onItem;

    public ClothesAdapter(List<ClothesRes> ls, Context c, OnItem onItem) {
        this.ls = ls;
        this.c = c;
        this.onItem = onItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_clothes_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothesRes clothes = ls.get(position);

        holder.tvNameClothesItem.setText(clothes.getName());
//        holder.tvTypeClothesItem.setText(clothes.get);
//        holder.tvNameClothesItem.setText(clothes.getName());
//        holder.tvTypeClothesItem.setText(clothes.getCategory());
//        holder.tvQuantityClothesItem.setText(clothes.getQuantity()+"");
//        Glide.with(c).load(linkUrlTest).into(holder.ivClothesItem);
        holder.tVMoreDetailsClothesItem.setPaintFlags(holder.tVMoreDetailsClothesItem.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        onItem.fillData(holder.ivClothesItem,
                holder.tvNameClothesItem,
                holder.tvTypeClothesItem,
                holder.tvQuantityClothesItem,
                clothes,
                position);

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameClothesItem, tvTypeClothesItem,
                tvQuantityClothesItem, tVMoreDetailsClothesItem;
        ImageView ivClothesItem, ivDeleteClothesItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNameClothesItem = itemView.findViewById(R.id.tvNameClothesItem);
            tvTypeClothesItem = itemView.findViewById(R.id.tvTypeClothesItem);
            tvQuantityClothesItem = itemView.findViewById(R.id.tvQuantityClothesItem);
            tVMoreDetailsClothesItem = itemView.findViewById(R.id.tVMoreDetailsClothesItem);
            ivClothesItem = itemView.findViewById(R.id.ivClothesItem);
            ivDeleteClothesItem = itemView.findViewById(R.id.ivDeleteClothesItem);

        }
    }
}
