package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import android.widget.Toast;

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAnEvent;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.SpecifiedProperty;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Club;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Participant;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ParticipantSearchEventsSecondPage extends AppCompatActivity implements CanReceiveAUser, CanReceiveAnEvent {

    Event event;
    String participantEmail;
    String participantPassword;
    String participantUsername;
    ArrayList<Event> events;
    String eventName;
    String eventTypeName;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_search_events_second_page);

        Intent intent = getIntent();

        event = (Event) intent.getSerializableExtra("event");

        participantEmail = intent.getStringExtra("participantEmail");
        participantPassword = intent.getStringExtra("participantPassword");
        participantUsername = intent.getStringExtra("participantUsername");
        events = (ArrayList<Event>) intent.getSerializableExtra("events");

        eventName = event.getName();
        String clubName = event.getClubName();
        eventTypeName = event.getEventTypeName();
        ArrayList<SpecifiedProperty> eventSpecifiedProperties = event.getSpecifiedProperties();

        LinearLayout linearLayout = findViewById(R.id.fieldsLinearLayoutEventsSearchSecondPage);

        for (SpecifiedProperty specifiedProperty : eventSpecifiedProperties) {
            String propertyName = specifiedProperty.getPropertyType().getName();
            String propertyType = specifiedProperty.getPropertyType().getType();

            LinearLayout fieldLinearLayout = new LinearLayout(this);
            int fieldGroupId = View.generateViewId();
            fieldLinearLayout.setId(fieldGroupId);
            fieldLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            fieldLinearLayout.setOrientation(LinearLayout.VERTICAL);

            TextView newProperty = new TextView(this);
            newProperty.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));

            newProperty.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
            newProperty.setTypeface(null, Typeface.BOLD);

            int newPropertyID = View.generateViewId();
            newProperty.setId(newPropertyID);
            newProperty.setText("Field Name: " + propertyName + " \nField Value: " + specifiedProperty.getValue().toString());
            fieldLinearLayout.addView(newProperty);
//
//            TextView newSpecifiedProperty = new EditText(this);
//            newProperty.setLayoutParams(new LinearLayout.LayoutParams(
//                    LinearLayout.LayoutParams.MATCH_PARENT,
//                    LinearLayout.LayoutParams.WRAP_CONTENT));
//
//            int newSpecifiedPropertyID = View.generateViewId();
//            fieldValueIDs.add(newSpecifiedPropertyID);
//            newSpecifiedProperty.setId(newSpecifiedPropertyID);
//            newSpecifiedProperty.setText(specifiedProperty.getValue().toString());
//            fieldLinearLayout.addView(newSpecifiedProperty);

            Space space = new Space(this);
            space.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    24));
            fieldLinearLayout.addView(space);

            linearLayout.addView(fieldLinearLayout);

            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.loadUserDataUsingUsername(this, clubName, "club");


        }
    }

    public void goBack(View view){
        finish();
    }

    public void onSelectEventSearchButtonSecondPage(View view) {
                System.out.println("Adding event to associated user" + event.getName() + participantUsername+ "participant");

                DatabaseHandler db = new DatabaseHandler();
                db.loadUserDataUsingUsername((CanReceiveAUser) this, participantUsername, "participant");

    }


    @Override
    public void onUserDataRetrieved (User user) {

        if (user.getRole().equals("participant")) {
            ArrayList<String> eventNames = ((Participant) user).getEventNames();

            if (eventNames != null) {
                for (String eventName : eventNames) {
                    if (eventName.equals(event.getName())) {
                        Toast.makeText(getApplicationContext(), "Event already added to your joined events!", Toast.LENGTH_SHORT).show();
                        finish();
                        return;
                    }
                }
            }
            DatabaseHandler databaseHandler = new DatabaseHandler();
            databaseHandler.addEventToAssociatedUser(event.getName(), participantUsername, "participant");
            databaseHandler.loadEvent(this, event.getName(), "addParticipant");

            Toast.makeText(getApplicationContext(), "Event added to your events!", Toast.LENGTH_SHORT).show();
            finish();
        }
        else if (user.getRole().equals("club")) {

            Club club = (Club) user;

            TextView eventNameEditText = findViewById(R.id.selectEventSearchSecondPage);
            eventNameEditText.setText("Event Name: " + eventName + " \n Creating Club Name: " + club.getUsername() + "\nClub Phone Number: " + club.getPhoneNumber() + "\nClub Social Link: " + club.getSocialMediaLink()
                    + "\nClub Bio: " + club.getBio() + "\nClub Main Contact: " + club.getMainContactName() + "\n Event Type Name: " + eventTypeName);

        }
        else {
            Toast.makeText(getApplicationContext(), "User retrieval failed!", Toast.LENGTH_SHORT).show();
            finish();
        }


    }

    @Override
    public void onUserDatabaseFailure () {
        Toast.makeText(getApplicationContext(), "Event retrieval failed!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onEventRetrieved (String retrievingFunctionName, Event event) {
        event.addParticipant(participantUsername);

        DatabaseHandler databaseHandler = new DatabaseHandler();
        databaseHandler.addEvent(event.getName(), event, "addParticipant");
    }

    @Override
    public void onEventDatabaseFailure(String retrievingFunctionName) {
        Toast.makeText(getApplicationContext(), "Event addition failed!", Toast.LENGTH_SHORT).show();
    }

}