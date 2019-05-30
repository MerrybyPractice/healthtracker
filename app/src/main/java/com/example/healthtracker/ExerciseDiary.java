package com.example.healthtracker;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

public class ExerciseDiary extends AppCompatActivity {

    private AppDatabase db;
    private RecyclerView diaryRecycler;
    private RecyclerView.Adapter dAdapter;
    private RecyclerView.LayoutManager diaryLayoutManager;
    List<Diary> entries;
    ArrayList<Diary> displayEntries;
    RequestQueue queue;

    String url = "https://healthtracker-backend.herokuapp.com";
    TextView titleView;
    TextView timestampView;
    TextView quantView;
    TextView descriptionView;
    FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_diary);
        titleView = findViewById(R.id.form_Title);
        timestampView = findViewById(R.id.form_Timestamp);
        quantView = findViewById(R.id.form_Quant);
        descriptionView = findViewById(R.id.form_Description);
        client = LocationServices.getFusedLocationProviderClient(this);
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


    public void updateEntries() {
        entries = (db.diaryDao().getAll());
        displayEntries = new ArrayList<>();

        for (Diary entry : entries) {
            displayEntries.add(entry);
        }

        dAdapter = new DiaryAdapter(displayEntries);
        diaryRecycler.swapAdapter(dAdapter, true);
    }

    public void onDiaryButtonClick(View view) {
        System.out.println("Just inside OnClick");
        entryPost(view);

        String title = "" + titleView.getText();
        String quantity = "" + quantView.getText();
        String description = "" + descriptionView.getText();
        String timestamp = "" + timestampView.getText();

        checkLocationPermissions();
        Diary d = new Diary(title, quantity, description, timestamp);

        System.out.println("Before adding to DB");
        db.diaryDao().add(d);
        Log.i("Diary added:", title);
        System.out.println("After adding to DB");
        updateEntries();

        titleView.setText(R.string.form_hint_title);
        timestampView.setText(R.string.form_hint_timestamp);
        quantView.setText(R.string.form_hint_quantity);
        descriptionView.setText(R.string.form_hint_description);
    }


    public void entryPost(View view) {

        StringRequest request = new StringRequest(
                Request.Method.POST,
                url + "/entries",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("Response", response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error Response", error.toString());

                        //text.setText(error.toString());
                    }

                }) {
            //found at https://stackoverflow.com/questions/33573803/how-to-send-a-post-request-using-volley-with-string-body
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("title", titleView.getText().toString());
                params.put("quantity", quantView.getText().toString());
                params.put("description", descriptionView.getText().toString());
                params.put("timestamp", timestampView.getText().toString());

                return params;
            }
        };
        System.out.println("BEFORE ENQUEING THE REQUEST" + queue.toString());
        System.out.println(request.toString());
        queue.add(request);
    }

    public void entryGet(View view) {

        StringRequest request = new StringRequest(
                Request.Method.GET,
                url + "/all",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //TODO: make this work(currently trying to set a diary entry as a string not good)


                        //displayEntries.add(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("ERROR", error.toString());
                    }
                });
        queue.add(request);
    }

    public void checkLocationPermissions(){
        if(checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            if(shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_COARSE_LOCATION)){
                locationRequestDialog();
            } else {
                requestLocationPermission();
            }

            return;
        }
        //TODO: What?
        client.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location == null){
                            //TODO: handling for null locations: fall back to a default?

                            Log.e("ExerciseDiary", "Location Was Null");
                        } else {
                            //TODO: actually do something with returned data(likely utilizing location.getLatitude(), location.getLongitude();
                        }
                    }
                });
    }

    public static final int MY_LOCATION_REQUEST = 7;

    public void requestLocationPermission() {
        requestPermissions(
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_LOCATION_REQUEST
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {

        switch (requestCode) {
            case MY_LOCATION_REQUEST: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("ExerciseDiary", "Location Access Granted");

                    //TODO: Once I have confirmation this is working:
                    // append location to the diary entry
                } else {
                    Log.i("ExerciseDiary", "Location Access Denied");
                    //TODO: place defaults? ensure user wont keep being asked?

                }
                return;
            }
        }

    }

    public void locationRequestDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("If you would like us to help track where you work out");
        builder.setMessage("We need access to your location data. This data will only be used here, displayed with your exercise data to help you keep track of what you do where.");

        builder.setPositiveButton("Yes, I would like to share my location.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                requestLocationPermission();
            }
        });

        builder.setNegativeButton("I would rather not.", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //TODO: add handling for if they do not want to provide location
            }
        });

        AlertDialog dialog = builder.create();

        dialog.show();
    }


}
