package com.example.p0021firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class MainActivity extends AppCompatActivity {
    TextView serviceCalcTextView;
    EditText addressBox;
    EditText periodBox;
    EditText livingSpaceBox;

    private final static String FILE_NAME = "content.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        serviceCalcTextView = (TextView) findViewById(R.id.totalSumTextView);
        addressBox = (EditText) findViewById(R.id.addressEditText);
        periodBox = (EditText) findViewById(R.id.periodEditText);
        livingSpaceBox = (EditText) findViewById(R.id.livingSpaceEditText);
    }

    public void Calculate(View view) {
        serviceCalcTextView.setText("9750 рублей");
    }

    // сохранение файла
    public void saveText(View view) {
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            String addressValue = addressBox.getText().toString();
            String periodValue = periodBox.getText().toString();
            String livingSpaceValue = livingSpaceBox.getText().toString();
            CalcData calcData = new CalcData(addressValue, periodValue, livingSpaceValue);

            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(calcData);

            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (oos != null) oos.close();
                if (fos != null) fos.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    // открытие файла
    public void openText(View view) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = openFileInput(FILE_NAME);
            ois = new ObjectInputStream(fis);
            CalcData calcData = (CalcData) ois.readObject();

            addressBox.setText(calcData.getAddress());
            periodBox.setText(calcData.getPeriod());
            livingSpaceBox.setText(calcData.getLivingSpace());

        } catch (Exception ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (ois != null) ois.close();
                if (fis != null) fis.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}

class CalcData implements Serializable {
    private String address;
    private String period;
    private String livingSpace;

    CalcData(String address, String period, String livingSpace) {
        this.address = address;
        this.period = period;
        this.livingSpace = livingSpace;
    }

    String getAddress() {
        return address;
    }

    String getPeriod() {
        return period;
    }

    String getLivingSpace() {
        return livingSpace;
    }
}