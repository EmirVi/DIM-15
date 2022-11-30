package com.example.p0021firstproject;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView serviceCalcTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        serviceCalcTextView = findViewById(R.id.totalSumTextView);
    }

    public void onClick(View view){
        serviceCalcTextView.setText("9750 рублей");
    }
}