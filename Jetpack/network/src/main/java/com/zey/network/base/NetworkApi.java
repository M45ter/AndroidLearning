package com.zey.network.base;

import android.widget.ListAdapter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zey.network.Interceptor.HttpLoggerInterceptor;

import java.util.HashMap;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class NetworkApi {
    private String mBaseUrl;
    private static HashMap<String, Retrofit> retrofitHashMap = new HashMap<>();

    protected NetworkApi() {
        mBaseUrl = getBaseUrl();
    }

    protected Retrofit getRetrofit(Class service) {
        if (retrofitHashMap.get(mBaseUrl + service.getName()) != null) {
            return retrofitHashMap.get(mBaseUrl + service.getName());
        }
        Gson gson = new GsonBuilder()
                .serializeNulls()
                .create();
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder();
        retrofitBuilder.baseUrl(mBaseUrl)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
        Retrofit retrofit = retrofitBuilder.build();
        retrofitHashMap.put(mBaseUrl + service.getName(), retrofit);
        return retrofit;
    }

    protected OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        if (getInterceptor() != null) {
            builder.addInterceptor(getInterceptor());
        }

        HttpLoggerInterceptor httpLoggingInterceptor = new HttpLoggerInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggerInterceptor.Level.BODY);
        builder.addNetworkInterceptor(httpLoggingInterceptor);

        return builder.build();
    }

    abstract String getBaseUrl();

    abstract Interceptor getInterceptor();
}
