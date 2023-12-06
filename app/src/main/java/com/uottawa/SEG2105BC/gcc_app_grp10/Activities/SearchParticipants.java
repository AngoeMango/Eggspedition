package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;

import java.util.ArrayList;

public class SearchParticipants extends AppCompatActivity {

    ArrayList<String> participants;
    TextView joinedEventsText;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_participants);

        Intent intent = getIntent();

        participants = (ArrayList<String>) intent.getSerializableExtra("participants");
        joinedEventsText = findViewById(R.id.searchParticipantsText);

        joinedEventsText.setText("No Participants Found!");

        if (participants != null && participants.size() > 0) {
            String textView = "";

            int i = 0;

            for (String participant : participants) {
                i++;
                textView += "Participant " + i + "'s username: " + participant + "\n \n";
            }

            joinedEventsText.setText(textView);
        }

    }

    public void goBack1(View view) {
        finish();
    }

}