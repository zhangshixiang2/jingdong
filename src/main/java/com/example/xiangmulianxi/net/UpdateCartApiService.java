package com.example.xiangmulianxi.net;



import com.example.xiangmulianxi.bean.BaseBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UpdateCartApiService {
    @FormUrlEncoded
    @POST("product/updateCarts")
    Observable<BaseBean> updateCarts(@Field("uid") String uid,
                                     @Field("sellerid") String sellerid,
                                     @Field("pid") String pid,
                                     @Field("num") String num,
                                     @Field("selected") String selected,
                                     @Field("token") String token);

}
