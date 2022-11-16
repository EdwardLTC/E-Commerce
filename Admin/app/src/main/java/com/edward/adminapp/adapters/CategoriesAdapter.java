package com.edward.adminapp.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.adminapp.CategoriesManagementActivity;
import com.edward.adminapp.R;
import com.edward.adminapp.model.modelrespon.CategoryRes;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
    Context context;
    List<CategoryRes> ls;
    int[] backgrounds = {R.drawable.background_notify_color_blue,
            R.drawable.background_notify_color_green,
            R.drawable.background_notify_color_red,
            R.drawable.background_notify_color_yellow};


    public CategoriesAdapter(Context context, List<CategoryRes> ls) {
        this.context = context;
        this.ls = ls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_categories_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        CategoryRes categoryRes = ls.get(position);
        holder.tvNameCategoriesItem.setText(categoryRes.getName());

        if (position % 2 == 0)
            holder.ivCategoriesItem.setBackgroundResource(backgrounds[0]);
        else
            holder.ivCategoriesItem.setBackgroundResource(backgrounds[2]);

//        holder.ivCategoriesItem

        holder.ivDeleteCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setSelected(true);
                ((CategoriesManagementActivity)context).showDialogDeleteCategory(categoryRes);
            }
        });





    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        TextView tvNameCategoriesItem;
        ImageView ivDeleteCategories;
        ImageView ivCategoriesItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameCategoriesItem = itemView.findViewById(R.id.tvNameCategoriesItem);
            ivDeleteCategories = itemView.findViewById(R.id.ivDeleteCategoriesItem);
            ivCategoriesItem = itemView.findViewById(R.id.ivCategoriesItem);

        }
    }
}
