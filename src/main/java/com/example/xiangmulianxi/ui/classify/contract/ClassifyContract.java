package com.example.xiangmulianxi.ui.classify.contract;


import com.example.xiangmulianxi.bean.CatagoryBean;
import com.example.xiangmulianxi.bean.ProductCatagoryBean;
import com.example.xiangmulianxi.ui.base.BaseContract;

public interface ClassifyContract {
    interface View extends BaseContract.BaseView {
        void getProductCatagorySuccess(ProductCatagoryBean productCatagoryBean);

        void getCatagorySuccess(CatagoryBean catagoryBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProductCatagory(String cid);

        void getCatagory();
    }
}
