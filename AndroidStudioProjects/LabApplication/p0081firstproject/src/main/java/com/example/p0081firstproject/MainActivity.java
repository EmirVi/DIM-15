package com.example.p0081firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private SimpleCursorAdapter adapter;
    private static final String contentProviderUri = "content://com.example.p0081firstproject.contentprovider/todos/";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView todoListView = (ListView) findViewById(R.id.todoList);
        todoListView.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onToDoListItemClick(id);
            }
        });
        getLoaderManager().initLoader(0, null, this);
        String[] from = new String[]{"title", "description"};
        int[] to = new int[]{R.id.titleText, R.id.descriptionText};
        adapter = new SimpleCursorAdapter(this, R.layout.todo_item, null, from, to, 0);
        todoListView.setAdapter(adapter);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, Uri.parse(contentProviderUri), null, null, null, null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(this, ToDoEditorActivity.class);
        startActivityForResult(intent, 1);
        return true;
    }

    @SuppressLint("Range")
    public void onToDoListItemClick(long id) {
        Cursor todoCursor = getContentResolver().query(Uri.parse(contentProviderUri + id), null, null, null, null);
        todoCursor.moveToNext();
        Intent intent = new Intent(this, ToDoEditorActivity.class);
        intent.putExtra("id", todoCursor.getInt(todoCursor.getColumnIndex("_id")));
        intent.putExtra("title", todoCursor.getString(todoCursor.getColumnIndex("title")));
        intent.putExtra("description", todoCursor.getString(todoCursor.getColumnIndex("description")));
        intent.putExtra("dueDate", todoCursor.getString(todoCursor.getColumnIndex("dueDate")));
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        ContentValues cv = new ContentValues();
        cv.put("title", data.getStringExtra("title"));
        cv.put("description", data.getStringExtra("description"));
        cv.put("dueDate", data.getStringExtra("dueDate"));
        if (data.hasExtra("id")) {
            getContentResolver().update(Uri.parse(contentProviderUri + data.getIntExtra("id", 0)), cv, null, null);
        } else {
            getContentResolver().insert(Uri.parse(contentProviderUri), cv);
        }
    }
}