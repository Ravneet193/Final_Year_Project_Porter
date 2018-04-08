package com.example.dell.porterapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView mRatingScale;
    EditText mFeedback;
    Button mSendFeedback;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        Toast.makeText(this, "Feedback Page", Toast.LENGTH_SHORT).show();

       mRatingBar = (RatingBar) findViewById(R.id.ratingBar);
        mRatingScale = (TextView) findViewById(R.id.tvRatingScale);
        mFeedback = (EditText) findViewById(R.id.etFeedback);
        mSendFeedback = (Button) findViewById(R.id.btnSubmit);

        mRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                mRatingScale.setText(String.valueOf(v));
                switch ((int) ratingBar.getRating()) {
                    case 1:
                        Toast.makeText(FeedbackActivity.this, "Rating 1", Toast.LENGTH_SHORT).show();
                        mRatingScale.setText("Very bad");
                        break;
                    case 2:
                        mRatingScale.setText("Need some improvement");
                        break;
                    case 3:
                        mRatingScale.setText("Good");
                        break;
                    case 4:
                        mRatingScale.setText("Great");
                        break;
                    case 5:
                        mRatingScale.setText("Awesome. I love it");
                        break;
                    default:
                        mRatingScale.setText("");
                }
            }
        });

        mSendFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mFeedback.getText().toString().isEmpty()) {
                    Toast.makeText(FeedbackActivity.this, "Please fill in feedback text box", Toast.LENGTH_LONG).show();
                } else {
                    //mFeedback.setText("");
                    //mRatingBar.setRating(0);
                    //Toast.makeText(FeedbackActivity.this, "Thank you for sharing your feedback", Toast.LENGTH_SHORT).show();
                    populateDb();
                }
            }
        });

    }

    public void populateDb()
    {
        ContentValues values = new ContentValues();
        db.delete(DatabaseHelper.Feedback_Table,DatabaseHelper.Feedback_Id+" = 1",null);
        Toast.makeText(this, mRatingScale.getText()+" lalalalala "+ mFeedback.getText() , Toast.LENGTH_LONG).show();
        values.put(DatabaseHelper.Satisfaction_Level, (String) mRatingScale.getText());
        values.put(DatabaseHelper.Feedback_Description, String.valueOf(mFeedback.getText()));

        long id = db.insert(DatabaseHelper.Feedback_Table,null,values);
        if(id != -1)
        {
            Toast.makeText(this, "Thanks for sharing your feedback", Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(this, "Feedback Failed", Toast.LENGTH_SHORT).show();
        }
    }

}
