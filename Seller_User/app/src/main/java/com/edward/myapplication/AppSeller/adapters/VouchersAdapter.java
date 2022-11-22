package com.edward.myapplication.AppSeller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.R;
import com.edward.myapplication.AppSeller.views.VouchersManagementActivity;
import com.edward.myapplication.model.modelrespon.VoucherRes;

import java.util.List;

public class VouchersAdapter extends RecyclerView.Adapter<VouchersAdapter.ViewHolder> {
    Context context;
    List<VoucherRes> ls;
    int[] backgrounds = {R.drawable.background_notify_color_blue,
            R.drawable.background_notify_color_green,
            R.drawable.background_notify_color_red,
            R.drawable.background_notify_color_yellow};


    public VouchersAdapter(Context context, List<VoucherRes> ls) {
        this.context = context;
        this.ls = ls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_vouchers_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        VoucherRes voucherRes = ls.get(position);
        holder.tvNameVouchersItem.setText("Sale off " + voucherRes.getRatio() + " %");
        holder.clVouchersItem.setBackgroundResource(backgrounds[(int)(Math.random()*4)]);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((VouchersManagementActivity)context).showDialogUpdateVouchers(voucherRes);

            }
        });


        holder.ivDeleteNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((VouchersManagementActivity)context).showDialogDeleteVouchers(voucherRes);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ConstraintLayout clVouchersItem;
        TextView tvNameVouchersItem;
        ImageView ivDeleteNotification;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            clVouchersItem = itemView.findViewById(R.id.clVouchersItem);
            tvNameVouchersItem = itemView.findViewById(R.id.tvNameVouchersItem);
            ivDeleteNotification = itemView.findViewById(R.id.ivDeleteNotification);

        }
    }
}
