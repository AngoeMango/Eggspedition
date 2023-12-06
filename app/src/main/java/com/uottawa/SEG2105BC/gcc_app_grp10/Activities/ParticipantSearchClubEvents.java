package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.Toast;

import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.EventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;

import java.util.ArrayList;

public class ParticipantSearchClubEvents extends AppCompatActivity {

    String participantEmail;
    String participantPassword;
    String participantUsername;

    ArrayList<Event> events;
    int radioGroupID;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_search_club_events);

        Intent intent = getIntent();
        participantEmail = intent.getStringExtra("participantEmail");
        participantPassword = intent.getStringExtra("participantPassword");
        participantUsername = intent.getStringExtra("participantUsername");
        events = (ArrayList<Event>) intent.getSerializableExtra("events");


        LinearLayout linearLayout = findViewById(R.id.fieldsLinearLayoutEventsSearch);

        RadioGroup radioGroup = new RadioGroup(this);
        radioGroupID = View.generateViewId();
        radioGroup.setId(radioGroupID);
        radioGroup.setLayoutParams(new RadioGroup.LayoutParams(
                RadioGroup.LayoutParams.MATCH_PARENT,
                RadioGroup.LayoutParams.WRAP_CONTENT));
        radioGroup.setOrientation(RadioGroup.VERTICAL);

        for (Event event : events) {
            String eventTypeName = event.getName();

            RadioButton eventTypeButton = new RadioButton(this);
            eventTypeButton.setId(View.generateViewId());
            eventTypeButton.setText(eventTypeName);
            radioGroup.addView(eventTypeButton);
        }

        linearLayout.addView(radioGroup);

        Space space = new Space(this);
        space.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                24));
        linearLayout.addView(space);

    }

    public void onSelectEventSearchButton(View view) {
        RadioGroup radioGroup = findViewById(radioGroupID);
        int selectedID = radioGroup.getCheckedRadioButtonId();

        if (selectedID == -1) {
            Toast.makeText(getApplicationContext(), "You must select an event!", Toast.LENGTH_LONG).show();
            return;
        }

        RadioButton radioButton = radioGroup.findViewById(selectedID);
        String eventName = radioButton.getText().toString();

        Intent intent = new Intent(this, ParticipantSearchEventsSecondPage.class);
        intent.putExtra("participantEmail", participantEmail);
        intent.putExtra("participantPassword", participantPassword);
        intent.putExtra("participantUsername", participantUsername);
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                intent.putExtra("event", event);
            }
        }
        startActivity(intent);

        finish();
    }

    public void goBack(View view){
        finish();
    }
}