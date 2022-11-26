package com.edward.myapplication.Customer.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;
import com.edward.myapplication.model.Clothes;
import com.edward.myapplication.model.ClothesImage;

import java.util.ArrayList;

public class ProductsInHomeAdapter extends RecyclerView.Adapter<ProductsInHomeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Clothes> list;
    private ArrayList<ClothesImage> ListImg;

    public ProductsInHomeAdapter(Context context, ArrayList<Clothes> list, ArrayList<ClothesImage> ListImg){
        this.context= context;
        this.list= list;
        this.ListImg = ListImg;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.one_item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        ClothesImage clothesImage = ListImg.get(position);
//        if(clothesImage == null){
//            return;
//        }
//        holder.products.setImageResource(clothesImage.getImage());

        holder.name.setText(list.get(position).getName());
        holder.price.setText(list.get(position).getPrice());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView products;
        TextView name, price;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            products = itemView.findViewById(R.id.producthome);
            name = itemView.findViewById(R.id.nameProducthome);
            price = itemView.findViewById(R.id.PriceProducthome);

        }
    }
}
