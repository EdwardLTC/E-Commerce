package com.edward.myapplication.AppCustomer.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppCustomer.views.BillsManagementCustomerActivity;
import com.edward.myapplication.AppCustomer.views.CustomerBillDetailsActivity;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.BillRes;
import com.edward.myapplication.model.modelrespon.Respon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomerBillsAdapter extends RecyclerView.Adapter<CustomerBillsAdapter.ViewHolder> {

    Context c;
    List<BillRes> ls;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");;
    public CustomerBillsAdapter(Context c, List<BillRes> ls) {
        this.c = c;
        this.ls = ls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.layout_bill_customer_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BillRes billRes = ls.get(position);
        holder.tvBillNameCustomers.setText(billRes.getSellerName());
        holder.tvBillAddressCustomers.setText(billRes.getUserAddress());
        holder.tvBillStatusCustomers.setText("Status: " + billRes.getStatus());
        try {
            Date date = sdf.parse(billRes.getDateCreate());
            holder.tvBillDateCreatedCustomers.setText("Day of payment: " + sdf.format(date));

        } catch (ParseException e) {
            e.printStackTrace();
        }

//        Date date = new Date(billRes.getDateCreate().substring(0,9) )


        holder.clItemBillCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BillsManagementCustomerActivity.BILL = billRes;
                c.startActivity(new Intent(c, CustomerBillDetailsActivity.class));
            }
        });

        Log.d("Status: ", billRes.getStatus());
        if (billRes.getStatus().equals("Bill Completed")) {
            holder.btConfirmStatusBillCustomer.setVisibility(View.VISIBLE);
            holder.btConfirmStatusBillCustomer.setEnabled(true);
        }
        holder.btConfirmStatusBillCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ServiceAPI.serviceApi.MarkBillComplete(billRes.getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<Respon>() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onNext(Respon respon) {
                                if (respon.getRespone_code() == 200) {
                                    holder.tvBillStatusCustomers.setText("Status: Bill Completed");
                                    holder.btConfirmStatusBillCustomer.setVisibility(View.INVISIBLE);
                                    holder.btConfirmStatusBillCustomer.setEnabled(false);
                                    holder.ivCheckBillSuccessCustomer.setVisibility(View.VISIBLE);
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

    @Override
    public int getItemCount() {
        return ls.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout clItemBillCustomer;
        TextView tvBillNameCustomers,
                tvBillAddressCustomers,
                tvBillStatusCustomers,
                tvBillDateCreatedCustomers;
        Button btConfirmStatusBillCustomer;
        ImageView ivCheckBillSuccessCustomer;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clItemBillCustomer = itemView.findViewById(R.id.clItemBillCustomer);
            tvBillNameCustomers = itemView.findViewById(R.id.tvBillNameCustomers);
            tvBillAddressCustomers = itemView.findViewById(R.id.tvBillAddressCustomers);
            tvBillStatusCustomers = itemView.findViewById(R.id.tvBillStatusCustomers);
            tvBillDateCreatedCustomers = itemView.findViewById(R.id.tvBillDateCreatedCustomers);
            btConfirmStatusBillCustomer = itemView.findViewById(R.id.btConfirmStatusBillCustomer);
            ivCheckBillSuccessCustomer = itemView.findViewById(R.id.ivCheckBillSuccessCustomer);
        }
    }
}