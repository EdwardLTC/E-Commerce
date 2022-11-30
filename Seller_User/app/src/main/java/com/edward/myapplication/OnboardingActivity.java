package com.edward.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class OnboardingActivity extends AppCompatActivity {
    private TextView tvSkip;
    private ViewPager2 viewPager;
    private RelativeLayout layout_button;
    private LinearLayout layout_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        initUI();
        ViewPagerAdapter  viewPagerAdapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(viewPagerAdapter);

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
                }
            }
        });
    }
}