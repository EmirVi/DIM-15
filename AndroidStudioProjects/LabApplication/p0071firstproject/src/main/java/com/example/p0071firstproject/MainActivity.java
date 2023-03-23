package com.example.p0071firstproject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private Handler handler;
    private TextView ipTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipTextView = findViewById(R.id.ipTextView);
        progressBar = findViewById(R.id.progressBar);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                handleIPDeterminationMessage(msg);
            }
        };
    }

    public void onDetermineIPAddressClick(View v) {
        ipTextView.setText("");
        progressBar.setVisibility(View.VISIBLE);
        Thread determinationThread = new Thread() {
            @Override
            public void run() {
                determineIPAddress();
            }
        };
        determinationThread.start();
    }

    private void determineIPAddress() {
        try {
            URL url = new URL("https://api.ipify.org?format=json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.connect();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String ip = "Your IP is " +
                    (String) new JSONObject(reader.readLine()).get("ip");
            reader.close();
            handler.sendMessage(handler.obtainMessage(0, 0, 0, ip));
        } catch (Exception e) {
            handler.sendMessage(handler.obtainMessage(0, 0, 0, e.getMessage()));
        }
    }

    private void handleIPDeterminationMessage(Message msg) {
        progressBar.setVisibility(View.INVISIBLE);
        ipTextView.setText(msg.obj.toString());
    }
}