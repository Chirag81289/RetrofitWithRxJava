package com.example.indianic.retrofitwithrxjava;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <p/>
 * This Wrapper class will be mediator of service interface and calling class.
 * Whatever the features of retrofit and okhttp api will implement, need to be put into this class.
 * We can put common webservice security features to this class so that we can increase the reusability of code.
 */
public class ServiceWrapper {

    private final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private final ServiceInterface service;
    private Interceptor mHeaderInterceptor;

    private static final ServiceWrapper ourInstance = new ServiceWrapper();

    public static ServiceWrapper getInstance() {
        return ourInstance;
    }

    private ServiceWrapper() {
        Retrofit retrofit = getRetrofit(BASE_URL);
        service = retrofit.create(ServiceInterface.class);
    }

    ServiceInterface getService() {
        return service;
    }

    private Retrofit getRetrofit(String url) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(120, TimeUnit.SECONDS)
                .readTimeout(90, TimeUnit.SECONDS);
        if (BuildConfig.DEBUG)
            builder.addInterceptor(interceptor);
        OkHttpClient client = builder.build();

        final Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
    }
}
