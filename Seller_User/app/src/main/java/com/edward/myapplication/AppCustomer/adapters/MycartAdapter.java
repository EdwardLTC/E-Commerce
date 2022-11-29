package com.edward.myapplication.AppCustomer.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.BillDetail;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListProperties;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MycartAdapter extends RecyclerView.Adapter<MycartAdapter.MyViewHolder> {
    private Context context;
    private List<BillDetail> ls;

    public MycartAdapter(Context context, List<BillDetail> ls) {
        this.context = context;
        this.ls = ls;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.one_item_cart, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BillDetail billDetail = ls.get(position);
        holder.name.setText(billDetail.getNameClothes());
        holder.quantity.setText("Quantity: " + billDetail.getQuantity());
        holder.size.setText("Size: " + billDetail.getSize());
        holder.price.setText("$" + billDetail.getPrice());
        Glide.with(context).load(billDetail.getImgUrl()).into(holder.Img);


//        ServiceAPI.serviceApi.GetAllClothesProperties(list.get(position).id).subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResGetListProperties>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(ResGetListProperties resGetListProperties) {
//                        holder.price.setText(resGetListProperties.get_ClothesPropertiesRes().get(0).price);
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
//
//        holder.cong.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Img;
        TextView name, price, size, quantity;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Img = itemView.findViewById(R.id.imgmycart);
            name = itemView.findViewById(R.id.tvname);
            price = itemView.findViewById(R.id.tvprice);
            size = itemView.findViewById(R.id.tvsize);
            quantity = itemView.findViewById(R.id.tvQuantity);
        }
    }
}
