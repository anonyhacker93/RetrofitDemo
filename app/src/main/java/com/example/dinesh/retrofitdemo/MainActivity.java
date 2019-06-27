package com.example.dinesh.retrofitdemo;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private DataRecyclerViewAdapter adapter;
    private List<Data> _dataList;
    private MainActivityViewModel mainActivityViewModel;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        setAdapter();
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Please keep patience");
        progressDialog.show();
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        mainActivityViewModel.getMutableLiveData().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> dataList) {
                Log.i("DineshTag", "MainActivity Data size : " + dataList.size());
                progressDialog.hide();
                _dataList.clear();
                _dataList.addAll(dataList);
                adapter.notifyDataSetChanged();

            }
        });
    }

    private void setAdapter() {
        _dataList = new ArrayList<>();
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        adapter = new DataRecyclerViewAdapter(_dataList);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainActivityViewModel.onDestoy();
    }
}
