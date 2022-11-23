package com.edward.myapplication.Customer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.Customer.views.Filtering;
import com.edward.myapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeFragment extends Fragment {
    BottomNavigationView bottomNavigationView;
    ImageView filter, find, dress, shirts, pants, tshirts;
    TextView seeAll;
    EditText edtFind;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bottomNavigationView = view.findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setItemIconTintList(null);
        filter = view.findViewById(R.id.btnfilter);
        find = view.findViewById(R.id.ImgFind);
        edtFind = view.findViewById(R.id.txtFind);
        dress = view.findViewById(R.id.ImgDress);
        shirts = view.findViewById(R.id.ImgShirts);
        pants = view.findViewById(R.id.ImgPants);
        tshirts = view.findViewById(R.id.ImgTshirts);
        seeAll = view.findViewById(R.id.txtSeeAll);
        recyclerView = view.findViewById(R.id.RecyclerViewInHome);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Filtering filtering = new Filtering();
                filtering.show(getActivity().getSupportFragmentManager(), "TAG");
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.bottom_home:
                        break;
                    case R.id.bottom_shop:
                        break;
                    case R.id.bottom_heart:
                        break;
                    case R.id.bottom_profile:
                        break;
                }
                return true;
            }
        });


    }

    private void loadFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame_home, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
