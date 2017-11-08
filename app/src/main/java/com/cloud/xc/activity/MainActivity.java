package com.cloud.xc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cloud.xc.R;
import com.cloud.xc.adapter.MainPagerAdapter;
import com.cloud.xc.annotation.CloudContentView;
import com.cloud.xc.annotation.CloudFindView;
import com.cloud.xc.annotation.CloudViewInject;
import com.cloud.xc.fragment.Fragment1;
import com.cloud.xc.fragment.Fragment2;
import com.cloud.xc.fragment.Fragment3;
import com.cloud.xc.fragment.Fragment4;
import com.cloud.xc.view.BottomNavigationView.BottomNavigationView;
import com.cloud.xc.view.BottomNavigationView.OnBottomNavigationItemClickListener;
import com.cloud.xc.view.TitleBar;

import java.util.ArrayList;
import java.util.List;

@CloudContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements Fragment4.OnTitleChangeListener {

    @CloudFindView(R.id.titleBar)
    private TitleBar titleBar;
    @CloudFindView(R.id.viewPager)
    private ViewPager viewPager;
    @CloudFindView(R.id.bottomNavigation)
    private BottomNavigationView bottomNavigationView;

    private MainPagerAdapter mMainPagerAdapter;
    private ImageView rightbtn;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CloudViewInject.inject(this);
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNavigation);
        initTitleBar();
        initFragment();
        initBottomView();
    }

    private void initTitleBar() {
        titleBar.setLeftImageResource(R.mipmap.back);
        titleBar.setLeftText("返回");
        titleBar.setLeftTextColor(getResources().getColor(R.color.xc_white));
        titleBar.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent().setClass(MainActivity.this, DeviceInfoActivity.class));
            }
        });

        titleBar.setTitle("标题");
        titleBar.setTitleColor(getResources().getColor(R.color.xc_white));
        titleBar.setSubTitleColor(getResources().getColor(R.color.xc_white));
        titleBar.setDividerColor(getResources().getColor(R.color.xc_white));
        titleBar.setActionTextColor(getResources().getColor(R.color.xc_white));
        rightbtn = (ImageView) titleBar.addAction(new TitleBar.ImageAction(R.mipmap.rightbtn) {
            @Override
            public void performAction(View view) {
                Toast.makeText(MainActivity.this, "右侧", Toast.LENGTH_SHORT).show();
                rightbtn.setImageResource(R.mipmap.rightbtn);
                titleBar.setTitle("右侧");
            }
        });

        titleBar.addAction(new TitleBar.TextAction("发布") {
            @Override
            public void performAction(View view) {
                startActivity(new Intent().setClass(MainActivity.this, BottomViewActivity.class));
            }
        });
        titleBar.setImmersive(true);
    }

    private void initFragment() {
        String[] title = {"铃铛", "麋鹿", "红酒", "圣诞"};
        Fragment4 fragment4 = new Fragment4();
        fragment4.setOnTitleChangerListener(this);
        mFragments.add(new Fragment1());
        mFragments.add(new Fragment2());
        mFragments.add(new Fragment3());
        mFragments.add(fragment4);
        mMainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager(), mFragments, title);
        viewPager.setAdapter(mMainPagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initBottomView() {
        int[] image = {R.mipmap.icon1, R.mipmap.icon2, R.mipmap.icon3, R.mipmap.icon4};
        int[] color = {ContextCompat.getColor(this, R.color.xc_green), ContextCompat.getColor(this, R.color.xc_blue),
                ContextCompat.getColor(this, R.color.xc_gray), ContextCompat.getColor(this, R.color.xc_red)};
        if (bottomNavigationView != null) {
            bottomNavigationView.isWithText(false);
            bottomNavigationView.isColoredBackground(true);
            bottomNavigationView.disableShadow();
        }
        bottomNavigationView.setUpWithViewPager(viewPager, color, image);
        bottomNavigationView.setOnBottomNavigationItemClickListener(new OnBottomNavigationItemClickListener() {
            @Override
            public void onNavigationItemClick(int index) {
                viewPager.setCurrentItem(index);
            }
        });
    }

    @Override
    public void changeTitle(String title) {
        titleBar.setTitle(title);
    }
}
