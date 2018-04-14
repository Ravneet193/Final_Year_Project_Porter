package com.example.dell.porterapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by SG0302320 on 4/12/2018.
 */

public class PnrEntryActivity extends AppCompatActivity {

    private Button startTrip;
    SQLiteDatabase db;
    DatabaseHelper dbHelper;
    Cursor cursor;
    TextView UserDropDetails;
    private Button proceed;


    @Override
    public void  onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pnrentry);

        startTrip = findViewById(R.id.startTrip);
        final EditText pnr =  findViewById(R.id.pnrEntry);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();
        proceed = findViewById(R.id.proceed);
        proceed.setVisibility(View.INVISIBLE);

        startTrip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 String PnrEntry = pnr.getText().toString().trim();
                // Toast.makeText(PnrEntryActivity.this, " PNR Details", Toast.LENGTH_SHORT).show();


                if(PnrEntry.isEmpty())
                {
                    Toast.makeText(PnrEntryActivity.this, "Please Enter PNR Details", Toast.LENGTH_SHORT).show();
                }
                else{
                    validatePnr(PnrEntry);
                }

            }
        });


    }

    private void validatePnr(String pnrEntry) {

        if(pnrEntry.length() == 11){
            //Toast.makeText(PnrEntryActivity.this, "Valid PNR fetching details", Toast.LENGTH_SHORT).show();
            getCoachDetails(pnrEntry);
        }
        else{
            Toast.makeText(PnrEntryActivity.this, "Please Enter a valid PNR ", Toast.LENGTH_SHORT).show();
        }
    }


    private void getCoachDetails(final String pnrEntry) {

        Intent intent = getIntent();
        final Integer User_Id = intent.getIntExtra("User_Id",-1);
        //String msg = "Userid is " + User_Id;
        //Toast.makeText(this, pnrEntry, Toast.LENGTH_SHORT).show();
        //Toast.makeText(this, DatabaseHelper.Pnr, Toast.LENGTH_SHORT).show();


        cursor = db.rawQuery("SELECT *FROM "+DatabaseHelper.Booking_Train_Table+" WHERE "+DatabaseHelper.Pnr+"=?",new String[] {pnrEntry});

        if (cursor != null) {
           // Toast.makeText(this, "In If1", Toast.LENGTH_SHORT).show();


            if (cursor.getCount() > 0) {


               // Toast.makeText(this, "In If", Toast.LENGTH_SHORT).show();

                cursor.moveToFirst();


                Integer Coach_No = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.Coach_No));
                String Seat_No = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Seat_No));
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Long Booking_Date = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Booking_Date));
                Long Arrival_Date = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Arrival_Date));
                Long Destination_Date = cursor.getLong(cursor.getColumnIndex(DatabaseHelper.Destination_Date));
                String ArrivalDate =  dateFormat.format(new Date(Arrival_Date));
                String DestinationDate =  dateFormat.format(new Date(Destination_Date));
                String BookingDate = dateFormat.format(new Date(Booking_Date));
                if (Coach_No != null && Seat_No!= null) {
                    UserDropDetails = findViewById(R.id.UserDropDetails);

                    UserDropDetails.setText("Your train details"+ "\n" + "\n"+
                            "Coach Number:" + " " + Coach_No + "\n" + "Seat Number:" + " " + Seat_No + "\n" +
                            "Booking date:" + " " + BookingDate + "\n" + "Arrival Date:" + " " + ArrivalDate + "\n"
                            +"Destination Date:" + " " + DestinationDate);
                    proceed.setVisibility(View.VISIBLE);
                    proceed.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(PnrEntryActivity.this,MapActivity.class);
                            intent.putExtra("User_Id", User_Id);
                            intent.putExtra("Pnr",pnrEntry);
                            startActivity(intent);
                        }
                    });


                    //UserDropDetails.startAnimation(AnimationUtils.loadAnimation(PnrEntryActivity.this,android.R.anim.fade_in));

                }


            }

            else {

                Toast.makeText(this, "The given pnr cannot be fetched", Toast.LENGTH_SHORT).show();

            }
        }
        else{
             Toast.makeText(PnrEntryActivity.this, "Pnr could not be fetched", Toast.LENGTH_SHORT).show();

        }

         Toast.makeText(PnrEntryActivity.this, " Success", Toast.LENGTH_SHORT).show();

    }
}
