package com.social.truck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class AnimationActivity extends AppCompatActivity {

    @Override






    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        ImageView imageView = (ImageView) findViewById(R.id.imagetick);
        imageView.setAlpha(0f);
        imageView.animate().alpha(1f).setDuration(1000);
    }








    
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,BookingActivity.class);
        startActivity(intent);
    }
}