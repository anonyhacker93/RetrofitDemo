package com.example.dinesh.retrofitdemo.api;

import com.example.dinesh.retrofitdemo.model.Data;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

// Created by Dinesh Kumar on 6/26/2019
public interface DataFetchService {
    @GET("/getData")
    public Single<List<Data>> getDataLists();
}
