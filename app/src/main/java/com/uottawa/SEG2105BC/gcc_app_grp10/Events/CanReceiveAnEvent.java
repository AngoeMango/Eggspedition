package com.uottawa.SEG2105BC.gcc_app_grp10.Events;

import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

public interface CanReceiveAnEvent {
    void onEventRetrieved(Event event);
    void onDatabaseFailure();
}
