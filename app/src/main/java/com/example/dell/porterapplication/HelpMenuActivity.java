package com.example.dell.porterapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HelpMenuActivity extends AppCompatActivity {
    private Button GoHome;
    ExpandableListView expandableListView;
    List<String> helpQuestion;
    Map<String,List<String>> helpAnswer;
    ExpandableListAdapter listAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpmenu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView = findViewById(R.id.expandableListView);
        fillData();
        listAdapter = new HelpMenuExpandableListAdapter(this,helpQuestion,helpAnswer);
        expandableListView.setAdapter(listAdapter);


    }

    public void fillData(){
        String helpQtn1 = "How to raise a complaint regarding an issue about a porter service?";
        String helpQtn2 = "What is my luggage capacity limit for a service?";
        String helpQtn3 = "Can I book my porter by calling the customer care service?";
        String helpQtn4 = "I have a lot of luggage so can I book more than one porter for the service?";
        String helpQtn5 = "How can I inform customer support during emergency?";
        String helpQtn6 = "Will I get a fee waiver if the porter did not turn up at the expected time of arrival?";

        helpQuestion = new ArrayList<>();
        helpAnswer = new HashMap<>();

        helpQuestion.add(helpQtn1);
        helpQuestion.add(helpQtn2);
        helpQuestion.add(helpQtn3);
        helpQuestion.add(helpQtn4);
        helpQuestion.add(helpQtn5);
        helpQuestion.add(helpQtn6);

        List<String> Qtn1 = new ArrayList<>();
        List<String> Qtn2 = new ArrayList<>();
        List<String> Qtn3 = new ArrayList<>();
        List<String> Qtn4 = new ArrayList<>();
        List<String> Qtn5 = new ArrayList<>();
        List<String> Qtn6 = new ArrayList<>();

        Qtn1.add("\n"+"You can report any issue such as payment or porter related issue during your trip " +
                ""+"by calling our customer care service number and we will try to resolve it instantly." +
                "\n" + "\n" +
                "Service Desk Contact : 9632716880/9900214729"+"\n");
        Qtn2.add("\n" + "The average luggage capacity for a manual(without cart) service would be 65kgs" +
                " "+"and charges would be scaled/reduced for subsequent per kg increase/decrease in capacity."
        +"\n");
        Qtn3.add("\n" + "You can book a porter from your Porter App only.We do not accept" +
                " " +"any booking requests by call."
                +"\n");
        Qtn4.add("\n" + "Our App is built to book a single service during a single app session." +
                ""+"If you're carrying heavy luggage that will be taken care by the porter itself by " +
                ""+"using a luggage cart.You will be charged accordingly for the luggage capacity."
                +"\n");
        Qtn5.add("The App has an inbuilt red alert feature in the form of a button which appears" +
                " "+"during ongoing service and clicking the button would raise an" +
                " "+"alert to the service centre in case your porter runs away with your luggage" +
                " "+"and immediate action will be taken contacting the security at the railway" +
                " "+"station.");
        Qtn6.add("Yes a fee waiver is considered in case of critical situations "+
                ""+"when the porter does not arrive at the expected time or delays his service.");


        helpAnswer.put(helpQuestion.get(0),Qtn1);
        helpAnswer.put(helpQuestion.get(1),Qtn2);
        helpAnswer.put(helpQuestion.get(2),Qtn3);
        helpAnswer.put(helpQuestion.get(3),Qtn4);
        helpAnswer.put(helpQuestion.get(4),Qtn5);
        helpAnswer.put(helpQuestion.get(5),Qtn6);
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        Toast.makeText(this, "Back ", Toast.LENGTH_SHORT).show();
        finish();
        return true;
    }
}
