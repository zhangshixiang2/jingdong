package com.example.xiangmulianxi.ui.mine.contract;



import com.example.xiangmulianxi.bean.AddrsBean;
import com.example.xiangmulianxi.ui.base.BaseContract;

import java.util.List;

public interface MakeSureOrderContract {
    interface View extends BaseContract.BaseView {
        void addrsSuccess(List<AddrsBean.DataBean> list);

        void createOrderSuccess(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAddrs(String uid, String token);

        void createOrder(String uid, String price, String token);
    }
}
