package com.edward.myapplication.AppCustomer.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppSeller.views.VouchersManagementActivity;
import com.edward.myapplication.R;
import com.edward.myapplication.interfaces.ChooseVoucher;
import com.edward.myapplication.model.modelrespon.VoucherRes;

import java.util.List;

public class VouchersInMyCartAdapter extends RecyclerView.Adapter<VouchersInMyCartAdapter.ViewHolder> {
    Context context;
    List<VoucherRes> ls;
    int[] backgrounds = {R.drawable.background_notify_color_blue,
            R.drawable.background_notify_color_green,
            R.drawable.background_notify_color_red,
            R.drawable.background_notify_color_yellow};

    ChooseVoucher chooseVoucher;

    int checkPosition = -1;

    public VouchersInMyCartAdapter(Context context, List<VoucherRes> ls, ChooseVoucher chooseVoucher) {
        this.context = context;
        this.ls = ls;
        this.chooseVoucher = chooseVoucher;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_vouchers_in_mycart_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        VoucherRes voucherRes = ls.get(position);
        holder.tvNameVouchersItem.setText("Sale off " + voucherRes.getRatio() + "%");

        if (checkPosition == -1) {
            holder.ivCheck.setVisibility(View.INVISIBLE);
        } else {
            if (checkPosition == position)
                holder.ivCheck.setVisibility(View.VISIBLE);
            else
                holder.ivCheck.setVisibility(View.INVISIBLE);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                holder.ivCheck.setVisibility(View.VISIBLE);
                if (checkPosition != position) {
                    checkPosition = position;
                    chooseVoucher.chooseVoucher(voucherRes.getId(), voucherRes.getRatio());
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

        TextView tvNameVouchersItem;
        ImageView ivCheck;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNameVouchersItem = itemView.findViewById(R.id.tvNameVouchersItemInMyCart);
            ivCheck = itemView.findViewById(R.id.ivCheck);

        }
    }
}
