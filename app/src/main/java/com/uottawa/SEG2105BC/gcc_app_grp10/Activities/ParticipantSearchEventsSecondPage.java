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

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.SpecifiedProperty;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ParticipantSearchEventsSecondPage extends AppCompatActivity {

    Event event;
    String participantEmail;
    String participantPassword;
    String participantUsername;


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

        String eventName = event.getName();
        String clubName = event.getClubName();
        String eventTypeName = event.getEventTypeName();
        ArrayList<SpecifiedProperty> eventSpecifiedProperties = event.getSpecifiedProperties();

        TextView eventNameEditText = findViewById(R.id.selectEventSearchSecondPage);
        eventNameEditText.setText("Event Name: " + eventName + " \n Creating Club Name: " + clubName + " \n Event Type Name: " + eventTypeName);


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
        }
    }

    public void goBack(View view){
        finish();
    }

    public void onSelectEventSearchButtonSecondPage(View view) {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.addEventToAssociatedUser(event.getName(), participantUsername, "participant");
    }
}