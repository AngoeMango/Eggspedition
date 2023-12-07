package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAnEvent;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Participant;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

import java.util.ArrayList;

public class SearchParticipants extends AppCompatActivity implements CanReceiveAUser {

    ArrayList<String> participants;
    TextView joinedEventsText;
    String textView = "";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_participants);

        Intent intent = getIntent();

        participants = (ArrayList<String>) intent.getSerializableExtra("participants");
        joinedEventsText = findViewById(R.id.searchParticipantsText);

        joinedEventsText.setText("No Participants Found!");

        if (participants != null && participants.size() > 0) {
            for (String participant : participants) {
                DatabaseHandler databaseHandler = new DatabaseHandler();
                databaseHandler.loadUserDataUsingUsername(this, participant, "participant");
            }
            joinedEventsText.setText("");
        }

    }

    public void goBack2(View view) {
        finish();
    }

    @Override
    public void onUserDataRetrieved (User user) {

        Participant participant = (Participant) user;
        LinearLayout linearLayout = findViewById(R.id.fieldsLinearLayoutSearchParticipants);

        TextView textView = new TextView(this);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        textView.setLayoutParams(layoutParams);

        textView.setGravity(Gravity.CENTER);

        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);

        textView.setTypeface(textView.getTypeface(), Typeface.BOLD);

        textView.setText("Participant" + "'s username: " + participant.getUsername() + "\nBio: " + participant.getBio() + "\n \n");

        linearLayout.addView(textView);

    }

    @Override
    public void onUserDatabaseFailure () {

    }
}