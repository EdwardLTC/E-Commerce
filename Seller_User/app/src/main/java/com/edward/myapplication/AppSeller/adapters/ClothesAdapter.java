package com.edward.myapplication.AppSeller.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
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

import com.bumptech.glide.Glide;
import com.edward.myapplication.AppSeller.fragments.ClothesFragment;
import com.edward.myapplication.AppSeller.views.ClothesManagementActivity;
import com.edward.myapplication.AppSeller.views.SellerClothesInformationActivity;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.interfaces.OnItem;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetCategory;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    String linkUrlTest = "https://images.unsplash.com/photo-1583743814966-8936f5b7be1a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=687&q=80";

    List<ClothesRes> ls;
    Context c;
    OnItem onItem;
    private String categoryName = "er";

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

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothesRes clothes = ls.get(position);
        Log.d(">>>>>>>>>>>>", clothes.getId()+"");
        holder.tvNameClothesItem.setText(clothes.getName());
        holder.tvQuantityClothesItem.setText("Quantity: " + clothes.getQuantily());
        holder.tvTypeClothesItem.setText("Type: " + clothes.getCategoryName());
        Glide.with(c).load(clothes.getImgsUrl().get(0)).into(holder.ivClothesItem);

        // set text for category name
//        ServiceAPI.serviceApi.GetCategoryWhere(clothes.getIdCategory())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResGetCategory>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResGetCategory resGetCategory) {
//
//                        if (resGetCategory.get_Respon().getRespone_code() == 200) {
//                            categoryName = resGetCategory.get_CategoryRes().getName();
//                            holder.tvTypeClothesItem.setText("Type: " + categoryName);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(">>>>>>>>>>>..", "errr");
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
        ServiceAPI.serviceApi.GetCategoryWhere(clothes.getIdCategory())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetCategory>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResGetCategory resGetCategory) {

                        if (resGetCategory.get_Respon().getRespone_code() == 200) {
                            categoryName = resGetCategory.get_CategoryRes().getName();
                            holder.tvTypeClothesItem.setText("Type: " + categoryName);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>>>>>..", "errr");
                    }

                    @Override
                    public void onComplete() {

                    }
                });

//        Glide.with(c).load(linkUrlTest).into(holder.ivClothesItem);
        holder.tVMoreDetailsClothesItem.setPaintFlags(holder.tVMoreDetailsClothesItem.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        holder.ivDeleteClothesItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItem.showDialogDeleteClothes(clothes);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, SellerClothesInformationActivity.class);
                intent.putExtra("clothes", clothes);
                ClothesManagementActivity.CLOTHES_RES = clothes;
                c.startActivity(intent);
            }
        });

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