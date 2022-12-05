package com.edward.myapplication.AppCustomer.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.edward.myapplication.AppCustomer.adapters.CustomerBillsAdapter;
import com.edward.myapplication.AppSeller.adapters.SellerBillsAdapter;
import com.edward.myapplication.AppSeller.views.SellerDashboardActivity;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.helper.MyHelper;
import com.edward.myapplication.model.modelrespon.BillRes;
import com.edward.myapplication.model.modelrespon.ResGetListBill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BillsManagementCustomerActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cvBackToHomeFromBillsCustomer;
    private EditText edtSearchBillsCustomer;
    private RecyclerView rcvBillsManagementCustomer;
    private ImageView ivStatusBillsCustomer;
    private CustomerBillsAdapter customerBillsAdapter;
    private List<BillRes> ls;

    public static BillRes BILL= null;
//    public static int ID_BILL= -1;


    private final int idCustomer = LoginActivity.PERSONRES.getId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_customer_management);
        ls = new ArrayList<>();
        initViews();
        initRecyclerView();
        loadListBill();

        cvBackToHomeFromBillsCustomer.setOnClickListener(this);
        ivStatusBillsCustomer.setOnClickListener(this);
        edtSearchBillsCustomer.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    String find = textView.getText().toString();
                    if (!find.isEmpty()) {
                        searchBills(find);
//                        searchCustomers(find);
                    }
                    MyHelper.hideKeyboard(BillsManagementCustomerActivity.this);
                    return true;
                }

                return false;
            }
        });

    }

    private void searchBills(String find) {
    }

    private void initViews() {
        cvBackToHomeFromBillsCustomer = findViewById(R.id.cvBackToHomeFromBillsCustomer);
        edtSearchBillsCustomer = findViewById(R.id.edtSearchBillsCustomer);
        rcvBillsManagementCustomer = findViewById(R.id.rcvBillsManagementCustomer);
        ivStatusBillsCustomer = findViewById(R.id.ivStatusBillsCustomer);
    }

    private void initRecyclerView() {
        rcvBillsManagementCustomer.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvBillsManagementCustomer.setLayoutManager(layoutManager);
    }

    private void loadListBill() {
        ServiceAPI.serviceApi.GetBillOfUser(idCustomer)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListBill>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(BillsManagementCustomerActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListBill resGetListBill) {
                        Log.d(">>>>>>>: ", resGetListBill.get_Respon().getRespone_code()+"" );

                        if (resGetListBill.get_Respon().getRespone_code() == 200) {
                            ls = resGetListBill.get_BillRes();
                            Collections.reverse(ls);
                            Toast.makeText(BillsManagementCustomerActivity.this, ls.size()+"", Toast.LENGTH_SHORT).show();
                            customerBillsAdapter = new CustomerBillsAdapter(BillsManagementCustomerActivity.this, ls);
                            rcvBillsManagementCustomer.setAdapter(customerBillsAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(">>>>>>>: ", "err" );
                        ProgressDialogCustom.dismissProgressDialog();

                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });

//         .subscribe(new Observer<ResGetListBill>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                ProgressDialogCustom.showProgressDialog(BillsManagementCustomerActivity.this, "Please wait");
//            }
//
//            @Override
//            public void onNext(ResGetListBill resGetListBill) {
//                Log.d(">>>>>>>: ", resGetListBill.get_Respon().getRespone_code()+"" );
//
//                if (resGetListBill.get_Respon().getRespone_code() == 200) {
//                    ls = resGetListBill.get_BillRes();
//                    Collections.reverse(ls);
//                    Toast.makeText(BillsManagementCustomerActivity.this, ls.size()+"", Toast.LENGTH_SHORT).show();
//                    customerBillsAdapter = new CustomerBillsAdapter(BillsManagementCustomerActivity.this, ls);
//                    rcvBillsManagementCustomer.setAdapter(customerBillsAdapter);
//                    Log.d("Bill: ", ls.get(ls.size()-1).toString());
//                }
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(">>>>>>>: ", "err" );
//                ProgressDialogCustom.dismissProgressDialog();
//
//            }
//
//            @Override
//            public void onComplete() {
//                ProgressDialogCustom.dismissProgressDialog();
//            }
//        });
    }

//
//    private void loadListBillCompleted() {
//        ServiceAPI.serviceApi.GetBillWhereCompleted()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResGetListBill>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        ProgressDialogCustom.showProgressDialog(BillsManagementCustomerActivity.this, "Please wait");
//                    }
//
//                    @Override
//                    public void onNext(ResGetListBill resGetListBill) {
//                        Log.d(">>>>>>>: ", resGetListBill.get_Respon().getRespone_code()+"" );
//
//                        if (resGetListBill.get_Respon().getRespone_code() == 200) {
//                            ls = resGetListBill.get_BillRes();
//                            Collections.reverse(ls);
//
//                            customerBillsAdapter = new CustomerBillsAdapter(BillsManagementCustomerActivity.this, ls);
//                            rcvBillsManagementCustomer.setAdapter(customerBillsAdapter);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(">>>>>>>: ", "err" );
//                        ProgressDialogCustom.dismissProgressDialog();
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        ProgressDialogCustom.dismissProgressDialog();
//                    }
//                });
//    }
//    private void loadListBillNotCompleted() {
//        ServiceAPI.serviceApi.GetBillWhereNotCompleted()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResGetListBill>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        ProgressDialogCustom.showProgressDialog(BillsManagementCustomerActivity.this, "Please wait");
//                    }
//
//                    @Override
//                    public void onNext(ResGetListBill resGetListBill) {
//                        Log.d(">>>>>>>: ", resGetListBill.get_Respon().getRespone_code()+"" );
//
//                        if (resGetListBill.get_Respon().getRespone_code() == 200) {
//                            ls = resGetListBill.get_BillRes();
//                            Collections.reverse(ls);
//                            customerBillsAdapter = new CustomerBillsAdapter(BillsManagementCustomerActivity.this, ls);
//                            rcvBillsManagementCustomer.setAdapter(customerBillsAdapter);
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.d(">>>>>>>: ", "err" );
//                        ProgressDialogCustom.dismissProgressDialog();
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        ProgressDialogCustom.dismissProgressDialog();
//                    }
//                });
//    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivStatusBillsCustomer:
                showPopupMenuStatus();
                break;
            case R.id.cvBackToHomeFromBillsCustomer:
                super.onBackPressed();
                break;
        }
    }

    private void showPopupMenuStatus() {
        // Initializing the popup menu and giving the reference as current context
        PopupMenu popupMenu = new PopupMenu(this, ivStatusBillsCustomer);

        // Inflating popup menu from popup_menu.xml file
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_status, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuSellerStatusDefault:
                        loadListBill();
                        break;
//                    case R.id.menuSellerStatusCompleted:
//                        loadListBillCompleted();
//                        break;
//                    case R.id.menuSellerStatusNotCompleted:
//                        loadListBillNotCompleted();
//
//                        break;

                }
                return true;
            }
        });
        // Showing the popup menu
        popupMenu.show();
    }
}