package com.example.p0071firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private TextView ipTextView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ipTextView = findViewById(R.id.ipTextView);
        progressBar = findViewById(R.id.progressBar);
    }

    private class IPDeterminationTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.ipify.org?format=json");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.connect();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(conn.getInputStream()));
                String ip = "Your IP is " +
                        (String) new JSONObject(reader.readLine()).get("ip");
                reader.close();
                return ip;
            } catch (Exception e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPreExecute() {
            ipTextView.setText("");
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String ip) {
            progressBar.setVisibility(View.INVISIBLE);
            ipTextView.setText(ip);
        }
    }

    public void onDetermineIPAddressClick(View v) {
        new IPDeterminationTask().execute();
    }
}