package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class FingerWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_workout);
    }


    int countingClicks = 0;

    public void onButtonClick(View view){


        TextView text = findViewById(R.id.finger_text);

        countingClicks++;

        String changeText = countingClicks + " Clicks";

        text.setText(changeText);
    }
}
