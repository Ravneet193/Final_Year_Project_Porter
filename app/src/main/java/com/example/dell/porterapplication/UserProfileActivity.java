package com.example.dell.porterapplication;

import android.app.ActionBar;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.WindowDecorActionBar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;


public class UserProfileActivity extends AppCompatActivity {

    Intent intent;
    Integer User_Id;
    Cursor cursor, cursor1;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;

    ImageView SaveButton;

    TextView ChangeImage;

    Toolbar mToolbar;

    EditText UserName, UserEmail, UserPhoneNo;
    @Override
    public void  onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        intent = getIntent();
        User_Id = intent.getIntExtra("User_Id",-1);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        UserName = (EditText)findViewById(R.id.etPrUserName);
        UserEmail = (EditText)findViewById(R.id.etPrEmailAddress);
        UserPhoneNo = (EditText)findViewById(R.id.etPrPhoneNumber);
        ChangeImage = (TextView)findViewById(R.id.tvChangeImage);
        SaveButton = (ImageView)findViewById(R.id.saveUser);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });

        SetData();

    }

    public void SetData()
    {

        Toast.makeText(this, "User_Id: "+User_Id, Toast.LENGTH_SHORT).show();
        cursor = db.rawQuery("SELECT *FROM "+DatabaseHelper.User_Table+" WHERE "+DatabaseHelper.User_Id+"="+User_Id,null);

        if (cursor != null) {
            if (cursor.getCount() > 0) {

                cursor.moveToFirst();
                String User_Email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.User_Email));
                String First_Name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.User_First_Name));
                String Last_Name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.User_Last_Name));
                String Phone_No = cursor.getString(cursor.getColumnIndex(DatabaseHelper.User_Phone_No));

                if (First_Name != null && !First_Name.isEmpty() && !First_Name.equals("null")) {
                    UserName.setText(First_Name);
                }
                if (User_Email != null && !User_Email.isEmpty() && !User_Email.equals("null")) {
                    UserEmail.setText(User_Email);
                }
                if (Phone_No != null && !Phone_No.isEmpty() && !Phone_No.equals("null")) {
                    UserPhoneNo.setText(Phone_No);
                }
            }

             else {

                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();

            }
        }
        else
        {
            Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }
    }

    public void saveData()
    {
        String First_Name = UserName.getText().toString().trim();
        String User_Email  = UserEmail.getText().toString().trim();
        String User_Phone_No = UserPhoneNo.getText().toString().trim();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.User_First_Name,First_Name);
        values.put(DatabaseHelper.User_Email,User_Email);
        values.put(DatabaseHelper.User_Phone_No,User_Phone_No);
        long id = db.update(DatabaseHelper.User_Table,values,DatabaseHelper.User_Id+" = "+User_Id,null);
        if(id !=-1)
        {
            Toast.makeText(this, "Saved Data", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "Update Failed", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Back ", Toast.LENGTH_SHORT).show();
        finish();
        return true;
    }

}
