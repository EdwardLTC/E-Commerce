package com.edward.myapplication.AppCustomer.fragments;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.edward.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class ProfileFragment extends Fragment {
    ImageView ic_more,iv_profile,ic_bag,ic_ticket,ic_star;
    TextView txtProfileName,txtProfileEmail,txtProfileProcessOrderNumber,txtProfilePromocodesNumber,txtProfileReviewesNumber,txtPIName,txtPIMail,txtPIAddess,txtPIPhoneNumber;
    BottomNavigationView bottomNavigationView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }
}