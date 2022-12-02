package com.example.p0042firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView carImageView = (ImageView) findViewById(R.id.car);
        Animation carMoveAnimation = AnimationUtils.loadAnimation(this, R.anim.car_move);

        carImageView.startAnimation(carMoveAnimation);
    }
}