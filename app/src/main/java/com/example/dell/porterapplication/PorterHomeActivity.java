package com.example.dell.porterapplication;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class PorterHomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Button onBoard;
    private Button offBoard;

    Integer User_Id;
    Intent intent;

    DatabaseHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_porterhome);

        onBoard = findViewById(R.id.OnBoard);
        onBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = getIntent();
                User_Id = intent.getIntExtra("User_Id",-1);
                Intent intent = new Intent(PorterHomeActivity.this,PnrEntryActivity.class);
                intent.putExtra("User_Id", User_Id);

                startActivity(intent);
                Toast.makeText(PorterHomeActivity.this, "button clicked", Toast.LENGTH_SHORT).show();

            }
        });


        offBoard = findViewById(R.id.OffBoard);
        offBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PorterHomeActivity.this,PnrEntryActivity.class);
                startActivity(intent);
            }
        });

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        intent = getIntent();
        User_Id = intent.getIntExtra("User_Id",-1);

        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(PorterHomeActivity.this,mDrawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);//listen to toggle button
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);//backbutton method to get back from navigation
        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        //View hview = navigationView.getHeaderView(0);
        //TextView nav_user = (TextView)hview.findViewById(R.id.navigation_view);
        //nav_user.setText("Profile");
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mToggle.onOptionsItemSelected(item)){

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.logoutMenu){
            finish();
            Intent intent = new Intent(PorterHomeActivity.this,MainActivity.class);
            startActivity(intent);
        }
        if(id == R.id.profileMenu){
            Intent intent = new Intent(PorterHomeActivity.this,UserProfileActivity.class);
            intent.putExtra("User_Id", User_Id);
            startActivityForResult(intent, 1);
        }
        if(id == R.id.PorterRatingsMenu){
            Intent intent = new Intent(PorterHomeActivity.this,PorterRatingActivity.class);
            startActivity(intent);
        }
        if(id == R.id.TripsMenu){
            Intent intent = new Intent(PorterHomeActivity.this,FeedbackActivity.class);
            startActivity(intent);
        }
        if(id == R.id.HelpMenu){
            Intent intent = new Intent(PorterHomeActivity.this,HelpMenuActivity.class);
            startActivity(intent);
        }

        return false;
    }


}



















