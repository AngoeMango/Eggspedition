package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;

import java.util.ArrayList;

public class ParticipantSearchClubEvents extends AppCompatActivity {

    String participantEmail;
    String participantPassword;
    ArrayList<Event> events;


    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_search_club_events);

        Intent intent = getIntent();
        participantEmail = intent.getStringExtra("participantEmail");
        participantPassword = intent.getStringExtra("participantPassword");
        events = (ArrayList<Event>) intent.getSerializableExtra("events");

    }
}