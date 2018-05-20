package com.example.xiangmulianxi.ui.mine.presenter;



import com.example.xiangmulianxi.bean.AddrsBean;
import com.example.xiangmulianxi.bean.BaseBean;
import com.example.xiangmulianxi.net.AddrsApi;
import com.example.xiangmulianxi.net.CreateOrderApi;
import com.example.xiangmulianxi.ui.base.BasePresenter;
import com.example.xiangmulianxi.ui.mine.contract.MakeSureOrderContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MakeSureOrderPresenter extends BasePresenter<MakeSureOrderContract.View> implements
        MakeSureOrderContract.Presenter {
    private AddrsApi addrsApi;
    private CreateOrderApi createOrderApi;

    @Inject
    public MakeSureOrderPresenter(AddrsApi addrsApi, CreateOrderApi createOrderApi) {
        this.addrsApi = addrsApi;
        this.createOrderApi = createOrderApi;
    }

    @Override
    public void getAddrs(String uid, String token) {
        addrsApi.getAddrs(uid, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<AddrsBean, List<AddrsBean.DataBean>>() {
                    @Override
                    public List<AddrsBean.DataBean> apply(AddrsBean addrsBean) throws Exception {
                        return addrsBean.getData();
                    }
                }).subscribe(new Consumer<List<AddrsBean.DataBean>>() {
            @Override
            public void accept(List<AddrsBean.DataBean> dataBeans) throws Exception {
                if (mView != null) {
                    mView.addrsSuccess(dataBeans);
                }
            }
        });

    }

    @Override
    public void createOrder(String uid, String price, String token) {
        createOrderApi.getCatagory(uid, price, token)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<BaseBean, String>() {

                    @Override
                    public String apply(BaseBean baseBean) throws Exception {
                        return baseBean.getMsg();
                    }
                }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                if (mView != null) {
                    mView.createOrderSuccess(s);
                }
            }
        });
    }
}
