package com.edward.adminapp;

import static com.edward.adminapp.API.ServiceAPI.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.edward.adminapp.API.ServiceAPI;
import com.edward.adminapp.adapter.ProgressDialogCustom;
import com.edward.adminapp.model.modelrespon.ResGetListPerson;
import com.edward.adminapp.model.modelrespon.ResGetPerson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                DemoCallAPI();
            }
        });
    }


    private void DemoCallAPI() {
        ServiceAPI.serviceApi.Login(edtEmailLogin.getText().toString(), edtPassword.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetPerson>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(LoginActivity.this, "please wait");
                    }

                    @Override
                    public void onNext(ResGetPerson resGetPerson) {
                        if (resGetPerson._Respon.getRespone_code() != 200) {
                            Toast.makeText(LoginActivity.this, "Check username, Psw pls", Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        ProgressDialogCustom.dismissProgressDialog();
                        Toast.makeText(LoginActivity.this, "non null field", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();
                    }
                });
    }

}