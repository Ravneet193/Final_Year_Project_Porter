package com.example.dell.porterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
//static page containing the top rated porters throughout the Porter Service
//best porters need to be fetched from db and updated here.

public class PorterRatingActivity extends AppCompatActivity {

    private Button GoHome;
    @Override
    public void  onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porterrating);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Back ", Toast.LENGTH_SHORT).show();
        finish();
        return true;
    }

}
