package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.CanReceiveAnEventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.EventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Admin;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.CanReceiveAUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AdminWelcome extends AppCompatActivity implements CanReceiveAnEventType, CanReceiveAUser {

    EditText addEventTypeName;
    Admin admin;

    EventType newEventType;
    EditText deleteUserName;

    RadioButton deleteParticipant;
    RadioButton deleteClub;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        addEventTypeName = findViewById(R.id.addEventType);

        deleteUserName = findViewById(R.id.deleteUser);

        deleteParticipant = findViewById(R.id.deleteParticipant);

        deleteClub = findViewById(R.id.deleteClub);

        admin = new Admin("admin", "admin1", "admin@admin.com", "admin");
    }

    public void onAddEventTypeButton(View view) {

        if (addEventTypeName.getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter an event type name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            admin.loadEventType(this, addEventTypeName.getText().toString(), "addEventType");

        }
    }

    public void onDeleteEventTypeButton(View view) {
        if (addEventTypeName.getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter an event type name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            admin.loadEventType(this, addEventTypeName.getText().toString(), "deleteEventType");
        }
    }

    public void onEditEventTypeButton(View view) {
        if (addEventTypeName.getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter an event type name!", Snackbar.LENGTH_LONG).show();
        }
        else {
            admin.loadEventType(this, addEventTypeName.getText().toString(), "editEventType");
        }
    }

    private String selectRole(){
        if (deleteParticipant.isChecked()) {
            return "participant";
        } else if (deleteClub.isChecked()) {
            return "club";
        }
        return null;
    }

    public void onDeleteUserButton(View view) {
        if (deleteUserName.getText().toString().equals("")) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a username to delete!", Snackbar.LENGTH_LONG).show();
        }
        else if(selectRole() == null) {
            Snackbar.make(findViewById(android.R.id.content), "You must enter a role for the user!", Snackbar.LENGTH_LONG).show();
        }
        else {
            admin.deleteAccount(this, deleteUserName.getText().toString(), selectRole());
        }
    }

    @Override
    public void onEventTypeRetrieved (String retreivingFunctionName, EventType eventType) {

        if (retreivingFunctionName.equals("addEventType")) {
            Snackbar.make(findViewById(android.R.id.content), "Event Type already exists!", Snackbar.LENGTH_LONG).show();
        } else if (retreivingFunctionName.equals("editEventType")){
            Intent intent = new Intent(getApplicationContext(), EditEventTypeProperties.class);
            intent.putExtra("eventTypeName", eventType.getName());
            intent.putExtra("eventTypeProperties", eventType.getProperties());
            startActivity(intent);
        }
        else if ((retreivingFunctionName.equals("deleteEventType"))){
            admin.deleteEventType(addEventTypeName.getText().toString());
            Snackbar.make(findViewById(android.R.id.content), "Deleted event type!", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void onEventTypeDatabaseFailure (String retreivingFunctionName) {
        if (retreivingFunctionName.equals("addEventType")) {
            Intent intent = new Intent(getApplicationContext(), AddEventTypeProperties.class);
            intent.putExtra("eventTypeName", addEventTypeName.getText().toString());
            startActivity(intent);
        } else if (retreivingFunctionName.equals("editEventType")){
            Snackbar.make(findViewById(android.R.id.content), "No event type exists with that name!", Snackbar.LENGTH_LONG).show();
        } else if (retreivingFunctionName.equals("deleteEventType")){
            Snackbar.make(findViewById(android.R.id.content), "No event type exists with that name!", Snackbar.LENGTH_LONG).show();
        }
        else if (retreivingFunctionName.equals("invalidName")){
            Snackbar.make(findViewById(android.R.id.content), "Invalid event type name (or other database failure)!", Snackbar.LENGTH_LONG).show();
        }
    }

    @Override
    public void  onUserDataRetrieved(User user) {

    }

    @Override
    public void onUserDatabaseFailure() {

    }

    @Override
    public void onUserDeleteAccountSuccess() {
        Snackbar.make(findViewById(android.R.id.content), "Account successfully deleted!", Snackbar.LENGTH_LONG).show();
    };

    @Override
    public void onUserDeleteAccountFailed() {
        Snackbar.make(findViewById(android.R.id.content),"Failure to delete account (either it doesn't exist with specified role or database had an error)", Snackbar.LENGTH_LONG).show();
    };

}