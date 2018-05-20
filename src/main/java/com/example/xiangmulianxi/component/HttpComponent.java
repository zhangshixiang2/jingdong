package com.example.xiangmulianxi.component;

import com.example.xiangmulianxi.module.HttpModule;

import com.example.xiangmulianxi.ui.classify.ClassifyFragment;
import com.example.xiangmulianxi.ui.classify.ListActivity;
import com.example.xiangmulianxi.ui.classify.ListDetailsActivity;
import com.example.xiangmulianxi.ui.homepage.HomePageFragment;
import com.example.xiangmulianxi.ui.login.LoginActivity;
import com.example.xiangmulianxi.ui.mine.MakeSureOrderActivity;
import com.example.xiangmulianxi.ui.shopcart.ShopCartActivity;


import dagger.Component;

@Component(modules = HttpModule.class)
public interface HttpComponent {
    void inject(LoginActivity loginActivity);

    void inject(HomePageFragment homePageFragment);

    void inject(ListActivity listActivity);

    void inject(ListDetailsActivity listDetailsActivity);

    void inject(ShopCartActivity shopCartActivity);

    void inject(MakeSureOrderActivity makeSureOrderActivity);

    void inject(ClassifyFragment classifyFragment);
}
