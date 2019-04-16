package com.rbi.customprogressbar;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ProgressBar progressBar;
    DatabaseHelper mydb;

    EditText e1, e2;
    Button b1, b2;
    int current_spnd;
    int totalPmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bundle extras=getIntent().getExtras();
        totalPmoney=extras.getInt("Total");
        Toast.makeText(this,""+totalPmoney,Toast.LENGTH_LONG).show();

        progressBar = findViewById(R.id.progress_bar);




        mydb = new DatabaseHelper(this);
        e1 = findViewById(R.id.e1);
        e2 = findViewById(R.id.e2);
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.b1) {
            int spnd_amt = Integer.parseInt(e2.getText().toString());
            current_spnd += spnd_amt;
            float progress_percent = (((float) current_spnd) / totalPmoney) * 100;
            progressBar.setProgress((int) progress_percent);

            boolean isInserted = mydb.insertdata(e1.getText().toString(), spnd_amt);
            if (isInserted = true)
                Toast.makeText(MainActivity.this, "Inserted" + progress_percent, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_LONG).show();
        }
        if (v.getId() == R.id.b2) {
             /*Cursor result = mydb.getAllData();
                if (result.getCount() == 0) {
                    //show message
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (result.moveToNext()) {
                    buffer.append("Description:" + result.getString(1) + "\n");
                    buffer.append("Spendings:" + result.getString(2) + "\n\n");
                }
                showMessage("Data", buffer.toString());*/
            Intent intent = new Intent(this, SpendingsListView.class);
            startActivity(intent);
        }
    }

    /*public void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }*/

}

