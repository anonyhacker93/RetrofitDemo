package com.example.dinesh.retrofitdemo.repository;

import android.app.Application;

import com.example.dinesh.retrofitdemo.api.DataFetchService;
import com.example.dinesh.retrofitdemo.model.Data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

// Created by Dinesh Kumar on 6/26/2019

public class Repo {
    private static final String url = "https:10.0.2.2:5000/";
    static private Repo _instance;
    private static Retrofit retrofit;

    public static Repo getInstance(Application application) {
        if (_instance == null) {
            _instance = new Repo();
        }
        retrofit = getRetrofit();
        return _instance;
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

    public Single<List<Data>> getSingleObservable() {
        DataFetchService dataFetchService = retrofit.create(DataFetchService.class);
        return dataFetchService.getDataLists();
    }
}
