package com.example.healthtracker;

import android.app.Activity;
import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    //TODO: import android test implimentation rules
    @Rule
    public ActivityTestRule<FingerWorkout> activityTestRule = new ActivityTestRule<>(FingerWorkout.class);

    public void testClickCounter(){
        for(int x = 0; x < 10; x++){
            onView(withId(R.id.display_Finger_Count));
        }
    }

}
