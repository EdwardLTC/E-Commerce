package com.edward.myapplication.AppCustomer.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.LinearLayout;

import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.R;

public class SettingFragment extends Fragment {

    ImageView ic_mailsp,ic_FAQ,ic_PS;
    Switch sw_notic,sw_emailsp;
    private LinearLayout Emailsupport, FAQ,Privacy;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View nview4 = inflater.inflate(R.layout.fragment_setting, container, false);
        Emailsupport =nview4.findViewById(R.id.Emailsupport);
        FAQ=nview4.findViewById(R.id.FAQ);
        Privacy=nview4.findViewById(R.id.Privacy);


        Emailsupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EmailSupportActivity.class);
                getActivity().startActivity(intent);
            }
        });


        FAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FAQctivity.class);
                getActivity().startActivity(intent);
            }
        });

        Privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PrivacyActivity.class);
                getActivity().startActivity(intent);
            }
        });
        return nview4;
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