package com.example.xiangmulianxi.ui.classify.contract;


import com.example.xiangmulianxi.ui.base.BaseContract;

public interface AddCartContract {
    interface View extends BaseContract.BaseView {
        void onSuccess(String str);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void addCart(String uid, String pid, String token);
    }
}
