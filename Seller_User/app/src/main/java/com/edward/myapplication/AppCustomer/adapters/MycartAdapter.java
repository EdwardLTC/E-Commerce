package com.edward.myapplication.AppCustomer.adapters;

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
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListProperties;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MycartAdapter extends RecyclerView.Adapter<MycartAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<ClothesRes> list;

    public MycartAdapter(Context context, ArrayList<ClothesRes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(R.layout.one_item_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (list.get(position).getImgsUrl().size() != 0) {
            Glide.with(context).load(list.get(position).getImgsUrl().get(0)).into(holder.Img);
        }
        list.get(position).getImgsUrl().get(0);

        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getMaxPrice());
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
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView Img;
        TextView name, price, values;
        Button cong, tru;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            Img = itemView.findViewById(R.id.imgmycart);
            values = itemView.findViewById(R.id.tvvalue);
            name = itemView.findViewById(R.id.tvname);
            price = itemView.findViewById(R.id.tvprice);
            cong = itemView.findViewById(R.id.btncong);
            tru = itemView.findViewById(R.id.btntru);
        }
    }
}
