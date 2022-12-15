package com.edward.adminapp.views;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edward.adminapp.API.ServiceAPI;
import com.edward.adminapp.R;
import com.edward.adminapp.adapter.ProgressDialogCustom;
import com.edward.adminapp.helpers.MyHelpers;
import com.edward.adminapp.model.modelrequest.PersonReq;
import com.edward.adminapp.model.modelrespon.ResGetPerson;
import com.edward.adminapp.model.modelrespon.Respon;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

import at.favre.lib.crypto.bcrypt.BCrypt;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    private EditText edtEmailLogin;
    private EditText edtPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btLogin = findViewById(R.id.btLogin);
        edtEmailLogin = findViewById(R.id.edtEmailLogin);
        edtPassword = findViewById(R.id.edtPassword);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyHelpers.addClickEffect(view);
                login();
            }
        });


        // create person
//        String pass = MyHelpers.getHashPassword("abc");
//        Log.d("pass ", pass);
//        boolean verified = MyHelpers.isVerifiedHash("abc", pass);
//        Log.d("pass ", verified+"");
    }


    private void login() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
//        ServiceAPI.serviceApi.Login(edtEmailLogin.getText().toString(), edtPassword.getText().toString())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<ResGetPerson>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        ProgressDialogCustom.showProgressDialog(LoginActivity.this, "please wait");
//                    }
//
//                    @Override
//                    public void onNext(ResGetPerson resGetPerson) {
//                        if (resGetPerson._Respon.getRespone_code() != 200) {
//                            Toast.makeText(LoginActivity.this, "Check username, Psw pls", Toast.LENGTH_SHORT).show();
//                        } else {
//                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ProgressDialogCustom.dismissProgressDialog();
//                        Toast.makeText(LoginActivity.this, "non null field", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        ProgressDialogCustom.dismissProgressDialog();
//                    }
//                });
    }

}