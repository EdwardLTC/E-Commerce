package com.edward.myapplication.AppCustomer.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppCustomer.adapters.CustomersAllCategoriesAdapter;
import com.edward.myapplication.AppSeller.adapters.CategoriesAdapter;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.modelrespon.CategoryRes;
import com.edward.myapplication.model.modelrespon.ResGetListCategory;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomerAllCategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CustomerAllCategoriesFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rcvCategoriesManagement;
    private List<CategoryRes> ls;
    private CustomersAllCategoriesAdapter categoriesAdapter;
    private TextView tvCantFindCategories, tvTryAgainCategories;
    private EditText edtSearchCategories;
    private ImageView ivReloadCategoriesList, ivBackToInHomeFragment;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CustomerAllCategoriesFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static CustomerAllCategoriesFragment newInstance(String param1, String param2) {
        CustomerAllCategoriesFragment fragment = new CustomerAllCategoriesFragment();
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
        return inflater.inflate(R.layout.fragment_customers_all_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initRecycleView();
        ls = new ArrayList<>();
        loadCategoriesList();

        ivReloadCategoriesList.setOnClickListener(this);
        ivBackToInHomeFragment.setOnClickListener(this);

        edtSearchCategories.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String find = textView.getText().toString();
                    if (!find.isEmpty()) {
                        searchCategories(find);
                    }
                    MyHelper.hideKeyboard(requireActivity());
                    return true;
                }

                return false;
            }
        });

    }

    private void initViews(View view) {
        rcvCategoriesManagement = view.findViewById(R.id.rcvCustomersAllCategoriesManagement);
        tvCantFindCategories = view.findViewById(R.id.tvCantFindCustomersAllCategories);
        tvTryAgainCategories = view.findViewById(R.id.tvTryAgainCustomersAllCategories);
        edtSearchCategories = view.findViewById(R.id.edtSearchCustomersAllCategories);
        ivReloadCategoriesList = view.findViewById(R.id.ivReloadCustomersAllCategoriesList);
        ivBackToInHomeFragment = view.findViewById(R.id.ivBackToInHomeFragment);

    }

    private void initRecycleView() {
        rcvCategoriesManagement.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(requireContext(), 4);
        rcvCategoriesManagement.setLayoutManager(layoutManager);
    }

    private void loadCategoriesList() {
        ServiceAPI.serviceApi.GetAllCategory()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListCategory>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(requireContext(), "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListCategory resGetListCategory) {
                        if (resGetListCategory.get_Respon().getRespone_code() == 200) {
                            ls = resGetListCategory.get_CategoryRes();
                            categoriesAdapter = new CustomersAllCategoriesAdapter(ls, requireContext());
                            rcvCategoriesManagement.setAdapter(categoriesAdapter);
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

    private void searchCategories(String find) {
        List<CategoryRes> lsSearch = new ArrayList<>();
        for (CategoryRes categoryRes : ls) {
            if (categoryRes.getName().toLowerCase().contains(find.toLowerCase())) {
                lsSearch.add(categoryRes);
            }
        }
        categoriesAdapter = new CustomersAllCategoriesAdapter(lsSearch, requireContext());
        rcvCategoriesManagement.setAdapter(categoriesAdapter);

        if (lsSearch.size() == 0) {
            tvTryAgainCategories.setVisibility(View.VISIBLE);
            tvCantFindCategories.setVisibility(View.VISIBLE);
        } else {
            tvTryAgainCategories.setVisibility(View.INVISIBLE);
            tvCantFindCategories.setVisibility(View.INVISIBLE);
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivReloadCustomersAllCategoriesList:
                loadCategoriesList();
                break;
            case R.id.ivBackToInHomeFragment:
                break;
        }
    }
}