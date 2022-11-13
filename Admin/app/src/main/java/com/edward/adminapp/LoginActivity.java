package com.edward.adminapp;

import static com.edward.adminapp.API.ServiceAPI.BASE_SERVICE;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.edward.adminapp.API.ServiceAPI;
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
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    private Button btLogin;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DemoCallAPI();

        btLogin = findViewById(R.id.btLogin);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
    }

    private void DemoCallAPI() {
        ServiceAPI.serviceApi.GetAllPerson()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetListPerson>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //progress dialog
                    }

                    @Override
                    public void onNext(ResGetListPerson resGetListPerson) {
                        int k = resGetListPerson._PersonRes.size();
                        Log.e(String.valueOf(k), "onNext: ");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(e.getMessage(),"log");
                        Log.e("Ngu", "Log");
                    }

                    @Override
                    public void onComplete() {
                        //dismiss
                    }
                });
    }

}