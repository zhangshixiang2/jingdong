package com.example.xiangmulianxi.ui.classify.contract;



import com.example.xiangmulianxi.bean.ProductsBean;
import com.example.xiangmulianxi.ui.base.BaseContract;

import java.util.List;

public interface ListContract {
    interface View extends BaseContract.BaseView {
        void onSuccess(List<ProductsBean.DataBean> list);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getProducts(String pscid);
    }
}
