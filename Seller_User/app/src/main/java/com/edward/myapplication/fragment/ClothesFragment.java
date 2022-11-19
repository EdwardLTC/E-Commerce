package com.edward.myapplication.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.edward.myapplication.R;
import com.edward.myapplication.adapter.CategoriesAdapter;
import com.edward.myapplication.adapter.ClothesAdapter;
import com.edward.myapplication.model.Categories;
import com.edward.myapplication.model.Clothes;

import java.util.ArrayList;
import java.util.List;

public class ClothesFragment extends Fragment {

    private RecyclerView rcvClothesManagement;
    private List<Clothes> ls;
    private ClothesAdapter clothesAdapter;


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
//
//        ls.add(new Categories("Sleeve Shirt Rolo"));
//        ls.add(new Categories("Sleeve Shirt Rolo"));
//        ls.add(new Categories("Sleeve Shirt Rolo"));
//        ls.add(new Categories("Sleeve Shirt Rolo"));
//        ls.add(new Categories("Sleeve Shirt Rolo"));
//        ls.add(new Categories("Sleeve Shirt Rolo"));

        ls.add(new Clothes("shirt", 20, "shirt"));
        ls.add(new Clothes("shirtshirtshirtshirtshirt", 20, "shirt"));
        ls.add(new Clothes("shirt", 20, "shirt"));
        ls.add(new Clothes("shirt", 20, "shirt"));
        ls.add(new Clothes("shirt", 20, "shirt"));
        ls.add(new Clothes("shirt", 20, "shirt"));
        ls.add(new Clothes("shirt", 20, "shirt"));

        clothesAdapter = new ClothesAdapter(ls, requireContext());
        rcvClothesManagement.setAdapter(clothesAdapter);
    }

    private void initViews(View view) {
        rcvClothesManagement = view.findViewById(R.id.rcvClothesManagement);
    }

    private void initRecycleView() {
        rcvClothesManagement.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        rcvClothesManagement.setLayoutManager(layoutManager);
    }
}