package com.edward.adminapp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edward.adminapp.API.ServiceAPI;
import com.edward.adminapp.R;
import com.edward.adminapp.adapter.ProgressDialogCustom;
import com.edward.adminapp.adapters.UsersAdapter;
import com.edward.adminapp.helpers.MyHelpers;
import com.edward.adminapp.model.modelrespon.PersonRes;
import com.edward.adminapp.model.modelrespon.ResGetListPerson;
import com.edward.adminapp.model.modelrespon.Respon;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SellersManagementActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rcvSellersManagement;
    CardView cvBackToHomeFromSellers;
    UsersAdapter usersAdapter;
    EditText edtSearchSellers;
    Dialog dialog;
    TextView tvRefreshSellers;
    List<PersonRes> ls;

    private final String TAG = ">>>>>>>>>>>>>> SellersManagementActivity ";


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellers_management);
        ls = new ArrayList<>();
        initViews();
        initRecyclerView();
        loadRecycleView();

        // handle listener
        cvBackToHomeFromSellers.setOnClickListener(this);
        tvRefreshSellers.setOnClickListener(this);

//        String password = "123";
//        byte[] bcryptHashBytes = BCrypt.withDefaults().hash(6, password.getBytes(StandardCharsets.UTF_8));
//        Log.d(TAG, bcryptHashBytes.toString());
//
//        // verify hash
//        BCrypt.Result result = BCrypt.verifyer().verify(password.getBytes(StandardCharsets.UTF_8), bcryptHashBytes);
//        Log.d(TAG, String.valueOf(result.verified));


        edtSearchSellers.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String find = textView.getText().toString();
                    if (!find.isEmpty()) {
                        searchSellers(find);
                    }
                    MyHelpers.hideKeyboard(SellersManagementActivity.this);
                    return true;
                }

                return false;
            }
        });


    }


    private void initViews() {
        rcvSellersManagement = findViewById(R.id.rcvSellersManagement);
        edtSearchSellers = findViewById(R.id.edtSearchSellers);
        cvBackToHomeFromSellers = findViewById(R.id.cvBackToHomeFromSellers);
        tvRefreshSellers = findViewById(R.id.tvRefreshSellers);
        tvRefreshSellers.setPaintFlags(tvRefreshSellers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    private void initRecyclerView() {
        rcvSellersManagement.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rcvSellersManagement.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtSearchSellers:
                break;
            case R.id.btCancelDialogDeleteUser:
                dialog.dismiss();
                break;
            case R.id.cvBackToHomeFromSellers:
                startActivity(new Intent(SellersManagementActivity.this, MainActivity.class));
                break;
            case R.id.tvRefreshSellers:
                loadRecycleView();
                edtSearchSellers.setText("");
                break;

        }
    }

    private void deleteUser(PersonRes personRes) {

        ServiceAPI.serviceApi.DeletePerson(personRes.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Respon>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onNext(Respon respon) {
                        Log.d(">>>>> ", respon.getRespone_code()+"");
                        Log.d(">>>>> ", personRes.getId()+"");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void searchSellers(String find) {
        List<PersonRes> lsSearch = new ArrayList<>();
        for (PersonRes personRes : ls) {
            if (personRes.getName().contains(find)) {
                lsSearch.add(personRes);
            }
        }
        if (lsSearch.size() > 0) {
            usersAdapter = new UsersAdapter(this, lsSearch, rcvSellersManagement);
            rcvSellersManagement.setAdapter(usersAdapter);
        }
    }

    public void showDialogDeleteUser(PersonRes personRes) {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_delete_user);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogFade2;

        Button btCancelDialogDeleteUser = dialog.findViewById(R.id.btCancelDialogDeleteUser);
        Button btDeleteUser = dialog.findViewById(R.id.btDeleteUser);

        btDeleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(">>>>>>>>>>>>>>", personRes.getId()+"");

                deleteUser(personRes);
            }
        });
        btCancelDialogDeleteUser.setOnClickListener(this);

        Window window = dialog.getWindow();
        if (window == null)
            return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    private void loadRecycleView() {

        ServiceAPI.serviceApi.GetAllPerson(2)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListPerson>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   ProgressDialogCustom.showProgressDialog(SellersManagementActivity.this, "please wait");
                               }

                               @SuppressLint("LongLogTag")
                               @Override
                               public void onNext(ResGetListPerson resGetListPerson) {
//                                   Log.d(TAG, resGetListPerson._Respon.getRespone_code()+"");
                                   if (resGetListPerson._Respon.getRespone_code() != 200) {
                                       Toast.makeText(getApplicationContext(), "Check username, Psw pls", Toast.LENGTH_SHORT).show();
                                   } else {
                                       ls = resGetListPerson._PersonRes;
                                       Log.d(TAG, ls.size()+"");
                                       usersAdapter = new UsersAdapter(SellersManagementActivity.this, ls, rcvSellersManagement);
                                       rcvSellersManagement.setAdapter(usersAdapter);
                                   }
                               }

                               @SuppressLint("LongLogTag")
                               @Override
                               public void onError(Throwable e) {
                                   Log.d(TAG, "get data failed");
                               }

                               @SuppressLint("LongLogTag")
                               @Override
                               public void onComplete() {
                                   ProgressDialogCustom.dismissProgressDialog();
                                   Log.d(TAG, "complete");
                               }
                           }
                );
    }

}