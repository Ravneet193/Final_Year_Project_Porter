package com.example.dell.porterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by SG0302320 on 4/15/2018.
 */

public class MapActivity extends AppCompatActivity {
    @Override
    public void  onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        Integer User_Id = intent.getIntExtra("User_Id",-1);
        String Pnr = intent.getStringExtra("Pnr");
        Toast.makeText(this, "in map activity with userID:" + User_Id + " " + "and PNR:" + " " + Pnr, Toast.LENGTH_SHORT).show();

    }
}
