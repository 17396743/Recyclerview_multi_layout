package com.example.netutils;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 10:28
 * @QQ 1481583730
 */
public interface ApiServer {
    @GET
    Observable<ResponseBody> getData(@Url String url);
}
