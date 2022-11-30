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

import com.edward.myapplication.R;
import com.edward.myapplication.model.modelrespon.CategoryRes;

import java.util.ArrayList;

public class CategoryInHomeAdapter extends RecyclerView.Adapter<CategoryInHomeAdapter.ViewHolder> {

    private Context context;
    private ArrayList<CategoryRes> list;

    public CategoryInHomeAdapter(Context context, ArrayList<CategoryRes> list){
        this.context= context;
        this.list= list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.one_item_categorys, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        holder.name.setText(list.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView category;
        TextView name;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            category = itemView.findViewById(R.id.categorys);
            name = itemView.findViewById(R.id.nameCategory);

        }
    }
}
