package com.uottawa.SEG2105BC.gcc_app_grp10.Database.Interfaces;

import com.uottawa.SEG2105BC.gcc_app_grp10.Events.Event;
import com.uottawa.SEG2105BC.gcc_app_grp10.Users.User;

public interface CanReceiveAnEvent {
    void onEventRetrieved(Event event);
    void onEventDatabaseFailure();
}
