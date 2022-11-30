package com.edward.myapplication.AppSeller.fragments;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.edward.myapplication.AppSeller.views.VouchersManagementActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.AppSeller.adapters.ClothesAdapter;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.interfaces.OnItem;
import com.edward.myapplication.model.modelrespon.ClothesRes;
import com.edward.myapplication.model.modelrespon.ResGetListClothes;
import com.edward.myapplication.model.modelrespon.Respon;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ClothesFragment extends Fragment implements OnItem {

    private RecyclerView rcvClothesManagement;
    private List<ClothesRes> ls;
    private TextView tvCantFindClothesManagement, tvTryAgainClothesManagement;
    private ClothesAdapter clothesAdapter;
    private EditText edtSearchClothes;

    private int idSeller = 11;


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

        edtSearchClothes.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String find = textView.getText().toString();
                    if (!find.isEmpty()) {
                        edtSearchClothes(find);
                    }
                    MyHelper.hideKeyboard(requireActivity());
                    return true;
                }

                return false;
            }
        });

    }

    private void edtSearchClothes(String find) {
        List<ClothesRes> lsSearch = new ArrayList<>();
        for (ClothesRes clothesRes : ls) {
            if (clothesRes.getName().toLowerCase().contains(find.toLowerCase()))
                lsSearch.add(clothesRes);
        }
        clothesAdapter = new ClothesAdapter(lsSearch, requireContext(), this);
        rcvClothesManagement.setAdapter(clothesAdapter);

        if (lsSearch.size() == 0) {
            tvTryAgainClothesManagement.setVisibility(View.VISIBLE);
            tvCantFindClothesManagement.setVisibility(View.VISIBLE);
        } else {
            tvCantFindClothesManagement.setVisibility(View.INVISIBLE);
            tvTryAgainClothesManagement.setVisibility(View.INVISIBLE);
        }
    }

    private void initViews(View view) {
        rcvClothesManagement = view.findViewById(R.id.rcvClothesManagement);
        tvCantFindClothesManagement = view.findViewById(R.id.tvCantFindClothesManagement);
        tvTryAgainClothesManagement = view.findViewById(R.id.tvTryAgainClothesManagement);
        edtSearchClothes = view.findViewById(R.id.edtSearchClothes);
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
                            clothesAdapter = new ClothesAdapter(ls, requireContext(), ClothesFragment.this);
                            rcvClothesManagement.setAdapter(clothesAdapter);

                            if (ls.size() == 0) {
                                tvCantFindClothesManagement.setVisibility(View.VISIBLE);
                                tvCantFindClothesManagement.setText("You have no products");
                                tvTryAgainClothesManagement.setVisibility(View.VISIBLE);
                                tvTryAgainClothesManagement.setText("Let's create a new once");
                            } else {
                                tvCantFindClothesManagement.setVisibility(View.INVISIBLE);
                                tvCantFindClothesManagement.setText("Can't not find any result");
                                tvTryAgainClothesManagement.setVisibility(View.INVISIBLE);
                                tvTryAgainClothesManagement.setText("Please try again");
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

    @Override
    public void fillData(ImageView ivClothes, TextView tvNameClothes, TextView tvTypeClothes, TextView tvQuantity, ClothesRes clothesRes, int position) {

    }

    @Override
    public void showDialogDeleteClothes(ClothesRes clothesRes) {
        PopupDialog.getInstance(requireContext())
                .setStyle(Styles.IOS)
                .setHeading("Delete")
                .setDescription("Are you sure you want to delete this Vouchers?"+
                        " You won't be able to see them again.")
                .setPositiveButtonText("Delete")
                .setPositiveButtonTextColor(R.color.red_blur)
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onPositiveClicked(Dialog dialog) {
                        deleteClothes(clothesRes);
                        super.onPositiveClicked(dialog);
                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                    }
                });
    }


    public void deleteClothes(ClothesRes clothesRes) {
        ServiceAPI.serviceApi.DeleteClothes(clothesRes.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Respon respon) {
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(requireContext())
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " deleted")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadClothesList();
                                        }
                                    });
                        }
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