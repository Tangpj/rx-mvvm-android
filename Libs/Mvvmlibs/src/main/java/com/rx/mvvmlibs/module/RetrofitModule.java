package com.rx.mvvmlibs.module;

import android.databinding.ObservableField;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.bind.TypeAdapters;
import com.rx.mvvmlibs.internal.ObservableFieldTypeAdapter;
import com.rx.mvvmlibs.network.BaseParamsInterceptor;
import com.rx.mvvmlibs.scope.RetrofitScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @ClassName: RetrofitModule
 * @author create by Tang
 * @date date 16/11/11 上午11:27
 * @Description: TODO
 */

@Module
public class RetrofitModule {

    private String serverUrl;

    private int timeout;

    private Gson mGson;

    private BaseParamsInterceptor.Builder interceptorBuilder;

    public RetrofitModule(String url,int timeout,Gson gson){
        this.serverUrl = url;
        this.timeout = timeout;
        this.mGson = gson;

    }

    @RetrofitScope
    @Provides
    public BaseParamsInterceptor.Builder providesInterceptorBuilder(){
        interceptorBuilder = new BaseParamsInterceptor.Builder();
        return interceptorBuilder;
    }

    @RetrofitScope
    @Provides
    public Retrofit providesRetrofit(OkHttpClient okHttpClient){
        return new Retrofit.Builder().baseUrl(serverUrl)
                .addConverterFactory(initGsonConverterFactory())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

    }

    @RetrofitScope
    @Provides
    public OkHttpClient initOkHttpClient(BaseParamsInterceptor.Builder builder){
        return new OkHttpClient.Builder()
                .addNetworkInterceptor(builder.build())
                .retryOnConnectionFailure(true)
                .connectTimeout(timeout, TimeUnit.SECONDS)
                .build();
    }


    private GsonConverterFactory initGsonConverterFactory(){
        return GsonConverterFactory.create(mGson);
    }



}
