package com.nit.demo;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerAdapter_Alarm extends RecyclerView.Adapter<RecyclerAdapter.RecyclerAdapterViewHolder> {
    private Context mContext;
    public RecyclerAdapter_Alarm(AlarmShow addAlarm) {
    }

    @NonNull
    @Override
    public RecyclerAdapter.RecyclerAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.RecyclerAdapterViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
