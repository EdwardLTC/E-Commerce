package com.edward.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;
import com.edward.myapplication.model.Categories;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {

    List<Categories> ls;
    Context c;

    public CategoriesAdapter(List<Categories> ls, Context c) {
        this.ls = ls;
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_categories_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Categories categories = ls.get(position);

        holder.tvNameCategoriesItem.setText(categories.getName());

        if (position % 2 == 0) {
            holder.ivCategoriesItem.setBackgroundResource(R.drawable.background_notify_color_blue);
        } else
            holder.ivCategoriesItem.setBackgroundResource(R.drawable.background_notify_color_green);

    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameCategoriesItem;
        ImageView ivCategoriesItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategoriesItem = itemView.findViewById(R.id.tvNameCategoriesItem);
            ivCategoriesItem = itemView.findViewById(R.id.ivCategoriesItem);
        }
    }
}
