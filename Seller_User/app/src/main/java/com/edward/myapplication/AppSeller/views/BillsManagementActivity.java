package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.edward.myapplication.AppSeller.adapters.SellerBillsAdapter;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.BillRes;
import com.edward.myapplication.model.modelrespon.ResGetListBill;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BillsManagementActivity extends AppCompatActivity implements View.OnClickListener {
    private CardView cvBackToHomeFromBills;
    private EditText edtSearchBills;
    private RecyclerView rcvBillsManagement;
    private ImageView ivStatusBills;
    private SellerBillsAdapter sellerBillsAdapter;
    private List<BillRes> ls;

    public static BillRes BILL= null;
//    public static int ID_BILL= -1;


    private int idSeller = LoginActivity.PERSONRES.getId();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bills_management);
        ls = new ArrayList<>();
        initViews();
        initRecyclerView();
        loadListBill();

        cvBackToHomeFromBills.setOnClickListener(this);
        ivStatusBills.setOnClickListener(this);
    }

    private void initViews() {
        cvBackToHomeFromBills = findViewById(R.id.cvBackToHomeFromBills);
        edtSearchBills = findViewById(R.id.edtSearchBills);
        rcvBillsManagement = findViewById(R.id.rcvBillsManagement);
        ivStatusBills = findViewById(R.id.ivStatusBills);
    }

    private void initRecyclerView() {
        rcvBillsManagement.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvBillsManagement.setLayoutManager(layoutManager);
    }

    private void loadListBill() {
        ServiceAPI.serviceApi.GetBillOfSeller(idSeller)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListBill>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(BillsManagementActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListBill resGetListBill) {
                        Log.d(">>>>>>>: ", resGetListBill.get_Respon().getRespone_code()+"" );

                        if (resGetListBill.get_Respon().getRespone_code() == 200) {
                            ls = resGetListBill.get_BillRes();
                            Toast.makeText(BillsManagementActivity.this, ls.size()+"", Toast.LENGTH_SHORT).show();
                            sellerBillsAdapter = new SellerBillsAdapter(BillsManagementActivity.this, ls);
                            rcvBillsManagement.setAdapter(sellerBillsAdapter);
                            Log.d("Bill: ", ls.get(ls.size()-1).toString());
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
    }


    private void loadListBillCompleted() {
        ServiceAPI.serviceApi.GetBillWhereCompleted()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListBill>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(BillsManagementActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListBill resGetListBill) {
                        Log.d(">>>>>>>: ", resGetListBill.get_Respon().getRespone_code()+"" );

                        if (resGetListBill.get_Respon().getRespone_code() == 200) {
                            ls = resGetListBill.get_BillRes();
                            sellerBillsAdapter = new SellerBillsAdapter(BillsManagementActivity.this, ls);
                            rcvBillsManagement.setAdapter(sellerBillsAdapter);
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
    }
    private void loadListBillNotCompleted() {
        ServiceAPI.serviceApi.GetBillWhereNotCompleted()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListBill>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(BillsManagementActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetListBill resGetListBill) {
                        Log.d(">>>>>>>: ", resGetListBill.get_Respon().getRespone_code()+"" );

                        if (resGetListBill.get_Respon().getRespone_code() == 200) {
                            ls = resGetListBill.get_BillRes();
                            sellerBillsAdapter = new SellerBillsAdapter(BillsManagementActivity.this, ls);
                            rcvBillsManagement.setAdapter(sellerBillsAdapter);
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
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ivStatusBills:
                showPopupMenuStatus();
                break;
            case R.id.cvBackToHomeFromBills:
                startActivity(new Intent(this, SellerDashboardActivity.class));
                break;
        }
    }

    private void showPopupMenuStatus() {
        // Initializing the popup menu and giving the reference as current context
        PopupMenu popupMenu = new PopupMenu(this, ivStatusBills);

        // Inflating popup menu from popup_menu.xml file
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_status, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.menuSellerStatusDefault:
                        loadListBill();
                        break;
                    case R.id.menuSellerStatusCompleted:
                        loadListBillCompleted();
                        break;
                    case R.id.menuSellerStatusNotCompleted:
                        loadListBillNotCompleted();

                        break;

                }
                return true;
            }
        });
        // Showing the popup menu
        popupMenu.show();
    }
}