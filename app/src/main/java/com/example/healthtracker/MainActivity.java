package com.example.healthtracker;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ViewListener;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    /* from https://github.com/sayyam/carouselview as part of the provided implementation for the
     carousel view library */


    CarouselView carouselView;
    ImageView image;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carouselView = findViewById(R.id.carousel_view);
        carouselView.setPageCount(fitnessImages.length);
        image = findViewById(R.id.carousel_image);
        text = findViewById(R.id.carousel_caption);

        carouselView.setViewListener(viewListener);
    }

    //when adding/subtracting images and captions, be sure to alter them here as well
    int[] fitnessImages = new int[]{R.drawable.alfred_and_joa_1284545_unsplash, R.drawable.noel_nichols_443895_unsplash, R.drawable.purnomo_capunk_1201351_unsplash, R.drawable.rishikesh_yogpeeth_1505701_unsplash};

    int[] captionStrings = new int[]{R.string.alfred_and_joa, R.string.noel_nichols, R.string.purnomo_capunk, R.string.rishikesh_yogpeeth};

    ViewListener viewListener = new ViewListener() {
        @Override
        public View setViewForPosition(int position) {
            View customView = getLayoutInflater().inflate(R.layout.carousel_custom_captions, null);
            //target image view in layout and set it
            image.setImageResource(fitnessImages[position]);
            //need to target text view in layout and set it
            text.setText(captionStrings[position]);

            return customView;
        }

    };


    //end splicing in from https://github.com/sayyam/carouselview


    // here are all the intents that govern the "nav bar" on the home page

    public void onFingerButtonClick(View view) {
        Intent intent = new Intent(this, FingerWorkout.class);
        startActivity(intent);
    }

    public void onDiaryButtonClick(View view){
        Intent intent = new Intent(this, ExerciseDiary.class);
        startActivity(intent);
    }

}




