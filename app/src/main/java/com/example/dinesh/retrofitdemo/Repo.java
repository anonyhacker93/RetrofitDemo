package com.example.dinesh.retrofitdemo;

import android.app.Application;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

// Created by Dinesh Kumar on 6/26/2019

public class Repo {
    static private Repo _instance;
    private static Retrofit retrofit;
    private static final String url = "https:10.0.2.2:5000/";

    public static Repo getInstance(Application application) {
        if (_instance == null) {
            _instance = new Repo();
        }
        retrofit = getRetrofit();
        return _instance;
    }


  /*  public Observable<List<Data>> getObservable() {
        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        DataFetchService dataFetchService = retrofit.create(DataFetchService.class);
        Call<List<Data>> listObservable = dataFetchService.getDataList();
        return listObservable;
    }*/

    public Call<List<Data>> getCallable() {

        DataFetchService dataFetchService = retrofit.create(DataFetchService.class);
        Call<List<Data>> listCall = dataFetchService.getDataList();
        return listCall;
    }

    public Single<List<Data>> getSingleObservable() {
        DataFetchService dataFetchService = retrofit.create(DataFetchService.class);
        return dataFetchService.getDataLists();
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
