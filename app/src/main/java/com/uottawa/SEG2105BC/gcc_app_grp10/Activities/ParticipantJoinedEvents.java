package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveEvents;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;

import java.util.ArrayList;

public class ParticipantJoinedEvents extends AppCompatActivity implements CanReceiveEvents {
    ArrayList<String> joinedEvents;
    TextView joinedEventsText;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_joined_events);

        Intent intent = getIntent();

        joinedEvents = (ArrayList<String>) intent.getSerializableExtra("joinedEvents");
        DatabaseHandler db = new DatabaseHandler();
        db.loadMultipleEvents(this, joinedEvents);

    }

    public void goBack1(View view) {
        finish();
    }

    @Override
    public void onEventsRetrieved (ArrayList<Event> events) {
        joinedEventsText = findViewById(R.id.joinedEventsText);

        joinedEventsText.setText("No Events Found!");

        String textView = "";

        for (Event event : events) {
            String eventName = event.getName();
            String clubName = event.getClubName();
            String eventTypeName = event.getEventTypeName();
            textView += "Event Name: " + eventName + " \n Creating Club Name: " + clubName + " \n Event Type Name: " + eventTypeName + "\n \n";
        }

        joinedEventsText.setText(textView);
    }

    @Override
    public void onEventsDatabaseFailure (String failureDescription) {
        Toast.makeText(this, "Loading events failed!", Toast.LENGTH_SHORT).show();
    }
}