package com.example.xiangmulianxi.net;


import com.example.xiangmulianxi.bean.AdBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AdApiService {
   
    @GET("ad/getAd")
    Observable<AdBean> getAd();
}
