package com.example.p0061firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClickListener {
    final String LOG_TAG = "myLogs";
    Button btnAdd, btnRead, btnClear, btnUpd, btnDel;
    EditText etID, etColdWater, etHotWater, etElectricity;
    DBHelper dbHelper;

    GridView gvMain;
    ArrayList<String> data = new ArrayList<String>();
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvMain = (GridView) findViewById(R.id.gvMain);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);
        btnRead = (Button) findViewById(R.id.btnRead);
        btnRead.setOnClickListener(this);
        btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnUpd = (Button) findViewById(R.id.btnUpd);
        btnUpd.setOnClickListener(this);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnDel.setOnClickListener(this);
        etID = (EditText) findViewById(R.id.etID);
        etColdWater = (EditText) findViewById(R.id.etColdWater);
        etHotWater = (EditText) findViewById(R.id.etHotWater);
        etElectricity = (EditText) findViewById(R.id.etElectricity);
        // создаем объект для создания и управления версиями БД
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {
        // создаем объект для данных
        ContentValues cv = new ContentValues();
        // получаем данные из полей ввода
        String id = etID.getText().toString();
        String coldWater = etColdWater.getText().toString();
        String hotWater = etHotWater.getText().toString();
        String electricity = etElectricity.getText().toString();
        // подключаемся к БД
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (view.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "---Insert in mytable: ---");
                // подготовим данные для вставки в виде пар: наименование столбца - значение
                cv.put("coldWater", coldWater);
                cv.put("hotWater", hotWater);
                cv.put("electricity", electricity);
                // вставляем запись и получаем ее ID
                long rowID = db.insert("mytable", null, cv);
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                break;
            case R.id.btnRead:
                Log.d(LOG_TAG, "---Rows in mytable: ---");
                data.clear();
                // делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor c = db.query("mytable", null, null, null, null, null, null);
                // ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, вернется false
                if (c.moveToFirst()) {
                    // определяем номера столбцов по имени в выборке
                    int idColIndex = c.getColumnIndex("id");
                    int coldWaterColIndex = c.getColumnIndex("coldWater");
                    int hotWaterColIndex = c.getColumnIndex("hotWater");
                    int electricityColIndex = c.getColumnIndex("electricity");
                    do {
                        // получаем значения по номерам столбцов и пишем все в лог
                        String rowData = "ID квартиры = " + c.getInt(idColIndex) +
                                "\nХолодная вода = " + c.getString(coldWaterColIndex) + " м3" +
                                "\nГорячая вода = " + c.getString(hotWaterColIndex) + " м3" +
                                "\nЭлектроэнергия = " + c.getString(electricityColIndex) + " кВт ч";
                        Log.d(LOG_TAG, rowData);
                        data.add(rowData);
                        // переход на следующую строку
                        // а если следующей нет (текущая -последняя), то false -выходим из цикла
                    } while (c.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                c.close();
                adapter = new ArrayAdapter<String>(this, R.layout.item, R.id.tvText, data);
                gvMain.setAdapter(adapter);
                break;
            case R.id.btnClear:
                Log.d(LOG_TAG, "---Clear mytable: ---");
                // удаляем все записи
                int clearCount = db.delete("mytable", null, null);
                // нужно чтобы сбросить ID
                db.execSQL("delete from sqlite_sequence where name='mytable'");
                Log.d(LOG_TAG, "deleted rows count = " + clearCount);
                break;
            case R.id.btnUpd:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(LOG_TAG, "---Update mytable: ---");
                // подготовим значения для обновления
                cv.put("coldWater", coldWater);
                cv.put("hotWater", hotWater);
                cv.put("electricity", electricity);
                // обновляем по id
                int updCount = db.update("mytable", cv, "id = ?",
                        new String[]{id});
                Log.d(LOG_TAG, "updated rows count = " +
                        updCount);
                break;
            case R.id.btnDel:
                if (id.equalsIgnoreCase("")) {
                    break;
                }
                Log.d(LOG_TAG, "---Delete from mytable: ---");
                // удаляем по id
                int delCount = db.delete("mytable", "id = " + id, null);
                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                break;
        }
        // закрываем подключение к БД
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "---onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "coldWater text,"
                    + "hotWater text,"
                    + "electricity text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int
                newVersion) {
        }
    }
}