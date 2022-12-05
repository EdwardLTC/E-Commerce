package com.edward.myapplication.AppCustomer.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.edward.myapplication.R;


public class MyWalletFragment extends Fragment {
    ImageView ic_back,ic_mywallet_more;
    RecyclerView rv_wallet;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_wallet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ic_back= view.findViewById(R.id.ic_back);
        ic_mywallet_more = view.findViewById(R.id.ic_mywallet_more);
        rv_wallet= view.findViewById(R.id.rv_wallet);
    }
}