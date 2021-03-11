package com.example.netutils;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author 小狼不是哈士奇
 * @date 2021/3/11 10:30
 * @QQ 1481583730
 */
public class RetrofitUtils implements INetInterfaces {
    private static RetrofitUtils retrofitUtils;
    private final ApiServer apiServer;

    private RetrofitUtils() {
        Retrofit build = new Retrofit.Builder()
                .baseUrl(UrlsInterface.BANNERURL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        apiServer = build.create(ApiServer.class);
    }

    public static RetrofitUtils getInstance() {
        if (retrofitUtils == null) {
            synchronized (RetrofitUtils.class) {
                if (retrofitUtils == null) {
                    retrofitUtils = new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }


    @Override
    public <B> void get(String url, INetInterface<B> iNetInterface) {
        apiServer.getData(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull ResponseBody responseBody) {
                        try {
                            String string = responseBody.string();
                            Type[] genericInterfaces = iNetInterface.getClass().getGenericInterfaces();
                            Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
                            Type t = actualTypeArguments[0];
                            Gson gson = new Gson();
                            B result = gson.fromJson(string, t);
                            iNetInterface.setData(result);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        iNetInterface.setError(e + "");
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
