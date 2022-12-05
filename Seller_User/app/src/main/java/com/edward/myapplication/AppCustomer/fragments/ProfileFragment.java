package com.edward.myapplication.AppCustomer.fragments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.AppCustomer.views.BillsManagementCustomerActivity;
import com.edward.myapplication.AppCustomer.views.MainMycart;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.R;
import com.edward.myapplication.dao.BillDao;
import com.edward.myapplication.model.BillDetail;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    CircleImageView iv_profile;
    ImageView ic_more,ic_bag,ic_ticket,ic_star;
    TextView txtProfileName, tvNameCustomer, tvMailCustomer, tvAddressCustomer, tvPhoneCustomer, tvUpdateProfile
            ,txtProfileEmail,tvCountClothesInBill;

    BillDao billDao;
    List<BillDetail> lsBillDetail;
    LinearLayout llMoveToMyCart, llProgressOrder;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        billDao = new BillDao(requireActivity());
        initViews(view);
        fillValue();

        tvUpdateProfile.setOnClickListener(this);
        llMoveToMyCart.setOnClickListener(this);
        llProgressOrder.setOnClickListener(this);
    }

    private void initViews(View view) {
        iv_profile = view.findViewById(R.id.iv_profile);
        txtProfileName = view.findViewById(R.id.txtProfileName);
        txtProfileEmail = view.findViewById(R.id.txtProfileEmail);
        tvNameCustomer = view.findViewById(R.id.tvNameCustomer);
        tvMailCustomer = view.findViewById(R.id.tvMailCustomer);
        tvAddressCustomer = view.findViewById(R.id.tvAddressCustomer);
        tvPhoneCustomer = view.findViewById(R.id.tvPhoneCustomer);
        tvUpdateProfile = view.findViewById(R.id.tvUpdateProfile);
        llMoveToMyCart = view.findViewById(R.id.llMoveToMyCart);
        tvCountClothesInBill = view.findViewById(R.id.tvCountClothesInBill);
        llProgressOrder = view.findViewById(R.id.llProgressOrder);

    }

    @SuppressLint("SetTextI18n")
    private void fillValue() {
        Glide.with(requireActivity()).load(LoginActivity.PERSONRES.getImgUrl()).into(iv_profile);
        txtProfileName.setText(LoginActivity.PERSONRES.getName());
        txtProfileEmail.setText(LoginActivity.PERSONRES.getMail());
        tvNameCustomer.setText(LoginActivity.PERSONRES.getName());
        tvMailCustomer.setText(LoginActivity.PERSONRES.getMail());
        tvPhoneCustomer.setText(LoginActivity.PERSONRES.getPhoneNum());
        tvAddressCustomer.setText(LoginActivity.PERSONRES.getAddress());
        tvUpdateProfile.setPaintFlags(tvUpdateProfile.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        lsBillDetail = billDao.getListBillDetail( LoginActivity.PERSONRES.getId()+"");
        tvCountClothesInBill.setText(lsBillDetail.size()+"");
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvUpdateProfile:
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame_home, new UpdateProfileFragment())
                        .commit();
                break;
            case R.id.llMoveToMyCart:
                startActivity(new Intent(requireActivity(), MainMycart.class));
                break;
            case R.id.llProgressOrder:
                startActivity(new Intent(requireActivity(), BillsManagementCustomerActivity.class));
        }
    }
}