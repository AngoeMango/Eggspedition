package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

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

public class AdminWelcome extends AppCompatActivity {

    EditText addEventTypeName;
    EditText addEventTypePropertyName;
    EditText addEventTypePropertyTypeName;
    Admin admin;

    EventType newEventType;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_welcome);

        addEventTypeName = findViewById(R.id.addEventType);

        addEventTypePropertyName = findViewById(R.id.addEventTypeProperty);

        addEventTypePropertyTypeName = findViewById(R.id.addEventTypePropertyType);


        admin = new Admin("admin", "admin1", "admin@admin.com", "admin");
    }

    public void onAddEventTypeButton(View view) {
        String name = "tt";
        HashMap<String, Type> propertyList = new HashMap<>();

        //propertyList.put("why",String.class);

        newEventType = admin.createEventType (addEventTypeName.getText().toString(), propertyList);

        System.out.println(newEventType.getName());
    }

    public void onAddPropertyType(View view) {
        newEventType.addPropertyToType(addEventTypePropertyName.getText().toString(), addEventTypePropertyTypeName.getText().toString());
    }

    public void onDeleteEventTypeButton(View view) {
        System.out.println(addEventTypeName.getText().toString());
        try {
            admin.deleteEventType(addEventTypeName.getText().toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}