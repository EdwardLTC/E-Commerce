package com.edward.myapplication.AppCustomer.adapters;

import android.app.Activity;
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
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.Clothes;
import com.edward.myapplication.model.ClothesImage;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListProperties;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ClothesRes> list;

    public SearchAdapter(Context context, ArrayList<ClothesRes> list, ArrayList<ClothesImage> ListImg){
        this.context= context;
        this.list= list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.one_item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getImgsUrl().size() != 0 ){
            Glide.with(context).load( list.get(position).getImgsUrl().get(0)).into(holder.products);
        }
        list.get(position).getImgsUrl().get(0);


        holder.name.setText(list.get(position).getName());

        ServiceAPI.serviceApi.GetAllClothesProperties(list.get(position).id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListProperties>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResGetListProperties resGetProperties) {
                        holder.price.setText(resGetProperties.get_ClothesPropertiesRes().get(0).price);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView products, like;
        TextView name, price;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            products = itemView.findViewById(R.id.productsearch);
            like = itemView.findViewById(R.id.likesearch);
            name = itemView.findViewById(R.id.NameProductsearch);
            price = itemView.findViewById(R.id.PriceProductsearch);

        }
    }
}
