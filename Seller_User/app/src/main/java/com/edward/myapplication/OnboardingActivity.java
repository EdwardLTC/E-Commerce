package com.edward.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrequest.PersonReq;
import com.edward.myapplication.model.modelrespon.Respon;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class OnboardingActivity extends AppCompatActivity {
    private TextView tvSkip;
    private ViewPager2 viewPager;
    private RelativeLayout layout_button;
    private CardView layout_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        initUI();
        ViewPagerAdapter  viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);
//
//        String Name = name.getText().toString();
//        String Phone = phone.getText().toString();
//        String Address = address.getText().toString();
//        String link = "https://www.shareicon.net/data/512x512/2016/05/24/770117_people_512x512.png";
//        PersonReq personReq = new PersonReq(21, "vinh thanh nguyen", "customer@gmail.com",  "09091773333", 3,  link,"108 hong ha p2");
//        ServiceAPI.serviceApi.UpdatePerson(personReq)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<Respon>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        ProgressDialogCustom.showProgressDialog(OnboardingActivity.this, "Please wait");
//                    }
//
//                    @Override
//                    public void onNext(Respon respon) {
//                        if (respon.getRespone_code() == 200) {
//                            PopupDialog.getInstance(OnboardingActivity.this)
//                                    .setStyle(Styles.SUCCESS)
//                                    .setHeading("Well Done")
//                                    .setHeading("You have successfully"+
//                                            " updated")
//                                    .setCancelable(false)
//                                    .showDialog(new OnDialogButtonClickListener() {
//                                        @Override
//                                        public void onDismissClicked(Dialog dialog1) {
//                                            super.onDismissClicked(dialog1);
//                                        }
//                                    });
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        ProgressDialogCustom.dismissProgressDialog();
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        ProgressDialogCustom.dismissProgressDialog();
//                    }
//                });

    }

    private void initUI() {
        tvSkip = findViewById(R.id.tv_skip);
        viewPager = findViewById(R.id.view_pager);
        layout_button=findViewById(R.id.layout_bottom);
        layout_next=findViewById(R.id.layout_next);
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getBaseContext(),LoginActivity.class);
                startActivity(intent);
            }
        });
        layout_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (viewPager.getCurrentItem()<2){
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                } else
                    startActivity(new Intent(OnboardingActivity.this, LoginActivity.class));
            }
        });
    }
}