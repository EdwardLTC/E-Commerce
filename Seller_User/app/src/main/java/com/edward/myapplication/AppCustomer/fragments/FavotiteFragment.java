package com.edward.myapplication.AppCustomer.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppCustomer.adapters.CategoryInHomeAdapter;
import com.edward.myapplication.AppCustomer.adapters.FavoriteAdapter;
import com.edward.myapplication.AppCustomer.views.MainActivity;
import com.edward.myapplication.AppCustomer.views.ProductsDetailActivity;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.ResGetListCategory;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavotiteFragment extends Fragment {
    ImageView ImgBack;
    RecyclerView recyclerView;
    FavoriteAdapter favoriteAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(requireContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        LoadListFavorite();

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProductsDetailActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ImgBack = view.findViewById(R.id.ImgBack);
        recyclerView = view.findViewById(R.id.RecyclerViewFavorite);
    }

    public void LoadListFavorite(){
        ServiceAPI.serviceApi.GetAllFavoritesOf(21).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListClothes>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(ResGetListClothes resGetListClothes) {
                        favoriteAdapter= new FavoriteAdapter(requireContext(), resGetListClothes.get_ClothesRes());
                        recyclerView.setAdapter(favoriteAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
}
