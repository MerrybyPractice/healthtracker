package com.example.healthtracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class PrefHelper {

    public static SharedPreferences getMyFilePreferences(Activity activity){
        String filename = activity.getString(R.string.app_main_preferences);
        SharedPreferences preferences = activity.getSharedPreferences(filename, Context.MODE_PRIVATE);
        return preferences;
    }


}
