package com.example.xiangmulianxi.ui.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.dash.zxinglibrary.activity.CaptureActivity;
import com.dash.zxinglibrary.activity.CodeUtils;
import com.example.xiangmulianxi.R;
import com.example.xiangmulianxi.WebViewActivity;
import com.example.xiangmulianxi.bean.AdBean;
import com.example.xiangmulianxi.bean.CatagoryBean;

import com.example.xiangmulianxi.component.DaggerHttpComponent;
import com.example.xiangmulianxi.inter.OnItemClickListener;
import com.example.xiangmulianxi.module.HttpModule;
import com.example.xiangmulianxi.ui.base.BaseFragment;
import com.example.xiangmulianxi.ui.classify.ListDetailsActivity;
import com.example.xiangmulianxi.ui.homepage.adapter.RvClassAdapter;
import com.example.xiangmulianxi.ui.homepage.adapter.RvRecommendAdapter;
import com.example.xiangmulianxi.ui.homepage.adapter.RvSecKillAdapter;
import com.example.xiangmulianxi.ui.homepage.contract.HomPageContract;
import com.example.xiangmulianxi.ui.homepage.presenter.HomePagePresenter;
import com.example.xiangmulianxi.utils.GlideImageLoader;
import com.sunfusheng.marqueeview.MarqueeView;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

public class HomePageFragment extends BaseFragment<HomePagePresenter> implements HomPageContract.View, View
        .OnClickListener {
    private Banner banner;
    private RecyclerView rvClass;
    private MarqueeView marqueeView;
    private RecyclerView rvSecKill;
    private RecyclerView rvRecommend;
    private ImageView ivZxing;
    public static final int HOMEPAGE_FRAGMENT = 0;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .httpModule(new HttpModule())
                .build()
                .inject(this);
    }

    @Override
    public void initView(View view) {
        marqueeView = view.findViewById(R.id.marqueeView);
        List<String> info = new ArrayList<>();
        info.add("1. 大家好，我是孙福生。");
        info.add("2. 欢迎大家关注我哦！");
        info.add("3. GitHub帐号：sfsheng0322");
        info.add("4. 新浪微博：孙福生微博");
        info.add("5. 个人博客：sunfusheng.com");
        info.add("6. 微信公众号：孙福生");
        marqueeView.startWithList(info);

        banner = (Banner) view.findViewById(R.id.banner);
        rvClass = view.findViewById(R.id.rvClass);
        //设置布局管理器
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.HORIZONTAL, false);
        rvClass.setLayoutManager(gridLayoutManager);

        rvSecKill = view.findViewById(R.id.rvSecKill);
        //设置布局管理器
        GridLayoutManager gridLayoutManager1 = new GridLayoutManager(getContext(), 1, RecyclerView.HORIZONTAL, false);
        rvSecKill.setLayoutManager(gridLayoutManager1);

        //设置布局管理器
        rvRecommend = view.findViewById(R.id.rvRecommend);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        rvRecommend.setLayoutManager(gridLayoutManager2);

        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());

        //二维码
        ivZxing = view.findViewById(R.id.ivZxing);

        //设置点击事件
        setListener();

        //请求数据
        initData();


    }

    private void setListener() {
        ivZxing.setOnClickListener(this);

    }

    /**
     * 请求数据
     */
    private void initData() {
        mPresenter.getAd();
        mPresenter.getCatagory();
    }

    @Override
    public void getAdSuccess(final AdBean adBean) {
        final List<AdBean.DataBean> data = adBean.getData();
        List<String> images = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            images.add(data.get(i).getIcon());
        }
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();

        //给Banner设置点击事件
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                String url = data.get(position).getUrl();
                if (!TextUtils.isEmpty(url)) {
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    intent.putExtra("detailUrl", url);
                    startActivity(intent);
                }
            }
        });


        RvSecKillAdapter rvSecKillAdapter = new RvSecKillAdapter(getActivity(), adBean.getMiaosha().getList());
        rvSecKill.setAdapter(rvSecKillAdapter);
        rvSecKillAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转显示详情
                //获取地址
                String detailUrl = adBean.getMiaosha().getList().get(position).getDetailUrl();
                Intent intent = new Intent(getContext(), WebViewActivity.class);
                intent.putExtra("detailUrl", detailUrl);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });


        RvRecommendAdapter rvRecommendAdapter = new RvRecommendAdapter(getActivity(), adBean.getTuijian().getList());
        rvRecommend.setAdapter(rvRecommendAdapter);

        rvRecommendAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                //跳转到详情页
                Intent intent = new Intent(getActivity(), ListDetailsActivity.class);
                AdBean.TuijianBean.ListBean listBean = adBean.getTuijian().getList().get(position);
                intent.putExtra("flag", HOMEPAGE_FRAGMENT);
                intent.putExtra("bean", listBean);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //结束轮播
        banner.stopAutoPlay();
    }

    @Override
    public void getCatagorySuccess(CatagoryBean catagoryBean) {
        final List<CatagoryBean.DataBean> data = catagoryBean.getData();
        RvClassAdapter rvClassAdapter = new RvClassAdapter(getActivity(), data);
        rvClass.setAdapter(rvClassAdapter);
        rvClassAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(getContext(), data.get(position).getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(int position) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivZxing:
                Intent intent = new Intent(getContext(), CaptureActivity.class);
                startActivityForResult(intent, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && data != null) {
            Bundle bundle = data.getExtras();
            if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                String string = bundle.getString(CodeUtils.RESULT_STRING);
                //拿到最终结果
                //Intent intent = new Intent(getContext(),WebViewActivity.class);

            }
        }
    }
}
