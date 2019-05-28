package com.example.healthtracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FingerWorkout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_workout);
    }


    public final static String CLICK_KEY = "clicks";
    int countingClicks = 0;

    public void onButtonClick(View view) {


        TextView text = findViewById(R.id.finger_text);

        countingClicks++;

        String changeText = countingClicks + " Clicks";

        text.setText(changeText);

        //save to shared preferences

        SharedPreferences preferences = PrefHelper.getMyFilePreferences(this);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(CLICK_KEY, changeText);

        editor.apply();
    }
}
