package com.edward.myapplication.AppSeller.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.AppSeller.adapters.ClothesAdapter;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClothesFragment extends Fragment {

    private RecyclerView rcvClothesManagement;
    private List<ClothesRes> ls;
    private TextView tvCantFindClothesManagement, tvTryAgainClothesManagement;
    private ClothesAdapter clothesAdapter;

    private int idSeller = 7;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ClothesFragment() {
        // Required empty public constructor
    }


    public static ClothesFragment newInstance(String param1, String param2) {
        ClothesFragment fragment = new ClothesFragment();
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
        return inflater.inflate(R.layout.fragment_clothes, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initRecycleView();
        ls = new ArrayList<>();
        loadClothesList();


//        clothesAdapter = new ClothesAdapter(ls, requireContext());
//        rcvClothesManagement.setAdapter(clothesAdapter);
    }

    private void initViews(View view) {
        rcvClothesManagement = view.findViewById(R.id.rcvClothesManagement);
    }

    private void initRecycleView() {
        rcvClothesManagement.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        rcvClothesManagement.setLayoutManager(layoutManager);
    }

    private void loadClothesList() {
        ServiceAPI.serviceApi.getAllClothesFromSeller(idSeller)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListClothes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(requireContext(), "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListClothes resGetListClothes) {
                        if (resGetListClothes.get_Respon().getRespone_code() == 200) {
                            ls = resGetListClothes.get_ClothesRes();
                            clothesAdapter = new ClothesAdapter(ls, requireContext());
                            rcvClothesManagement.setAdapter(clothesAdapter);

                            if (ls.size() == 0) {
                                tvCantFindClothesManagement.setVisibility(View.VISIBLE);
                                tvCantFindClothesManagement.setVisibility(View.VISIBLE);
                            } else {
                                tvCantFindClothesManagement.setVisibility(View.INVISIBLE);
                                tvCantFindClothesManagement.setVisibility(View.INVISIBLE);
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });
    }
}