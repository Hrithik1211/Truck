package com.social.truck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Tracking extends AppCompatActivity {















    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        String mobile = getIntent().getStringExtra("mobile").toString();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Driver").child(mobile);


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                lat=snapshot.child("Lat").getValue(String.class);
                lon=snapshot.child("Lon").getValue(String.class);
                TextView latitude = (TextView) findViewById(R.id.lat);
                TextView longitude = (TextView) findViewById(R.id.lon);

                latitude.setText(lat);
                longitude.setText(lon);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    String lat,lon;
}