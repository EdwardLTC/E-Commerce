package com.edward.myapplication.AppCustomer.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;
import com.edward.myapplication.interfaces.ChooseSize;
import com.edward.myapplication.interfaces.ChooseVoucher;
import com.edward.myapplication.model.modelrespon.VoucherRes;

import java.util.List;

public class ButtonSizeAdapter extends RecyclerView.Adapter<ButtonSizeAdapter.ViewHolder> {
    Context context;
    List<String> ls;

    ChooseSize chooseSize;

    int checkPosition = -1;

    public ButtonSizeAdapter(Context context, List<String> ls, ChooseSize chooseSize) {
        this.context = context;
        this.ls = ls;
        this.chooseSize = chooseSize;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_bt_size_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        String size = ls.get(position);
        holder.btSize.setText(size);

        if (checkPosition == -1) {
            holder.btSize.setBackgroundResource(R.drawable.background_size_clothes);
        } else {
            if (checkPosition == position)
                holder.btSize.setBackgroundResource(R.drawable.background_size_clothes_selected);
            else
                holder.btSize.setBackgroundResource(R.drawable.background_size_clothes);


        }
        holder.btSize.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                holder.btSize.setBackgroundResource(R.drawable.background_size_clothes_selected);
                if (checkPosition != position) {
                    checkPosition = position;
                    chooseSize.chooseSize(position);
                    notifyDataSetChanged();
                }
            }
        });

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        Button btSize;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            btSize = itemView.findViewById(R.id.btSizeItem);

        }
    }
}
