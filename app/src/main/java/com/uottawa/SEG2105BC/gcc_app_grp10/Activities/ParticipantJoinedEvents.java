package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;

import java.util.ArrayList;

public class ParticipantJoinedEvents extends AppCompatActivity {
    ArrayList<Event> joinedEvents;
    TextView joinedEventsText;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_joined_events);

        Intent intent = getIntent();

        joinedEvents = (ArrayList<Event>) intent.getSerializableExtra("joinedEvents");

        joinedEventsText = findViewById(R.id.joinedEventsText);

        String textView = "";

        for (Event event : joinedEvents) {
            String eventName = event.getName();
            String clubName = event.getClubName();
            String eventTypeName = event.getEventTypeName();
            textView += "Event Name: " + eventName + " \n Creating Club Name: " + clubName + " \n Event Type Name: " + eventTypeName + "\n \n";
        }

        joinedEventsText.setText(textView);

    }
}