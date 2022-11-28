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


public class ProfileFragment extends Fragment {
    ImageView ic_more,iv_profile,ic_bag,ic_ticket,ic_star;
    TextView txtProfileName,txtProfileEmail,txtProfileProcessOrderNumber,txtProfilePromocodesNumber,txtProfileReviewesNumber,txtPIName,txtPIMail,txtPIAddess,txtPIPhoneNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ic_more = view.findViewById(R.id.ic_more);
        ic_bag=view.findViewById(R.id.ic_profile_bag);
        ic_ticket=view.findViewById(R.id.ic_ticket);
        ic_star=view.findViewById(R.id.ic_star);
        iv_profile=view.findViewById(R.id.iv_profile);
        txtProfileName=view.findViewById(R.id.txtProfileName);
        txtProfileEmail=view.findViewById(R.id.txtProfileEmail);
        txtProfileProcessOrderNumber=view.findViewById(R.id.txtProfileProcessOrderNumber);
        txtProfilePromocodesNumber=view.findViewById(R.id.txtProfilePromocodesNumber);
        txtProfileReviewesNumber= view.findViewById(R.id.txtProfileReviewesNumber);
        txtPIName=view.findViewById(R.id.txtPIName);
        txtPIMail=view.findViewById(R.id.txtPIMail);
        txtPIAddess=view.findViewById(R.id.txtPIAddress);
        txtPIPhoneNumber=view.findViewById(R.id.txtPIPhoneNumber);
    }
}