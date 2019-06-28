package com.example.dinesh.retrofitdemo.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dinesh.retrofitdemo.R;
import com.example.dinesh.retrofitdemo.databinding.LayoutRowBinding;
import com.example.dinesh.retrofitdemo.model.Data;

import java.util.List;

// Created by Dinesh Kumar on 6/26/2019
public class DataRecyclerViewAdapter extends RecyclerView.Adapter<DataRecyclerViewAdapter.MyViewHolder> {
    private List<Data> mDataList;

    public DataRecyclerViewAdapter(List<Data> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        LayoutRowBinding rowBinding = DataBindingUtil.inflate(inflater, R.layout.layout_row, parent, false);
        return new MyViewHolder(rowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data data = mDataList.get(position);
        holder.rowBinding.setDataInfo(data);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        LayoutRowBinding rowBinding;


        public MyViewHolder(LayoutRowBinding v) {
            super(v.getRoot());
            rowBinding = v;
        }
    }
}
