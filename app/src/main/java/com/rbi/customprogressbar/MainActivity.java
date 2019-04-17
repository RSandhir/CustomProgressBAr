package com.rbi.customprogressbar;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
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
    String channelId = "notif_channel_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Bundle extras=getIntent().getExtras();
        totalPmoney=extras.getInt("Total");
        Toast.makeText(this,""+totalPmoney,Toast.LENGTH_LONG).show();

        progressBar = findViewById(R.id.progress_bar);

        createNotificationChannel();




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
            createNotification(current_spnd);

            boolean isInserted = mydb.insertdata(e1.getText().toString(), spnd_amt);
            if (isInserted = true)
                Toast.makeText(MainActivity.this, "Inserted" + progress_percent, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(MainActivity.this, "Not Inserted", Toast.LENGTH_LONG).show();
        }
        if (v.getId() == R.id.b2) {
            Intent intent = new Intent(this, SpendingsListView.class);
            startActivity(intent);
        }
    }


    private void createNotificationChannel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


        CharSequence channelName = "Some Channel";
        int importance = NotificationManager.IMPORTANCE_LOW;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.enableVibration(true);
            notificationChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void createNotification(int current_spnd) {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notifyId = 1;
        this.current_spnd = current_spnd;

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(MainActivity.this, channelId)
                    .setContentTitle("Spendings")
                    .setContentText("You've received new messages!")
                    .setSmallIcon(R.drawable.ic_notification)
                    .setProgress(totalPmoney, current_spnd, false)
                    .setChannelId(channelId)
                    .setOngoing(true)
                    .build();
        }

        notificationManager.notify(notifyId, notification);
    }

}

