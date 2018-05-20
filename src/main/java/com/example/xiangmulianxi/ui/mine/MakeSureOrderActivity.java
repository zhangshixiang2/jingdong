package com.example.xiangmulianxi.ui.mine;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.xiangmulianxi.R;
import com.example.xiangmulianxi.bean.AddrsBean;
import com.example.xiangmulianxi.bean.GetCartsBean;
import com.example.xiangmulianxi.bean.SellerBean;
import com.example.xiangmulianxi.bean.eventbus.MessageEvent;

import com.example.xiangmulianxi.component.DaggerHttpComponent;
import com.example.xiangmulianxi.ui.base.BaseActivity;
import com.example.xiangmulianxi.ui.mine.adapter.MakeSureOrderAdapter;
import com.example.xiangmulianxi.ui.mine.contract.MakeSureOrderContract;
import com.example.xiangmulianxi.ui.mine.presenter.MakeSureOrderPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

public class MakeSureOrderActivity extends BaseActivity<MakeSureOrderPresenter> implements MakeSureOrderContract.View {

    private ImageView mDetailImageBack;
    private RelativeLayout mDetaiRelative;
    /**
     * 收货人:
     */
    private TextView mTextName;
    private TextView mTextPhone;
    private ImageView mDingWeiImage;
    private TextView mTextAddr;
    private RelativeLayout mRelativeAddr01;
    private ExpandableListView elv;
    /**
     * 实付款:¥0.00
     */
    private TextView mTextShiFuKu;
    /**
     * 提交订单
     */
    private TextView mTextSubmitOrder;
    private LinearLayout mLinearBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        initView();
        //先去获取常用收货地址列表
        if (mPresenter != null) {
            mPresenter.getAddrs(getUid(), getToken());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_make_sure_order;
    }

    @Override
    public void inject() {
        DaggerHttpComponent.builder()
                .build()
                .inject(this);
    }

    private void initView() {
        mDetailImageBack = (ImageView) findViewById(R.id.detail_image_back);
        mDetaiRelative = (RelativeLayout) findViewById(R.id.detai_relative);
        mTextName = (TextView) findViewById(R.id.text_name);
        mTextPhone = (TextView) findViewById(R.id.text_phone);
        mDingWeiImage = (ImageView) findViewById(R.id.ding_wei_image);
        mTextAddr = (TextView) findViewById(R.id.text_addr);
        mRelativeAddr01 = (RelativeLayout) findViewById(R.id.relative_addr_01);
        elv = (ExpandableListView) findViewById(R.id.elv);
        mTextShiFuKu = (TextView) findViewById(R.id.text_shi_fu_ku);
        mTextSubmitOrder = (TextView) findViewById(R.id.text_submit_order);
        mLinearBottom = (LinearLayout) findViewById(R.id.linear_bottom);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessageEvent(final MessageEvent event) {
        /* Do something */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                List<List<GetCartsBean.DataBean.ListBean>> cList = event.getcList();
                List<SellerBean> gList = event.getgList();
                MakeSureOrderAdapter adapter = new MakeSureOrderAdapter(MakeSureOrderActivity.this, gList, cList);
                elv.setAdapter(adapter);
                for (int i = 0; i < gList.size(); i++) {
                    elv.expandGroup(i);
                }
            }
        },1000);


    }


    @Override
    public void addrsSuccess(List<AddrsBean.DataBean> list) {

        if (list != null && list.size() > 0) {
            //如果有数据，说明之前添加过地址
            toast("获取到地址列表");
            mTextName.setText(list.get(0).getName());
            mTextAddr.setText(list.get(0).getAddr());

        } else {
            //如果没有数据，则没有则弹出一个dialog
            onGetDefaultAddrEmpty();
        }

    }

    @Override
    public void createOrderSuccess(String msg) {

    }


    public void onGetDefaultAddrEmpty() {
        //弹出对话框...取消,,,finish/////确定...添加新地址...没添加点击了返回,当前确认订单页面finish,,,如果添加了显示地址
        AlertDialog.Builder builder = new AlertDialog.Builder(MakeSureOrderActivity.this);
        builder.setTitle("提示")
                .setMessage("您还没有默认的收货地址,请设置收货地址")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //结束确认订单显示
                        MakeSureOrderActivity.this.finish();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //确定...跳转添加新地址...如果没有保存地址,确认订单finish,,,
                        //如果保存了地址...数据传回来进行显示(带有回传值的跳转)
                        Intent intent = new Intent(MakeSureOrderActivity.this, AddNewAddrActivity.class);
                        startActivityForResult(intent, 1001);
                    }
                })
                .show();
    }
}
