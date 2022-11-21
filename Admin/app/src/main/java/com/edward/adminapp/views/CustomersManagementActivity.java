package com.edward.adminapp.views;

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

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.adminapp.API.ServiceAPI;
import com.edward.adminapp.R;
import com.edward.adminapp.adapter.ProgressDialogCustom;
import com.edward.adminapp.adapters.UsersCustomerAdapter;
import com.edward.adminapp.helpers.MyHelpers;
import com.edward.adminapp.model.modelrespon.PersonRes;
import com.edward.adminapp.model.modelrespon.ResGetListPerson;
import com.edward.adminapp.model.modelrespon.Respon;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CustomersManagementActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rcvCustomersManagement;
    CardView cvBackToHomeFromCustomers;
    UsersCustomerAdapter usersCustomerAdapter;
    EditText edtSearchCustomers;
    Dialog dialog;
    TextView tvRefreshCustomers, tvTryAgainCustomers, tvCantFindCustomers;
    List<PersonRes> ls;

    private final String TAG = ">>>>>>>>>>>>>> CustomersManagementActivity ";


    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_management);
        ls = new ArrayList<>();
        initViews();
        initRecyclerView();
        loadRecycleView();

        // handle listener
        cvBackToHomeFromCustomers.setOnClickListener(this);
        tvRefreshCustomers.setOnClickListener(this);

//        String password = "123";
//        byte[] bcryptHashBytes = BCrypt.withDefaults().hash(6, password.getBytes(StandardCharsets.UTF_8));
//        Log.d(TAG, bcryptHashBytes.toString());
//
//        // verify hash
//        BCrypt.Result result = BCrypt.verifyer().verify(password.getBytes(StandardCharsets.UTF_8), bcryptHashBytes);
//        Log.d(TAG, String.valueOf(result.verified));


        edtSearchCustomers.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String find = textView.getText().toString();
                    if (!find.isEmpty()) {
                        searchCustomers(find);
                    }
                    MyHelpers.hideKeyboard(CustomersManagementActivity.this);
                    return true;
                }

                return false;
            }
        });

    }


    private void initViews() {
        rcvCustomersManagement = findViewById(R.id.rcvCustomersManagement);
        edtSearchCustomers = findViewById(R.id.edtSearchCustomers);
        cvBackToHomeFromCustomers = findViewById(R.id.cvBackToHomeFromCustomers);
        tvRefreshCustomers = findViewById(R.id.tvRefreshCustomers);
        tvRefreshCustomers.setPaintFlags(tvRefreshCustomers.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        tvTryAgainCustomers = findViewById(R.id.tvTryAgainCustomers);
        tvCantFindCustomers = findViewById(R.id.tvCantFindCustomers);
    }

    private void initRecyclerView() {
        rcvCustomersManagement.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rcvCustomersManagement.setLayoutManager(layoutManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtSearchCustomers:
                break;
            case R.id.btCancelDialogDeleteUser:
                dialog.dismiss();
                break;
            case R.id.cvBackToHomeFromCustomers:
                startActivity(new Intent(CustomersManagementActivity.this, MainActivity.class));
                break;
            case R.id.tvRefreshCustomers:
                loadRecycleView();
                edtSearchCustomers.setText("");
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
                        if (respon.getRespone_code() == 200) {
                            PopupDialog.getInstance(CustomersManagementActivity.this)
                                    .setStyle(Styles.SUCCESS)
                                    .setHeading("Well Done")
                                    .setHeading("You have successfully"+
                                            " deleted")
                                    .setCancelable(false)
                                    .showDialog(new OnDialogButtonClickListener() {
                                        @Override
                                        public void onDismissClicked(Dialog dialog1) {
                                            super.onDismissClicked(dialog1);
                                            loadRecycleView();
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

    private void searchCustomers(String find) {
        List<PersonRes> lsSearch = new ArrayList<>();
        for (PersonRes personRes : ls) {
            if (personRes.getName().toLowerCase().contains(find.toLowerCase())) {
                lsSearch.add(personRes);
            }
        }
        usersCustomerAdapter = new UsersCustomerAdapter(this, lsSearch, rcvCustomersManagement);
        rcvCustomersManagement.setAdapter(usersCustomerAdapter);

        if (lsSearch.size() == 0) {
            tvTryAgainCustomers.setVisibility(View.VISIBLE);
            tvCantFindCustomers.setVisibility(View.VISIBLE);
        } else {
            tvTryAgainCustomers.setVisibility(View.INVISIBLE);
            tvCantFindCustomers.setVisibility(View.INVISIBLE);
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
                deleteUser(personRes);
                dialog.dismiss();

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

        ServiceAPI.serviceApi.GetAllPerson(3)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListPerson>() {
                               @Override
                               public void onSubscribe(Disposable d) {
                                   ProgressDialogCustom.showProgressDialog(CustomersManagementActivity.this, "please wait");
                               }

                               @SuppressLint("LongLogTag")
                               @Override
                               public void onNext(ResGetListPerson resGetListPerson) {
//                                   Log.d(TAG, resGetListPerson._Respon.getRespone_code()+"");
                                   if (resGetListPerson._Respon.getRespone_code() != 200) {
                                       Toast.makeText(getApplicationContext(), "Check username, Psw pls", Toast.LENGTH_SHORT).show();
                                   } else {
                                       ls = resGetListPerson._PersonRes;
                                       Log.d(TAG, ls.size() + "");
                                       usersCustomerAdapter = new UsersCustomerAdapter(CustomersManagementActivity.this, ls, rcvCustomersManagement);
                                       rcvCustomersManagement.setAdapter(usersCustomerAdapter);
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