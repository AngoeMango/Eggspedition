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
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Club;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Participant;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Rating;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

import java.util.ArrayList;

public class ParticipantWelcome extends AppCompatActivity implements CanReceiveAUser, CanReceiveEvents, CanReceiveAnEvent {

    com.google.android.material.textfield.TextInputLayout searchText;
    String participantEmail;
    String participantPassword;
    String participantUsername;

    com.google.android.material.textfield.TextInputLayout ratingClubName;
    com.google.android.material.textfield.TextInputLayout ratingComment;
    com.google.android.material.textfield.TextInputLayout ratingValue;
    boolean addRating = false;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_welcome);

        Intent intent = getIntent();
        participantEmail = intent.getStringExtra("participantEmail");
        participantPassword = intent.getStringExtra("participantPassword");
        participantUsername = intent.getStringExtra("participantUsername");

        searchText = findViewById(R.id.EventEditTextParticipant);

        ratingClubName = findViewById(R.id.EventEditTextParticipant2);
        ratingComment = findViewById(R.id.EventEditTextParticipant3);
        ratingValue = findViewById(R.id.EventEditTextParticipant4);


    }



    public void onSearchClubButton(View view) {

        if (searchText.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.loadEventsFromClubName(this, searchText.getEditText().getText().toString());
        }
    }

    public void onSearchEventButton(View view) {

        if (searchText.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.loadEvent(this, searchText.getEditText().getText().toString(),"searchEvent");        }
    }

    public void onSearchEventTypeButton(View view) {
        if (searchText.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.loadEventsFromEventTypeName(this, searchText.getEditText().getText().toString());

        }
    }

    public void onEventRetrieved(String retrievingFunctionName, Event event) {
        Intent intent = new Intent(getApplicationContext(), ParticipantSearchEventsSecondPage.class);
        intent.putExtra("participantEmail", participantEmail);
        intent.putExtra("participantPassword", participantPassword);
        intent.putExtra("participantUsername", participantUsername);
        intent.putExtra("event", event);
        startActivity(intent);
    }

    public void onEventDatabaseFailure(String retrievingFunctionName) {
        Toast.makeText(getApplicationContext(), "Event retrieval failed!", Toast.LENGTH_LONG).show();
    }

//    public void onEventTypeRetrieved(String retrievingFunctionName, EventType eventType) {
//        DatabaseHandler databaseHandler=new DatabaseHandler();
//        databaseHandler.loadEventsFromEventTypeName(this, searchText.getEditText().getText().toString());
//
//    }
//
//    public void onEventTypeDatabaseFailure(String retrievingFunctionName) {
//
//    }

    @Override
    public void onEventsRetrieved (ArrayList<Event> events) {
        System.out.println(events.get(0).getName());
        Intent intent = new Intent(getApplicationContext(), ParticipantSearchClubEvents.class);
        intent.putExtra("participantEmail", participantEmail);
        intent.putExtra("participantPassword", participantPassword);
        intent.putExtra("participantUsername", participantUsername);
        intent.putExtra("events", events);
        startActivity(intent);
    }

    @Override
    public void onEventsDatabaseFailure (String failureDescription) {
        switch(failureDescription){
            case "errorLoadingClub":
                Toast.makeText(getApplicationContext(), "Event retrieval failed from club name given!", Toast.LENGTH_LONG).show();
                break;
            case "noEventsForClub":
                Toast.makeText(getApplicationContext(), "No events for given club!", Toast.LENGTH_LONG).show();
                break;
            default:
                Toast.makeText(getApplicationContext(), "Event retrieval failed!", Toast.LENGTH_LONG).show();
        }
    }

    public void onRateClubButton(View view){
        if (ratingClubName.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
        }
        else if (ratingComment.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a comment!", Snackbar.LENGTH_LONG).show();
        }
        else if (ratingValue.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a rating!", Snackbar.LENGTH_LONG).show();
        }
        try {
            Integer.parseInt(ratingValue.getEditText().getText().toString());
        }
        catch (Exception e) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a number for the rating!", Snackbar.LENGTH_LONG).show();
            return;
        }

        if (Integer.parseInt(ratingValue.getEditText().getText().toString())>5 || Integer.parseInt(ratingValue.getEditText().getText().toString())<0) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a rating between 0 and 5!", Snackbar.LENGTH_LONG).show();
        }
        else {
            addRating = true;
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.loadUserDataUsingUsername(this, ratingClubName.getEditText().getText().toString(), "club");
        }
    }

    public void onJoinedEventsButton(View view) {

        DatabaseHandler databaseHandler=new DatabaseHandler();
        databaseHandler.loadUserDataUsingUsername(this, participantUsername, "participant");

    }

    @Override
    public void onUserDataRetrieved(User user){
        if (user.getRole().equals("participant")) {
            Intent intent = new Intent(getApplicationContext(), ParticipantJoinedEvents.class);

            ArrayList<Event> events = ((Participant) user).getEvents();

            intent.putExtra("joinedEvents", events);
        }
        if (user.getRole().equals("club")) {
            Rating rating = new Rating(ratingClubName.getEditText().getText().toString(), ratingComment.getEditText().getText().toString(), Integer.parseInt(ratingValue.getEditText().getText().toString()));
            ((Club) user).addRating(rating);
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.updateUserData(user);
            Toast.makeText(getApplicationContext(), "Rating added!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUserDatabaseFailure(){
        if (addRating) {
            Snackbar.make(findViewById(android.R.id.content), "Could not find club with that name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(getApplicationContext(), "User retrieval failed!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

}