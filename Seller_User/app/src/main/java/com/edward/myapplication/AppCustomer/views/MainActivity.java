package com.edward.myapplication.AppCustomer.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edward.myapplication.AppCustomer.fragments.HomeFragment;
import com.edward.myapplication.LoginActivity;
import com.edward.myapplication.R;
import com.edward.myapplication.api.ServiceAPI;
import com.edward.myapplication.model.modelrespon.PersonRes;
import com.edward.myapplication.model.modelrespon.ResGetPerson;
import com.google.android.material.navigation.NavigationView;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {



    DrawerLayout drawerLayout;
    LinearLayout content;
    CircleImageView ivAvatar;
    TextView tvName, tvEmail;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        drawerLayout = findViewById(R.id.drawerLayout);
        content = findViewById(R.id.content);
        ivAvatar = findViewById(R.id.ivAvatar);
        tvName = findViewById(R.id.tvName);
        tvEmail = findViewById(R.id.tvEmail);



        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close) {
            private float scaleFactor = 6f;
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                float slideX = drawerView.getWidth() * slideOffset;
                content.setBackgroundResource(R.drawable.style_white);
                content.setElevation(20);
                content.setTranslationX(slideX);
                content.setScaleX(1 - (slideOffset / scaleFactor));
                content.setScaleY(1 - (slideOffset / scaleFactor));
            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void onDrawerClosed(@NonNull View view) {
                content.setElevation(0);
                content.setBackgroundResource(R.color.white_blur);
            }
        };
        drawerLayout.setScrimColor(Color.TRANSPARENT);
        drawerLayout.setDrawerElevation(0f);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                switch (id) {
                    case R.id.log_out:
                        PopupDialog.getInstance(MainActivity.this)
                                .setStyle(Styles.IOS)
                                .setHeading("Confirm logout")
                                .setDescription("Are you sure you want to log out?")
                                .setPositiveButtonText("OK")
                                .setPositiveButtonTextColor(R.color.red_blur)
                                .setCancelable(false)
                                .showDialog(new OnDialogButtonClickListener() {
                                    @Override
                                    public void onPositiveClicked(Dialog dialog) {
                                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                                        super.onPositiveClicked(dialog);
                                    }

                                    @Override
                                    public void onNegativeClicked(Dialog dialog) {
                                        super.onNegativeClicked(dialog);
                                    }
                                });
                        break;

                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        navigationView.setCheckedItem(R.id.home_page);
        loadFragment(new HomeFragment());

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        fillValue();

    }

    private void fillValue() {

        ServiceAPI.serviceApi.GetPersonWhere(LoginActivity.PERSONRES.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResGetPerson>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResGetPerson resGetPerson) {
                        PersonRes personRes = resGetPerson._PersonRes;

                        Glide.with(MainActivity.this).load(personRes.getImgUrl()).into(ivAvatar);
                        tvName.setText(personRes.getName());
                        tvEmail.setText(personRes.getMail());

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
