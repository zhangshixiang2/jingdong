package com.example.xiangmulianxi.net;



import com.example.xiangmulianxi.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DeleteCartApiService {
    @FormUrlEncoded
    @POST("product/deleteCart")
    Observable<BaseBean> deleteCart(@Field("uid") String uid,
                                    @Field("pid") String pid,
                                    @Field("token") String token);

}
