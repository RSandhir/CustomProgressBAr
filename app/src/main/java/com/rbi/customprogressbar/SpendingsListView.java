package com.rbi.customprogressbar;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class SpendingsListView extends AppCompatActivity {

    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    SQLiteDatabase db;
    ListView lv;
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Description = new ArrayList<String>();
    private ArrayList<String> Amount = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spendings_list_view);
        lv = findViewById(R.id.lv);
    }

    @Override
    protected void onResume() {
        displayData();
        super.onResume();
    }

    private void displayData() {
        Cursor cursor = databaseHelper.getAllData();
        Id.clear();
        Description.clear();
        Amount.clear();
        while (cursor.moveToNext()) {
            String fetch_id = cursor.getString(cursor.getColumnIndex("Id"));
            Id.add(fetch_id);
                Description.add(cursor.getString(cursor.getColumnIndex("Description")));
                Amount.add(cursor.getString(cursor.getColumnIndex("Amount")));
        }
        CustomAdapter ca = new CustomAdapter(SpendingsListView.this, Id, Description, Amount);
        lv.setAdapter(ca);
        cursor.close();
    }
}
