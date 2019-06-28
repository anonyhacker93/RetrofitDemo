package com.example.dinesh.retrofitdemo.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.dinesh.retrofitdemo.model.Data;
import com.example.dinesh.retrofitdemo.repository.Repo;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


// Created by Dinesh Kumar on 6/26/2019
public class MainActivityViewModel extends AndroidViewModel {
    private Repo mRepo;
    private Application mApplication;
    private SingleObserver<List<Data>> observer;
    private MutableLiveData<List<Data>> mutableLiveData;
    private Disposable disposable;

    public MainActivityViewModel(Application application) {
        super(application);
        mutableLiveData = new MutableLiveData<>();
        mApplication = application;
        init();
    }

    private void init() {
        Log.i("DineshTag", "View Model init");
        mRepo = Repo.getInstance(mApplication);

        mRepo.getSingleObservable().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Data>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.i("DineshTag", "onSubscribe");
                    }

                    @Override
                    public void onSuccess(List<Data> dataList) {
                        Log.i("DineshTag", "onSuccess size:" + dataList.size());
                        mutableLiveData.postValue(dataList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("DineshTag", "getSingleObservable onError");
                    }
                });

    }

    public MutableLiveData<List<Data>> getMutableLiveData() {
        return mutableLiveData;
    }

    public void onDestoy() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

}
