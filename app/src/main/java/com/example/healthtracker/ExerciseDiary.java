package com.example.healthtracker;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

public class ExerciseDiary extends AppCompatActivity {

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "diary db")
                .allowMainThreadQueries()
                .build();

        Diary fill =  new Diary("fill","fill", "fill", "fill");
        db.diaryDao().add(fill);


    }

    public void onDiaryButtonClick(View view){

        TextView titleView = findViewById(R.id.form_Title);
        TextView timestampView = findViewById(R.id.form_Timestamp);
        TextView quantView = findViewById(R.id.form_Quant);
        TextView descriptionView = findViewById(R.id.form_Description);

        String title = (String) titleView.getText();
        String quantity = (String) quantView.getText();
        String description = (String) descriptionView.getText();
        String timestamp = (String) timestampView.getText();

        Diary d = new Diary(title, quantity, description, timestamp);

        db.diaryDao().add(d);
        Log.i("Diary added:", title);
    }

}
