package com.example.xiangmulianxi.net;



import com.example.xiangmulianxi.bean.ProductCatagoryBean;

import io.reactivex.Observable;

public class ProductCatagoryApi {
    private static ProductCatagoryApi productCatagoryApi;
    private ProductCatagoryApiService productCatagoryApiService;

    private ProductCatagoryApi(ProductCatagoryApiService productCatagoryApiService) {
        this.productCatagoryApiService = productCatagoryApiService;
    }

    public static ProductCatagoryApi getProductCatagoryApi(ProductCatagoryApiService productCatagoryApiService) {
        if (productCatagoryApi == null) {
            productCatagoryApi = new ProductCatagoryApi(productCatagoryApiService);
        }
        return productCatagoryApi;
    }

    public Observable<ProductCatagoryBean> getProductCatagory(String cid) {
        return productCatagoryApiService.getProductCatagory(cid);
    }
}
