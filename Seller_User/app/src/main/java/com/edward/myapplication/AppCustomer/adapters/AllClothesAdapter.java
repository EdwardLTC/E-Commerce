package com.edward.myapplication.AppCustomer.adapters;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ClothesRes clothesRes = ls.get(position);
//        getClothesPrice(clothesRes.getId());
        holder.tvNameAllClothesItem.setText(clothesRes.getName());
//        holder.tvPriceAllClothesItem.setText(price);

        Glide.with(c).load(clothesRes.getImgsUrl().get(0)).into(holder.ivAllClothesItem);

        ServiceAPI.serviceApi.GetAllClothesProperties(clothesRes.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListProperties>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResGetListProperties resGetListProperties) {
                        Log.d(">>>>>>>>>>>.. ", resGetListProperties.get_Respon().getRespone_code()+"");
                        if (resGetListProperties.get_Respon().getRespone_code() == 200) {
                            List<ClothesPropertiesRes> ls = resGetListProperties.get_ClothesPropertiesRes();

                            if (ls.size() == 0) {
                                price = "0.0";
                            } else
                                price = "$" + resGetListProperties.get_ClothesPropertiesRes().get(0).getPrice();
                            holder.tvPriceAllClothesItem.setText(price);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>>>>>.. ", "errr");

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(c, MainProductdetalls.class);
                intent.putExtra("clothesRes", clothesRes);
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

    public void getClothesPrice(int id) {

        ServiceAPI.serviceApi.GetAllClothesProperties(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListProperties>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResGetListProperties resGetListProperties) {
                        Log.d(">>>>>>>>>>>.. ", resGetListProperties.get_Respon().getRespone_code()+"");
                        if (resGetListProperties.get_Respon().getRespone_code() == 200) {
                            List<ClothesPropertiesRes> ls = new ArrayList<>();
                            ls = resGetListProperties.get_ClothesPropertiesRes();

                            if (ls.size() == 0) {
                                price = "0.0";
                            } else
                                price = "$" + resGetListProperties.get_ClothesPropertiesRes().get(0).getPrice();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>>>>>.. ", "errr");

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
