package com.example.xiangmulianxi.ui.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

;

import com.example.xiangmulianxi.R;
import com.example.xiangmulianxi.bean.ProductsBean;
import com.example.xiangmulianxi.ui.classify.adapter.MyAdapter;
import com.example.xiangmulianxi.ui.widget.HackyViewPager;

import java.util.Arrays;
import java.util.List;

public class BannerDetailsActivity extends AppCompatActivity {

    private HackyViewPager mHvp;
    private TextView mTv;
    private ProductsBean.DataBean bean;
    private List<String> list;
    private int position;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_details);
        //获取JavaBean
        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);
        String images =  intent.getStringExtra("imgs");
        String[] split = images.split("\\|");
        list = Arrays.asList(split);
        count = list.size();
        initView();

    }

    private void initView() {
        mHvp = (HackyViewPager) findViewById(R.id.hvp);
        mTv = (TextView) findViewById(R.id.tv);
        MyAdapter myAdapter = new MyAdapter(this, list);
        mHvp.setAdapter(myAdapter);

        mHvp.setCurrentItem(position);

        mTv.setText((position + 1) + "/" + count);

        mHvp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int p) {
                BannerDetailsActivity.this.position = p;
                mTv.setText((position + 1) + "/" + count);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}
