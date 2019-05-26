package com.example.healthtracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DiaryAdapter extends RecyclerView.Adapter<DiaryAdapter.DiaryHolder> {

    public static class DiaryHolder extends RecyclerView.ViewHolder {
        public TextView textDate;
        public TextView textTitleAndNumber;
        public TextView textDescription;

        //Do I want to adjust the view a bit? If so, this needs to be updated too.
        public DiaryHolder(@NonNull View v) {
            super(v);
            this.textDate = itemView.findViewById(R.id.text_When);
            this.textTitleAndNumber = itemView.findViewById(R.id.text_Title_And_Number);
            this.textDescription = itemView.findViewById(R.id.text_Description);
        }

        public void setDiary(Diary diary) {
            this.textDate.setText(diary.getTimestamp());
            //TODO: Create resource string here
            this.textTitleAndNumber.setText(diary.getQuantity() + " " + diary.getTitle());
            this.textDescription.setText(diary.getDescription());
        }

    }

    private ArrayList<Diary> displayEntries;

    public DiaryAdapter(ArrayList<Diary> displayEntries) {

        this.displayEntries = displayEntries;
    }

    public void removeEntry(int index) {

        //TODO: add logic for removing from database as well

        this.displayEntries.remove(index);
    }

    public void setEntries(ArrayList<Diary> displayEntries) {

        this.displayEntries = displayEntries;
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DiaryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false);

        DiaryHolder holder = new DiaryHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryHolder holder, int position) {

        Diary diary = displayEntries.get(position);

        holder.setDiary(diary);
    }

    @Override
    public int getItemCount() {
        return displayEntries.size();
    }

}
