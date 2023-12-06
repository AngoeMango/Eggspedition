package com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces;

import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

import java.util.ArrayList;

public interface CanReceiveUsers {
    void onUserDataRetrieved(ArrayList<String> participants);
    void onUserDatabaseFailure();
}
