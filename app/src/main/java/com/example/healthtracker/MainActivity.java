package com.example.healthtracker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

public class MainActivity extends AppCompatActivity {
    /* from https://github.com/sayyam/carouselview as part of the provided implementation for the
     carousel view library */
    CarouselView carouselView;

    //when adding/subtracting images, be sure to alter them here as well
    int[] fitnessImages = {R.drawable.alfred_and_joa_1284545_unsplash, R.drawable.noel_nichols_443895_unsplash, R.drawable.purnomo_capunk_1201351_unsplash, R.drawable.rishikesh_yogpeeth_1505701_unsplash};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(fitnessImages.length);

        carouselView.setImageListener(imageListener);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(fitnessImages[position]);
        }
    };

    //end splicing in from https://github.com/sayyam/carouselview

    int countingClicks = 0;

    public void onButtonClick(View view){


        TextView text = findViewById(R.id.finger_text);

        countingClicks++;

        String changeText = countingClicks + " Clicks";

        text.setText(changeText);

    }


}

