package com.uottawa.SEG2105BC.gcc_app_grp10.Activities;

import androidx.appcompat.app.AppCompatActivity;

import com.uottawa.SEG2105BC.gcc_app_grp10.CanReceiveAUser;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

public class AdminPage extends AppCompatActivity implements CanReceiveAUser {
    @Override
    public void onUserDataRetrieved(User user) {

    }

    @Override
    public void onDatabaseFailure() {

    }
}
