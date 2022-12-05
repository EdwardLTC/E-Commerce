package com.edward.myapplication.AppCustomer.fragments;


import android.annotation.SuppressLint;
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
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {
    CircleImageView iv_profile;
    ImageView ic_more,ic_bag,ic_ticket,ic_star;
    TextView txtProfileName, tvNameCustomer, tvMailCustomer, tvAddressCustomer, tvPhoneCustomer, tvUpdateProfile
            ,txtProfileEmail,txtProfileProcessOrderNumber,txtProfilePromocodesNumber,txtProfileReviewesNumber,txtPIName,txtPIMail,txtPIAddess,txtPIPhoneNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        fillValue();

        tvUpdateProfile.setOnClickListener(this);
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

    }

    private void fillValue() {
        Glide.with(requireActivity()).load(LoginActivity.PERSONRES.getImgUrl()).into(iv_profile);
        txtProfileName.setText(LoginActivity.PERSONRES.getName());
        txtProfileEmail.setText(LoginActivity.PERSONRES.getMail());
        tvNameCustomer.setText(LoginActivity.PERSONRES.getName());
        tvMailCustomer.setText(LoginActivity.PERSONRES.getMail());
        tvPhoneCustomer.setText(LoginActivity.PERSONRES.getPhoneNum());
        tvAddressCustomer.setText(LoginActivity.PERSONRES.getAddress());
        tvUpdateProfile.setPaintFlags(tvUpdateProfile.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
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
        }
    }
}