package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.uottawa.SEG2105BC.gcc_app_grp10.Database.DatabaseHandler;
import com.uottawa.SEG2105BC.gcc_app_grp10.R;

public class AdminControlPanel extends AppCompatActivity {

    DatabaseHandler databaseHandler;
    private Button buttonViewEventTypes;

    private Button buttonViewEvents;

    private Button buttonViewAccounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control);

    }

    public void onCreateEventTypeButton(View view) {

    }
}