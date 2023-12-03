package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAnEvent;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAnEventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveEvents;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.EventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

import java.util.ArrayList;

public class ParticipantWelcome extends AppCompatActivity implements CanReceiveAUser, CanReceiveEvents, CanReceiveAnEvent, CanReceiveAnEventType {

    EditText searchText;
    String participantEmail;
    String participantPassword;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_welcome);

        Intent intent = getIntent();
        participantEmail = intent.getStringExtra("participantEmail");
        participantPassword = intent.getStringExtra("participantPassword");

        searchText = findViewById(R.id.EventEditTextParticipant);


    }

    @Override
    public void onUserDataRetrieved(User user){

    }

    @Override
    public void onUserDatabaseFailure(){
        Toast.makeText(getApplicationContext(), "User retrieval failed!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void onSearchClubButton(View view) {

        if (searchText.getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.loadEventsFromClubName(this, searchText.getText().toString());
        }
    }

    public void onSearchEventButton(View view) {

        if (searchText.getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.loadEvent(this, searchText.getText().toString(),"searchEvent");        }
    }

    public void onSearchEventTypeButton(View view) {
        if (searchText.getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.loadEventsFromEventTypeName(this, searchText.getText().toString());

        }
    }

    public void onEventRetrieved(String retrievingFunctionName, Event event) {
        System.out.println(event.getName());
    }

    public void onEventDatabaseFailure(String retrievingFunctionName) {

    }

    public void onEventTypeRetrieved(String retrievingFunctionName, EventType eventType) {

    }

    public void onEventTypeDatabaseFailure(String retrievingFunctionName) {

    }

    @Override
    public void onEventsRetrieved (ArrayList<Event> events) {
        System.out.println(events.get(0).getName());
        Intent intent = new Intent(getApplicationContext(), ParticipantSearchClubEvents.class);
        intent.putExtra("participantEmail", participantEmail);
        intent.putExtra("participantPassword", participantPassword);
        intent.putExtra("events", events);
        startActivity(intent);
    }

    @Override
    public void onEventsDatabaseFailure () {
        Toast.makeText(getApplicationContext(), "Event retrieval failed from club name given!", Toast.LENGTH_LONG).show();
    }
}