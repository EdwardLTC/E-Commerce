package com.edward.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class OnboardingActivity extends AppCompatActivity {
private TextView tvSkip;
private ViewPager viewPager;


private ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);


   initUI();

   viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),);

   viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
       @Override
       public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

       }

       @Override
       public void onPageSelected(int position) {
          if (position==2){
              tvSkip.setVisibility(View.GONE);

          }else {
              tvSkip.setVisibility(View.VISIBLE);
          }
       }

       @Override
       public void onPageScrollStateChanged(int state) {

       }
   });
    }
    private void initUI(){
       tvSkip=findViewById(R.id.tv_skip);
        viewPager=findViewById(R.id.view_pager);
    tvSkip.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            viewPager.setCurrentItem(2);
        }
    });
    }
}