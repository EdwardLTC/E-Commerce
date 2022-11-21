package com.edward.myapplication.Seller.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edward.myapplication.R;
import com.edward.myapplication.Seller.adapters.CategoriesAdapter;
import com.edward.myapplication.ProgressDialogCustom;
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
 * Use the {@link CategoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CategoriesFragment extends Fragment implements View.OnClickListener {

    private RecyclerView rcvCategoriesManagement;
    private List<CategoryRes> ls;
    private CategoriesAdapter categoriesAdapter;
    private TextView tvCantFindCategories, tvTryAgainCategories;
    private EditText edtSearchCategories;
    private ImageView ivReloadCategoriesList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CategoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CategoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CategoriesFragment newInstance(String param1, String param2) {
        CategoriesFragment fragment = new CategoriesFragment();
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
        return inflater.inflate(R.layout.fragment_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initRecycleView();
        ls = new ArrayList<>();
        loadCategoriesList();

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
        rcvCategoriesManagement = view.findViewById(R.id.rcvCategoriesManagement);
        tvCantFindCategories = view.findViewById(R.id.tvCantFindCategories);
        tvTryAgainCategories = view.findViewById(R.id.tvTryAgainCategories);
        edtSearchCategories = view.findViewById(R.id.edtSearchCategories);
        ivReloadCategoriesList = view.findViewById(R.id.ivReloadCategoriesList);

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
                            categoriesAdapter = new CategoriesAdapter(ls, requireContext());
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
        categoriesAdapter = new CategoriesAdapter(lsSearch, requireContext());
        rcvCategoriesManagement.setAdapter(categoriesAdapter);

        if (lsSearch.size() == 0) {
            tvTryAgainCategories.setVisibility(View.VISIBLE);
            tvCantFindCategories.setVisibility(View.VISIBLE);
        } else {
            tvTryAgainCategories.setVisibility(View.INVISIBLE);
            tvCantFindCategories.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivReloadCategoriesList:
                loadCategoriesList();
                break;
        }
    }
}