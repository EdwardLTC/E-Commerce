package com.edward.myapplication.AppSeller.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.edward.myapplication.AppSeller.adapters.SellerStatisicAdapter;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.ProgressDialogCustom;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.ClothesStatisticalRes;
import com.edward.myapplication.model.modelrespon.ResGetStatistical;
import com.edward.myapplication.model.modelrespon.ResSellerIncome;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class StatisticActivity extends AppCompatActivity {

    CardView cvBackToHomeFromSellerStatistic;
    TextView tvSellerIncome;
    RecyclerView rcvSellerStatistic;
    SellerStatisicAdapter sellerStatisicAdapter;
    List<ClothesStatisticalRes> ls;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staticstic);
        initViews();
        initRecyclerView();
        loadSellerStatisticList();

        ServiceAPI.serviceApi.SellerIncome(LoginActivity.PERSONRES.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResSellerIncome>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResSellerIncome resSellerIncome) {
                        if (resSellerIncome._Respon.getRespone_code() == 200) {
                            tvSellerIncome.setText(resSellerIncome._Income);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                    }
                });

        cvBackToHomeFromSellerStatistic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(StatisticActivity.this, SellerDashboardActivity.class));
            }
        });
    }

    private void initViews() {
        cvBackToHomeFromSellerStatistic = findViewById(R.id.cvBackToHomeFromSellerStatistic);
        tvSellerIncome = findViewById(R.id.tvSellerIncome);
        rcvSellerStatistic = findViewById(R.id.rcvSellerStatistic);
    }

    private void initRecyclerView() {
        rcvSellerStatistic.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rcvSellerStatistic.setLayoutManager(layoutManager);
    }

    private void loadSellerStatisticList() {
        ServiceAPI.serviceApi.GetStatistical()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetStatistical>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        ProgressDialogCustom.showProgressDialog(StatisticActivity.this, "Please wait");
                    }

                    @Override
                    public void onNext(ResGetStatistical resGetStatistical) {
                        if (resGetStatistical._Respon.getRespone_code() == 200) {
                            ls = resGetStatistical.clothesStatisticalRes;
                            sellerStatisicAdapter = new SellerStatisicAdapter(StatisticActivity.this, ls);
                            rcvSellerStatistic.setAdapter(sellerStatisicAdapter);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("TAG: ", "err");
                        ProgressDialogCustom.dismissProgressDialog();
                    }

                    @Override
                    public void onComplete() {
                        ProgressDialogCustom.dismissProgressDialog();

                    }
                });
    }
}