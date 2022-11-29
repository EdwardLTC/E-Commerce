package com.edward.myapplication.AppCustomer.fragments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.app.Service;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppCustomer.adapters.AllClothesAdapter;
import com.edward.myapplication.AppCustomer.adapters.CategoryInHomeAdapter;
import com.edward.myapplication.AppCustomer.adapters.ProductsInHomeAdapter;
import com.edward.myapplication.AppCustomer.views.CustomerAllClothesActivity;
import com.edward.myapplication.AppCustomer.views.Filtering;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.ResGetListCategory;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class InHomeFragment extends Fragment implements View.OnClickListener {
    ImageView filter, find;
    TextView seeAllNew, seeAllCategory;
    EditText edtFind;
    RecyclerView recyclerViewNew, recyclerViewCategory;
    CategoryInHomeAdapter categoryInHomeAdapter;
    ProductsInHomeAdapter productsInHomeAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_inhome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initRecycleViewClothes();

        seeAllNew.setOnClickListener(this);

//        LinearLayoutManager linearLayoutManager  = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
//        recyclerViewCategory.setLayoutManager(linearLayoutManager);
//        recyclerViewNew.setLayoutManager(linearLayoutManager);
        loadListCategoriesInHome();

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filtering filtering = new Filtering();
                filtering.show(getActivity().getSupportFragmentManager(), "TAG");
            }
        });
    }

    private void initViews(View view) {
        filter = view.findViewById(R.id.btnfilter);
        find = view.findViewById(R.id.ImgFind);
        edtFind = view.findViewById(R.id.txtFind);
        seeAllNew = view.findViewById(R.id.SeeAllNew);
        seeAllCategory = view.findViewById(R.id.SeeAllCategory);
        recyclerViewNew = view.findViewById(R.id.RecyclerViewNew);
        recyclerViewCategory = view.findViewById(R.id.RecyclerViewCategory);
    }

    private void initRecycleViewClothes() {
        recyclerViewNew.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerViewNew.setLayoutManager(layoutManager);

        recyclerViewCategory.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false);
        recyclerViewCategory.setLayoutManager(layoutManager1);
    }

    private void loadListCategoriesInHome() {
        ServiceAPI.serviceApi.GetAllCategory().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListCategory>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(requireContext(), "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListCategory resGetListCategory) {
                        categoryInHomeAdapter= new CategoryInHomeAdapter(requireContext(),resGetListCategory.get_CategoryRes());
                        recyclerViewCategory.setAdapter(categoryInHomeAdapter);
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

    private void loadListProductsInHome() {

    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.SeeAllCategory:
                break;
            case R.id.SeeAllNew:
                startActivity(new Intent(requireContext(), CustomerAllClothesActivity.class));
                break;
        }

    }
}
