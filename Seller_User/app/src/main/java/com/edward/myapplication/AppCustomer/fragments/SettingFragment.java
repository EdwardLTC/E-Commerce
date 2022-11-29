package com.edward.myapplication.AppCustomer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;

import com.edward.myapplication.R;


public class SettingFragment extends Fragment {

   ImageView ic_mailsp,ic_FAQ,ic_PS;
   Switch sw_notic,sw_emailsp;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ic_mailsp= view.findViewById(R.id.ic_moreif);
        ic_FAQ= view.findViewById(R.id.ic_moreif2);
        ic_PS= view.findViewById(R.id.ic_moreif3);
        sw_notic = view.findViewById(R.id.switch1);
        sw_emailsp = view.findViewById(R.id.switch2);
    }
}