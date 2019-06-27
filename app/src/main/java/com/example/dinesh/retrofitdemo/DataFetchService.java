package com.example.dinesh.retrofitdemo;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;

// Created by Dinesh Kumar on 6/26/2019
public interface DataFetchService {
    @GET("/todos")
    public Call<List<Data>> getDataList();

    @GET("/getData")
    public Single<List<Data>> getDataLists();
}
