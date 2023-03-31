package com.example.p0081firstproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class ToDoEditorActivity extends AppCompatActivity {
    private EditText titleText, descriptionText;
    private DatePicker dueDatePicker;
    private Intent resultIntent = new Intent();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy−MM−dd");

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.todo_editor);
        titleText = (EditText) findViewById(R.id.titleText);
        descriptionText = (EditText) findViewById(R.id.descriptionText);
        dueDatePicker = (DatePicker) findViewById(R.id.dueDatePicker);
        if (getIntent().hasExtra("id")) {
            resultIntent.putExtra("id", getIntent().getIntExtra("id", 0));
            titleText.setText(getIntent().getStringExtra("title"));
            descriptionText.setText(getIntent().getStringExtra("description"));
            GregorianCalendar calendar = stringToDate(
                    getIntent().getStringExtra("dueDate"));
            dueDatePicker.init(calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH), null);
        }
    }

    private static String dateToString(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return dateFormat.format(calendar.getTime());
    }

    private static GregorianCalendar stringToDate(String dateString) {
        try {
            Date date = dateFormat.parse(dateString);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }

    public void onOkButtonClick(View v) {
        resultIntent.putExtra("title", titleText.getText().toString());
        resultIntent.putExtra("description", descriptionText.getText().toString());
        resultIntent.putExtra("dueDate", dateToString(dueDatePicker.getYear(),
                dueDatePicker.getMonth(), dueDatePicker.getDayOfMonth()));
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void onCancelButtonClick(View v) {
        setResult(RESULT_CANCELED);
        finish();
    }
}