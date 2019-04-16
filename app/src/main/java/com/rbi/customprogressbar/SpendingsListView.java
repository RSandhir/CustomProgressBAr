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
    private ArrayList<String> Id = new ArrayList<String>();
    private ArrayList<String> Description = new ArrayList<String>();
    private ArrayList<String> Amount = new ArrayList<String>();
    ListView lv;

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
        db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM  Spendings", null);
        Id.clear();
        Description.clear();
        Amount.clear();
        if (cursor.moveToFirst()) {
            do {
                Id.add(cursor.getString(cursor.getColumnIndex("Id")));
                Description.add(cursor.getString(cursor.getColumnIndex("Description")));
                Amount.add(cursor.getString(cursor.getColumnIndex("Amount")));
            } while (cursor.moveToNext());
        }
        CustomAdapter ca = new CustomAdapter(SpendingsListView.this, Id, Description, Amount);
        lv.setAdapter(ca);
        cursor.close();
    }
}
