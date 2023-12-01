//package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import com.google.android.material.snackbar.Snackbar;
//import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
//import com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces.CanReceiveAUser;
//import com.uottawa.SEG2105BC.gcc_app_grp10.R;
//import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;
//
//public class ParticipantWelcome extends AppCompatActivity implements CanReceiveAUser, CanReceiveAnEvent, CanReceiveAnEventType {
//
//    EditText searchText;
//    String participantEmail;
//    String participantPassword;
//
//    @Override
//    protected void onCreate (Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_participant_welcome);
//
//        Intent intent = getIntent();
//        participantEmail = intent.getStringExtra("participantEmail");
//        participantPassword = intent.getStringExtra("participantPassword");
//
//        searchText = findViewById(R.id.EventEditTextParticipant);
//
//
//    }
//
//    @Override
//    public void onUserDataRetrieved(User user){
//
//    }
//
//    @Override
//    public void onUserDatabaseFailure(){
//        Toast.makeText(getApplicationContext(), "User retrieval failed!", Toast.LENGTH_LONG).show();
//        finish();
//    }
//
//    public void onSearchClubButton(View view) {
//
//        if (searchText.getText().toString().equals("")) {
//            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
//        }
//        else {
//            DatabaseHandler databaseHandler = new DatabaseHandler();
////            databaseHandler.loadClub(this, searchText.getText().toString(),  "searchClub");
//        }
//    }
//
//    public void onSearchEventButton(View view) {
//
//        if (searchText.getText().toString().equals("")) {
//            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
//        }
//        else {
//            DatabaseHandler databaseHandler=new DatabaseHandler();
//            databaseHandler.loadEvent(this, searchText.getText().toString(), "searchEvent");        }
//    }
//
//    public void onSearchEventTypeButton(View view) {
//        if (searchText.getText().toString().equals("")) {
//            Snackbar.make(findViewById(android.R.id.content), "You must enter a name!", Snackbar.LENGTH_LONG).show();
//        }
//        else {
//            DatabaseHandler databaseHandler=new DatabaseHandler();
//            databaseHandler.loadEventType(this, searchText.getText().toString(),  "deleteEvent");        }
//    }
//}