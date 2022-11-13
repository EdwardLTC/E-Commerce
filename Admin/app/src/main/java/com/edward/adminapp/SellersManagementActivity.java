package com.edward.adminapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edward.adminapp.adapters.UsersAdapter;

import java.util.ArrayList;
import java.util.List;

public class SellersManagementActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView rcvSellersManagement;
    UsersAdapter usersAdapter;
    List<Users> ls;
    EditText edtSearchSellers;
    Dialog dialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sellers_management);
        ls = new ArrayList<>();
        initData();
        initViews();
        dialog = new Dialog(this);
        usersAdapter = new UsersAdapter(this, ls, rcvSellersManagement);
        rcvSellersManagement.setLayoutManager(new LinearLayoutManager(this));
        rcvSellersManagement.setAdapter(usersAdapter);


        edtSearchSellers.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    searchSellers();
                    return true;
                }
                return false;
            }
        });
    }

    private void initData() {
        ls.add(new Users(1, "Alex", "alex@gmail.com", "222-456-768", 2, "", "New York"));
        ls.add(new Users(1, "Alex", "alex@gmail.com", "222-456-768", 2, "", "New York"));
        ls.add(new Users(1, "Alex", "alex@gmail.com", "222-456-768", 2, "", "New York"));
        ls.add(new Users(1, "Alex", "alex@gmail.com", "222-456-768", 2, "", "New York"));
        ls.add(new Users(1, "Alex", "alex@gmail.com", "222-456-768", 2, "", "New York"));
        ls.add(new Users(1, "Alex", "alex@gmail.com", "222-456-768", 2, "", "New York"));
    }

    private void initViews() {
        rcvSellersManagement = findViewById(R.id.rcvSellersManagement);
        edtSearchSellers = findViewById(R.id.edtSearchSellers);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.edtSearchSellers:
                break;
            case R.id.btCancelDialogDeleteUser:
                dialog.dismiss();
                break;
            case R.id.btDeleteUser:
                deleteUser();
                dialog.dismiss();
                break;
        }
    }

    private void deleteUser() {
    }

    private void searchSellers() {

    }

    public void showDialogDeleteUser() {
       
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_delete_user);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogFade2;

        Button btCancelDialogDeleteUser = dialog.findViewById(R.id.btCancelDialogDeleteUser);
        Button btDeleteUser = dialog.findViewById(R.id.btDeleteUser);
        
        btDeleteUser.setOnClickListener(this);
        btCancelDialogDeleteUser.setOnClickListener(this);
        
        Window window = dialog.getWindow();
        if (window == null)
            return;

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}