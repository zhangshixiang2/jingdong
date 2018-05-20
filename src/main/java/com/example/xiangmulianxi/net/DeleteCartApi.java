package com.example.xiangmulianxi.net;



import com.example.xiangmulianxi.bean.BaseBean;

import io.reactivex.Observable;

public class DeleteCartApi {
    private static DeleteCartApi deleteCartApi;
    private DeleteCartApiService deleteCartApiService;

    private DeleteCartApi(DeleteCartApiService deleteCartApiService) {
        this.deleteCartApiService = deleteCartApiService;
    }

    public static DeleteCartApi getDeleteCartApi(DeleteCartApiService deleteCartApiService) {
        if (deleteCartApi == null) {
            deleteCartApi = new DeleteCartApi(deleteCartApiService);
        }
        return deleteCartApi;
    }

    public Observable<BaseBean> deleteCart(String uid, String pid,
                                           String token) {
        return deleteCartApiService.deleteCart(uid, pid, token);
    }
}
