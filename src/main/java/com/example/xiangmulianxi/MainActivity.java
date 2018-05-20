package com.example.xiangmulianxi;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.xiangmulianxi.ui.base.BaseActivity;
import com.example.xiangmulianxi.ui.classify.ClassifyFragment;
import com.example.xiangmulianxi.ui.homepage.HomePageFragment;


public class MainActivity extends BaseActivity {

    private FrameLayout mFl;
    private RadioButton mRbHomepage;
    private RadioButton mRbClass;
    private RadioButton mRbFind;
    private RadioButton mRbShopCar;
    private RadioButton mRbMine;
    private RadioGroup mRg;
    private FrameLayout mFlContent;
    private FragmentManager fragmentManager;
    private HomePageFragment homePageFragment;
    private int currentIndex = 1;
    private ClassifyFragment classifyFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        fragmentManager = getSupportFragmentManager();
        homePageFragment = new HomePageFragment();
        classifyFragment = new ClassifyFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.flContent, homePageFragment)
                .commit();
        mRbHomepage.setChecked(true);

        setLisenter();
    }

    private void setLisenter() {
        mRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbHomepage:
                        //首页
                        if (currentIndex == 1) {
                            return;
                        }
                        currentIndex = 1;
                        fragmentManager.beginTransaction().replace(R.id.flContent, homePageFragment).commit();
                        break;
                    case R.id.rbClass:
                        if (currentIndex == 2) {
                            return;
                        }
                        currentIndex = 2;
                        fragmentManager.beginTransaction().replace(R.id.flContent, classifyFragment).commit();
                        break;
                    case R.id.rbFind:
                        if (currentIndex == 3) {
                            return;
                        }
                        currentIndex = 3;
                        break;
                    case R.id.rbShopCar:
                        if (currentIndex == 4) {
                            return;
                        }
                        currentIndex = 4;
                        break;
                    case R.id.rbMine:
                        if (currentIndex == 5) {
                            return;
                        }
                        currentIndex = 5;
                        break;
                }
            }
        });
    }


    private void initView() {
        mFl = (FrameLayout) findViewById(R.id.flContent);
        mRbHomepage = (RadioButton) findViewById(R.id.rbHomepage);
        mRbClass = (RadioButton) findViewById(R.id.rbClass);
        mRbFind = (RadioButton) findViewById(R.id.rbFind);
        mRbShopCar = (RadioButton) findViewById(R.id.rbShopCar);
        mRbMine = (RadioButton) findViewById(R.id.rbMine);
        mRg = (RadioGroup) findViewById(R.id.rg);
        mFlContent = (FrameLayout) findViewById(R.id.flContent);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void inject() {

    }
}
