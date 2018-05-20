package com.example.xiangmulianxi.ui.classify;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.StrikethroughSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xiangmulianxi.R;
import com.example.xiangmulianxi.bean.AdBean;
import com.example.xiangmulianxi.bean.ProductsBean;
import com.example.xiangmulianxi.component.DaggerHttpComponent;
import com.example.xiangmulianxi.ui.base.BaseActivity;
import com.example.xiangmulianxi.ui.classify.contract.AddCartContract;
import com.example.xiangmulianxi.ui.classify.presenter.AddCartPresenter;
import com.example.xiangmulianxi.ui.login.LoginActivity;
import com.example.xiangmulianxi.ui.shopcart.ShopCartActivity;
import com.example.xiangmulianxi.utils.GlideImageLoader;
import com.example.xiangmulianxi.utils.SharedPreferencesUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.Arrays;


public class ListDetailsActivity extends BaseActivity<AddCartPresenter> implements View.OnClickListener,
        AddCartContract.View {

    private ProductsBean.DataBean bean;
    private Banner mBanner;
    private TextView mTvTitle;
    private TextView mTvPrice;
    private TextView mTvVipPrice;
    private ImageView mIvShare;
    /**
     * 购物车
     */
    private TextView mTvShopCard;
    /**
     * 加入购物车
     */
    private TextView mTvAddCard;
    private AdBean.TuijianBean.ListBean listBean;
    private int flag;
    private String images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_details);

        //获取JavaBean
        Intent intent = getIntent();
        flag = intent.getIntExtra("flag", -1);
        if (flag == -1) {
            return;
        }
        if (flag == ListActivity.LISTACTIVITY) {
            bean = (ProductsBean.DataBean) intent.getSerializableExtra("bean");
            images = bean.getImages();
        } else {
            listBean = (AdBean.TuijianBean.ListBean) intent.getSerializableExtra("bean");
            images = listBean.getImages();
        }


        initView();
        //设置值
        setData();
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_list_details;
    }

    private void initView() {
        mBanner = (Banner) findViewById(R.id.banner);
        mTvTitle = (TextView) findViewById(R.id.tvTitle);
        mTvPrice = (TextView) findViewById(R.id.tvPrice);
        mTvVipPrice = (TextView) findViewById(R.id.tvVipPrice);
        mIvShare = (ImageView) findViewById(R.id.ivShare);
        mIvShare.setOnClickListener(this);
        mTvShopCard = (TextView) findViewById(R.id.tvShopCard);
        mTvShopCard.setOnClickListener(this);
        mTvAddCard = (TextView) findViewById(R.id.tvAddCard);
        mTvAddCard.setOnClickListener(this);
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    /**
     * 设置值
     */
    private void setData() {
        int money = 0;
        if (flag == ListActivity.LISTACTIVITY) {
            money = bean.getSalenum();
        } else {
            money = listBean.getSalenum();
        }



        SpannableString spannableString = new SpannableString("原价:" + money);
        StrikethroughSpan strikethroughSpan = new StrikethroughSpan();
        spannableString.setSpan(strikethroughSpan, 0, spannableString.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        mTvPrice.setText(spannableString);

        mBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Intent intent = new Intent(ListDetailsActivity.this, BannerDetailsActivity.class);
                intent.putExtra("imgs", images);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });





        mBanner.setImageLoader(new GlideImageLoader());
        String[] imgs = null;

        if (flag == ListActivity.LISTACTIVITY) {
            imgs = bean.getImages().split("\\|");
            mTvTitle.setText(bean.getTitle());
            mTvVipPrice.setText("现价：" + bean.getPrice());
        } else {
            imgs = listBean.getImages().split("\\|");
            mTvTitle.setText(listBean.getTitle());
            mTvVipPrice.setText("现价：" + listBean.getPrice());

        }


        mBanner.setImages(Arrays.asList(imgs));
        mBanner.start();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvAddCard:
                //先判断是否登录
                String token = (String) SharedPreferencesUtils.getParam(ListDetailsActivity.this, "token", "");
                if (TextUtils.isEmpty(token)) {
                    //还未登录
                    //跳转到登录页面
                    Intent intent = new Intent(ListDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    //登录过了
                    String uid = (String) SharedPreferencesUtils.getParam(ListDetailsActivity.this, "uid", "");
                    int pid = 0;
                    if (flag == ListActivity.LISTACTIVITY) {
                        pid = bean.getPid();
                    } else {
                        pid = listBean.getPid();
                    }
                    mPresenter.addCart(uid, pid + "", token);
                }
                break;
            case R.id.tvShopCard:
                //跳转到购物车
                Intent intent = new Intent(ListDetailsActivity.this, ShopCartActivity.class);
                startActivity(intent);
                break;
        }
    }



    @Override
    public void onSuccess(String str) {
        Toast.makeText(ListDetailsActivity.this, str, Toast.LENGTH_LONG).show();
    }
}

