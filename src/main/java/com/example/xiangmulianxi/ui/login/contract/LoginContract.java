package com.example.xiangmulianxi.ui.login.contract;


import com.example.xiangmulianxi.bean.UserBean;
import com.example.xiangmulianxi.ui.base.BaseContract;

public interface LoginContract {
    interface View extends BaseContract.BaseView {
        void loginSuccess(UserBean userBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void login(String mobile, String password);
    }
}
