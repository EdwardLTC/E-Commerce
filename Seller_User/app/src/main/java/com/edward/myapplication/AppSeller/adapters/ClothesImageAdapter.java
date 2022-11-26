package com.edward.myapplication.AppSeller.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;
import com.edward.myapplication.helper.GlideApp;
import com.edward.myapplication.model.ClothesImage;

import java.util.List;

public class ClothesImageAdapter extends RecyclerView.Adapter<ClothesImageAdapter.ViewHolder> {
    Context c;
    List<ClothesImage> ls;
    String linkUrlTest = "https://images.unsplash.com/photo-1618354691373-d851c5c3a990?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=415&q=80";

    public ClothesImageAdapter(Context c, List<ClothesImage> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_clothes_image_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GlideApp.with(c).load(ls.get(position).getImage())
                .into(holder.ivClothesImageItem);
//        Glide.with(c).load(linkUrlTest).into(holder.ivClothesImageItem);
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivClothesImageItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivClothesImageItem = itemView.findViewById(R.id.ivClothesImageItem);
        }
    }
}
