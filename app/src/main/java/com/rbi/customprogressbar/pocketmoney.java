package com.rbi.customprogressbar;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class pocketmoney extends AppCompatActivity {

    Button sav_btn;
    EditText p_moneyet;
    int totalPmoney;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocketmoney);



        p_moneyet=findViewById(R.id.p_money);
        sav_btn=findViewById(R.id.save_btn);

        sav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                totalPmoney= Integer.parseInt(p_moneyet.getText().toString());
                Intent i=new Intent(getApplicationContext(),MainActivity.class);
                i.putExtra("Total",totalPmoney);
                startActivity(i);
            }
        });
    }
}
