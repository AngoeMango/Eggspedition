package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAnEvent;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Club;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Rating;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

public class ClubWelcome extends AppCompatActivity implements CanReceiveAnEvent, CanReceiveAUser {

    com.google.android.material.textfield.TextInputLayout addEventName;
    String clubName;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_welcome);

        Intent intent = getIntent();
        String firstName = intent.getStringExtra("firstName");
        String role = intent.getStringExtra("role");
        clubName = intent.getStringExtra("clubName");

        addEventName = findViewById(R.id.EventEditText);

        // Select the text views on the screen
        TextView welcomeMessageTextView = findViewById(R.id.welcomeTextView3);
        TextView welcomeRoleTextView = findViewById(R.id.welcomeRoleTextView3);

        // Set the welcome messages with the username and role
        if (firstName != null) {
            welcomeMessageTextView.setText("Welcome " + firstName + "!");
        }

        if (role != null && !role.equals("unknown")) {
            welcomeRoleTextView.setText("You are logged in as " + role + ".");
        }

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.loadUserDataUsingUsername(this, clubName, "club");

    }

    public void onAddEventButton(View view) {

        if (addEventName.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter an event name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.loadEvent(this, addEventName.getEditText().getText().toString(), "addEvent");        }
    }

    public void onEditEventButton(View view) {

        if (addEventName.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter an event name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.loadEvent(this, addEventName.getEditText().getText().toString(), "editEvent");        }
    }

    public void onDeleteEventButton(View view) {
        if (addEventName.getEditText().getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter an event name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            DatabaseHandler databaseHandler=new DatabaseHandler();
            databaseHandler.loadEvent(this, addEventName.getEditText().getText().toString(), "deleteEvent");        }
    }

    @Override
    public void onEventRetrieved(String retrievingFunctionName, Event event) {
        switch(retrievingFunctionName){
            case "addEvent":
                Snackbar.make(findViewById(android.R.id.content), "Event already exists!", Snackbar.LENGTH_LONG).show();
                break;
            case "editEvent":
                Intent intent = new Intent(getApplicationContext(), EditEventProperties.class);
                intent.putExtra("eventName", event.getName());
                intent.putExtra("eventTypeName", event.getEventTypeName());
                intent.putExtra("clubName", event.getClubName());
                intent.putExtra("callingClubName", clubName);
                startActivity(intent);
                break;
            case "deleteEvent":
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.deleteEvent(event.getName(), event);
                Snackbar.make(findViewById(android.R.id.content), "Deleted event!", Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(findViewById(android.R.id.content), "invalid function name got passed", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onEventDatabaseFailure (String retrievingFunctionName) {
        switch(retrievingFunctionName){
            case "addEvent":
                Intent intent = new Intent(getApplicationContext(), AddEventProperties.class);
                intent.putExtra("eventName", addEventName.getEditText().getText().toString());
                intent.putExtra("clubName", clubName);
                startActivity(intent);
                break;
            case "editEvent":
                Snackbar.make(findViewById(android.R.id.content), "No event exists with that name!", Snackbar.LENGTH_LONG).show();
                break;
            case "deleteEvent":
                Snackbar.make(findViewById(android.R.id.content),  "No event exists with that name!", Snackbar.LENGTH_LONG).show();
                break;
            case "callingClubNotAuthorized":
                Snackbar.make(findViewById(android.R.id.content), "You can only allowed to make changes to events your club created!", Snackbar.LENGTH_LONG).show();
                break;
            default:
                Snackbar.make(findViewById(android.R.id.content), "Invalid event type name (or other database failure)!", Snackbar.LENGTH_LONG).show();
        }

    }

    @Override
    public void onUserDataRetrieved (User club) {
        TextView ratingsText = findViewById(R.id.welcomeTextView11);
        if (((Club) club).getRatings() == null) {
            ratingsText.setText("You have no ratings yet!");
        }
        else {
            String ratingsTextString = "";
            for (Rating rating : ((Club) club).getRatings()) {
                ratingsTextString += " \n Rating: " + rating.getRating() + " \n Comment: " + rating.getRatingDescription() + "\n";

            }

            ratingsText.setText(ratingsTextString);
        }
    }

    @Override
    public void onUserDatabaseFailure () {
    }

}