package com.example.healthtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class DiaryAdapter extends RecyclerView.Adapter {

    private final String[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public MyViewHolder(@NonNull TextView v) {
        super(v);
        textView = v;
    }
}

public DiaryAdapter(String[] data){

    mDataset = data;
}

@Override
public DiaryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        TextView v = (TextView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);
        
}


}
