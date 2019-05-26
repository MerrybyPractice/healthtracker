package com.example.healthtracker;

import com.example.healthtracker.DiaryAdapter;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import static android.net.wifi.WifiConfiguration.Status.strings;

public class ExerciseDiary extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView diaryRecycler;
    private RecyclerView.Adapter dAdapter;
    private RecyclerView.LayoutManager diaryLayoutManager;
    List<Diary> entries;
    ArrayList<Diary> displayEntries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        //allowing items to be added to the database, needs to be first thing in file.
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "diary db")
                .allowMainThreadQueries()
                .build();

        if (db.diaryDao().getAll() == null) {
            Diary fill = new Diary("fill", "fill", "fill", "fill");
            db.diaryDao().add(fill);
        }

        //putting the database in a format the recycler view can manage
        entries = (db.diaryDao().getAll());
        displayEntries = new ArrayList<>();

        for (Diary entry : entries) {
            displayEntries.add(entry);
        }

        //pure recycler view logic
        diaryRecycler = (RecyclerView) findViewById(R.id.view_Diary);
        //not in demo
        diaryRecycler.setHasFixedSize(true);

        diaryLayoutManager = new LinearLayoutManager(this);
        diaryRecycler.setLayoutManager(diaryLayoutManager);

        dAdapter = new DiaryAdapter(displayEntries); //need to wire in database here and in adapter class
        diaryRecycler.setAdapter(dAdapter);
    }

    public void updateEntries(){
        entries = (db.diaryDao().getAll());
        displayEntries = new ArrayList<>();

        for (Diary entry : entries) {
            displayEntries.add(entry);
        }

        dAdapter = new DiaryAdapter(displayEntries);
        diaryRecycler.swapAdapter(dAdapter, true);
    }

    public void onDiaryButtonClick(View view) {

        TextView titleView = findViewById(R.id.form_Title);
        TextView timestampView = findViewById(R.id.form_Timestamp);
        TextView quantView = findViewById(R.id.form_Quant);
        TextView descriptionView = findViewById(R.id.form_Description);

        //this seems to be the issue...
        String title = "" + titleView.getText();
        String quantity = "" + quantView.getText();
        String description = "" + descriptionView.getText();
        String timestamp = "" + timestampView.getText();

        Diary d = new Diary(title, quantity, description, timestamp);

        db.diaryDao().add(d);
        Log.i("Diary added:", title);

        updateEntries();

        titleView.setText(R.string.form_hint_title);
        timestampView.setText(R.string.form_hint_timestamp);
        quantView.setText(R.string.form_hint_quantity);
        descriptionView.setText(R.string.form_hint_description);
    }

}
