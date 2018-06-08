package com.exchange.extchange.network;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class NBPApiClient {

    private final NBPService nbpService;

    private static NBPApiClient instance;

    private NBPApiClient(String url) {
        this.nbpService = createRetrofit(url).create(NBPService.class);
    }


    private Retrofit createRetrofit(String url) {
        return new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }


    public Single<CoursesTableResponse> getCourserForTableA() {
        return nbpService.getCoursesForTableA()
                .map((responses) -> responses.get(0));
    }


    public synchronized static void init(String url) {
        if (instance == null) {
            instance = new NBPApiClient(url);
        }
    }

    public synchronized static NBPApiClient getInstance() {
        return instance;
    }
}
