package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.CanReceiveAnEventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.Events.EventType;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminWelcome extends AppCompatActivity implements CanReceiveAnEventType {

    EditText addEventTypeName;
    Admin admin;

    EventType newEventType;
    EditText deleteUserName;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        addEventTypeName = findViewById(R.id.addEventType);

        deleteUserName = findViewById(R.id.deleteUser);

        admin = new Admin("admin", "admin1", "admin@admin.com", "admin");
    }

    public void onAddEventTypeButton(View view) {
        String name = "tt";
        ArrayList<String> propertyList = new ArrayList<String>();

        Intent intent = new Intent(getApplicationContext(), AddEventTypeProperties.class);
        intent.putExtra("eventTypeName", addEventTypeName.getText().toString());
        startActivity (intent);
    }

    public void onDeleteEventTypeButton(View view) {
        try {
            admin.deleteEventType(addEventTypeName.getText().toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onEditEventTypeButton(View view) {
        try {
            admin.loadEventType(this, addEventTypeName.getText().toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void onDeleteUserButton(View view) {
        System.out.println(deleteUserName.getText().toString());
        try {
            admin.deleteEventType(deleteUserName.getText().toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onEventTypeRetrieved (EventType eventType) {
        Intent intent = new Intent(getApplicationContext(), EditEventTypeProperties.class);
        intent.putExtra("eventTypeName", eventType.getName());
        intent.putExtra("eventTypeProperties", eventType.getProperties());
        startActivity (intent);
    }

    @Override
    public void onDatabaseFailure () {
        Snackbar.make(findViewById(android.R.id.content), "No event type exists with given role!", Snackbar.LENGTH_LONG).show();
    }
}