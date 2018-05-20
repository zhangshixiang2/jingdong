package com.example.xiangmulianxi.ui.homepage.contract;


import com.example.xiangmulianxi.bean.AdBean;
import com.example.xiangmulianxi.bean.CatagoryBean;
import com.example.xiangmulianxi.ui.base.BaseContract;

public interface HomPageContract {
    interface View extends BaseContract.BaseView {
        void getAdSuccess(AdBean adBean);

        void getCatagorySuccess(CatagoryBean catagoryBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getAd();

        void getCatagory();
    }

}
