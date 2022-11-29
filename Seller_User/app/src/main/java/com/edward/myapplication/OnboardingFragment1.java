package com.edward.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.edward.myapplication.AppCustomer.views.MainActivity;
import com.edward.myapplication.interfaces.LoadFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OnboardingFragment1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OnboardingFragment1 extends Fragment {
    private Button btn_obf1;
    private View nview;
    private LoadFragment loadFragment;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OnboardingFragment1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OnboardingFragment1.
     */
    // TODO: Rename and change types and number of parameters
    public static OnboardingFragment1 newInstance(String param1, String param2) {
        OnboardingFragment1 fragment = new OnboardingFragment1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        nview = inflater.inflate(R.layout.fragment_onboarding1, container, false);
        btn_obf1 = nview.findViewById(R.id.btn_obd1);
        btn_obf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        return nview;
    }
}