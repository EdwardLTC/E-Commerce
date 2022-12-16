package com.edward.myapplication.AppSeller.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppSeller.views.BillsManagementActivity;
import com.edward.myapplication.AppSeller.views.SellerBillDetailsActivity;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.BillRes;
import com.edward.myapplication.model.modelrespon.ResGetListBill;
import com.edward.myapplication.model.modelrespon.ResGetPerson;
import com.edward.myapplication.model.modelrespon.Respon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SellerBillsAdapter extends RecyclerView.Adapter<SellerBillsAdapter.ViewHolder> {

    Context c;
    List<BillRes> ls;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");;
    public SellerBillsAdapter(Context c, List<BillRes> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_bill_seller_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillRes billRes = ls.get(position);
        holder.tvSellerBillNameCustomers.setText(billRes.getSellerName());
        holder.tvSellerBillAddressCustomers.setText(billRes.getUserAddress());
        holder.tvSellerBillStatusCustomers.setText("Status: " + billRes.getStatus());
        try {
            Date date = sdf.parse(billRes.getDateCreate());
            holder.tvSellerBillDateCreatedCustomers.setText("Day of payment: " + sdf.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }
//        Date date = new Date(billRes.getDateCreate().substring(0,9) )


        holder.clItemBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BillsManagementActivity.BILL = billRes;
                c.startActivity(new Intent(c, SellerBillDetailsActivity.class));
            }
        });

        Log.d("Status: ", billRes.getStatus());
        if (billRes.getStatus().equals("Bill Completed")) {
            holder.btConfirmStatusBill.setVisibility(View.INVISIBLE);
            holder.btConfirmStatusBill.setEnabled(false);
        } else {
            holder.btConfirmStatusBill.setVisibility(View.VISIBLE);
            holder.btConfirmStatusBill.setEnabled(true);
            holder.btConfirmStatusBill.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ServiceAPI.serviceApi.UpdateStatusBill("Bill Completed", billRes.getId())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<Respon>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(Respon respon) {
                                    if (respon.getRespone_code() == 200) {
                                        holder.tvSellerBillStatusCustomers.setText("Status:Bill Completed");
                                        holder.btConfirmStatusBill.setVisibility(View.INVISIBLE);
                                        holder.btConfirmStatusBill.setEnabled(false);
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clItemBill;
        TextView tvSellerBillNameCustomers,
                tvSellerBillAddressCustomers,
                tvSellerBillStatusCustomers,
                tvSellerBillDateCreatedCustomers;
        Button btConfirmStatusBill;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clItemBill = itemView.findViewById(R.id.clItemBill);
            tvSellerBillNameCustomers = itemView.findViewById(R.id.tvSellerBillNameCustomers);
            tvSellerBillAddressCustomers = itemView.findViewById(R.id.tvSellerBillAddressCustomers);
            tvSellerBillStatusCustomers = itemView.findViewById(R.id.tvSellerBillStatusCustomers);
            tvSellerBillDateCreatedCustomers = itemView.findViewById(R.id.tvSellerBillDateCreatedCustomers);
            btConfirmStatusBill = itemView.findViewById(R.id.btConfirmStatusBill);

        }
    }
}