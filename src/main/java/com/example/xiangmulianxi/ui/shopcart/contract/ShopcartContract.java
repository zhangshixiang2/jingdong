package com.example.xiangmulianxi.ui.shopcart.contract;


import com.example.xiangmulianxi.bean.GetCartsBean;
import com.example.xiangmulianxi.bean.SellerBean;
import com.example.xiangmulianxi.ui.base.BaseContract;

import java.util.List;

public interface ShopcartContract {
    interface View extends BaseContract.BaseView {
        void showCartList(List<SellerBean> groupList, List<List<GetCartsBean.DataBean.ListBean>> childList);

        void updateCartsSuccess(String msg);

        void deleteCartSuccess(String msg);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void getCarts(String uid, String token);

        void updateCarts(String uid, String sellerid, String pid, String num, String selected, String token);

        void deleteCart(String uid, String pid, String token);
    }
}
